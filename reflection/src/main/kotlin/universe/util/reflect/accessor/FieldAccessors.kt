@file:Suppress("UNCHECKED_CAST")

package universe.util.reflect.accessor

import universe.UniverseActual
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import kotlin.reflect.KProperty

abstract class FieldAccessorBase(field: Field){
  init {
    UniverseActual.reflection.makeAccessible(field)
    if (Modifier.isFinal(field.modifiers)) {
      UniverseActual.reflection.makeMutable(field)
    }
  }

  internal val accessor = UniverseActual.reflection.obtainFieldAccessor(field)
}

private fun checkNotStatic(field: Field) {
  if (Modifier.isStatic(field.modifiers))
    throw IllegalArgumentException("Member field accessor must not be static")
}

// Field accessors
class FieldAccessor<O: Any, T: Any?>(field: Field): FieldAccessorBase(field) {
  init{ checkNotStatic(field) }
  fun get(instance: O): T = accessor.get(instance) as T
  fun set(instance: O, value: T) = accessor.set(instance, value) as T
  operator fun getValue(instance: O, property: KProperty<*>): T = accessor.get(instance) as T
  operator fun setValue(instance: O, property: KProperty<*>, value: T) = accessor.set(instance, value)
}
class ByteAccessor<O: Any>(field: Field): FieldAccessorBase(field) {
  init{ checkNotStatic(field) }
  fun get(instance: O): Byte = accessor.get(instance) as Byte
  fun set(instance: O, value: Byte) = accessor.set(instance, value)
  operator fun getValue(instance: O, property: KProperty<*>): Byte = accessor.getByte(instance)
  operator fun setValue(instance: O, property: KProperty<*>, value: Byte) = accessor.setByte(instance, value)
}
class ShortAccessor<O: Any>(field: Field): FieldAccessorBase(field) {
  init{ checkNotStatic(field) }
  fun get(instance: O): Short = accessor.get(instance) as Short
  fun set(instance: O, value: Short) = accessor.set(instance, value)
  operator fun getValue(instance: O, property: KProperty<*>): Short = accessor.getShort(instance)
  operator fun setValue(instance: O, property: KProperty<*>, value: Short) = accessor.setShort(instance, value)
}
class IntAccessor<O: Any>(field: Field): FieldAccessorBase(field) {
  init{ checkNotStatic(field) }
  fun get(instance: O): Int = accessor.get(instance) as Int
  fun set(instance: O, value: Int) = accessor.set(instance, value)
  operator fun getValue(instance: O, property: KProperty<*>): Int = accessor.getInt(instance)
  operator fun setValue(instance: O, property: KProperty<*>, value: Int) = accessor.setInt(instance, value)
}
class LongAccessor<O: Any>(field: Field): FieldAccessorBase(field) {
  init{ checkNotStatic(field) }
  fun get(instance: O): Long = accessor.get(instance) as Long
  fun set(instance: O, value: Long) = accessor.set(instance, value)
  operator fun getValue(instance: O, property: KProperty<*>): Long = accessor.getLong(instance)
  operator fun setValue(instance: O, property: KProperty<*>, value: Long) = accessor.setLong(instance, value)
}
class FloatAccessor<O: Any>(field: Field): FieldAccessorBase(field) {
  init{ checkNotStatic(field) }
  fun get(instance: O): Float = accessor.get(instance) as Float
  fun set(instance: O, value: Float) = accessor.set(instance, value)
  operator fun getValue(instance: O, property: KProperty<*>): Float = accessor.getFloat(instance)
  operator fun setValue(instance: O, property: KProperty<*>, value: Float) = accessor.setFloat(instance, value)
}
class DoubleAccessor<O: Any>(field: Field): FieldAccessorBase(field) {
  init{ checkNotStatic(field) }
  fun get(instance: O): Double = accessor.get(instance) as Double
  fun set(instance: O, value: Double) = accessor.set(instance, value)
  operator fun getValue(instance: O, property: KProperty<*>): Double = accessor.getDouble(instance)
  operator fun setValue(instance: O, property: KProperty<*>, value: Double) = accessor.setDouble(instance, value)
}
class BooleanAccessor<O: Any>(field: Field): FieldAccessorBase(field) {
  init{ checkNotStatic(field) }
  fun get(instance: O): Boolean = accessor.get(instance) as Boolean
  fun set(instance: O, value: Boolean) = accessor.set(instance, value)
  operator fun getValue(instance: O, property: KProperty<*>): Boolean = accessor.getBoolean(instance)
  operator fun setValue(instance: O, property: KProperty<*>, value: Boolean) = accessor.setBoolean(instance, value)
}
class CharAccessor<O: Any>(field: Field): FieldAccessorBase(field) {
  init{ checkNotStatic(field) }
  fun get(instance: O): Char = accessor.get(instance) as Char
  fun set(instance: O, value: Char) = accessor.set(instance, value)
  operator fun getValue(instance: O, property: KProperty<*>): Char = accessor.getChar(instance)
  operator fun setValue(instance: O, property: KProperty<*>, value: Char) = accessor.setChar(instance, value)
}

