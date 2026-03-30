package universe.ui.markdown.elemdraw

import arc.func.Cons
import arc.graphics.g2d.Font
import arc.graphics.gl.Shader
import arc.scene.ui.layout.Scl
import universe.ui.markdown.RendererContext

private const val MAX_SPLITTABLE_WORD_SIZE = 32

val distanceFieldShader: Shader = createDistanceFieldShader()

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
  val data = font.getData()

  var builder = StringBuilder()
  var splitIndex = 0
  var nonSpaceNow = false

  var curr = getScope()

  var currOff = curr.currOffsetX
  var currIndex = 0

  if (mdShouldWrap) {
    str.forEach { char ->
      builder.append(char)
      currIndex++

      if (char.isWhitespace()) {
        if (nonSpaceNow) {
          nonSpaceNow = false
          splitIndex = currIndex
        }
      }
      else {
        nonSpaceNow = true

        if (currIndex - splitIndex > MAX_SPLITTABLE_WORD_SIZE) {
          splitIndex = -1
          nonSpaceNow = false
        }
      }

      val glyph = data.getGlyph(char)
      if (currOff + glyph.xadvance*scl > curr.boundX - curr.marginRight) {
        if (splitIndex < 0)
          splitIndex = builder.length - 1

        val p1 = builder.substring(0, splitIndex)
        doDraw.get(p1)

        curr = row(Scl.scl(mdStyle.linesPadding))

        builder = StringBuilder(
          if (splitIndex < builder.length) builder.substring(splitIndex).trimStart()
          else ""
        )
        splitIndex = 0
        nonSpaceNow = builder.isNotBlank()
        currIndex = builder.length - 1
        currOff = curr.currOffsetX + builder.sumOf { data.getGlyph(it).xadvance }*scl
      }
      else {
        currOff += glyph.xadvance*scl
      }
    }

    if (builder.any()) {
      var w = 0f
      var l = 0

      builder.forEachIndexed { i, char ->
        val g = data.getGlyph(char)
        w += g.xadvance*scl

        if (curr.currOffsetX + w > curr.boundX - curr.marginRight) {
          doDraw.get(builder.substring(l, i))
          row(Scl.scl(mdStyle.linesPadding))
          l = i
          w = 0f
        }
      }

      if (l < builder.length){
        doDraw.get(builder.substring(l))
      }
    }
  }
  else {
    doDraw.get(str)
  }
}

