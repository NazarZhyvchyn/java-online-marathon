class Operation{
    public static int squareRectangle (int a, int b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("both arguments should be more than zero");        }
        int square = a*b;
        return square;
        // if (a <= 0 || b <= 0) {
        //     throw new IllegalArgumentException("both arguments should be more than zero");
        // }
        // return a * b;
    }

    public static int trySquareRectangle(int a, int b) {
        //  if(a < 0 | b < 0) {
        //     return -1;
        // }
        try {
            return squareRectangle(a, b);
        }
        catch(IllegalArgumentException e) {
            return -1;
        }
    } 
}
    
