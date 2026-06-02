package universe

import universe.actuals.Android29Provider
import universe.actuals.AndroidProvider
import universe.actuals.Desktop9Provider
import universe.actuals.DesktopProvider
import universe.expects.PlatformProvider
import universe.expects.ReflectionHandle
import kotlin.reflect.KProperty

private class MutableLazy<T>(
  private val initializer: () -> T,
){
  var value: T? = null
    private set

  operator fun getValue(thisRef: Any?, property: KProperty<*>): T{
    if (value == null){
      value = initializer()
    }

    return value!!
  }

  operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T){
    this.value = value
  }
}

object UniverseActual {
  private var platformProvider = getPlatform()

  @JvmStatic var reflection: ReflectionHandle by MutableLazy { platformProvider.getReflectionHandle() }
    private set

  fun customPlatformProvider(platform: PlatformProvider) {
    platformProvider = platform

    reflection = platform.getReflectionHandle()
  }

  private fun getPlatform(): PlatformProvider {
    try {
      val ver = Class.forName($$"android.os.Build$VERSION")
      val sdkField = ver.getField("SDK_INT")
      sdkField.isAccessible = true

      return if (sdkField.getInt(null) >= 29) Android29Provider()
      else AndroidProvider()
    } catch (_:ClassNotFoundException){}

    try {
      Class.forName("java.lang.Module")
      return Desktop9Provider()
    } catch (_:ClassNotFoundException){}

    return DesktopProvider()
  }
}