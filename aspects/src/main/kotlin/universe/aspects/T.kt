package universe.aspects

import kotlin.reflect.KType

@Test(T::class)
class T {
  fun foo(
    @Test(Any::class)
    arg1: Any,
    arg2: @Test(Any::class) Any,
  ) {
    Any::class.supertypes.forEach { t ->
      t.annotations
    }
  }
}