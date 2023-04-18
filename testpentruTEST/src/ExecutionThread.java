public class ExecutionThread extends Thread{
    Integer[] monitor;
    int sleep_min, sleep_max, activity_min, activity_max;
    public ExecutionThread(Integer[] monitor, int sleep_min, int sleep_max, int activity_min, int activity_max) {
        this.monitor = monitor;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }

    public void run() {
        System.out.println(this.getName() + "State 0");

        if(this.getName().equals("Thread-0")) {
            try {
                Thread.sleep(7 * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " State 1");

            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            synchronized (monitor[0]) {
                synchronized ((monitor[1])) {
                    monitor[0].notify();
                    monitor[1].notify();
                }
            }
            System.out.println(this.getName() + "State 2");
        }
        if(this.getName().equals("Thread-1")) {
            synchronized (monitor[0]) {
                try {
                    monitor[0].wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(this.getName() + "State 1");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            System.out.println(this.getName() + "State 2");
        }
        if(this.getName().equals("Thread-2")) {
            synchronized (monitor[1]) {
                try {
                    monitor[1].wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(this.getName() + "State 1");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            System.out.println(this.getName() + "State 2");
        }
    }

}
