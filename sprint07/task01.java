public static boolean isLeapYear(int year) {
 return LocalDate.ofYearDay(year, 1).isLeapYear();
    // Write your code here

}