import java.sql.SQLOutput;
import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread{
    int delay, permit, activity_min, activity_max;
    Semaphore semaphore;
    Integer monitor;

    ExecutionThread(Integer[] monitor, Semaphore semaphore, int delay, int activity_min, int activity_max, int permit) {
        this.semaphore = semaphore;
        this.delay = delay;
        this.activity_max = activity_max;
        this.activity_min = activity_min;
        this.permit = permit;
    }
    public void run() {
        if(this.getName().equals("thread-0")) {
            System.out.println(this.getName() + "State 0");
            System.out.println(this.getName() + "State 1");
            try {
                this.semaphore.acquire(this.permit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Acquired permit");

            try {
                Thread.sleep(this.delay * 300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "State 2");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 10000; i++) {
                i++; i--;
            }
            this.semaphore.release(this.permit);
            System.out.println(this.getName() + "State 3");
        }
        if(this.getName().equals("thread-1")) {
            System.out.println(this.getName() + "State 0");
            System.out.println(this.getName() + "State 1");
            try {
                this.semaphore.acquire(this.permit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "State 2");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 10000; i++) {
                i++; i--;
            }
            this.semaphore.release(this.permit);
            //wait sau notify sau ceva
            Thread.sleep(Math.round(Math.random() * (sleep_max
                    - sleep_min)+ sleep_min) * 500);
            System.out.println(this.getName() + "State 3");

        }
    }
}
