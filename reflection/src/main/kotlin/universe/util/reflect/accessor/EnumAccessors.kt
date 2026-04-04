package universe.util.reflect.accessor

import universe.UniverseActual
import universe.util.reflect.accessInt
import kotlin.enums.EnumEntries
import kotlin.reflect.KClass

abstract class EnumAccessorBase<E: Enum<E>>(
  internal val enumClass: KClass<E>
){
  companion object{
    private val enumsClazz = try {
      Class.forName("kotlin.enums.EnumEntriesKt")
    } catch (e: ClassNotFoundException) { null }
    private val enumsType = java.lang.reflect.Array.newInstance(Enum::class.java, 0)::class.java
  }

  private val createEntriesMethod = enumsClazz?.let {
    StaticInvoker1<Array<E>, EnumEntries<E>>(
      it.getDeclaredMethod(
        "enumEntries",
        enumsType
      )
    )
  }

  internal val valuesAccessor = enumClass.java.getDeclaredField($$"$VALUES").let {
    UniverseActual.reflection.makeAccessible(it)
    UniverseActual.reflection.makeMutable(it)
    UniverseActual.reflection.obtainFieldAccessor(it)
  }
  internal val entriesAccessor = try {
    enumClass.java.getDeclaredField($$"$ENTRIES").let {
      UniverseActual.reflection.makeAccessible(it)
      UniverseActual.reflection.makeMutable(it)
      UniverseActual.reflection.obtainFieldAccessor(it)
    }
  } catch (e: NoSuchFieldException) {
    null
  }

  internal var Enum<*>.ordinalModify: Int by accessInt("ordinal")

  @Suppress("UNCHECKED_CAST")
  internal fun insertEnumItem(e: E){
    val list = (valuesAccessor.getStatic() as Array<E>).toMutableList()
    list.add(e.ordinal, e)
    list.forEachIndexed { index, value -> value.ordinalModify = index }

    val entries = (list as java.util.Collection<E>).toArray(
      java.lang.reflect.Array.newInstance(
        enumClass.java, 0
      ) as Array<E>
    )

    valuesAccessor.setStatic(entries)
    entriesAccessor?.also { it.setStatic(createEntriesMethod!!.invoke(entries)) }
  }

  internal fun enumSize() = enumClass.java.enumConstants.size
}

class EnumAccessor0<E : Enum<E>>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker2<String, Int, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), isolated)

  fun newEnumInstance(name: String, ordinal: Int, isolated: Boolean = false) =
    constructor.invoke(name, ordinal)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor1<E : Enum<E>, P1>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker3<String, Int, P1, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor2<E : Enum<E>, P1, P2>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker4<String, Int, P1, P2, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor3<E : Enum<E>, P1, P2, P3>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker5<String, Int, P1, P2, P3, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor4<E : Enum<E>, P1, P2, P3, P4>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker6<String, Int, P1, P2, P3, P4, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor5<E : Enum<E>, P1, P2, P3, P4, P5>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker7<String, Int, P1, P2, P3, P4, P5, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor6<E : Enum<E>, P1, P2, P3, P4, P5, P6>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker8<String, Int, P1, P2, P3, P4, P5, P6, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor7<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker9<String, Int, P1, P2, P3, P4, P5, P6, P7, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor8<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker10<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor9<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker11<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor10<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker12<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor11<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker13<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor12<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker14<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor13<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker15<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor14<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker16<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor15<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker17<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor16<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker18<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor17<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker19<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}

class EnumAccessor18<E : Enum<E>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18>(
  clazz: KClass<E>,
  private val constructor: ConstructorInvoker20<String, Int, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, E>
) : EnumAccessorBase<E>(clazz) {
  fun appendEnumInstance(name: String, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, isolated: Boolean = false) =
    newEnumInstance(name, enumSize(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, isolated)

  fun newEnumInstance(name: String, ordinal: Int, p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, isolated: Boolean = false) =
    constructor.invoke(name, ordinal, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18)
      .also { enum -> if (!isolated) insertEnumItem(enum) }
}