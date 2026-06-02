@file:Suppress("UNCHECKED_CAST", "HasPlatformType", "DuplicatedCode")

/**Kotlin reflection DSL utilities provide a range of convenient and type-safe reflection tools.
*
* You can use those reflection wrappers in the same way as origin field access and method calls, just like:
*
* ```kotlin
* //member field
* var TargetType.field: FieldType by accessField("fieldName")
* var TargetType.numberRef by accessInt("number")
* //static field
* var staticField: FieldType by TargetClass::class.accessField("staticFieldName")
*
* //member method
* val methodRef: TargetType.(ArgType) -> ReturnType = accessMethod1("methodName")
* //static method
* val staticRef: (ArgType1, ArgType2) -> ReturnType = TargetClass::class.accessMethod2("methodName")
*
* val constructorRef: (ArgType) -> TargetType = TargetType::class.accessConstructor()
* ```
*
* Then you can use dot expression to access it in valid scope, just like use normal field and method:
*
* ```kotlin
* val newInstance = constructorRef(arg)
* instance.field = "fieldValue"
* instance.numberRef = 123
* staticField = "fieldValue"
* val result = instance.methodRef(arg)
* val resultStatic = staticRef(arg1, arg2)
* ```
*
* These visits bypass the modifier checks for the target field, and you can use them to access those non-public members.
*
* @author EBwilson
* @since 1.0*/

package universe.util.reflect

import universe.UniverseActual.reflection
import universe.util.reflect.accessor.*
import java.lang.reflect.Method
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaField

fun KClass<*>.asType() = javaPrimitiveType?:java

//=========
//  Field
//=========
// member
inline fun <reified O : Any, reified T: Any?> accessField(property: KProperty<T>) =
  property.javaField?.let { FieldAccessor<O, T>(it) }
  ?: IllegalArgumentException("this property don't have a java field.")
inline fun <reified O : Any, reified T: Any?> accessField(name: String) =
  FieldAccessor<O, T>(reflection.findField(O::class, name).also {
    if (!it.type.isAssignableFrom(T::class.asType()))
      throw IllegalArgumentException("field $it type is not instance of ${T::class.asType()}")
  })
inline fun <reified O : Any> accessByte(property: KProperty<Byte>) =
  property.javaField?.let { ByteAccessor<O>(it) }
  ?: IllegalArgumentException("this property don't have a java field.")
inline fun <reified O : Any> accessByte(name: String) =
  ByteAccessor<O>(reflection.findField(O::class, name).also {
    if (it.type != Byte::class.asType())
      throw IllegalArgumentException("field $it type is not byte")
  })
inline fun <reified O : Any> accessShort(property: KProperty<Short>) =
  property.javaField?.let { ShortAccessor<O>(it) }
  ?: IllegalArgumentException("this property don't have a java field.")
inline fun <reified O : Any> accessShort(name: String) =
  ShortAccessor<O>(reflection.findField(O::class, name).also {
    if (it.type != Short::class.asType())
      throw IllegalArgumentException("field $it type is not short")
  })
inline fun <reified O : Any> accessInt(property: KProperty<Int>) =
  property.javaField?.let { IntAccessor<O>(it) }
  ?: IllegalArgumentException("this property don't have a java field.")
inline fun <reified O : Any> accessInt(name: String) =
  IntAccessor<O>(reflection.findField(O::class, name).also {
    if (it.type != Int::class.asType())
      throw IllegalArgumentException("field $it type is not int")
  })
inline fun <reified O : Any> accessLong(property: KProperty<Long>) =
  property.javaField?.let { LongAccessor<O>(it) }
  ?: IllegalArgumentException("this property don't have a java field.")
inline fun <reified O : Any> accessLong(name: String) =
  LongAccessor<O>(reflection.findField(O::class, name).also {
    if (it.type != Long::class.asType())
      throw IllegalArgumentException("field $it type is not long")
  })
inline fun <reified O : Any> accessFloat(property: KProperty<Float>) =
  property.javaField?.let { FloatAccessor<O>(it) }
  ?: IllegalArgumentException("this property don't have a java field.")
inline fun <reified O : Any> accessFloat(name: String) =
  FloatAccessor<O>(reflection.findField(O::class, name).also {
    if (it.type != Float::class.asType())
      throw IllegalArgumentException("field $it type is not float")
  })
inline fun <reified O : Any> accessDouble(property: KProperty<Double>) =
  property.javaField?.let { DoubleAccessor<O>(it) }
  ?: IllegalArgumentException("this property don't have a java field.")
