package universe.graphic

import arc.graphics.Color
import arc.graphics.Pixmap
import arc.graphics.Texture
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.graphics.gl.Shader
import universe.graphic.MathShaderBuilder.Companion.argX
import universe.graphic.MathShaderBuilder.Companion.argY
import universe.graphic.expressions.const
import universe.graphic.expressions.minus
import universe.graphic.expressions.plus
import universe.graphic.expressions.sin
import universe.graphic.expressions.times


class MathShader(
  vert: String,
  frag: String,
  uniforms: List<MathShaderBuilder.Uniform>,
): Shader(vert, frag) {
  val uniforms = uniforms.associateBy { it.name }

  init {
    uniforms.forEach { it.shader = this }
  }

  companion object {
    private val canvas = let {
      val pix = Pixmap(2, 2)
      pix.fill(Color.white)
      TextureRegion(Texture(pix))
    }

    val sinShader = MathShaderBuilder().apply {
      val scl = makeUniform("scl", "float")
      val off = makeUniform("off", "float")
      setFunction(argY - sin(argX*scl + off))
    }.build()

    val circleShader = MathShaderBuilder().apply {
      val radius = makeUniform("radius", "float")
      setFunction(argX*argX + argY*argY - radius)
    }.build()

    val ovalShader = MathShaderBuilder().apply {
      val a = makeUniform("a", "float")
      val b = makeUniform("b", "float")
      setFunction(argX*argX*a + argY*argY*b - const(1f))
    }.build()

    val curveCircleShader = MathShaderBuilder().apply {
      val radius = makeUniform("radius", "float")
      val roundScale = makeUniform("roundScale", "float")
      val curvature = makeUniform("curvature", "float")

      val arctan = addNamedFunc(
        "arctan",
        roundScale*atan()
      )
      setFunction()
    }
  }

  var dispersion = 1f
  var lowerBound = 0f
  var upperBound = 1f
  var sclX = 0f
  var sclY = 0f

  fun getUniform(name: String): MathShaderBuilder.Uniform {
    return uniforms[name]?: throw NoSuchElementException("This shader has no uniform $name")
  }

  fun draw(
    dispersion: Float = 1f,
    lowerBound: Float = 0f,
    upperBound: Float = 1f,
    sclX: Float = 0f,
    sclY: Float = 0f,
    drawBody: MathShader.(TextureRegion) -> Unit,
  ){
    this.dispersion = dispersion
    this.lowerBound = lowerBound
    this.upperBound = upperBound
    this.sclX = sclX
    this.sclY = sclY

    Draw.shader(this)
    this.drawBody(canvas)
    Draw.shader()
  }

  override fun apply() {
    setUniformf("u_dispersion", dispersion)
    setUniformf("u_lowerBound", lowerBound)
    setUniformf("u_upperBound", upperBound)
    setUniformf("u_sclX", sclX)
    setUniformf("u_sclY", sclY)
  }
}