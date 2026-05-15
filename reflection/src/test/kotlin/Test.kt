import universe.util.reflect.accessByte
import universe.util.reflect.accessConstructor1
import universe.util.reflect.accessField
import universe.util.reflect.accessMethod3

class Test(
  vararg args: String,
) {
  fun sample(t: String, i: Int, vararg args: String) {
    print(args.joinToString(", "))
  }
}

val cstr: (Array<String>) -> Test = Test::class.accessConstructor1()
val sampleF: Test.(String, Int, Array<String>) -> Unit = accessMethod3("sample")

var ststicField: Array<String> by Test::class.accessField<Array<String>>("sjide")
var ststicFiel: Byte by Test::class.accessByte("sjide")

fun main() {
  val t = cstr(arrayOf("1", "2", "3"))

  t.sample("sd", 2, "a1", "a2", "a3")
  t.sample("sd", 2, *arrayOf("a1", "a2", "a3"))
  t.sampleF("sd", 2, arrayOf("a1", "a2", "a3"))
}
