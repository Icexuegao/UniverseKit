## Markdown

此模块提供渲染Markdown文本的UI工具，包含一个核心的类型`Markdown`及其周边的一系列工具，对标准的Markdown格式进行了实现，同时也提供了完备的抽象用于添加或修改自定义行为。

### 快速开始

您可以通过如下方式添加对此模块的依赖：

Gradle：

```groovy
dependencies {
  implementation 'com.github.EBwilson:UniverseKit:markdown:$version'
}
```

Maven：

```xml
<dependency>
    <groupId>com.github.EB-wilson.UniverseKit</groupId>
    <artifactId>markdown</artifactId>
    <version>[version]</version>
</dependency>
```

Markdown模块的核心类型为`universe.ui.markdown.Markdown`，它包含一个主要构造函数：

```kotlin
Markdown(
  content,  //: String
  style,    //: MarkdownStyle
  provider, //: MarkdownProvider, default = BaseProvider()
)
```

其中各参数含义：

- `content`：Markdown的原始文本内容，格式请参阅[Markdown官方教程](https://markdown.com.cn/basic-syntax/)。
- `style`：此Markdown的绘制样式，包括各类文本使用的字体，行间距及块间距等。
- `provider`：渲染Markdown的工厂，提供解析与渲染的扩展对象及实际构造绘制目标的相关功能，是自定义行为的核心目标。

其中`provider`具有一个`BaseProvider()`的默认值，它定义了对常规Markdown规范的默认实现，而自定义Markdown语法或渲染行为也通常是通过继承并重写此类型的函数来实现。

`style`描述此Markdown的布局与绘制样式，如果您是在**Mindustry mod**环境中使用Markdown，则您可以在`MarkdownStyles`单例中获取Mindustry风格的默认样式。

以下是一个简单的默认用例：

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

此例中使用默认的Markdown实现与Mindustry默认样式，运行`sample()`，您将会看到如下画面：

![Hello world](images/hello-world.png)

Markdown中仅有一个属性`wrapContent`，它表示这个Markdown是否依据布局宽度自动换行，默认为真。

需要注意的是，当`wrapContent`为真时，此Markdown的最适宽度（`getPrefWidth()`）会被设为0，此状态下您不应以Markdown内容为参考进行布局。

这类似于`Label`，当您将`wrapContent`设为`false`后，Markdown将不会自动换行，并自动计算其所占宽度。

### 支持语法

本工具实现了Markdown的标准规范及部分常用扩展语法，如下：

- **多级标题**：由若干`#`号起始的行，`#`数量也代表标题级别。标题还会被记录到章节索引中，您可以通过对Markdown调用其`findChapter`函数来获取某一章节的标题位置。

  ```md
  # Heading 1
  ## Heading 2
  ### Heading 3
  #### Heading 4
  ##### Heading 5
  ###### Heading 6
  ```
  
  ![Headings](images/headings.png)
- **强调**：由`*`号或`-`号包围的内嵌文本。多次嵌套只会在强调和增强强调间来回切换。

  ```md
  *Emphasize*
  
  **Strong**
  
  ***Strong Emphasize***
  ```

  ![Emphasis](images/emphasis.png)
- **内联代码**：由``` ` ```号包围的内嵌文本，使用等宽字体。

  ```md
  `Code`
  ```

  ![Inline Code](images/inline-code.png)
- **引用块**：由`>`符号开头的多个连续行组成的块文本。

  ```md
  > Quote Block Line 1  
  > Quote Block Line 2  
  > Quote Block Line 3  
  ```
  
  ![Quote Block](images/quote-block.png)
- **列表**：由一系列缩进一致的`-`或数字序号开头的多个行，缩进量可表达列表等级。

  无序表列：
  ```md
  - List item 1
  - List item 2
  - List item 3
    - Sub list item 1
    - Sub list item 2
  - List item 4
  ```

  ![Bullet List](images/bullet-list.png)  

  有序列表：
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

- **围栏代码块**：由一对` ``` `包围的一块多行文本，首个` ``` `后可跟随一个标签信息用于描述代码的语言等。

  ```md
  
  ```
