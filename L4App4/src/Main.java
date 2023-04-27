public class Main {
    public static void main (String[] args) {
        Integer[] monitor = {0, 1};
        int sleep_fix, activity_min, activity_max;

        ExecutionThread t1 = new ExecutionThread(monitor,  0, 2, 3,null);
        ExecutionThread t2 = new ExecutionThread(monitor,  0, 3, 5,t1);
        ExecutionThread t3 = new ExecutionThread(monitor,  0, 4, 6,t1);
        t1.start();
        t2.start();
        t3.start();
    }
}
