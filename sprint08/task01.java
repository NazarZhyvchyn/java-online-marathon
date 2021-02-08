import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class MyUtils {
    public Stream<Integer> duplicateElements(Stream<Integer> stream) {
        return stream.filter(Objects::nonNull)
                .collect(groupingBy(i -> i, counting()))
                .entrySet().stream()
                .filter(mapEntry -> mapEntry.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted();
    }
}