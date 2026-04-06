package universe.shared

import java.lang.reflect.Field
import java.util.*

abstract class SharedObject {
  protected abstract fun sharedID(): String

  protected open fun sharedReferenceFields(): List<Field> = this::class.java.declaredFields
    .filter { it.getAnnotation(SharedField::class.java) != null }

  protected open fun sharedDataSwapProp(): Properties = System.getProperties()

  protected fun setupSharedReferences(){
    val properties = sharedDataSwapProp()
    val className = properties["shared-${sharedID()}"]

    if (className == null) {
      setupProperties(properties)
    }
    else {
      getByProperties(properties)
    }
  }

  protected open fun setupProperties(properties: Properties){
    val sharedFields = sharedReferenceFields()
    properties["shared-${sharedID()}"] = this
    properties["shared-${sharedID()}-fields"] = sharedFields
  }

  @Suppress("UNCHECKED_CAST")
  protected open fun getByProperties(properties: Properties){
    val existingSharedObject = properties["shared-${sharedID()}"]!!
    val sharedFields = properties["shared-${sharedID()}-fields"] as List<Field>

    val fieldMap = sharedFields.associateBy { field ->
      field.isAccessible = true
      field.name
    }
    sharedReferenceFields()
      .forEach { field ->
        val existing = fieldMap[field.name]?:
          throw IllegalArgumentException("Field ${field.name} not found in existed shared object.")
        field.isAccessible = true

        field.set(this, existing.get(existingSharedObject))
      }
  }

  annotation class SharedField
}