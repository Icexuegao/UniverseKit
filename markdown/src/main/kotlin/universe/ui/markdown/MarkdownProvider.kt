package universe.ui.markdown

import arc.func.Boolf
import arc.func.Cons
import org.commonmark.Extension
import org.commonmark.node.*

interface MarkdownProvider {
  fun RendererContext.renderChildren(parent: Node){
    var node = parent.firstChild
    while (node != null) {
      render(node)
      node = node.next
    }
  }

  fun Node.eachChildren(callback: Cons<Node>){
    var node = firstChild
    while (node != null) {
      callback.get(node)
      node = node.next
    }
  }

  fun Node.findChild(filter: Boolf<Node>): Node?{
    var node = firstChild
    while (node != null) {
      if (filter.get(node)) return node
      node = node.next
    }

    return null
  }

  fun Node.eachDescendants(callback: Cons<Node>){
    var node = firstChild
    while (node != null) {
      callback.get(node)
      node.eachDescendants(callback)
      node = node.next
    }
  }

  fun Node.findDescendants(filter: Boolf<Node>): Node?{
    var node = firstChild
    while (node != null) {
      if (filter.get(node)) return node
      else {
        val des = node.findDescendants(filter)
        if (des != null) return des

        node = node.next
      }
    }

    return null
  }

  fun extensions(): List<Extension>
  fun urlHandlers(): List<UrlHandler>

  fun handleLayoutException(exception: Throwable)

  fun RendererContext.add(node: Document)
  fun RendererContext.add(node: Heading)
  fun RendererContext.add(node: Paragraph)
  fun RendererContext.add(node: BlockQuote)
  fun RendererContext.add(node: Link)
  fun RendererContext.add(node: Text)
  fun RendererContext.add(node: Code)
  fun RendererContext.add(node: BulletList)
  fun RendererContext.add(node: OrderedList)
  fun RendererContext.add(node: ListItem)
  fun RendererContext.add(node: IndentedCodeBlock)
  fun RendererContext.add(node: FencedCodeBlock)
  fun RendererContext.add(node: ThematicBreak)
  fun RendererContext.add(node: Image)
  fun RendererContext.add(node: Emphasis)
  fun RendererContext.add(node: StrongEmphasis)
  fun RendererContext.add(node: SoftLineBreak)
  fun RendererContext.add(node: HardLineBreak)
  fun RendererContext.add(node: LinkReferenceDefinition)

  fun RendererContext.add(node: HtmlInline){ throw UnsupportedOperationException("Html was unsupported yet.") }
  fun RendererContext.add(node: HtmlBlock){ throw UnsupportedOperationException("Html was unsupported yet.") }

  fun RendererContext.add(node: CustomNode){
    throw UnsupportedOperationException("node type ${node.javaClass} not supported")
  }
  fun RendererContext.add(node: CustomBlock){
    throw UnsupportedOperationException("block type ${node.javaClass} not supported")
  }
}