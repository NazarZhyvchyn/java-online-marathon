import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

class MyUtils {
    static Predicate<Integer> getPredicateFromSet(
            Set<Predicate<Integer>> predicateSet
    ) {
        return predicateSet.stream().reduce(__ -> true, Predicate::and);
    }

    public static void main(String[] args) {
        Set<Predicate<Integer>> predicates = Set.of(
                i -> i >= 2,
                i -> i < 5,
                i -> i % 2 == 0
        );
        Predicate<Integer> all = getPredicateFromSet(predicates);
        IntStream.range(0, 20).boxed().filter(all).forEach(System.out::print); // => 24
    }
}