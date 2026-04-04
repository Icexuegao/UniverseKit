import kotlin.reflect.KClass

interface Aspect1{
  @AspectElement(Insert.OVERRIDE) fun update()
  @AspectElement(Insert.AFTER) fun draw()
}

interface Aspect2{
  @AspectElement(Insert.AFTER) fun added(){
    println("added")
  }
  @AspectElement(Insert.BEFORE) fun removed(){
    println("removed")
  }
}

interface ObjectStub {
  fun update(){}
  fun draw(){}
}

class AspectImpl:
  @Stub ObjectStub,
  @Aspect Aspect1,
  @Aspect Aspect2
{
  override fun update() {
    super.update()
    println("update")
  }

  override fun draw() {
    super.draw()
    println("draw")
  }
}

fun foo(){
  val aspector = Aspector()
  val unit = aspector.makeInstance(Unit::class, AspectImpl::class)
  val build = aspector.makeInstance(Building::class, AspectImpl::class)

  val aspect1U = unit as Aspect1
  val aspect1B = build as Aspect1
  val aspect2U = unit as Aspect2
  val aspect2B = build as Aspect2
}

class Aspector{
  fun <T: Any> makeInstance(
    sourceClass: KClass<T>,
    aspectImplementation: KClass<*>,
  ): T {
    TODO()
  }
}

open class Unit()
open class Building()
@Target(AnnotationTarget.TYPE)
annotation class Stub()
@Target(AnnotationTarget.TYPE)
annotation class Aspect()
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class AspectElement(
  val insertion: Insert
)

enum class Insert{
  BEFORE,
  OVERRIDE,
  AFTER,
}