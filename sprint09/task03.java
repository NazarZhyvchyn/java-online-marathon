import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TestSuite {
    String[] value();
}

class TestSuitHandler {
    public static void run(Class<?> cls) {
        if (!cls.isAnnotationPresent(TestSuite.class)) {
            System.out.printf("Class %s isn't annotated%n",
                    cls.getName());
            return;
        }

        Object instance = null;

        TestSuite annotation = cls.getAnnotation(TestSuite.class);
        for (String methodName : annotation.value()) {
            Method method = null;
            try {
                method = cls.getDeclaredMethod(methodName);
                int modifiers = method.getModifiers();
                if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                    throw new NoSuchMethodException();
                }
            } catch (NoSuchMethodException e) {
                System.out.printf("Method with name %s doesn't exists or not public in class %s%n",
                        methodName, cls.getName());
                continue;
            }

            if (instance == null) {
                try {
                    Constructor<?> constructor = cls.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    instance = constructor.newInstance();
                } catch (Exception e) {
                    System.out.println("Can't create an instance of " + cls.getName());
                    return;
                }
            }

            System.out.printf("\t -- Method %s.%s started --%n",
                    cls.getName(), method.getName());
            try {
                method.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.printf("\t -- Method %s.%s finished --%n",
                    cls.getName(), method.getName());
        }
    }

    public static void main(String[] args) {
        run(Class1.class);
    }
}

@TestSuite({"m1", "m2"})
class Class1 {
    public void m2() {
        System.out.println("Hello");
    }
}