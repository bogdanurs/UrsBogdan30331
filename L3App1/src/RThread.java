public class RThread extends Thread{

    FileService service;

    public RThread(FileService service) {

        this.service = service;

    }

    public void run(){

        synchronized (service){
        while (!Main.isStopThreads()) {

            try {

                String readMsg = this.service.read();

                System.out.println(readMsg);

                Thread.sleep(3000);

            } catch (Exception e) {

                e.printStackTrace();

            }
        }

        }

    }

}