fun checkStatic(field: Field){
  if (!Modifier.isStatic(field.modifiers))
    throw IllegalArgumentException("Static field accessor must be static")
}

// static
class FieldAccessorStatic<T: Any?>(field: Field) : FieldAccessorBase(field) {
  init{ checkStatic(field) }
  fun get(): T = accessor.getStatic() as T
  fun set(instance: T) = accessor.setStatic(instance)
  operator fun getValue(instance: Nothing?, property: KProperty<*>): T = accessor.getStatic() as T
  operator fun setValue(instance: Nothing?, property: KProperty<*>, value: T) = accessor.setStatic(value)
}
class ByteAccessorStatic(field: Field): FieldAccessorBase(field) {
  init{ checkStatic(field) }
  fun get(): Byte = accessor.getStatic() as Byte
  fun set(instance: Byte) = accessor.setStatic(instance)
  operator fun getValue(instance: Nothing?, property: KProperty<*>): Byte = accessor.getStaticByte()
  operator fun setValue(instance: Nothing?, property: KProperty<*>, value: Byte) = accessor.setStaticByte(value)
}
class ShortAccessorStatic(field: Field): FieldAccessorBase(field) {
  init{ checkStatic(field) }
  fun get(): Short = accessor.getStatic() as Short
  fun set(instance: Short) = accessor.setStatic(instance)
  operator fun getValue(instance: Nothing?, property: KProperty<*>): Short = accessor.getStaticShort()
  operator fun setValue(instance: Nothing?, property: KProperty<*>, value: Short) = accessor.setStaticShort(value)
}
class IntAccessorStatic(field: Field): FieldAccessorBase(field) {
  init{ checkStatic(field) }
  fun get(): Int = accessor.getStatic() as Int
  fun set(instance: Int) = accessor.setStatic(instance)
  operator fun getValue(instance: Nothing?, property: KProperty<*>): Int = accessor.getStaticInt()
  operator fun setValue(instance: Nothing?, property: KProperty<*>, value: Int) = accessor.setStaticInt(value)
}
class LongAccessorStatic(field: Field): FieldAccessorBase(field) {
  init{ checkStatic(field) }
  fun get(): Long = accessor.getStatic() as Long
  fun set(instance: Long) = accessor.setStatic(instance)
  operator fun getValue(instance: Nothing?, property: KProperty<*>): Long = accessor.getStaticLong()
  operator fun setValue(instance: Nothing?, property: KProperty<*>, value: Long) = accessor.setStaticLong(value)
}
class FloatAccessorStatic(field: Field): FieldAccessorBase(field) {
  init{ checkStatic(field) }
  fun get(): Float = accessor.getStatic() as Float
  fun set(instance: Float) = accessor.setStatic(instance)
  operator fun getValue(instance: Nothing?, property: KProperty<*>): Float = accessor.getStaticFloat()
  operator fun setValue(instance: Nothing?, property: KProperty<*>, value: Float) = accessor.setStaticFloat(value)
}
class DoubleAccessorStatic(field: Field): FieldAccessorBase(field) {
  init{ checkStatic(field) }
  fun get(): Double = accessor.getStatic() as Double
  fun set(instance: Double) = accessor.setStatic(instance)
  operator fun getValue(instance: Nothing?, property: KProperty<*>): Double = accessor.getStaticDouble()
  operator fun setValue(instance: Nothing?, property: KProperty<*>, value: Double) = accessor.setStaticDouble(value)
}
class BooleanAccessorStatic(field: Field): FieldAccessorBase(field) {
  init{ checkStatic(field) }
  fun get(): Boolean = accessor.getStatic() as Boolean
  fun set(instance: Boolean) = accessor.setStatic(instance)
  operator fun getValue(instance: Nothing?, property: KProperty<*>): Boolean = accessor.getStaticBoolean()
  operator fun setValue(instance: Nothing?, property: KProperty<*>, value: Boolean) = accessor.setStaticBoolean(value)
}
class CharAccessorStatic(field: Field): FieldAccessorBase(field) {
  init{ checkStatic(field) }
  fun get(): Char = accessor.getStatic() as Char
  fun set(instance: Char) = accessor.setStatic(instance)
  operator fun getValue(instance: Nothing?, property: KProperty<*>): Char = accessor.getStaticChar()
  operator fun setValue(instance: Nothing?, property: KProperty<*>, value: Char) = accessor.setStaticChar(value)
}