inline fun <reified O : Any> accessDouble(name: String) =
  DoubleAccessor<O>(reflection.findField(O::class, name).also {
    if (it.type != Double::class.asType())
      throw IllegalArgumentException("field $it type is not double")
  })
inline fun <reified O : Any> accessBoolean(property: KProperty<Boolean>) =
  property.javaField?.let { BooleanAccessor<O>(it) }
  ?: IllegalArgumentException("this property don't have a java field.")
inline fun <reified O : Any> accessBoolean(name: String) =
  BooleanAccessor<O>(reflection.findField(O::class, name).also {
    if (it.type != Boolean::class.asType())
      throw IllegalArgumentException("field $it type is not boolean")
  })
inline fun <reified O : Any> accessChar(property: KProperty<Char>) =
  property.javaField?.let { CharAccessor<O>(it) }
  ?: IllegalArgumentException("this property don't have a java field.")
inline fun <reified O : Any> accessChar(name: String) =
  CharAccessor<O>(reflection.findField(O::class, name).also {
    if (it.type != Boolean::class.asType())
      throw IllegalArgumentException("field $it type is not boolean")
  })

// static
inline fun <reified T: Any?> accessStaticField(property: KProperty<T>) =
  property.javaField?.let { FieldAccessorStatic<T>(it) }
  ?: throw IllegalArgumentException("this property don't have a java field.")
inline fun <reified T: Any?> KClass<*>.accessField(name: String) =
  FieldAccessorStatic<T>(reflection.findStaticField(this, name).also {
    if (!it.type.isAssignableFrom(T::class.asType()))
      throw IllegalArgumentException("field $it type is not instance of ${T::class.asType()}")
  })
fun accessStaticByte(property: KProperty<Byte>) =
  property.javaField?.let { ByteAccessorStatic(it) }
  ?: throw IllegalArgumentException("this property don't have a java field.")
fun KClass<*>.accessByte(name: String) =
  ByteAccessorStatic(reflection.findStaticField(this, name).also {
    if (it.type != Byte::class.asType())
      throw IllegalArgumentException("field $it type is not byte")
  })
fun accessStaticShort(property: KProperty<Short>) =
  property.javaField?.let { ShortAccessorStatic(it) }
  ?: throw IllegalArgumentException("this property don't have a java field.")
fun KClass<*>.accessShort(name: String) =
  ShortAccessorStatic(reflection.findStaticField(this, name).also {
    if (it.type != Short::class.asType())
      throw IllegalArgumentException("field $it type is not short")
  })
fun accessStaticInt(property: KProperty<Int>) =
  property.javaField?.let { IntAccessorStatic(it) }
  ?: throw IllegalArgumentException("this property don't have a java field.")
fun KClass<*>.accessInt(name: String) =
  IntAccessorStatic(reflection.findStaticField(this, name).also {
    if (it.type != Int::class.asType())
      throw IllegalArgumentException("field $it type is not int")
  })
fun accessStaticLong(property: KProperty<Long>) =
  property.javaField?.let { LongAccessorStatic(it) }
  ?: throw IllegalArgumentException("this property don't have a java field.")
fun KClass<*>.accessLong(name: String) =
  LongAccessorStatic(reflection.findStaticField(this, name).also {
    if (it.type != Long::class.asType())
      throw IllegalArgumentException("field $it type is not long")
  })
fun accessStaticFloat(property: KProperty<Float>) =
  property.javaField?.let { FloatAccessorStatic(it) }
  ?: throw IllegalArgumentException("this property don't have a java field.")
fun KClass<*>.accessFloat(name: String) =
  FloatAccessorStatic(reflection.findStaticField(this, name).also {
    if (it.type != Float::class.asType())
      throw IllegalArgumentException("field $it type is not float")
  })
fun accessStaticDouble(property: KProperty<Double>) =
  property.javaField?.let { DoubleAccessorStatic(it) }
  ?: throw IllegalArgumentException("this property don't have a java field.")
fun KClass<*>.accessDouble(name: String) =
  DoubleAccessorStatic(reflection.findStaticField(this, name).also {
    if (it.type != Double::class.asType())
      throw IllegalArgumentException("field $it type is not double")
  })
fun accessStaticBoolean(property: KProperty<Boolean>) =
  property.javaField?.let { BooleanAccessorStatic(it) }
  ?: throw IllegalArgumentException("this property don't have a java field.")
fun KClass<*>.accessBoolean(name: String) =
  BooleanAccessorStatic(reflection.findStaticField(this, name).also {
    if (it.type != Boolean::class.asType())
      throw IllegalArgumentException("field $it type is not boolean")
  })
