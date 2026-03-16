package concurrency.threadlocal.Part1;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2 {
    public static void main(String[] args) {
        Display d = new Display();
        MyThread t1 = new MyThread(d,"Dhoni");
        MyThread t2 = new MyThread(d,"Rohit");
        t1.start();
        t2.start();
    }
}
class Display{
    ReentrantLock l = new ReentrantLock();
    public void wish(String name){
        l.lock();  // acquiring lock
        for (int i =0 ; i< 5;i++){
            System.out.print("Good Morning: ");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Exception is : " + e);
            }
            System.out.println(name);
        }
        l.unlock(); // releasing lock
    }
}

class MyThread extends Thread{
    Display display ;
    String name ;
    MyThread(Display display , String name){
        this.display = display ;
        this.name = name ;
    }
    public void run(){
        display.wish(name);
    }
}