package universe.graphic

import arc.graphics.gl.Shader
import universe.graphic.expressions.Expression
import universe.graphic.expressions.Variable
import universe.graphic.expressions.vari
import universe.graphic.expressions.vec2

abstract class MathShaderBuilder() {
  companion object {

  }

  private val uniforms = mutableListOf<Uniform>()
  private val variables = mutableListOf<Pair<String, Expression>>()
  private lateinit var function: Expression
  private lateinit var gradient: Expression

  fun makeUniform(name: String): Uniform = Uniform(name).also { uniforms.add(it) }
  fun addVariable(name: String, function: Expression) = also { variables.add(name to function) }

  fun setFunction(function: Expression) = also { it.function = function }
  fun setGradient(function: Expression) = also { it.gradient = function }

  fun build(): Shader {
    val function = function
    val gradient =
      if (this::gradient.isInitialized) gradient
      else vec2(function.diff(vari("x")), function.diff(vari("y")))

    TODO()
  }

  class Uniform(
    name: String
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