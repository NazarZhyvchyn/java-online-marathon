// Describe LineType enum here
enum LineType {
    SOLID, DOTTED, DASHED, DOUBLE
}
public static String drawLine(LineType lineType) {

    // Write method code here
    return String.format("The line is %s type",
            lineType.name().toLowerCase());

}