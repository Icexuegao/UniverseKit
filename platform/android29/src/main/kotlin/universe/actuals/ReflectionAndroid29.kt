package universe.actuals

import universe.actuals.reflect.Android29ConstructorInvoker
import universe.actuals.reflect.Android29FieldAccessor
import universe.actuals.reflect.Android29MethodInvoker
import universe.actuals.reflect.Android29StaticInvoker
import universe.expects.ReflectionHandle
import universe.expects.reflect.ConstructorInvoker
import universe.expects.reflect.MethodInvoker
import universe.expects.reflect.StaticInvoker
import universe.util.HiddenApiBypass
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.reflect.KClass

class ReflectionAndroid29: ReflectionHandle {
  private fun KClass<*>.asType() = javaPrimitiveType?:java

  override fun findField(clazz: KClass<*>, name: String): Field =
    HiddenApiBypass.getInstanceFields(clazz.java).find { it.name == name } ?:
    throw NoSuchFieldException("Field $name not found in $clazz")

  override fun findStaticField(clazz: KClass<*>, name: String): Field =
    HiddenApiBypass.getStaticFields(clazz.java).find { it.name == name } ?:
    throw NoSuchFieldException("Static field $name not found in $clazz")

  override fun makeAccessible(field: Field) {
    field.isAccessible = true
  }
  override fun makeMutable(field: Field) {}
  override fun obtainFieldAccessor(field: Field) = Android29FieldAccessor(field)

  override fun findMethod(
    clazz: KClass<*>,
    name: String,
    vararg argTypes: KClass<*>,
  ): Method = HiddenApiBypass.getDeclaredMethod(
    clazz.java,
    name,
    *argTypes.map { it.asType() }.toTypedArray()
  )
  override fun makeAccessible(method: Method) {
    method.isAccessible = true
  }
  override fun <R> obtainMethodInvoker(method: Method): MethodInvoker<R> = Android29MethodInvoker(method)
  override fun <R> obtainStaticInvoker(method: Method): StaticInvoker<R> = Android29StaticInvoker(method)

  @Suppress("UNCHECKED_CAST")
  override fun <T : Any> findConstructor(
    clazz: KClass<T>,
    vararg argTypes: KClass<*>,
  ): Constructor<T> = HiddenApiBypass.getDeclaredConstructor(
    clazz.java,
    *argTypes.map { it.asType() }.toTypedArray()
  ) as Constructor<T>
  override fun makeAccessible(constructor: Constructor<*>) {
    constructor.isAccessible = true
  }
  override fun <T: Any> obtainConstructorInvoker(constructor: Constructor<T>): ConstructorInvoker<T> =
    Android29ConstructorInvoker(constructor)
}