public class Main {
    public static void main (String[] args) {
        Integer[] monitor = {0};
        int sleep_min, sleep_max, activity_min, activity_max;

        new ExecutionThread(monitor, 0, 0, 4, 7).start();
        new ExecutionThread(monitor, 0, 0, 5, 7).start();
        new ExecutionThread(monitor, 0, 0, 4, 6).start();
    }
}
