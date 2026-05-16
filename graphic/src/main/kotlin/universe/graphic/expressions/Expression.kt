package universe.graphic.expressions

import kotlin.math.*

abstract class Expression {
  abstract fun diff(variable: Variable): Expression
  open fun simplify(): Expression = this
  abstract override fun toString(): String
}

operator fun Expression.plus(expression: Expression): Expression = Plus(this, expression).simplify()
operator fun Expression.minus(expression: Expression): Expression = Minus(this, expression).simplify()
operator fun Expression.times(variable: Expression): Expression = Times(this, variable).simplify()
operator fun Expression.div(variable: Expression): Expression = Division(this, variable).simplify()

operator fun Expression.unaryMinus(): Expression = UnaryMinus(this)

fun const(value: Double) = Constant(value.toFloat())
fun const(value: Float) = Constant(value)
fun const(value: Int) = Constant(value.toFloat())

fun min(exp: Expression, value: Expression) = Min(exp, value).simplify()
fun max(exp: Expression, value: Expression) = Max(exp, value).simplify()
fun clamp(exp: Expression, min: Expression, max: Expression) = Clamp(exp, min, max).simplify()

fun vari(name: String) = Variable(name)

fun ln(expression: Expression) = Ln(expression).simplify()
fun exp(base: Expression) = Exp(base).simplify()

fun len(x: Expression, y: Expression) = Length(x, y).simplify()

fun sin(expression: Expression) = Sin(expression).simplify()
fun cos(expression: Expression) = Cos(expression).simplify()
fun tan(expression: Expression) = Tan(expression).simplify()

fun asin(expression: Expression) = ArcSin(expression).simplify()
fun acos(expression: Expression) = ArcCos(expression).simplify()
fun atan(expression: Expression) = ArcTan(expression).simplify()

fun pow(base: Expression, exponent: Constant) = Power(base, exponent).simplify()
fun sqrt(base: Expression) = Sqrt(base).simplify()
fun exp(base: Constant, exponent: Expression) = Exponential(base, exponent).simplify()

fun vec2(v1: Expression, v2: Expression) = Vec2(v1, v2).simplify()
fun vec3(v1: Expression, v2: Expression, v3: Expression) = Vec3(v1, v2, v3).simplify()
fun vec4(v1: Expression, v2: Expression, v3: Expression, v4: Expression) = Vec4(v1, v2, v3, v4).simplify()

class Constant(
  val value: Float
) : Expression() {
  override fun diff(variable: Variable): Expression = const(0.0)

  override fun toString(): String = "$value"
}

open class Variable(
  val name: String
) : Expression() {
  override fun diff(variable: Variable): Expression =
    if (name == variable.name) const(1.0) else const(0.0)

  override fun toString(): String = name
}

class Vec2(
  val v1: Expression,
  val v2: Expression
) : Expression() {
  override fun diff(variable: Variable): Expression =
    vec2(v1.diff(variable), v2.diff(variable))

  override fun simplify(): Expression = Vec2(v1.simplify(), v2.simplify())

  override fun toString(): String = if (v1 == v2) "vec2($v1)" else "vec2($v1, $v2)"
}

class Vec3(
  val v1: Expression,
  val v2: Expression,
  val v3: Expression
) : Expression() {
  override fun diff(variable: Variable): Expression =
    vec3(v1.diff(variable), v2.diff(variable), v3.diff(variable))

  override fun simplify(): Expression = Vec3(v1.simplify(), v2.simplify(), v3.simplify())

  override fun toString(): String = if (v1 == v2) "vec3($v1)" else "vec3($v1, $v2, $v3)"
}

class Vec4(
  val v1: Expression,
  val v2: Expression,
  val v3: Expression,
  val v4: Expression
) : Expression() {
  override fun diff(variable: Variable): Expression =
    vec4(v1.diff(variable), v2.diff(variable), v3.diff(variable), v4.diff(variable))

  override fun simplify(): Expression = Vec4(v1.simplify(), v2.simplify(), v3.simplify(), v4.simplify())

  override fun toString(): String = if (v1 == v2) "vec4($v1)" else "vec4($v1, $v2, $v3, $v4)"
}

