package concurrency.threadlocal.Part1;

import java.util.concurrent.locks.ReentrantLock;
public class ReentrantLock3 {
    public static void main(String[] args) {
        MyThread2 t1 = new MyThread2("first-thread");
        MyThread2 t2 = new MyThread2("second-thread");
        t1.start();
        t2.start();
    }
}
// Demo of tryLock()  method of ReentrantLock class
class MyThread2 extends Thread{
    static ReentrantLock l = new ReentrantLock();
    MyThread2(String name){
        super();
    }
    @Override
    public void run() {
        if(l.tryLock()){
            System.out.println(Thread.currentThread().getName() + "---> got lock  and performing safe operations :");
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                System.out.println("Exception occurred : " + e);
            }
        }else{
            System.out.println(Thread.currentThread().getName() + " --> could not get lock and performing alternative operations");
        }
    }
}
