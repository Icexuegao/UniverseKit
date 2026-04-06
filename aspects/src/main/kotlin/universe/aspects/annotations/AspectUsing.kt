package universe.aspects.annotations

import universe.aspects.Using

@Retention(AnnotationRetention.RUNTIME)
@Target(
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER,
)
annotation class AspectUsing(
  val using: Using = Using.OVERRIDE,
)
