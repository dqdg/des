package process;

public class ThreadDemo1 {
    public static void main(String[] args) throws InterruptedException {
        new TestThread1().start();
        while(true){
            System.out.println("main thread is runing");
            Thread.sleep(1000);
        }
    }
}


class TestThread1 extends Thread{
    public void run(){
        while(true){
            System.out.println("TestThread1 is running");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}