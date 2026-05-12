package universe.actuals.reflect

import universe.expects.reflect.ConstructorInvoker
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.lang.reflect.Constructor

@Suppress("UNCHECKED_CAST")
class Desktop9ConstructorInvoker<T>(constructor: Constructor<T>): ConstructorInvoker<T> {
  companion object {
    private val lookup = MethodHandles.lookup()
  }

  private val methodHandle = lookup.unreflectConstructor(constructor).asFixedArity()

  override fun newInstance() =
    methodHandle.invoke() as T
  override fun <P1> newInstance(p1: P1) =
    methodHandle.invoke(p1) as T
  override fun <P1, P2> newInstance(p1: P1, p2: P2) =
    methodHandle.invoke(p1, p2) as T
  override fun <P1, P2, P3> newInstance(p1: P1, p2: P2, p3: P3) =
    methodHandle.invoke(p1, p2, p3) as T
  override fun <P1, P2, P3, P4> newInstance(p1: P1, p2: P2, p3: P3, p4: P4) =
    methodHandle.invoke(p1, p2, p3, p4) as T
  override fun <P1, P2, P3, P4, P5> newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) =
    methodHandle.invoke(p1, p2, p3, p4, p5) as T
  override fun <P1, P2, P3, P4, P5, P6>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6) as T
  override fun <P1, P2, P3, P4, P5, P6, P7>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>
      newInstance(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17) =
    methodHandle.invoke(
      p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17
    ) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18) =
    methodHandle.invoke(
      p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17, p18
    ) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19) =
    methodHandle.invoke(
      p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17, p18, p19
    ) as T
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20> newInstance(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20) =
    methodHandle.invoke(
      p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17, p18, p19, p20
    ) as T
}