public class ExecutionThread extends Thread {
    Integer mon1, mon2;
    int sleep, act1Min, act1Max, act2Min, act2Max;
    public ExecutionThread(Integer monitor1, Integer monitor2, int sleep, int activity1_min, int activity1_max, int activity2_min, int activity2_max) {
        this.mon1 = monitor1;
        this.mon2 = monitor2;
        this.sleep = sleep;
        this.act1Min = activity1_min;
        this.act1Max = activity1_max;
        this.act2Min = activity2_min;
        this.act2Max = activity2_max;
    }
    public void run() {
        System.out.println(this.getName() + " - STATE 1 - Work");
        int k = (int) Math.round(Math.random()*(act1Max - act1Min) + act1Min);
        for (int i = 0; i < k * 100000; i++) {
            i++; i--;
        }
        System.out.println(this.getName()+" Wait for lock 1 and 2!");
        synchronized (this.mon1) {
            synchronized(this.mon2){
                System.out.println(this.getName()+ " Got lock 1 and 2!");
                System.out.println(this.getName() + " - STATE 2 - Work");
                k = (int) Math.round(Math.random()*(act2Max - act2Min) + act2Min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                System.out.println(this.getName()+ " - STATE 3");
                System.out.println(this.getName()+" Trans: sleep");
                try {
                    Thread.sleep(Math.round(Math.random() * sleep + sleep) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName()+" Trans: Return lock 2");
            }
            System.out.println(this.getName()+" Trans: Return lock 1");
        }
        System.out.println(this.getName() + " - STATE 4-FINAL");
    }
}
