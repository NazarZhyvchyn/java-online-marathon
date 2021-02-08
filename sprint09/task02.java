import java.lang.annotation.*;
import java.time.LocalDate;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Review {
    String reviewer();
    String date() default "today";
}

class Util {
    public static void review(String className) {
        try {
            Class<?> cls = Class.forName(className);
            if (cls.isAnnotationPresent(Review.class)) {
                Review review = cls.getAnnotation(Review.class);
                String dateString = review.date();
                LocalDate date = "today".equals(dateString)
                        ? LocalDate.now()
                        : LocalDate.parse(dateString);
                System.out.printf("Class %s was reviewed %s by %s.%n",
                        className, date, review.reviewer());
            } else {
                System.out.printf("Class %s isn't marked as Reviewed%n",
                        className);
            }
        } catch (ClassNotFoundException e) {
            System.out.printf("Class %s was not found%n",
                    className);
        }
    }
}