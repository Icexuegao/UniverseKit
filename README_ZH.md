# UniverseKit

这是一个主要面向**Mindustry**模组或**Arc引擎**（LibGDX变体）游戏开发的实用工具库，包含了若干个功能子模块，其中少部分模块可在非Arc引擎项目重独立使用。

您可以像使用任何一个java依赖库一样添加对此工具的依赖。对于Gradle，需将`jitpack.io`添加到Maven仓库，在您的`build.gralde`中添加如下内容：

```groovy
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://www.jitpack.io' }
  }
}
```

然后在`dependencies`中添加对本工具中特定模块的依赖：

```groovy
dependencies {
  implementation 'com.github.EBwilson:UniverseKit:[module]:$universeKitVersion'
}
```

其中`module`填写您需要的模块名称，**UniverseKit**中包含的工具模块列表：

| 模块名称         | 模块功能                                                                    |
|--------------|-------------------------------------------------------------------------|
| `reflection` | 反射工具库，包含了一系列**深反射**工具，除绕过访问权限读写变量外，还包括枚举创建等工具。<br/>[转到模块详情](reflection) |
| `markdown`   | Markdown渲染工具库，提供了一个UI元素`Markdown`及其自定义工具等。<br/>[转到模块详情](markdown)       |
| `graphic`    | 图形实用工具，包含一系列绘图相关的工具，如屏幕空间采样器等。（尚未完成）<br/>[转到模块详情](graphic)              |

另外，还包含若干平台相关的实现模块，作为幕后工作，通常情况下您不需要关注这些模块的具体行为：

- `platform`：仅包含一个单例对象`UniverseActual`，保存那些平台相关的模块所需要的平台实现。
- `expects`：包含平台相关功能的抽象接口，作为调用者的访问入口
- `desktop`：桌面（JDK8及以下）环境的平台实现
- `desktop9`：桌面（JDK9及以上）环境的平台实现
- `android`：安卓SDK级别28及以下（即安卓9之前）环境下的平台实现
- `android29`：安卓SDK级别29及以上（即安卓10之后）环境下的平台实现
