// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.stream.Collectors;
// import java.util.stream.IntStream;


class Scratch {


    // public static void writeFile(String filename, String text) {
    //     try {
    //         Files.writeString(
    //                 Path.of(filename),
    //                 text.chars()
    //                         .mapToObj(Integer::toBinaryString)
    //                         .map(s -> String.format("%7s", s))
    //                         .collect(Collectors.joining())
    //                         .replace(" ", "0"));
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    public static String readFile(String filename) {
        try {
            String data = Files.readString(Path.of(filename));
            char[] converted = new char[data.length() / 7];
            for (int i = 7, j = 0; i <= data.length(); i += 7, j++) {
                converted[j] = (char) Integer.parseInt(data.substring(i - 7, i), 2);
            }
            return new String(converted);

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}