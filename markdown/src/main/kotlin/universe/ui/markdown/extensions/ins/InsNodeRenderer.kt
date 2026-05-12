package universe.ui.markdown.extensions.ins

import mindustry.logic.LAccess
import org.commonmark.ext.ins.Ins
import org.commonmark.node.CustomNode
import org.commonmark.node.Node
import universe.ui.markdown.LayoutNodeRenderer
import universe.ui.markdown.NoActionVisitor
import universe.ui.markdown.RendererContext

class InsNodeRenderer(
  element: RendererContext,
  provider: InsProvider
) : LayoutNodeRenderer<InsProvider>(element, provider) {
  private inner class Visitor: NoActionVisitor() {
    override fun visit(customNode: CustomNode) {
      if (customNode is Ins) provider.apply { context.add(customNode) }
    }
  }
  private val visitorInst = Visitor()

  override fun getNodeTypes(): Set<Class<out Node>> {
    return setOf(Ins::class.java)
  }

  override fun render(node: Node) {
    node.accept(visitorInst)
  }
}