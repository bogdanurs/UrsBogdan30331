public class Main {
    public static void main(String[] args) {
        Integer[] monitor = {0, 1};
        int sleep;
        int[] activity1 = {2, 4};
        int[] activity2 = {4, 6};
        int[] activity3 = {3, 5};
        int[] activity4 = {5, 7};

        new ExecutionThread(monitor, activity1, activity2, 4).start();
        new ExecutionThread(monitor, activity3, activity4, 5).start();

    }
}
//make one transition acquire both of them to fix!!