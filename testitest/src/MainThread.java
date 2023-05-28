import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainThread extends  Thread {
    int a_min, a_max;
    Integer monitor;
    CyclicBarrier bar;

    MainThread(CyclicBarrier bar, Integer monitor, int a_min, int a_max) {
        this.bar = bar;
        this.monitor = monitor;
        this.a_min = a_min;
        this.a_max = a_max;
        this.setPriority(MAX_PRIORITY);
    }
    public void run() {
        while(true) {
            try {
                activity();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                bar.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void activity() throws InterruptedException {
        System.out.println(this.getName() + "State 1");

        synchronized (monitor) {
            monitor.wait();
        }

        System.out.println(this.getName() + "State 2");
        int k = (int)Math.round(Math.random() * (a_max - a_min) + a_min);
        for(int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
    }
}
