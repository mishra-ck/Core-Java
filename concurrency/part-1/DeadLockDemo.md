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