import universe.util.reflect.Reflection;
import universe.util.reflect.accessor.FloatAccessor;
import universe.util.reflect.accessor.MethodInvoker0;

class C1 {
  private float field = 12;

  private void test(){
    System.out.println("test");
  }
}

public class Test {
  static MethodInvoker0<C1, Void> invoker = Reflection.accessMethod(C1.class, "test", void.class);
  static FloatAccessor<C1> accessor = Reflection.accessFloat(C1.class, "field");

  public static void main(String[] args) {
    C1 c1 = new C1();
    invoker.invoke(c1);
    accessor.set(c1, 19f);
  }
}
