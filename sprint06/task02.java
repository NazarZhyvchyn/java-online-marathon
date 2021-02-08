import java.util.Arrays;
import java.util.function.Consumer;

public class App {
    public static Consumer<double[]> cons = arr -> {
        for (int i = 0; i < arr.length; i++) {
            double val = arr[i];
            arr[i] = val > 2 ? 0.8 * val : 0.9 * val;
        }
    };

    public static double[] getChanged(
            double[] initialArray,
            Consumer<double[]> consumer
    ) {
        double[] res = Arrays.copyOf(initialArray, initialArray.length);
        consumer.accept(res);
        return res;
    }
}