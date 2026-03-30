package universe.ui.markdown.elemdraw

import arc.func.Cons
import arc.graphics.Color
import arc.graphics.g2d.Font
import arc.scene.ui.layout.Scl
import universe.ui.markdown.RendererContext

private const val MAX_SPLITTABLE_WORD_SIZE = 32

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

