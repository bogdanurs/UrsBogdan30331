import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainThread extends Thread {
    int a_min, a_max;
    Integer monitor;
    CyclicBarrier bar;

    MainThread(CyclicBarrier bar, Integer monitor, int a_min, int a_max) {
        this.bar = bar;
        this.monitor = monitor;
        this.a_max = a_max;
        this.a_min = a_min;
        this.setPriority(MAX_PRIORITY);
    }

    public void run() {
        while (true) {
            activity();
            try {
                bar.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void activity() {
        System.out.println(this.getName() + " - state 1");

        synchronized (monitor) {
            try {
                monitor.wait();
                System.out.println(this.getName() + " - waiting");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        System.out.println(this.getName() + " - state 2");
        int k = (int) Math.round(Math.random() * (a_max - a_min) + a_min);
        for (int i = 0; i < k * 100000; i++) {
            i++; i--;
        }

    }
}
