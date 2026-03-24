PROBLEM 1: Print numbers 1–100 using two threads.
---
Thread-A prints odd, Thread-B prints even, in order.

```java 

import java.util.concurrent.locks.ReentrantLock;

public class EvenOddPrinter {
    private volatile int num = 1;
    private final int MAX = 100;
    private final Object lock = new Object();
    
    public void printOdd() throws InterruptedException{
        while(num <= MAX){
            synchronized (lock){
                if(num %2 == 0){
                    lock.wait();
                }else{
                    System.out.println("ODD : "+ num++);
                    lock.notifyAll();
                }
            }
        }
    }

    public void printEven() throws InterruptedException{
        while(num <= MAX){
            synchronized (lock){
                if(num %2 != 0){
                    lock.wait();
                }else{
                    System.out.println("EVEN : "+ num++);
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        EvenOddPrinter printer = new EvenOddPrinter();
        
        Thread t1 = new Thread(() ->{
            try{
                printer.printEven();
            }catch(InterruptedException ex){
                System.out.println(ex);}
        });
        Thread t2 = new Thread(() ->{
            try{
                printer.printOdd();
            }catch(InterruptedException ex){
                System.out.println(ex);}
        });
        t1.start();
        t2.start();
        
    }
    
}

```

