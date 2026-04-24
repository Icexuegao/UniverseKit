package universe.ui.markdown.elemdraw

import arc.func.Cons
import arc.graphics.g2d.Font
import arc.graphics.gl.Shader
import arc.scene.ui.layout.Scl
import universe.ui.markdown.RendererContext

private const val MAX_SPLITTABLE_WIDTH = 32*16
private val wordSplitMatcher = Regex("[^a-zA-Z0-9_]")

val distanceFieldShader: Shader = createDistanceFieldShader()

private fun createDistanceFieldShader(): Shader {
  val vertexShader =
    """
    attribute vec4 ${Shader.positionAttribute};
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
    """
    uniform sampler2D u_texture;
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

fun RendererContext.drawTextWrap(
  str: String,
  font: Font = getScope().font,
  offsetX: Float = getScope().fontOffsetX,
  offsetY: Float = getScope().fontOffsetY,
  italic: Boolean = getScope().fontIsItalic,
  scl: Float = getScope().fontScale,
  doDraw: Cons<CharSequence> = Cons{ s ->
    draw(
      DrawStr.get(
        s.toString(),
        font,
        offsetX,
        offsetY,
        italic,
        getScope().fontColor,
        scl,
      )
    )
  }
) {
  if (mdShouldWrap) {
    val data = font.getData()

    var lastIndex = 0
    var splitIndex = 0
    var currWidth = 0f
    var splitWidth = 0f

    var currScope = getScope()
    str.forEachIndexed { index, c ->
      val glyph = data.getGlyph(c)

      if (wordSplitMatcher.matches(c.toString())) {
        splitIndex = index
        splitWidth = 0f
      }

      if (splitWidth + glyph.xadvance > MAX_SPLITTABLE_WIDTH) {
        splitIndex = index
      }

      if (currWidth + glyph.xadvance*scl > currScope.boundX - currScope.currOffsetX - currScope.marginRight) {
        val appendText = str.substring(lastIndex, splitIndex)
        val remText = str.substring(splitIndex, index).trimStart()

        doDraw.get(appendText)
        currScope = row(Scl.scl(mdStyle.linesPadding))

        lastIndex = splitIndex
        splitIndex = index
        splitWidth = remText.sumOf { data.getGlyph(it).xadvance }.toFloat()
        currWidth = splitWidth*scl
      }

      currWidth += glyph.xadvance*scl
      splitWidth += glyph.xadvance
    }

    if (lastIndex < str.length) {
      doDraw.get(str.substring(lastIndex))
    }
  }
  else {
    doDraw.get(str)
  }
}

