import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
public class MyUtils {
    // Code
    public boolean listMapCompare(List<String> list, Map<String, String> map) {
        // Code
                return new HashSet<>(list).equals(new HashSet<>(map.values()));
    }

    }
