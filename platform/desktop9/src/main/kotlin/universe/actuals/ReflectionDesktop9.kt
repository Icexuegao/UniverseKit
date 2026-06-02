package universe.actuals

import sun.misc.Unsafe
import universe.actuals.reflect.Desktop9ConstructorInvoker
import universe.actuals.reflect.Desktop9FieldAccessor
import universe.actuals.reflect.Desktop9MethodInvoker
import universe.actuals.reflect.Desktop9StaticInvoker
import universe.expects.ReflectionHandle
import universe.expects.reflect.ConstructorInvoker
import universe.expects.reflect.FieldAccessor
import universe.expects.reflect.MethodInvoker
import universe.expects.reflect.StaticInvoker
import universe.util.Demodulator
import java.lang.reflect.Constructor
import java.lang.reflect.Executable
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

class ReflectionDesktop9: ReflectionHandle {
  private val modifierField = Field::class.java.getDeclaredField("modifiers").also {
    Demodulator.makeModuleOpen(
      Field::class.java.module,
      Field::class.java,
      ReflectionDesktop9::class.java.module,
    )

    it.isAccessible = true
  }

  private val unsafe = run{
    val unsafeField = Unsafe::class.java.getDeclaredField("theUnsafe")
    unsafeField.setAccessible(true)
    unsafeField.get(null) as Unsafe
  }

  private fun KClass<*>.asType() = javaPrimitiveType?:java

  private fun makeAccessible0(executable: Executable){
    if (executable is Method) {
      Demodulator.makeModuleOpen(
        executable.returnType.module,
        executable.returnType,
        ReflectionDesktop9::class.java.module
      )
    }
    else if (executable is Constructor<*>){
      Demodulator.makeModuleOpen(
        executable.declaringClass.module,
        executable.declaringClass,
        ReflectionDesktop9::class.java.module
      )
    }

    for (type in executable.parameterTypes) {
      Demodulator.makeModuleOpen(
        type.module,
        type,
        ReflectionDesktop9::class.java.module
      )
    }

    executable.isAccessible = true
  }

  override fun findField(clazz: KClass<*>, name: String): Field = clazz.java.getDeclaredField(name)
  override fun findStaticField(clazz: KClass<*>, name: String): Field = clazz.java.getDeclaredField(name)

  override fun makeAccessible(field: Field) {
    Demodulator.makeModuleOpen(
      field.type.module,
      field.type,
      ReflectionDesktop9::class.java.module
    )

    field.isAccessible = true
  }
  override fun makeMutable(field: Field) {
    val newModifiers = field.modifiers and Modifier.FINAL.inv()
    modifierField.set(field, newModifiers)
  }
  override fun obtainFieldAccessor(field: Field): FieldAccessor = Desktop9FieldAccessor(field)

  override fun findMethod(
    clazz: KClass<*>,
    name: String,
    vararg argTypes: KClass<*>,
  ): Method = clazz.java.getDeclaredMethod(name, *argTypes.map { it.asType() }.toTypedArray())
  override fun makeAccessible(method: Method) {
    makeAccessible0(method)
  }
  override fun <R> obtainMethodInvoker(method: Method): MethodInvoker<R> = Desktop9MethodInvoker(method)
  override fun <R> obtainStaticInvoker(method: Method): StaticInvoker<R> = Desktop9StaticInvoker(method)

  override fun <T : Any> findConstructor(
    clazz: KClass<T>,
    vararg argTypes: KClass<*>,
  ): Constructor<T> = clazz.java.getDeclaredConstructor(*argTypes.map { it.asType() }.toTypedArray())
  override fun makeAccessible(constructor: Constructor<*>) {
    makeAccessible0(constructor)
  }
  override fun <T : Any> obtainConstructorInvoker(constructor: Constructor<T>): ConstructorInvoker<T> =
    Desktop9ConstructorInvoker(constructor)
}