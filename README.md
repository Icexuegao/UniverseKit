# UniverseKit

> [>点击<](README_ZH.md) 跳转至中文文档

This is a utility library primarily aimed at **Mindustry** mods or **Arc Engine** (a LibGDX variant) game development. It contains several functional submodules, a few of which can be used independently in non-Arc engine projects.

You can add a dependency on this tool just like any other Java dependency library. For Gradle, you need to add `jitpack.io` to the Maven repository and add the following to your `build.gradle`:

```groovy
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://www.jitpack.io' }
  }
}
```

Then add the dependency for a specific module in this tool within `dependencies`:

```groovy
dependencies {
  implementation 'com.github.EBwilson:UniverseKit:[module]:$universeKitVersion'
}
```

Where `module` is the name of the module you need. The list of tool modules included in **UniverseKit**:

| Module Name  | Module Description                                                                                                                                                                                                  |
|--------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `reflection` | Reflection utility library, containing a series of **deep reflection** tools, including bypassing access permissions to read/write variables, as well as enum creation, etc.<br/>[Go to module details](reflection) |
| `markdown`   | Markdown rendering library, providing a UI element `Markdown` and its customization tools, etc.<br/>[Go to module details](markdown)                                                                                |
| `graphic`    | Graphics utility tools, including a series of drawing-related utilities such as screen-space samplers. (Not yet completed)<br/>[Go to module details](graphic)                                                      |

Additionally, there are several platform-related implementation modules that work behind the scenes. Under normal circumstances, you do not need to pay attention to the specific behavior of these modules:

- `platform`: Contains only a singleton object `UniverseActual` that holds the platform implementations required by those platform-related modules.
- `expects`: Contains abstract interfaces for platform-related functions, serving as the access point for callers.
- `desktop`: Platform implementation for desktop (JDK8 and below) environments.
- `desktop9`: Platform implementation for desktop (JDK9 and above) environments.
- `android`: Platform implementation for Android SDK level 28 and below (i.e., before Android 9) environments.
- `android29`: Platform implementation for Android SDK level 29 and above (i.e., Android 10 and later) environments.
