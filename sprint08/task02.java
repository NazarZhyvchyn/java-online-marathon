class Accountant {

    public static int sum(int x, int y) {
        ParallelCalculator task = new ParallelCalculator(Integer::sum, x, y);
        Thread thread = new Thread(task);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ignored) {
            // nothing
        }
        return task.result;
    }
}