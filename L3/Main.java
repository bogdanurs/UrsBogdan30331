import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        System.out.println("Number > 50.000: ");
        Scanner scanner = new Scanner(System.in);

        int first_no = scanner.nextInt();
        System.out.println("Number > 20.000 and < 50.000: ");

        int second_no = scanner.nextInt();

        JoinTestThread w1 = new JoinTestThread("Thread 1",null, first_no);

        JoinTestThread w2 = new JoinTestThread("Thread 2",w1, second_no);

        w1.start();

        w2.start();

    }

}