import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread {
    Integer monitor = null;
    ReentrantLock l;
    int sleep, a_min, a_max, sleep_min, sleep_max;
    CyclicBarrier bar;

    ExecutionThread(CyclicBarrier bar, ReentrantLock l, int sleep, int a_min, int a_max) {
        this.bar = bar;
        this.l = l;
        this.sleep = sleep;
        this.a_max = a_max;
        this.a_min = a_min;
    }

    ExecutionThread(CyclicBarrier bar, ReentrantLock l, Integer monitor, int a_min, int a_max, int sleep_min,
                    int sleep_max) {
        this.a_max = a_max;
        this.a_min = a_min;
        this.bar = bar;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.l = l;
        this.monitor = monitor;
        this.setPriority(MIN_PRIORITY);
    }

    public void run() {
        while (true) {
            activity();
            try {
                bar.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void activity() {


        if (sleep != 0) {
            try {
                Thread.sleep(sleep * 500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        boolean locked = false;

        while (!locked) {
            locked = l.tryLock();
        }
        System.out.println(this.getName() + " - locked l");

        System.out.println(this.getName() + " - State 2");

        int k = (int) Math.round(Math.random() * (a_max - a_min) - a_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        l.unlock();
        System.out.println(this.getName() + " - unlocked l");

        if (monitor != null) {
            synchronized (monitor) {
                monitor.notify();
                System.out.println(this.getName() + " - notify");
            }
        }

        System.out.println(this.getName() + " - state 3");

    }
}
