package universe.expects

import universe.expects.reflect.ConstructorInvoker
import universe.expects.reflect.FieldAccessor
import universe.expects.reflect.MethodInvoker
import universe.expects.reflect.StaticInvoker
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.reflect.KClass

interface ReflectionHandle {
  //field access
  fun findField(clazz: KClass<*>, name: String): Field
  fun findStaticField(clazz: KClass<*>, name: String): Field
  fun makeAccessible(field: Field)
  fun makeMutable(field: Field)
  fun obtainFieldAccessor(field: Field): FieldAccessor

  //method access
  fun findMethod(clazz: KClass<*>, name: String, vararg argTypes: KClass<*>): Method
  fun makeAccessible(method: Method)
  fun <R> obtainMethodInvoker(method: Method): MethodInvoker<R>
  fun <R> obtainStaticInvoker(method: Method): StaticInvoker<R>

  //constructor access
  fun <T: Any> findConstructor(clazz: KClass<T>, vararg argTypes: KClass<*>): Constructor<T>
  fun makeAccessible(constructor: Constructor<*>)
  fun <T: Any> obtainConstructorInvoker(constructor: Constructor<T>): ConstructorInvoker<T>
}