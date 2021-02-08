import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

class Interactor {

    private int x;

    public synchronized void serve(UnaryOperator<Integer> uo, int initializer)
        throws InterruptedException
    {
        System.out.println("Serving thread running");
        x = uo.apply(initializer);
        System.out.println("Serving thread initializes the key");
        System.out.println("key = " + x);
        notifyAll();
        wait();
        System.out.println("Serving thread resumed");
    }

    public synchronized void consume(BinaryOperator<Integer> bo, int operand2)
        throws InterruptedException
    {
        wait(3000);
        System.out.println("Consuming thread received the key. key = " + x);
        x = bo.apply(x, operand2);
        System.out.println("Consuming thread changed the key. key = " + x);
        notifyAll();
    }
}