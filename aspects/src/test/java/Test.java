import universe.aspects.annotations.Aspect;
import universe.aspects.annotations.AspectUsing;
import universe.aspects.annotations.Stub;

interface AStub{}
interface A1{}

public class Test
    implements
    @Stub AStub,
    @Aspect A1
{
  @AspectUsing()
  public static void main(String[] args) {

  }
}
