class JoinTestThread extends Thread{
    Thread t;
    static int sum = 0;
    int num;

    JoinTestThread(String n, Thread t, int num){
        this.setName(n);
        this.t=t;
        this.num = num;
    }
    public void run() {
        System.out.println("Thread "+ this.getName() +" has entered the run() method");
        try {
            if (t != null) {
                t.join();
                sum += sumOfDivs(this.num);
                System.out.println("The sum of the dividers of a number greater than 20000(second thread) is " + sumOfDivs(this.num));
                System.out.println("Final sum is: " + sum);
            }
            else {
                    System.out.println("The sum of the dividers of a number greater than 50000(first thread) is " + sumOfDivs(this.num));
                    sum += sumOfDivs(this.num);
            }
            System.out.println("Thread "+this.getName()+" executing operation.");
            Thread.sleep(3000);
            System.out.println("Thread "+this.getName()+" has terminated operation.");
        }
        catch(Exception e){e.printStackTrace();}

        }
        public int sumOfDivs(int num){
            int Divisors = num + 1;
            for (int i = 2; i <= num/2; i++) {
                if( num % i == 0) {
                    Divisors += i;
                }
            } return Divisors;
        }
    }