class Plus(
  val expLeft: Expression,
  val expRight: Expression,
): Expression() {
  override fun diff(variable: Variable) =
    expLeft.diff(variable) + expRight.diff(variable)

  override fun simplify(): Expression {
    val l = expLeft.simplify()
    val r = expRight.simplify()
    return when {
      l is Constant && l.value == 0f -> r
      r is Constant && r.value == 0f -> l
      l is Constant && r is Constant -> const(l.value + r.value)
      l is UnaryMinus && r is UnaryMinus -> -(l.exp + r.exp)
      l is UnaryMinus -> r - l.exp
      r is UnaryMinus -> l - r.exp
      l is Variable && r is Variable && l.name == r.name -> const(2.0)*l
      l is Variable && r is Times && r.expLeft is Constant && r.expRight is Variable && r.expRight.name == l.name ->
        const(1.0 + r.expLeft.value)*l
      l is Variable && r is Times && r.expRight is Constant && r.expLeft is Variable && r.expLeft.name == l.name ->
        const(1.0 + r.expRight.value)*l
      r is Variable && l is Times && l.expLeft is Constant && l.expRight is Variable && l.expRight.name == r.name ->
        const(1.0 + l.expLeft.value)*r
      r is Variable && l is Times && l.expRight is Constant && l.expLeft is Variable && l.expLeft.name == r.name ->
        const(1.0 + l.expRight.value)*r
      else -> this
    }
  }

  override fun toString(): String = "($expLeft + $expRight)"
}

class Minus(
  val expLeft: Expression,
  val expRight: Expression,
): Expression() {
  override fun diff(variable: Variable): Expression =
    expLeft.diff(variable) - expRight.diff(variable)

  override fun simplify(): Expression {
    val l = expLeft.simplify()
    val r = expRight.simplify()
    return when {
      l is Constant && l.value == 0f -> -r
      r is Constant && r.value == 0f -> l
      l is Constant && r is Constant -> const(l.value - r.value)
      l is UnaryMinus && r is UnaryMinus -> r.exp - l.exp
      l is UnaryMinus -> -(l.exp + r)
      r is UnaryMinus -> l + r.exp
      l is Variable && r is Variable && l.name == r.name -> const(0.0)
      l is Variable && r is Times && r.expLeft is Constant && r.expRight is Variable && r.expRight.name == l.name ->
        const(1.0 - r.expLeft.value)*l
      l is Variable && r is Times && r.expRight is Constant && r.expLeft is Variable && r.expLeft.name == l.name ->
        const(1.0 - r.expRight.value)*l
      r is Variable && l is Times && l.expLeft is Constant && l.expRight is Variable && l.expRight.name == r.name ->
        const(1.0 - l.expLeft.value)*r
      r is Variable && l is Times && l.expRight is Constant && l.expLeft is Variable && l.expLeft.name == r.name ->
        const(1.0 - l.expRight.value)*r
      else -> this
    }
  }

  override fun toString(): String = "($expLeft - $expRight)"
}

class Times(
  val expLeft: Expression,
  val expRight: Expression,
): Expression() {
  override fun diff(variable: Variable): Expression =
    expLeft*expRight.diff(variable) + expLeft.diff(variable)*expRight

  private fun flowTimes(childFlow: (Times) -> Unit) {
    childFlow(this)
    if (expLeft is Times) expLeft.flowTimes(childFlow)
    if (expRight is Times) expRight.flowTimes(childFlow)
  }

  override fun simplify(): Expression {
    val l = expLeft.simplify()
    val r = expRight.simplify()
    return when {
      (l is Constant && l.value == 0f) || (r is Constant && r.value == 0f) -> const(0f)
      l is Constant && l.value == 1f -> r
      r is Constant && r.value == 1f -> l
      l is Constant && r is Constant -> const(l.value * r.value)
      r is Constant && l is Times && l.expLeft is Constant ->
        Times(const(r.value*l.expLeft.value), l)
      l is Constant && r is Times && r.expLeft is Constant ->
        Times(const(l.value*r.expLeft.value), r.expRight)
      l is UnaryMinus && r is UnaryMinus -> l.exp * r.exp
      l is UnaryMinus -> -(l.exp * r)
      r is UnaryMinus -> -(l * r.exp)
      else -> this
    }
  }

  override fun toString(): String = "($expLeft*$expRight)"
}

