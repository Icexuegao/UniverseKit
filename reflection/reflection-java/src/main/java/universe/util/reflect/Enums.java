package universe.util.reflect;

import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import universe.util.reflect.accessor.*;

public class Enums {
  private static <T> KClass<T> k(Class<T> clazz){
    return JvmClassMappingKt.getKotlinClass(clazz);
  }
  public static <T extends Enum<T>> EnumAccessor0<T> accessEnum(
      Class<T> enumClass
  ){
    ConstructorInvoker2<String, Integer, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class
    );
    return new EnumAccessor0<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1> EnumAccessor1<T, P1> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class
  ){
    ConstructorInvoker3<String, Integer, P1, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class
    );
    return new EnumAccessor1<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2> EnumAccessor2<T, P1, P2> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class
  ){
    ConstructorInvoker4<String, Integer, P1, P2, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class
    );
    return new EnumAccessor2<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3> EnumAccessor3<T, P1, P2, P3> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class
  ) {
    ConstructorInvoker5<String, Integer, P1, P2, P3, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class
    );
    return new EnumAccessor3<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4> EnumAccessor4<T, P1, P2, P3, P4> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class
  ) {
    ConstructorInvoker6<String, Integer, P1, P2, P3, P4, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class
    );
    return new EnumAccessor4<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5> EnumAccessor5<T, P1, P2, P3, P4, P5> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class
  ) {
    ConstructorInvoker7<String, Integer, P1, P2, P3, P4, P5, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class
    );
    return new EnumAccessor5<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6> EnumAccessor6<T, P1, P2, P3, P4, P5, P6> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class
  ) {
    ConstructorInvoker8<String, Integer, P1, P2, P3, P4, P5, P6, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class
    );
    return new EnumAccessor6<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7> EnumAccessor7<T, P1, P2, P3, P4, P5, P6, P7> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class
  ) {
    ConstructorInvoker9<String, Integer, P1, P2, P3, P4, P5, P6, P7, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class
    );
    return new EnumAccessor7<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8> EnumAccessor8<T, P1, P2, P3, P4, P5, P6, P7, P8> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class
  ) {
    ConstructorInvoker10<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class
    );
    return new EnumAccessor8<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9> EnumAccessor9<T, P1, P2, P3, P4, P5, P6, P7, P8, P9> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class
  ) {
    ConstructorInvoker11<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class
    );
    return new EnumAccessor9<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> EnumAccessor10<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class
  ) {
    ConstructorInvoker12<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class, p10Class
    );
    return new EnumAccessor10<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> EnumAccessor11<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class
  ) {
    ConstructorInvoker13<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class, p10Class,
        p11Class
    );
    return new EnumAccessor11<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> EnumAccessor12<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class
  ) {
    ConstructorInvoker14<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class, p10Class,
        p11Class, p12Class
    );
    return new EnumAccessor12<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> EnumAccessor13<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class
  ) {
    ConstructorInvoker15<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class, p10Class,
        p11Class, p12Class, p13Class
    );
    return new EnumAccessor13<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> EnumAccessor14<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class
  ) {
    ConstructorInvoker16<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class, p10Class,
        p11Class, p12Class, p13Class, p14Class
    );
    return new EnumAccessor14<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> EnumAccessor15<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class
  ) {
    ConstructorInvoker17<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class, p10Class,
        p11Class, p12Class, p13Class, p14Class, p15Class
    );
    return new EnumAccessor15<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> EnumAccessor16<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class
  ) {
    ConstructorInvoker18<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class, p10Class,
        p11Class, p12Class, p13Class, p14Class, p15Class,
        p16Class
    );
    return new EnumAccessor16<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17> EnumAccessor17<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class
  ) {
    ConstructorInvoker19<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class, p10Class,
        p11Class, p12Class, p13Class, p14Class, p15Class,
        p16Class, p17Class
    );
    return new EnumAccessor17<>(k(enumClass), cstr);
  }
  public static <T extends Enum<T>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18> EnumAccessor18<T, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18> accessEnum(
      Class<T> enumClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class
  ) {
    ConstructorInvoker20<String, Integer, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, T> cstr = Reflection.accessConstructor(
        enumClass, String.class, int.class,
        p1Class, p2Class, p3Class, p4Class, p5Class,
        p6Class, p7Class, p8Class, p9Class, p10Class,
        p11Class, p12Class, p13Class, p14Class, p15Class,
        p16Class, p17Class, p18Class
    );
    return new EnumAccessor18<>(k(enumClass), cstr);
  }
}
