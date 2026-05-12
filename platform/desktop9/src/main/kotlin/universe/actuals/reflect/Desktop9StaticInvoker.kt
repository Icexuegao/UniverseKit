package universe.actuals.reflect

import universe.expects.reflect.MethodInvoker
import universe.expects.reflect.StaticInvoker
import java.lang.invoke.MethodHandles
import java.lang.reflect.Method

@Suppress("UNCHECKED_CAST")
class Desktop9StaticInvoker<R>(method: Method): StaticInvoker<R> {
  companion object {
    private val lookup = MethodHandles.lookup()
  }

  private val methodHandle = lookup.unreflect(method).asFixedArity()

  override fun invoke() =
    methodHandle.invoke() as R
  override fun <P1> invoke(p1: P1) =
    methodHandle.invoke(p1) as R
  override fun <P1, P2> invoke(p1: P1, p2: P2) =
    methodHandle.invoke(p1, p2) as R
  override fun <P1, P2, P3> invoke(p1: P1, p2: P2, p3: P3) =
    methodHandle.invoke(p1, p2, p3) as R
  override fun <P1, P2, P3, P4> invoke(p1: P1, p2: P2, p3: P3, p4: P4) =
    methodHandle.invoke(p1, p2, p3, p4) as R
  override fun <P1, P2, P3, P4, P5> invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) =
    methodHandle.invoke(p1, p2, p3, p4, p5) as R
  override fun <P1, P2, P3, P4, P5, P6>
      invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6) as R
  override fun <P1, P2, P3, P4, P5, P6, P7>
      invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8>
      invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9>
      invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>
      invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16) =
    methodHandle.invoke(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17) =
    methodHandle.invoke(
      p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17
    ) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18) =
    methodHandle.invoke(
      p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17, p18
    ) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19) =
    methodHandle.invoke(
      p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17, p18, p19
    ) as R
  override fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20> invoke(
    p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10,
    p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20) =
    methodHandle.invoke(
      p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
      p11, p12, p13, p14, p15, p16, p17, p18, p19, p20
    ) as R
}