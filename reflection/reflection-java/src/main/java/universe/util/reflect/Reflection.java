package universe.util.reflect;

import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import universe.UniverseActual;
import universe.util.reflect.accessor.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings("DuplicatedCode")
public class Reflection {
  private static <T> KClass<T> k(Class<T> clazz){
    return JvmClassMappingKt.getKotlinClass(clazz);
  }

  //=========
  //  Field
  //=========
  // member
  public static <O, T> FieldAccessor<O, T> accessField(
      Class<O> declaringClass,
      Class<T> fieldType,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (!field.getType().isAssignableFrom(fieldType))
      throw new IllegalArgumentException("field " + field + " type is not instance of " + fieldType);

    return new FieldAccessor<>(field);
  }
  public static <O> ByteAccessor<O> accessByte(
      Class<O> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != byte.class)
      throw new IllegalArgumentException("field " + field + " type is not byte");

    return new ByteAccessor<>(field);
  }
  public static <O> ShortAccessor<O> accessShort(
      Class<O> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != short.class)
      throw new IllegalArgumentException("field " + field + " type is not short");

    return new ShortAccessor<>(field);
  }
  public static <O> IntAccessor<O> accessInt(
      Class<O> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != int.class)
      throw new IllegalArgumentException("field " + field + " type is not int");

    return new IntAccessor<>(field);
  }
  public static <O> LongAccessor<O> accessLong(
      Class<O> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != long.class)
      throw new IllegalArgumentException("field " + field + " type is not long");

    return new LongAccessor<>(field);
  }
  public static <O> FloatAccessor<O> accessFloat(
      Class<O> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != float.class)
      throw new IllegalArgumentException("field " + field + " type is not float");

    return new FloatAccessor<>(field);
  }
  public static <O> DoubleAccessor<O> accessDouble(
      Class<O> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != double.class)
      throw new IllegalArgumentException("field " + field + " type is not double");

    return new DoubleAccessor<>(field);
  }
  public static <O> BooleanAccessor<O> accessBoolean(
      Class<O> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != boolean.class)
      throw new IllegalArgumentException("field " + field + " type is not boolean");

    return new BooleanAccessor<>(field);
  }
  public static <O> CharAccessor<O> accessChar(
      Class<O> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != char.class)
      throw new IllegalArgumentException("field " + field + " type is not char");

    return new CharAccessor<>(field);
  }

  // static
  public static <T> FieldAccessorStatic<T> accessStaticField(
      Class<?> declaringClass,
      Class<T> fieldType,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (!field.getType().isAssignableFrom(fieldType))
      throw new IllegalArgumentException("field " + field + " type is not instance of " + fieldType);

    return new FieldAccessorStatic<>(field);
  }
  public static ByteAccessorStatic accessStaticByte(
      Class<?> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != byte.class)
      throw new IllegalArgumentException("field " + field + " type is not byte");

    return new ByteAccessorStatic(field);
  }
  public static ShortAccessorStatic accessStaticShort(
      Class<?> declaringClass,
      String name
  ) {
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != short.class)
      throw new IllegalArgumentException("field " + field + " type is not short");

    return new ShortAccessorStatic(field);
  }
  public static IntAccessorStatic accessStaticInt(
      Class<?> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != int.class)
      throw new IllegalArgumentException("field " + field + " type is not int");

    return new IntAccessorStatic(field);
  }
  public static LongAccessorStatic accessStaticLong(
      Class<?> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != long.class)
      throw new IllegalArgumentException("field " + field + " type is not long");

    return new LongAccessorStatic(field);
  }
  public static FloatAccessorStatic accessStaticFloat(
      Class<?> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != float.class)
      throw new IllegalArgumentException("field " + field + " type is not float");

    return new FloatAccessorStatic(field);
  }
  public static DoubleAccessorStatic accessStaticDouble(
      Class<?> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != double.class)
      throw new IllegalArgumentException("field " + field + " type is not double");

    return new DoubleAccessorStatic(field);
  }
  public static BooleanAccessorStatic accessStaticBoolean(
      Class<?> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != boolean.class)
      throw new IllegalArgumentException("field " + field + " type is not boolean");

    return new BooleanAccessorStatic(field);
  }
  public static CharAccessorStatic accessStaticChar(
      Class<?> declaringClass,
      String name
  ){
    Field field = UniverseActual.getReflection().findField(k(declaringClass), name);
    if (field.getType() != char.class)
      throw new IllegalArgumentException("field " + field + " type is not char");

    return new CharAccessorStatic(field);
  }

  private static void checkReturnType(Method met, Class<?> retType) {
    if (
      !(retType.isAssignableFrom(met.getReturnType())
      || (met.getReturnType() == Unit.class && retType == void.class))
      || (met.getReturnType() == void.class && retType == Unit.class)
    ) throw new IllegalArgumentException("method returned type ${met.returnType} is not instance of $retType");
  }

  //==========
  //  Method
  //==========
  // member
  public static <O, R> MethodInvoker0<O, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name
    );
    checkReturnType(method, returnType);
    return new MethodInvoker0<>(method);
  }
  public static <O, P1, R> MethodInvoker1<O, P1, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker1<>(method);
  }
  public static <O, P1, P2, R> MethodInvoker2<O, P1, P2, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker2<>(method);
  }
  public static <O, P1, P2, P3, R> MethodInvoker3<O, P1, P2, P3, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker3<>(method);
  }
  public static <O, P1, P2, P3, P4, R> MethodInvoker4<O, P1, P2, P3, P4, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker4<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, R> MethodInvoker5<O, P1, P2, P3, P4, P5, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker5<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, R> MethodInvoker6<O, P1, P2, P3, P4, P5, P6, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker6<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, R> MethodInvoker7<O, P1, P2, P3, P4, P5, P6, P7, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class), k(p6Class), k(p7Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker7<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, R> MethodInvoker8<O, P1, P2, P3, P4, P5, P6, P7, P8, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class,
      Class<P5> p5Class, Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker8<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, R> MethodInvoker9<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker9<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R> MethodInvoker10<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker10<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R> MethodInvoker11<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker11<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R> MethodInvoker12<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker12<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R> MethodInvoker13<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker13<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R> MethodInvoker14<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker14<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R> MethodInvoker15<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker15<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R> MethodInvoker16<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker16<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R> MethodInvoker17<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker17<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R> MethodInvoker18<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class), k(p18Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker18<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R> MethodInvoker19<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class, Class<P19> p19Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class), k(p18Class), k(p19Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker19<>(method);
  }
  public static <O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R> MethodInvoker20<O, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R> accessMethod(
      Class<O> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class, Class<P19> p19Class, Class<P20> p20Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class), k(p18Class), k(p19Class), k(p20Class)
    );
    checkReturnType(method, returnType);
    return new MethodInvoker20<>(method);
  }

  // static
  public static <R> StaticInvoker0<R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name
    );
    checkReturnType(method, returnType);
    return new StaticInvoker0<>(method);
  }
  public static <P1, R> StaticInvoker1<P1, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker1<>(method);
  }
  public static <P1, P2, R> StaticInvoker2<P1, P2, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker2<>(method);
  }
  public static <P1, P2, P3, R> StaticInvoker3<P1, P2, P3, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker3<>(method);
  }
  public static <P1, P2, P3, P4, R> StaticInvoker4<P1, P2, P3, P4, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker4<>(method);
  }
  public static <P1, P2, P3, P4, P5, R> StaticInvoker5<P1, P2, P3, P4, P5, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker5<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, R> StaticInvoker6<P1, P2, P3, P4, P5, P6, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker6<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, R> StaticInvoker7<P1, P2, P3, P4, P5, P6, P7, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class), k(p6Class), k(p7Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker7<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, R> StaticInvoker8<P1, P2, P3, P4, P5, P6, P7, P8, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class,
      Class<P5> p5Class, Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker8<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, R> StaticInvoker9<P1, P2, P3, P4, P5, P6, P7, P8, P9, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker9<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R> StaticInvoker10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker10<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R> StaticInvoker11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker11<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R> StaticInvoker12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker12<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R> StaticInvoker13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker13<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R> StaticInvoker14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker14<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R> StaticInvoker15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker15<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R> StaticInvoker16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker16<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R> StaticInvoker17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker17<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R> StaticInvoker18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class), k(p18Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker18<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R> StaticInvoker19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class, Class<P19> p19Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class), k(p18Class), k(p19Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker19<>(method);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R> StaticInvoker20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R> accessStaticMethod(
      Class<?> declaringClass, String name, Class<R> returnType,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class, Class<P19> p19Class, Class<P20> p20Class
  ) {
    Method method = UniverseActual.getReflection().findMethod(
        k(declaringClass), name,
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class), k(p18Class), k(p19Class), k(p20Class)
    );
    checkReturnType(method, returnType);
    return new StaticInvoker20<>(method);
  }

  //===============
  //  Constructor
  //===============
  public static <O> ConstructorInvoker0<O> accessConstructor(
      Class<O> declaringClass
  ){
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass)
    );
    return new ConstructorInvoker0<>(constructor);
  }
  public static <P1, O> ConstructorInvoker1<P1, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class
  ){
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class)
    );
    return new ConstructorInvoker1<>(constructor);
  }
  public static <P1, P2, O> ConstructorInvoker2<P1, P2, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class
  ){
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class)
    );
    return new ConstructorInvoker2<>(constructor);
  }
  public static <P1, P2, P3, O> ConstructorInvoker3<P1, P2, P3, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class)
    );
    return new ConstructorInvoker3<>(constructor);
  }
  public static <P1, P2, P3, P4, O> ConstructorInvoker4<P1, P2, P3, P4, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class)
    );
    return new ConstructorInvoker4<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, O> ConstructorInvoker5<P1, P2, P3, P4, P5, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class)
    );
    return new ConstructorInvoker5<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, O> ConstructorInvoker6<P1, P2, P3, P4, P5, P6, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class)
    );
    return new ConstructorInvoker6<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, O> ConstructorInvoker7<P1, P2, P3, P4, P5, P6, P7, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class)
    );
    return new ConstructorInvoker7<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, O> ConstructorInvoker8<P1, P2, P3, P4, P5, P6, P7, P8, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class)
    );
    return new ConstructorInvoker8<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, O> ConstructorInvoker9<P1, P2, P3, P4, P5, P6, P7, P8, P9, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class)
    );
    return new ConstructorInvoker9<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, O> ConstructorInvoker10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class)
    );
    return new ConstructorInvoker10<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, O> ConstructorInvoker11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class)
    );
    return new ConstructorInvoker11<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, O> ConstructorInvoker12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class)
    );
    return new ConstructorInvoker12<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, O> ConstructorInvoker13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class)
    );
    return new ConstructorInvoker13<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, O> ConstructorInvoker14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class)
    );
    return new ConstructorInvoker14<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, O> ConstructorInvoker15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class)
    );
    return new ConstructorInvoker15<>(constructor);
  }

  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, O> ConstructorInvoker16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class)
    );
    return new ConstructorInvoker16<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, O> ConstructorInvoker17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class)
    );
    return new ConstructorInvoker17<>(constructor);
  }

  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, O> ConstructorInvoker18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class), k(p18Class)
    );
    return new ConstructorInvoker18<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, O> ConstructorInvoker19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class, Class<P19> p19Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class), k(p18Class), k(p19Class)
    );
    return new ConstructorInvoker19<>(constructor);
  }
  public static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, O> ConstructorInvoker20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, O> accessConstructor(
      Class<O> declaringClass,
      Class<P1> p1Class, Class<P2> p2Class, Class<P3> p3Class, Class<P4> p4Class, Class<P5> p5Class,
      Class<P6> p6Class, Class<P7> p7Class, Class<P8> p8Class, Class<P9> p9Class, Class<P10> p10Class,
      Class<P11> p11Class, Class<P12> p12Class, Class<P13> p13Class, Class<P14> p14Class, Class<P15> p15Class,
      Class<P16> p16Class, Class<P17> p17Class, Class<P18> p18Class, Class<P19> p19Class, Class<P20> p20Class
  ) {
    Constructor<O> constructor = UniverseActual.getReflection().findConstructor(
        k(declaringClass),
        k(p1Class), k(p2Class), k(p3Class), k(p4Class), k(p5Class),
        k(p6Class), k(p7Class), k(p8Class), k(p9Class), k(p10Class),
        k(p11Class), k(p12Class), k(p13Class), k(p14Class), k(p15Class),
        k(p16Class), k(p17Class), k(p18Class), k(p19Class), k(p20Class)
    );
    return new ConstructorInvoker20<>(constructor);
  }
}
