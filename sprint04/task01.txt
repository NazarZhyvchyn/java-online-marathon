import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyUtils {
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        if (phones == null || phones.isEmpty()) {
            return Map.of();
        }
        Map<String, List<String>> res = phones.values().stream()
                .distinct().collect(Collectors.toMap(
                        Function.identity(),
                        name -> new ArrayList<>()));
        phones.forEach((k, v) -> res.get(v).add(k));
        return res;
    }
}