class Division(
  val expLeft: Expression,
  val expRight: Expression,
): Expression() {
  override fun diff(variable: Variable): Expression =
    (expLeft.diff(variable)*expRight - expLeft*expRight.diff(variable))/(expRight*expRight)

  private fun flowDivision(childFlow: (Division) -> Unit) {
    childFlow(this)
    if (expLeft is Division) expLeft.flowDivision(childFlow)
    if (expRight is Division) expRight.flowDivision(childFlow)
  }

  override fun simplify(): Expression {
    val l = expLeft.simplify()
    val r = expRight.simplify()
    return when {
      l is Constant && l.value == 0f -> const(0.0)
      r is Constant && r.value == 1f -> l
      l is Constant && r is Constant -> const(l.value / r.value)
      l is Variable && r is Variable && l.name == r.name -> const(1.0)
      else -> this
    }
  }

  override fun toString(): String = "($expLeft/$expRight)"
}

class UnaryMinus(
  val exp: Expression,
): Expression() {
  override fun diff(variable: Variable): Expression =
    -exp.diff(variable)

  override fun simplify(): Expression {
    return when (val e = exp.simplify()) {
      is Constant -> const(-e.value)
      is UnaryMinus -> exp.simplify()
      else -> this
    }
  }

  override fun toString(): String = "(-$exp)"
}

class Sin(
  val exp: Expression
): Expression() {
  override fun diff(variable: Variable): Expression =
    cos(exp) * exp.diff(variable)

  override fun simplify(): Expression {
    return when (val e = exp.simplify()) {
      is Constant -> const(sin(e.value))
      else -> this
    }
  }

  override fun toString(): String = "sin($exp)"
}

class Cos(
  val exp: Expression
): Expression() {
  override fun diff(variable: Variable): Expression =
    -sin(exp) * exp.diff(variable)

  override fun simplify(): Expression {
    return when (val e = exp.simplify()) {
      is Constant -> const(sin(e.value))
      else -> this
    }
  }

  override fun toString(): String = "cos($exp)"
}

class Tan(
  val exp: Expression
): Expression() {
  override fun diff(variable: Variable): Expression =
    pow(const(1.0)/cos(exp), const(2.0))*exp.diff(variable)

  override fun simplify(): Expression {
    return when (val e = exp.simplify()) {
      is Constant -> const(tan(e.value))
      else -> this
    }
  }

  override fun toString(): String = "tan($exp)"
}

class ArcSin(
  val exp: Expression
): Expression() {
  override fun diff(variable: Variable): Expression =
    const(1.0)/sqrt(const(1.0) - pow(exp, const(2.0)))*exp.diff(variable)

  override fun simplify(): Expression {
    return when (val e = exp.simplify()) {
      is Constant -> const(asin(e.value))
      else -> this
    }
  }

  override fun toString(): String = "asin($exp)"
}

class ArcCos(
  val exp: Expression
): Expression() {
  override fun diff(variable: Variable): Expression =
    -const(1.0)/sqrt(const(1.0) - pow(exp, const(2.0)))*exp.diff(variable)

  override fun simplify(): Expression {
    return when (val e = exp.simplify()) {
      is Constant -> const(asin(e.value))
      else -> this
    }
  }

  override fun toString(): String = "acos($exp)"
}

