## Markdown

> [>点击<](README_ZH.md) 跳转至中文文档

This module provides UI tools for rendering Markdown text, including a core type `Markdown` and a set of auxiliary tools. It implements standard Markdown syntax while offering comprehensive abstractions for adding or customizing behaviors.

### Quick Start

You can add a dependency on this module as follows:

Gradle:

```groovy
dependencies {
  implementation 'com.github.EBwilson:UniverseKit:markdown:$version'
}
```

Maven:

```xml
<dependency>
    <groupId>com.github.EB-wilson.UniverseKit</groupId>
    <artifactId>markdown</artifactId>
    <version>[version]</version>
</dependency>
```

The core type of the Markdown module is `universe.ui.markdown.Markdown`, which provides a primary constructor:

```kotlin
Markdown(
  content,  //: String
  style,    //: MarkdownStyle
  provider, //: MarkdownProvider, default = BaseProvider()
)
```

Parameter meanings:

- `content`: The raw Markdown text. Refer to the [official Markdown tutorial](https://markdown.com.cn/basic-syntax/) for syntax details.
- `style`: The rendering style for this Markdown, including fonts, line spacing, block margins, etc.
- `provider`: The factory for rendering Markdown, providing extension objects for parsing and rendering, as well as the actual construction of draw targets. This is the core point for customizing behavior.

`provider` has a default value of `BaseProvider()`, which defines the default implementation for standard Markdown specifications. Custom Markdown syntax or rendering behaviors are typically achieved by inheriting and overriding functions of this type.

`style` describes the layout and drawing style of the Markdown. If you are using Markdown in a **Mindustry mod** environment, you can obtain the default Mindustry style from the `MarkdownStyles` singleton.

Here is a simple default usage example:

```kotlin
fun sample(){
  val dialog = BaseDialog("markdown")
  val markdown = Markdown(
    """
    # Hello Markdown
    
    *Let's* **test** `markdown`
    """,
    MarkdownStyles.defaultMD
  )
  
  dialog.addCloseButton()
  dialog.cont.add(markdown).grow().pad(20f)
  dialog.show()
}
```

This example uses the default Markdown implementation and the Mindustry default style. Running `sample()` will display the following:

![Hello world](images/hello-world.png)

`Markdown` has a single property `wrapContent`. It indicates whether the Markdown should automatically wrap lines based on the layout width. The default is `true`.

Note that when `wrapContent` is `true`, the preferred width (`getPrefWidth()`) of this Markdown is set to `0`. In this state, you should not rely on the Markdown content for layout decisions.

This is similar to `Label`. When you set `wrapContent` to `false`, the Markdown will not automatically wrap and will instead calculate its own width.

### Supported Syntax

This tool implements most of the standard Markdown specifications **except inline HTML**, as well as some commonly used extended syntax, as follows:

#### Headings

Lines starting with one or more `#` characters, where the number of `#` indicates the heading level. Headings are also recorded in the section index; you can call the `findChapter` function on the Markdown to obtain the position of a specific heading.

```md
# Heading 1
## Heading 2
### Heading 3
#### Heading 4
##### Heading 5
###### Heading 6
```

![Headings](images/headings.png)

#### Emphasis

Inline text surrounded by `*` or `_`. Nested emphasis toggles between emphasis and strong emphasis.

```md
*Emphasize*

**Strong**

___Strong Emphasize___
```

![Emphasis](images/emphasis.png)

#### Underline

Text surrounded by a pair of `++`. Often used for emphasis as well.

```md
++Under line++
```

![Under Line](images/under-line.png)

*This syntax is an extended Markdown dialect, not part of the standard Markdown specification.*

#### Strikethrough

Text surrounded by a pair of `~~`. Draws a line through the middle of the text to indicate deletion.

```md
~~Strikethrough~~
```

![Strikethrough](images/strikethrough.png)

#### Links

A hyperlink pointing to a URL, structured as `[Link](URL)`. When a link is clicked, the Markdown emits a `UrlClickedEvent` carrying the clicked URL, which propagates through the UI hierarchy.

```md
[A Link](https://github.com/EB-wilson/UniverseKit)
```

![Link](images/link.png)

The Markdown also implements reference link definitions. You can define a reference link as follows:

```md
[RefName]: url
```

And then reference it as `[Link][ReferenceURL]`. Reference links can be placed anywhere in the document and are typically not rendered.

```md
[A Reference Link][RefLinkDef]

[RefLinkDef]: https://github.com/EB-wilson
```

![Reference Link](images/ref-link.png)

#### Curtain

Text blocks surrounded by a pair of `$` symbols. The text is covered by a black foreground and becomes visible only when the cursor hovers over it (or when tapped and held on touchscreens).

```md
$This is a Curtain$
```

When not hovered:

![Curtain Close](images/curtain-close.png)

When hovered:

![Curtain Open](images/curtain-open.png)

*This syntax is an extended Markdown dialect, not part of the standard Markdown specification.*

#### Inline Code

Inline text surrounded by `` ` ``. Uses a monospaced font.

```md
`Code`
```

![Inline Code](images/inline-code.png)

#### Blockquotes

Block text consisting of multiple consecutive lines starting with `>`. The content is enclosed in a background frame.

```md
> Quote Block Line 1  
> Quote Block Line 2  
> Quote Block Line 3  
```

![Quote Block](images/quote-block.png)

#### Thematic Break

A single line consisting of at least three `-` or `*` characters. Used to separate sections of the document. Additional symbols have no special effect.

```md
Part 1

---

Part 2
```

![Thematic Break](images/thematic-break.png)

#### Lists

Multiple lines starting with `-` or a number followed by a period, all with consistent indentation. Indentation level indicates nesting.

Unordered list:
```md
- List item 1
- List item 2
- List item 3
  - Sub list item 1
  - Sub list item 2
- List item 4
```

![Bullet List](images/bullet-list.png)

Ordered list:
```md
1. Item 1
2. Item 2
   1. Sub item 1
   2. Sub item 2
      1. Sub child item 1
      2. Sub child item 2
   3. Sub item 3
```

![Ordered List](images/ordered-list.png)

#### Fenced Code Blocks

A multi-line text block surrounded by a pair of ` ``` `. After the opening ` ``` `, an info string can be added to describe the language, etc.

<pre><code>Fenced Code Block:

```kotlin
fun sample(){
  println("Hello Markdown!")
}
```</code></pre>

![Fenced Code](images/fenced-code.png)

#### Indented Code Blocks

Multiple consecutive lines indented by at least four spaces are treated as an indented code block. It functions similarly to a fenced code block, except that it cannot have an info string.

```md
Indent Code block:

    fun sample(){
      println("Hello Markdown!")
    }
```

![Indent Code](images/indent-code.png)

#### Images

Image resources referenced from a URL path, defined as `![Title](url)`.

URL paths support several predefined protocols:

- `http`/`https`: Hypertext Transfer Protocol, fetches image resources from a web address.

  Example: `https://github.com/EB-wilson/Helium/blob/master/icon.png`
- `file`: Local file path, locates file resources from the local filesystem. Note that this uses files on the client and does not package files from the compile-time device; rarely used.

  Example: `file:///C:/User/UserName/images/sample.png`
- `resource`: JAR resource path, indexes resource files from the current program's JAR, with the JAR as the root.

  Example: `resource://sprites/items/sample.png`
- `atlas`: Atlas index, searches for an image resource with a given name among the game's loaded sprite atlas; directly provide the sprite name.

  Example: `atlas:item-copper`
- `data`: The URL encodes the resource content directly as text; for images, it should be Base64-encoded.

  Example: `data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJcAAADlCAYAAABedWWzAAA...`

```md
![Web Image](https://avatars.githubusercontent.com/u/77141581)

![Atlas Image](atlas:item-copper)

![Data Image](data:image/jpeg;base64,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)
```

![Images](images/images.png)

Additionally, you can set the width/height and scaling method for an image by appending a block surrounded by `{}` after the image definition, using `key=value` pairs to provide `width`, `height`, and `scaling` so that the image is scaled to the desired size.

The `scaling` definition is the same as the `Scaling` enum used by the `Image` element. You should use the scaling methods provided by this enum.

```md
![Web Image](https://avatars.githubusercontent.com/u/77141581){width=120 scaling=fillX}

![Web Image](https://avatars.githubusercontent.com/u/77141581){width=120 height=180 scaling=stretch}
```

![Sized Image](images/sized-image.png)
