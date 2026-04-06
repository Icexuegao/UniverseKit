import universe.aspects.Using
import universe.aspects.annotations.Aspect
import universe.aspects.annotations.AspectUsing
import universe.aspects.annotations.Stub

interface Aspect1{
  fun update()
  fun draw()
}

interface Aspect2{
  @AspectUsing(Using.OVERRIDE)
  fun update()
  @AspectUsing(Using.AFTER)
  fun added(){
    println("added")
  }
  @AspectUsing(Using.BEFORE)
  fun removed(){
    println("removed")
  }
  @AspectUsing(Using.RETURN_VALUE)
  fun getHealth(): Float {
    return 10000f
  }
}

open class ObjectStub {
  open fun update(){}
  open fun draw(){}
}

class AspectImpl:
  @Stub ObjectStub(),
  @Aspect Aspect1,
  @Aspect Aspect2
{
  override fun update() {
    super.update()
    println("update")
  }

  override fun draw() {
    println("draw")
    super.draw()
  }
}