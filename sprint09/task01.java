import java.lang.annotation.*;
import java.lang.reflect.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface CamelCase {
}

class CheckCamelCase {
    // code convention: https://rules.sonarsource.com/java/tag/convention/RSPEC-100
    public final static String CAMELCASE_PATTERN =
            "[a-z][a-zA-Z0-9]*";
    private final static String MESSAGE_TEMPLATE =
            "method %s.%s doesn't satisfy camelCase naming convention%n";

    public static boolean checkAndPrint(Class<?> cls) {
        boolean res = true;
        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CamelCase.class) &&
                    !method.getName().matches(CAMELCASE_PATTERN)) {
                res = false;
                System.out.printf(MESSAGE_TEMPLATE,
                        cls.getName(), method.getName());
            }
        }
        return res;
    }
}

class ClassForAnnot {
  @CamelCase
  public static void example() {
  }

  @CamelCase
  public void Example() {
  }

  public static void _main(String args[]) {
  }
}

class Class1{
  @CamelCase
  public void correct(){} 
  @CamelCase
  public void InCorrect(){} 
  @CamelCase
  public void JustMethod(){}
}

class Class2{
  @CamelCase
  public void correct(){} 
  @CamelCase
  public void oneMoreCorrect(){} 
}