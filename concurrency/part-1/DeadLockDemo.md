### Deadlock / Livelock / Starvation

```
✅ Deadlock : Two or more threads are waiting for each other to release locks, so nobody moves.

✅ Livelock : Threads keep changing their state in response to each other, but no actual work id being done.

✅ Starvation : A low priority thread is denied the resources it needs to execute because other high priority threads are executing.

```
### Sample Deadlock Situation in Java

```java

public class DeadLockDemo{
    Object lock1 = new Object();
    Object lock2 = new Object();
    
    Thread t1 = new Thread( () ->{
        synchronized (lock1){
            System.out.println("T1 acquired lock 1, waiting for lock 2");
            try{ Thread.sleep(100); }catch (InterruptedException ex) { }
            synchronized (lock2){
                System.out.println("T1 acquired both lock..");
            }
        }   
    });
    Thread t2 = new Thread(() -> {
        synchronized (lock2) {
            System.out.println("T2: acquired lock2, waiting for lock1...");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lock1) {
                System.out.println("T2: acquired both locks");
            }
        }
    });
    /** DEADLOCK : t1 waits for lock2 and t2 waits for lock1, therefore no progress ...*/
    
}
```
### DeadLock Detection - ThreadMXBean

```java 
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDetection {

    public void deadLockedThreads() {
        
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] deadLockThreads = bean.findDeadlockedThreads();
        if (deadLockThreads != null) {
            ThreadInfo[] threadInfos = bean.getThreadInfo(deadLockThreads);
            for (ThreadInfo info : threadInfos){
                System.out.println( "Deadlocked :"+ info.getThreadName() 
                        + " waiting for : "+ info.getLockName()
                );
            }
        }
    }
}
```

### Deadlock prevention strategies

```java

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PreventDeadLock {

    /** ------- FIRST :  Always Maintain lock ordering  --------*/
    public void lockOrder(Object obj1, Object obj2) {
        Object first = obj1;
        Object second = obj2;
        synchronized (first) {
            synchronized (second) {
                System.out.println("Lock ordering..");
            }
        }
    }

    /** tryLock with timeouts using ReentrantLock */
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public void lockTimeOuts() {
        boolean isTaskDone = false;
        while (!isTaskDone) {
            if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                try{
                    if(lock.trylock(50,TimeUnit.MILLISECONDS)){
                        try {isTaskDone = true ;}
                        finally{ lock2.unlock();}
                    }
                }finally{ lock1.unlock();}
            }
        }
    }

    /**  User Fair lock using ReentrantLock(true) - FIFO */
    ReentrantLock lock = new ReentrantLock(true); // FIFO Ordering of locks
    
    
}

```