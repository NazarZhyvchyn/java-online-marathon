// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.stream.Collectors;




    public static void writeFile(String filename, String text) {
        try {
            Files.writeString(
                    Path.of(filename),
                    text.chars()
                            .mapToObj(Integer::toBinaryString)
                            .map(s -> String.format("%7s", s))
                            .collect(Collectors.joining())
                            .replaceAll(" ", "0"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
