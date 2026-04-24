import universe.graphic.expressions.*

fun main() {
  val c =
    const(2)*cos(ln(pow(Variable("x"), const(3.0))))

  val diffX = c.diff(Variable("x")).simplify()

  println(c)
  println(diffX)
}