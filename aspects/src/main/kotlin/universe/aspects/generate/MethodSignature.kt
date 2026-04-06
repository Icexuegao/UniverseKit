package universe.aspects.generate

import kotlin.reflect.KClass

data class MethodSignature(
  val methodName: String,
  val paramTypes: List<KClass<*>>,
){

}