package universe.actuals

import universe.actuals.reflect.DesktopConstructorInvoker
import universe.actuals.reflect.DesktopFieldAccessor
import universe.actuals.reflect.DesktopMethodInvoker
import universe.actuals.reflect.DesktopStaticInvoker
import universe.expects.ReflectionHandle
import universe.expects.reflect.ConstructorInvoker
import universe.expects.reflect.MethodInvoker
import universe.expects.reflect.StaticInvoker
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

class ReflectionDesktop: ReflectionHandle {
  private val modifierField = Field::class.java.getDeclaredField("modifiers").also { it.isAccessible = true }

  private fun KClass<*>.asType() = javaPrimitiveType?:java

  override fun findField(clazz: KClass<*>, name: String): Field = clazz.java.getDeclaredField(name)
  override fun findStaticField(clazz: KClass<*>, name: String): Field = clazz.java.getDeclaredField(name)

  override fun makeAccessible(field: Field) {
    field.isAccessible = true
  }

  override fun makeMutable(field: Field) {
    val newModifiers = field.modifiers and (Modifier.FINAL.inv())
    modifierField.set(field, newModifiers)
  }
  override fun obtainFieldAccessor(field: Field) = DesktopFieldAccessor(field)
  override fun findMethod(
    clazz: KClass<*>,
    name: String,
    vararg argTypes: KClass<*>,
  ): Method = clazz.java.getDeclaredMethod(name, *argTypes.map { it.asType() }.toTypedArray())

  override fun makeAccessible(method: Method) {
    method.isAccessible = true
  }
  override fun <R> obtainMethodInvoker(method: Method): MethodInvoker<R> = DesktopMethodInvoker(method)
  override fun <R> obtainStaticInvoker(method: Method): StaticInvoker<R> = DesktopStaticInvoker(method)
  override fun <T : Any> findConstructor(
    clazz: KClass<T>,
    vararg argTypes: KClass<*>,
  ): Constructor<T> = clazz.java.getDeclaredConstructor(*argTypes.map { it.asType() }.toTypedArray())

  override fun makeAccessible(constructor: Constructor<*>) {
    constructor.isAccessible = true
  }
  override fun <T : Any> obtainConstructorInvoker(constructor: Constructor<T>): ConstructorInvoker<T> =
    DesktopConstructorInvoker(constructor)
}