class ArrayUtil {
    public static double averageValue(Array<? extends Number> array) {
        double res = 0;
        for (int i = 0; i < array.length(); i++) {
            res += array.get(i).doubleValue();
        }
        return res / array.length();
    }
}