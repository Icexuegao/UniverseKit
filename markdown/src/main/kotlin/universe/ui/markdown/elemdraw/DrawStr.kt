package universe.ui.markdown.elemdraw

import arc.graphics.Color
import arc.graphics.g2d.*
import arc.graphics.gl.Shader
import arc.math.Affine2
import arc.math.Mat
import arc.util.Align
import arc.util.pooling.Pools
import mindustry.ui.Fonts
import universe.ui.markdown.Markdown
import universe.ui.markdown.RendererContext

open class DrawStr internal constructor() : Markdown.MarkdownDraw() {
  companion object {
    val distanceFieldShader: Shader = createDistanceFieldShader()

    fun get(
      str: String,
      font: Font,
      fontOffsetX: Float,
      fontOffsetY: Float,
      italic: Boolean,
      color: Color,
      scl: Float,
    ): DrawStr = Pools.obtain(DrawStr::class.java) { DrawStr() }.apply {
      this.text = str
      this.font = font
      this.fontOffX = fontOffsetX
      this.fontOffY = fontOffsetY
      this.italic = italic
      this.scl = scl
      this.color = color
    }

    private fun createDistanceFieldShader(): Shader {
      val vertexShader =
        """attribute vec4 ${Shader.positionAttribute};
        attribute vec4 ${Shader.colorAttribute};
        attribute vec2 ${Shader.texcoordAttribute}0;
        uniform mat4 u_projTrans;
        varying vec4 v_color;
        varying vec2 v_texCoords;
        
        void main(){
          v_color = ${Shader.colorAttribute};
          v_color.a = v_color.a * (255.0/254.0);
          v_texCoords = ${Shader.texcoordAttribute}0;
          gl_Position =  u_projTrans * ${Shader.positionAttribute};
        }
        """

      val fragmentShader =
        """uniform sampler2D u_texture;
        uniform float u_smoothing;
        varying vec4 v_color;
        varying vec2 v_texCoords;
        
        void main(){
          if (u_smoothing > 0.0) {
            float smoothing = 0.25 / u_smoothing;
            vec4 color = texture2D(u_texture, v_texCoords);
            float distance = color.a;
            float alpha = smoothstep(0.5 - smoothing, 0.5 + smoothing, distance);
            gl_FragColor = vec4(v_color.rgb*color.rgb, alpha * v_color.a);
          } else {
            gl_FragColor = v_color * texture2D(u_texture, v_texCoords);
          }
        }
        """
      return Shader(vertexShader, fragmentShader)
    }
  }

  var text: String = ""
  var font: Font = Fonts.def
  var fontOffX: Float = 0f
  var fontOffY: Float = 0f
  var italic: Boolean = false
  var scl: Float = 0f
  var color: Color = Color.white

  private var isDistanceField: Boolean = false
  private var cache: FontCache? = null
  private var layout: GlyphLayout? = null

  override fun reset() {
    super.reset()
    text = ""
    font = Fonts.def
    fontOffX = 0f
    fontOffY = 0f
    italic = false
    scl = 0f
    color = Color.white
  }

  override fun prefWidth(): Float = layout?.width?:0f
  override fun prefHeight(): Float = layout?.height?:0f

  override fun setup(scope: RendererContext.Scope) {
    val data = font.getData()
    val lastScl = data.scaleX
    data.setScale(scl)
    isDistanceField = font is DistanceFieldFont
    cache = font.newFontCache()
    layout = cache!!.setText(
      text,
      0f, 0f,
      0, text.length,
      0f,
      Align.topLeft,
      false,
    )
    data.setScale(lastScl)
  }

  private val lastTrans = Mat()
  private val affineTrans = Mat()
  private val transform = Mat()
  private val affine2 = Affine2()
  override fun draw(x: Float, y: Float) {
    val data = font.data
    val cache = cache!!
    cache.tint(tmp1.set(color).mul(Draw.getColor()))

    val lastSclX = data.scaleX
    val lastSclY = data.scaleY
    data.setScale(scl)

    if (isDistanceField) {
      Draw.shader(distanceFieldShader)
      distanceFieldShader.bind()
      distanceFieldShader.setUniformf("u_smoothing", 0.5f * font.scaleX)
    }
    if (italic) {
      val last = lastTrans.set(Draw.trans())
      Draw.trans(
        transform.set(last)
          .translate(x + offsetX, y - offsetY)
          .mul(affineTrans.set(affine2.idt().shear(0.25f, 0f)))
      )
      cache.setPosition(fontOffX, fontOffY)
      cache.draw()
      Draw.trans(last)
    }
    else {
      cache.setPosition(x + offsetX + fontOffX, y - offsetY + fontOffY)
      cache.draw()
    }
    if (isDistanceField) Draw.shader()

    data.setScale(lastSclX, lastSclY)
  }
}
