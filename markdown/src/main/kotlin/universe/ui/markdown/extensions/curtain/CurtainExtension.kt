package universe.ui.markdown.extensions.curtain

import org.commonmark.Extension
import org.commonmark.parser.Parser
import org.commonmark.parser.Parser.ParserExtension
import universe.ui.markdown.MDLayoutRenderer
import universe.ui.markdown.MDLayoutRenderer.DrawRendererExtension

class CurtainExtension private constructor() : ParserExtension, DrawRendererExtension {
  companion object {
    fun create(): Extension = CurtainExtension()
  }

  override fun extend(parserBuilder: Parser.Builder) {
    parserBuilder.customInlineContentParserFactory(CurtainParser.Factory())
  }

  override fun extend(rendererBuilder: MDLayoutRenderer.Builder) {
    rendererBuilder.nodeRendererFactory(::CurtainRenderer)
  }
}
