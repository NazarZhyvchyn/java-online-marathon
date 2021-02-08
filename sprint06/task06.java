import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

class MyUtils{
    public static int findMaxByCondition(
        List<Integer> numbers,
        Predicate<Integer> pr
    ){
        return numbers.stream()
                .filter(pr)
                .max(Integer::compareTo).orElse(Integer.MIN_VALUE);
    }
}

class User {
    public final List<Integer> values = new ArrayList<>();

    int getFilterdValue(
            BiFunction<List<Integer>, Predicate<Integer>, Integer> listReducer,
            Predicate<Integer> filter
    ){
        return listReducer.apply(values, filter);
    }

    int getMaxValueByCondition(Predicate<Integer> predicate) {
        return getFilterdValue(MyUtils::findMaxByCondition, predicate);
    }
}
