import java.util.*;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

class MyUtils {

    public Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) {
        return list.stream().filter(Objects::nonNull)
                .flatMap(x -> x).filter(Objects::nonNull)
                .map(s -> s.replaceAll("\\D", ""))
                .filter(not(String::isEmpty))
                .collect(groupingBy(
                        s -> s.length() == 10 ? s.substring(0, 3)
                                : s.length() == 7 ? "loc"
                                : "err",
                        mapping(s -> s.length() == 10 ? s.substring(3) : s,
                                collectingAndThen(
                                        toSet(),
                                        set -> set.stream().sorted())
                        )
                ));
    }

    public static void main(String[] args) {
        // smoke test
        var in = new ArrayList<Stream<String>>() {{
            add(Stream.of("093 987 65 43", "(050)1234567", "12-345"));
            add(Stream.of("067-21-436-57", "050-2345678", "0939182736", "224-19-28"));
            add(Stream.of("(093)-11-22-334", "044 435-62-18", "721-73-45", null));
            add(Stream.of());
            add(Stream.of("", " "));
            add(Arrays.stream(new String[]{null}));
            add(null);
        }};
        var expected = Map.of(
                "050", Stream.of("1234567", "2345678"),
                "067", Stream.of("2143657"),
                "093", Stream.of("1122334", "9182736", "9876543"),
                "044", Stream.of("4356218"),
                "loc", Stream.of("2241928", "7217345"),
                "err", Stream.of("12345")
        );
        var out = new MyUtils().phoneNumbers(in);

        var outRead = out.entrySet().stream().collect(toMap(
                Map.Entry::getKey, me -> me.getValue().collect(toList())));
        var expRead = expected.entrySet().stream().collect(toMap(
                Map.Entry::getKey, me -> me.getValue().collect(toList())));

        boolean isOk = outRead.keySet().equals(expRead.keySet())
                && Objects.deepEquals(outRead, expRead);
        System.out.println(isOk ? "OK" : "FAIL");
        if (!isOk) {
            outRead.forEach((k, v) -> System.out.printf("%s : %s%n", k, v));
            System.out.println("^^^ GOT **************** EXPECTED vvv");
            expRead.forEach((k, v) -> System.out.printf("%s : %s%n", k, v));
        }
    }
}