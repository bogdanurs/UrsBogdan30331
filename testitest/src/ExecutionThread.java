import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread{
    Integer monitor;
    ReentrantLock l;
    int sleep, a_min, a_max, sl_min, sl_max;
    CyclicBarrier bar;


    ExecutionThread(CyclicBarrier bar, Integer monitor, ReentrantLock l, int sl_min, int sl_max, int a_min, int a_max) {
        this.bar = bar;
        this.monitor = monitor;
        this.l = l;
        this.a_min = a_min;
        this.a_max = a_max;
        this.sl_min = sl_min;
        this.sl_max = sl_max;
        this.setPriority(MIN_PRIORITY);
    }
    ExecutionThread(CyclicBarrier bar, ReentrantLock l, int sleep, int a_min, int a_max) {
        this.bar = bar;
        this.l = l;
        this.a_min = a_min;
        this.a_max = a_max;
        this.sleep = sleep;
    }
    public void run() {
        while(true) {
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
        System.out.println(this.getName() + "State 1");
        try {
            Thread.sleep(sleep * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean locked = false;
        while(!locked) {
            locked = l.tryLock();
        }
        System.out.println(this.getName() + "locked 1");
        System.out.println(this.getName() + "State 2");

        int k = (int)Math.round(Math.random() * (a_max - a_min) + a_min);
        for(int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        l.unlock();
        System.out.println(this.getName() + "unlocked 1");
        if(monitor != null) {
            synchronized (monitor) {
                monitor.notify();
            }
        }

        System.out.println(this.getName() + "State 3");

    }

}