fun accessStaticChar(property: KProperty<Char>) =
  property.javaField?.let { CharAccessorStatic(it) }
  ?: throw IllegalArgumentException("this property don't have a java field.")
fun KClass<*>.accessChar(name: String) =
  CharAccessorStatic(reflection.findStaticField(this, name).also {
    if (it.type != Char::class.asType())
      throw IllegalArgumentException("field $it type is not boolean")
  })

//==========
//  Method
//==========
// member
inline fun <reified O : Any, reified R> accessMethod0(name: String) =
  MethodInvoker0<O, R>(O::class.asType().getDeclaredMethod(name).also {
    checkReturnType(it, R::class.asType())
  })
inline fun <reified O : Any, reified P1, reified R> accessMethod1(name: String) =
  MethodInvoker1<O, P1, R>(
    reflection.findMethod(
      O::class, name,
      P1::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified R> accessMethod2(name: String) =
  MethodInvoker2<O, P1, P2, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified R> accessMethod3(name: String) =
  MethodInvoker3<O, P1, P2, P3, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified R> accessMethod4(name: String) =
  MethodInvoker4<O, P1, P2, P3, P4, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified R> accessMethod5(name: String) =
  MethodInvoker5<O, P1, P2, P3, P4, P5, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified R> accessMethod6(
  name: String
) =
  MethodInvoker6<O, P1, P2, P3, P4, P5, P6, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified R> accessMethod7(
  name: String
) =
  MethodInvoker7<O, P1, P2, P3, P4, P5, P6, P7, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified R> accessMethod8(
  name: String
) =
  MethodInvoker8<O, P1, P2, P3, P4, P5, P6, P7, P8, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified R> accessMethod9(
  name: String
) =
  MethodInvoker9<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified R> accessMethod10(
  name: String
) =
  MethodInvoker10<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified R> accessMethod11(
  name: String
) =
  MethodInvoker11<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified R> accessMethod12(
  name: String
) =
  MethodInvoker12<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified R> accessMethod13(
  name: String
) =
  MethodInvoker13<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified R> accessMethod14(
  name: String
) =
  MethodInvoker14<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified R> accessMethod15(
  name: String
) =
  MethodInvoker15<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified R> accessMethod16(
  name: String
) =
  MethodInvoker16<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified R> accessMethod17(
  name: String
) =
  MethodInvoker17<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18, reified R> accessMethod18(
  name: String
) =
  MethodInvoker18<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class, P18::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18, reified P19, reified R> accessMethod19(
  name: String
) =
  MethodInvoker19<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class, P18::class, P19::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <reified O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18, reified P19, reified P20, reified R> accessMethod20(
  name: String
) =
  MethodInvoker20<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R>(
    reflection.findMethod(
      O::class, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class, P18::class, P19::class, P20::class
    ).also {
      checkReturnType(it, R::class.asType())
    })

// static
inline fun <C : Any, reified R> KClass<C>.accessMethod0(name: String) =
  StaticInvoker0<R>(
    reflection.findMethod(
      this, name,
      ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified R> KClass<C>.accessMethod1(name: String) =
  StaticInvoker1<P1, R>(
    reflection.findMethod(
      this, name,
      P1::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified R> KClass<C>.accessMethod2(name: String) =
  StaticInvoker2<P1, P2, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified R> KClass<C>.accessMethod3(name: String) =
  StaticInvoker3<P1, P2, P3, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified R> KClass<C>.accessMethod4(name: String) =
  StaticInvoker4<P1, P2, P3, P4, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified R> KClass<C>.accessMethod5(
  name: String
) =
  StaticInvoker5<P1, P2, P3, P4, P5, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified R> KClass<C>.accessMethod6(
  name: String
) =
  StaticInvoker6<P1, P2, P3, P4, P5, P6, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified R> KClass<C>.accessMethod7(
  name: String
) =
  StaticInvoker7<P1, P2, P3, P4, P5, P6, P7, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified R> KClass<C>.accessMethod8(
  name: String
) =
  StaticInvoker8<P1, P2, P3, P4, P5, P6, P7, P8, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified R> KClass<C>.accessMethod9(
  name: String
) =
  StaticInvoker9<P1, P2, P3, P4, P5, P6, P7, P8, P9, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified R> KClass<C>.accessMethod10(
  name: String
) =
  StaticInvoker10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified R> KClass<C>.accessMethod11(
  name: String
) =
  StaticInvoker11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified R> KClass<C>.accessMethod12(
  name: String
) =
  StaticInvoker12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified R> KClass<C>.accessMethod13(
  name: String
) =
  StaticInvoker13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified R> KClass<C>.accessMethod14(
  name: String
) =
  StaticInvoker14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified R> KClass<C>.accessMethod15(
  name: String
) =
  StaticInvoker15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified R> KClass<C>.accessMethod16(
  name: String
) =
  StaticInvoker16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified R> KClass<C>.accessMethod17(
  name: String
) =
  StaticInvoker17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18, reified R> KClass<C>.accessMethod18(
  name: String
) =
  StaticInvoker18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class, P18::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18, reified P19, reified R> KClass<C>.accessMethod19(
  name: String
) =
  StaticInvoker19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class, P18::class, P19::class
    ).also {
      checkReturnType(it, R::class.asType())
    })
inline fun <C : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18, reified P19, reified P20, reified R> KClass<C>.accessMethod20(
  name: String
) =
  StaticInvoker20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R>(
    reflection.findMethod(
      this, name,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class, P18::class, P19::class, P20::class
    ).also {
      checkReturnType(it, R::class.asType())
    })

//===============
//  Constructor
//===============
inline fun <O : Any, reified P1> KClass<O>.accessConstructor1() =
  ConstructorInvoker1<P1, O>(reflection.findConstructor(
      this,P1::class))
inline fun <O : Any, reified P1, reified P2> KClass<O>.accessConstructor2() =
  ConstructorInvoker2<P1, P2, O>(reflection.findConstructor(
      this,P1::class, P2::class))
inline fun <O : Any, reified P1, reified P2, reified P3> KClass<O>.accessConstructor3() =
  ConstructorInvoker3<P1, P2, P3, O>(reflection.findConstructor(
      this,P1::class, P2::class, P3::class))
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4> KClass<O>.accessConstructor4() =
  ConstructorInvoker4<P1, P2, P3, P4, O>(reflection.findConstructor(
      this,P1::class, P2::class, P3::class, P4::class))
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5> KClass<O>.accessConstructor5() =
  ConstructorInvoker5<P1, P2, P3, P4, P5, O>(reflection.findConstructor(
      this,P1::class, P2::class, P3::class, P4::class, P5::class))
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6> KClass<O>.accessConstructor6() =
  ConstructorInvoker6<P1, P2, P3, P4, P5, P6, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7> KClass<O>.accessConstructor7() =
  ConstructorInvoker7<P1, P2, P3, P4, P5, P6, P7, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8> KClass<O>.accessConstructor8() =
  ConstructorInvoker8<P1, P2, P3, P4, P5, P6, P7, P8, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9> KClass<O>.accessConstructor9() =
  ConstructorInvoker9<P1, P2, P3, P4, P5, P6, P7, P8, P9, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10> KClass<O>.accessConstructor10() =
  ConstructorInvoker10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11> KClass<O>.accessConstructor11() =
  ConstructorInvoker11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12> KClass<O>.accessConstructor12() =
  ConstructorInvoker12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13> KClass<O>.accessConstructor13() =
  ConstructorInvoker13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14> KClass<O>.accessConstructor14() =
  ConstructorInvoker14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15> KClass<O>.accessConstructor15() =
  ConstructorInvoker15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16> KClass<O>.accessConstructor16() =
  ConstructorInvoker16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17> KClass<O>.accessConstructor17() =
  ConstructorInvoker17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18> KClass<O>.accessConstructor18() =
  ConstructorInvoker18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class, P18::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18, reified P19> KClass<O>.accessConstructor19() =
  ConstructorInvoker19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class, P18::class, P19::class
    )
  )
inline fun <O : Any, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18, reified P19, reified P20> KClass<O>.accessConstructor20() =
  ConstructorInvoker20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, O>(
    reflection.findConstructor(
      this,
      P1::class, P2::class, P3::class, P4::class, P5::class,
      P6::class, P7::class, P8::class, P9::class, P10::class,
      P11::class, P12::class, P13::class, P14::class, P15::class,
      P16::class, P17::class, P18::class, P19::class, P20::class
    )
  )

fun checkReturnType(met: Method, retType: Class<*>) {
  if (
    !(retType.isAssignableFrom(met.returnType) || (met.returnType == Void.TYPE && retType == Unit::class.asType()))
    || (met.returnType == Unit::class.asType() && retType == Void.TYPE)
  ) throw IllegalArgumentException("method returned type ${met.returnType} is not instance of $retType")
}