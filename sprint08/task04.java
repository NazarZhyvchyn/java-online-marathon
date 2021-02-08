class MyThreads {
    public final static Object den = new Object();
    public final static Object ada = new Object();

    public static int n;
    public static int m;

    public static Thread t1 = new Thread(() -> {
        try {
            synchronized (den) {
                for (int i = 0; i < 5; i++, n++)
                    System.out.println("Thread1 n = " + n);
            }
            synchronized (ada) {
                ada.notifyAll();
                ada.wait(1000);
                synchronized (den) {
                    for (int i = 0; i < 5; i++, m++)
                        System.out.println("Thread1 m = " + m);
                    System.out.println("Thread1 success!");
                }
            }
        } catch (InterruptedException ignored) { }
    });

    public static Thread t2 = new Thread(() -> {
        try {
            synchronized (ada) {
                ada.wait(1000);
                for (int i = 0; i < 5; i++, m++)
                    System.out.println("Thread2 m = " + m);
                synchronized (den) {
                    for (int i = 0; i < 5; i++, n++)
                        System.out.println("Thread2 n = " + n);
                    System.out.println("Thread2 success!");
                }
                ada.notifyAll();
            }
        } catch (InterruptedException ignored) { }
    });
}

class Main {
    public static void main(String[] args) {
        MyThreads.t1.start();
        MyThreads.t2.start();
    }
}