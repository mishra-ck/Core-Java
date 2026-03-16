package concurrency.threadlocal.Part1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock4 {
    public static void main(String[] args) {
        MyThread3 t1 = new MyThread3("Ram");
        MyThread3 t2 = new MyThread3("Shyam");
        t1.start();
        t2.start();
    }
}
class MyThread3 extends Thread{
    static ReentrantLock l = new ReentrantLock() ;
    MyThread3(String name){
        super(name);
    }
    @Override
    public void run() {
        do {
            try {
                if(l.tryLock(5000, TimeUnit.MILLISECONDS)){
                    System.out.println(Thread.currentThread().getName() + " acquired lock");
                    Thread.sleep(30000);
                    l.unlock();
                    System.out.println(Thread.currentThread().getName() + "released lock");
                    break;
                }else{
                    System.out.println(Thread.currentThread() + " trying for lock");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }while(true);
    }
}
