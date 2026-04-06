package universe.aspects

import kotlin.reflect.KClass

class Aspector {
  fun <S: Any> makeInstance(sourceClass: KClass<S>, aspectImpl: KClass<*>): S {
    TODO()
  }

  fun <S: Any> makeAspectedClass(sourceClass: KClass<S>, aspectImpl: KClass<*>): KClass<S> {
    TODO()
  }
}