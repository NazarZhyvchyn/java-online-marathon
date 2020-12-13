

 public static void addAtoB(int a, int b) {
        execute(a, b, new Strategy() {
            public double doOperation(int a, int b) {
                return a + b;
            }
        });
    }

    public static void subtractBfromA(int a, int b) {
        execute(a, b, (op1, op2) -> op1 - op2);
    }

    public static void multiplyAbyB(int a, int b) {
        execute(a, b, (op1, op2) -> op1 * op2);
    }

    public static void divideAbyB(int a, int b) {
        execute(a, b, (op1, op2) -> op1 / op2);
    }