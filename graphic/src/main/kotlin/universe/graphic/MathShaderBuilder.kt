package universe.graphic

import arc.graphics.gl.Shader
import universe.graphic.expressions.*

class MathShaderBuilder() {
  companion object {
    val argX = vari("x")
    val argY = vari("y")
  }

  private val uniforms = mutableListOf<Uniform>()
  private val namedFunctions = mutableListOf<NamedFunction>()
  private lateinit var function: Expression
  private lateinit var gradient: Expression

  private var factor: Expression? = null

  fun makeUniform(name: String, type: String): Uniform = Uniform(name, type).also { uniforms.add(it) }
  fun addNamedFunc(
    name: String,
    function: Expression,
    type: String,
    vararg variables: Variable
  ) = NamedFunction(
    name,
    function,
    type,
    *variables
  ).also { namedFunctions.add(it) }
  fun addNamedFunc(
    name: String,
    function: Expression,
    vararg variables: Variable
  ) = NamedFunction(
    name,
    function,
    *variables
  ).also { namedFunctions.add(it) }

  fun setFunction(function: Expression) = also { it.function = function }
  fun setGradient(function: Expression) = also { it.gradient = function }
  fun setFactor(function: Expression) = also { it.factor = function }

  fun build(): MathShader {
    val varBuilder = StringBuilder()
    namedFunctions.forEach {
      varBuilder.append("${it.type} ${it.name} = ${it.expression};\n")
      it.getDiffFunctions().forEach { diff ->
        varBuilder.append("${diff.type} ${diff.name} = ${diff.expression};\n")
      }
      varBuilder.append("\n")
    }

    val function = function
    val gradient =
      if (this::gradient.isInitialized) gradient
      else vec2(function.diff(vari("x")), function.diff(vari("y")))

    val vertexShader =
      """
      attribute vec4 a_position;
      attribute vec4 a_color;
      attribute vec2 a_texCoord0;
      attribute vec4 a_mix_color;
      uniform mat4 u_projTrans;
      varying vec4 v_color;
      varying vec4 v_mix_color;
      varying vec2 v_texCoords;
      
      void main(){
         v_color = a_color;
         v_color.a = v_color.a * (255.0/254.0);
         v_mix_color = a_mix_color;
         v_mix_color.a *= (255.0/254.0);
         v_texCoords = a_texCoord0;
         gl_Position = u_projTrans * a_position;
      }
      """.trimIndent()

    val fragmentShader =
      """
      uniform float u_dispersion;
      uniform float u_lowerBound;
      uniform float u_upperBound;
      uniform float u_sclX;
      uniform float u_sclY;
      uniform sampler2D u_texture;
      
      ${
        uniforms.joinToString("\n") {
          "uniform ${it.type} ${it.name};"
        }
      }
      
      varying vec4 v_color;
      varying vec4 v_mix_color;
      varying vec2 v_texCoords;
      
      void main(){
        vec4 c = texture2D(u_texture, v_texCoords);
        vec4 mixed = vec4(mix(v_color, v_mix_color, v_mix_color.a).rgb, v_color.a)*c;
        
        float x = ((v_texCoords.x - 0.5)*2.0)*u_sclX;
        float y = ((v_texCoords.y - 0.5)*2.0)*u_sclY;
        
        $varBuilder
        
        float res_func = $function;
        vec2 grad_func = $gradient;
        float grad = length(grad_func);
        
        float alpha = u_dispersion*grad/(abs(res_func) + u_dispersion*grad);
        alpha = clamp((alpha - u_lowerBound)/(u_upperBound - u_lowerBound), 0.0, 1.0)*mixed.a;
        
        ${if (factor == null) "" else "alpha = $factor*alpha;"}
        
        gl_FragColor = vec4(mixed.r, mixed.g, mixed.b, alpha);
      }
      """.trimIndent()

    return MathShader(vertexShader, fragmentShader, uniforms)
  }

  open class NamedFunction(
    name: String,
    val expression: Expression,
    val type: String,
    vararg val args: Variable,
  ): Variable(name) {
    constructor(
      name: String,
      expression: Expression,
      vararg args: Variable,
    ): this(name, expression, predictType(expression), *args)

    companion object {
      private fun predictType(expression: Expression): String = when(expression){
        is UnaryMinus -> predictType(expression.exp)
        is Plus -> predictType(expression.expLeft)
        is Minus -> predictType(expression.expLeft)
        is Times -> {
          val l = predictType(expression.expLeft)
          val r = predictType(expression.expRight)

          if (l == r) l
          else if (l.startsWith("vec") && r == "float") l
          else if (r.startsWith("vec") && l == "float") r
          else throw IllegalArgumentException("Cannot determine type of expression: $expression")
        }
        is Division -> {
          val l = predictType(expression.expLeft)
          val r = predictType(expression.expRight)

          if (l == r) l
          else if (l.startsWith("vec") && r == "float") l
          else throw IllegalArgumentException("Cannot determine type of expression: $expression")
        }
        is Vec2 -> "vec2"
        is Vec3 -> "vec3"
        is Vec4 -> "vec4"
        else -> "float"
      }
    }

    fun getDiffFunctions(): List<NamedFunction> = args.map {
      val f = expression.diff(it).simplify()

      NamedFunction(name + "_diff_" + it.name, f, type, *args)
    }

    override fun diff(variable: Variable): Expression {
      return if (args.any{ it.name == variable.name }) Variable(name + "_diff_" + variable.name)
      else const(0.0)
    }

    override fun toString(): String = name
  }

  class Uniform(
    name: String,
    val type: String,
  ): Variable(name) {
    lateinit var shader: Shader

    fun set(int: Int){
      shader.bind()
      shader.setUniformi(name, int)
    }

    fun set(float: Float){
      shader.bind()
      shader.setUniformf(name, float)
    }
  }
}