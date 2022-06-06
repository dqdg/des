package process;

public class Thread1 extends Thread{
    //多线程的创建
    public void run(){
        System.out.println("hello");
    }
    public static void main(String[] args) {
        new Thread1().start();//一经调用会触发run方法
    }
}
