import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.Arrays;
import java.util.function.Predicate;
public class MyUtils {

     public static int getCount(int[] arr, Predicate<Integer> condition) {
        return (int) Arrays.stream(arr).boxed().filter(condition).count();
    }
    public static int getCount2(int[] arr, IntPredicate condition) {
        return (int) IntStream.of(arr).filter(condition).count();
    }
}