class ArcTan(
  val exp: Expression
): Expression() {
  override fun diff(variable: Variable): Expression =
    const(1.0)/(const(1.0) + pow(exp, const(2.0)))*exp.diff(variable)

  override fun simplify(): Expression {
    return when (val e = exp.simplify()) {
      is Constant -> const(atan(e.value))
      else -> this
    }
  }

  override fun toString(): String = "atan($exp)"
}

class Ln(
  val exp: Expression
): Expression() {
  override fun diff(variable: Variable): Expression =
    exp.diff(variable)/variable

  override fun simplify(): Expression {
    return when (val e = exp.simplify()) {
      is Constant if e.value == 1f -> const(0.0)
      is Constant if e.value == 0f -> const(Double.POSITIVE_INFINITY)
      else -> this
    }
  }

  override fun toString(): String = "log($exp)"
}

class Power(
  val expBase: Expression,
  val exponent: Constant,
): Expression() {
  override fun diff(variable: Variable): Expression =
    exponent*(pow(expBase, const(exponent.value - 1.0)))*expBase.diff(variable)

  override fun simplify(): Expression {
    val b = expBase.simplify()
    val e = exponent
    return when {
      b is Constant -> const(b.value.pow(e.value))
      e.value == 0.5f -> sqrt(expBase)
      e.value == 0f -> const(1.0)
      e.value == 1f -> b
      else -> this
    }
  }

  override fun toString(): String = "pow($expBase, $exponent)"
}

class Sqrt(
  val expBase: Expression,
): Expression() {
  override fun diff(variable: Variable): Expression =
    const(0.5)/sqrt(expBase)*expBase.diff(variable)

  override fun simplify(): Expression {
    return when (val b = expBase.simplify()) {
      is Constant -> const(sqrt(b.value))
      else -> this
    }
  }

  override fun toString(): String = "sqrt($expBase)"
}

class Exponential(
  val base: Constant,
  val exp: Expression
): Expression(){
  override fun diff(variable: Variable): Expression =
    exp(base, exp)*ln(base)*exp.diff(variable)

  override fun simplify(): Expression {
    val e = exp.simplify()
    val eb = base
    return when {
      e is Constant -> const(eb.value.pow(e.value))
      eb.value == 0f -> const(0.0)
      eb.value == 1f -> const(1.0)
      else -> this
    }
  }

  override fun toString(): String = "exponential($base, $exp)"
}

class Exp(
  val exp: Expression
): Expression() {
  override fun diff(variable: Variable): Expression =
    exp(exp)*exp.diff(variable)

  override fun simplify(): Expression {
    return when (val e = exp.simplify()) {
      is Constant -> const(exp(e.value))
      else -> this
    }
  }

  override fun toString(): String = "exp($exp)"
}

// Special
class Length(
  val expX: Expression,
  val expY: Expression
): Expression() {
  private val composed = sqrt(expX*expX + expY*expY)

  override fun diff(variable: Variable): Expression = composed.diff(variable)

  override fun simplify(): Expression {
    return when{
      expX is Constant && expY is Constant -> const(sqrt(expX.value.pow(2f) + expY.value.pow(2f)))
      else -> this
    }
  }

  override fun toString(): String = "length(vec2($expX, $expY))"
}

// non-continuous
class Abs(
  val exp: Expression
): Expression() {
  override fun diff(variable: Variable): Expression = const(1.0)
  override fun toString(): String = "abs($exp)"
}

class Max(
  val exp: Expression,
  val value: Expression
): Expression() {
  override fun diff(variable: Variable): Expression = exp.diff(variable)
  override fun toString(): String = "max($value, $exp)"
}

class Min(
  val exp: Expression,
  val value: Expression
): Expression() {
  override fun diff(variable: Variable): Expression = exp.diff(variable)
  override fun toString(): String = "min($value, $exp)"
}

class Clamp(
  val exp: Expression,
  val min: Expression,
  val max: Expression
): Expression() {
  override fun diff(variable: Variable): Expression = exp.diff(variable)
  override fun toString(): String = "clamp($exp, $min, $max)"
}
