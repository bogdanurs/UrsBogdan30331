
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        CyclicBarrier bar = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println("Barrier runtime");
            }
        });
        ReentrantLock l = new ReentrantLock();
        Integer monitor = new Integer(1);

        MainThread t1 = new MainThread(bar, monitor, 4, 6);
        ExecutionThread t2 = new ExecutionThread(bar, l, 3, 3, 7);
        ExecutionThread t3 = new ExecutionThread(bar, monitor, l, 2, 4, 2, 5);

        t1.start();
        t2.start();
        t3.start();
    }

}