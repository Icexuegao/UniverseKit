package universe.actuals

import universe.actuals.reflect.AndroidConstructorInvoker
import universe.actuals.reflect.AndroidFieldAccessor
import universe.actuals.reflect.AndroidMethodInvoker
import universe.actuals.reflect.AndroidStaticInvoker
import universe.expects.ReflectionHandle
import universe.expects.reflect.ConstructorInvoker
import universe.expects.reflect.MethodInvoker
import universe.expects.reflect.StaticInvoker
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.jvm.java
import kotlin.reflect.KClass

class ReflectionAndroid: ReflectionHandle {
  private fun KClass<*>.asType() = javaPrimitiveType?:java

  override fun findField(clazz: KClass<*>, name: String): Field = clazz.java.getDeclaredField(name)
  override fun findStaticField(clazz: KClass<*>, name: String): Field = clazz.java.getDeclaredField(name)

  override fun makeAccessible(field: Field) {
    field.isAccessible = true
  }
  override fun makeMutable(field: Field) {}
  override fun obtainFieldAccessor(field: Field) = AndroidFieldAccessor(field)

  override fun findMethod(
    clazz: KClass<*>,
    name: String,
    vararg argTypes: KClass<*>,
  ): Method = clazz.java.getDeclaredMethod(name, *argTypes.map { it.asType() }.toTypedArray())
  override fun makeAccessible(method: Method) {
    method.isAccessible = true
  }
  override fun <R> obtainMethodInvoker(method: Method): MethodInvoker<R> = AndroidMethodInvoker(method)
  override fun <R> obtainStaticInvoker(method: Method): StaticInvoker<R> = AndroidStaticInvoker(method)

  override fun <T : Any> findConstructor(
    clazz: KClass<T>,
    vararg argTypes: KClass<*>,
  ): Constructor<T> = clazz.java.getDeclaredConstructor(*argTypes.map { it.asType() }.toTypedArray())
  override fun makeAccessible(constructor: Constructor<*>) {
    constructor.isAccessible = true
  }
  override fun <T: Any> obtainConstructorInvoker(constructor: Constructor<T>): ConstructorInvoker<T> =
    AndroidConstructorInvoker(constructor)
}