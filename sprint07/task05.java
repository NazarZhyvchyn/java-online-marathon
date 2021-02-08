import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

class MyUtils {
    public Stream<String> nameList(Map<String, Stream<String>> map) {
        return map.values().stream()
                .flatMap(x -> x)
                .filter(Objects::nonNull)
                .filter(not(String::isBlank))
                .map(MyUtils::normailze)
                .distinct()
                .sorted();
    }

    private static String normailze(String name) {
        name = name.replace(" ", "").toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public static void main(String[] args) {
        var test = Map.of(
                "Desktop", Stream.of(" iVan", "PeTro ", " Ira "),
                "Web", Stream.of("STepan", "ira ", " Andriy ", "an na"),
                "Spring", Stream.of("Ivan", "Anna"));
        System.out.println(new MyUtils().nameList(test)
                .collect(Collectors.joining(",","[","]"))
        );
    }
}