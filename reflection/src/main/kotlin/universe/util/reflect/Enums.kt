package universe.util.reflect

import universe.util.reflect.accessor.*
import kotlin.reflect.KClass

fun <E : Enum<E>> KClass<E>.accessEnum0(): EnumAccessor0<E> {
  val constructor = accessConstructor2<E, String, Int>()
  return EnumAccessor0(this, constructor)
}
inline fun <E : Enum<E>, reified P1> KClass<E>.accessEnum1(): EnumAccessor1<E, P1> {
  val constructor = accessConstructor3<E, String, Int, P1>()
  return EnumAccessor1(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2> KClass<E>.accessEnum2(): EnumAccessor2<E, P1, P2> {
  val constructor = accessConstructor4<E, String, Int, P1, P2>()
  return EnumAccessor2(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3> KClass<E>.accessEnum3(): EnumAccessor3<E, P1, P2, P3> {
  val constructor = accessConstructor5<E, String, Int, P1, P2, P3>()
  return EnumAccessor3(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4> KClass<E>.accessEnum4(): EnumAccessor4<E, P1, P2, P3, P4> {
  val constructor = accessConstructor6<E, String, Int, P1, P2, P3, P4>()
  return EnumAccessor4(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5> KClass<E>.accessEnum5(): EnumAccessor5<E, P1, P2, P3, P4, P5> {
  val constructor = accessConstructor7<E, String, Int, P1, P2, P3, P4, P5>()
  return EnumAccessor5(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6> KClass<E>.accessEnum6(): EnumAccessor6<E, P1, P2, P3, P4, P5, P6> {
  val constructor = accessConstructor8<E, String, Int, P1, P2, P3, P4, P5, P6>()
  return EnumAccessor6(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7> KClass<E>.accessEnum7(): EnumAccessor7<E, P1, P2, P3, P4, P5, P6, P7> {
  val constructor = accessConstructor9<E, String, Int, P1, P2, P3, P4, P5, P6, P7>()
  return EnumAccessor7(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8> KClass<E>.accessEnum8(): EnumAccessor8<E, P1, P2, P3, P4, P5, P6, P7, P8> {
  val constructor = accessConstructor10<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8>()
  return EnumAccessor8(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9> KClass<E>.accessEnum9(): EnumAccessor9<E, P1, P2, P3, P4, P5, P6, P7, P8, P9> {
  val constructor = accessConstructor11<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9>()
  return EnumAccessor9(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10> KClass<E>.accessEnum10(): EnumAccessor10<E, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> {
  val constructor = accessConstructor12<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>()
  return EnumAccessor10(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11> KClass<E>.accessEnum11(): EnumAccessor11<E, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> {
  val constructor = accessConstructor13<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11>()
  return EnumAccessor11(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12> KClass<E>.accessEnum12(): EnumAccessor12<E, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> {
  val constructor = accessConstructor14<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>()
  return EnumAccessor12(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13> KClass<E>.accessEnum13(): EnumAccessor13<E, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
  val constructor = accessConstructor15<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>()
  return EnumAccessor13(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14> KClass<E>.accessEnum14(): EnumAccessor14<E, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> {
  val constructor = accessConstructor16<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14>()
  return EnumAccessor14(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15> KClass<E>.accessEnum15(): EnumAccessor15<E, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> {
  val constructor = accessConstructor17<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15>()
  return EnumAccessor15(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16> KClass<E>.accessEnum16(): EnumAccessor16<E, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> {
  val constructor = accessConstructor18<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16>()
  return EnumAccessor16(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17> KClass<E>.accessEnum17(): EnumAccessor17<E, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17> {
  val constructor = accessConstructor19<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17>()
  return EnumAccessor17(this, constructor)
}
inline fun <E : Enum<E>, reified P1, reified P2, reified P3, reified P4, reified P5, reified P6, reified P7, reified P8, reified P9, reified P10, reified P11, reified P12, reified P13, reified P14, reified P15, reified P16, reified P17, reified P18> KClass<E>.accessEnum18(): EnumAccessor18<E, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18> {
  val constructor = accessConstructor20<E, String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18>()
  return EnumAccessor18(this, constructor)
}
