StampedLock - introduced in java 8 to overcome shortcomings of ReentrantReadWriteLock

Uses `capability-based` lock that uses `long` value(Stamp) to represent the 
state of the lock.
---
### Unlike standard locks(read-write), StampedLock offers 3 distinct modes
1. Writing: Works like a mutex, only one thread can hold.
2. Reading: Multiple threads can read at a time, but they block any thread trying to write.
3. Optimistic Reading: It returns a stamp without actually acquiring lock.We perform read and then `validate` if a write happened in meantime.
   if no write occurred, we save overhead of heavy lock.

```java
import java.util.concurrent.locks.StampedLock;

class PointCoordinateSystem {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    public double distanceFromOrigin(){
        long stamp = sl.tryOptimisticRead();
        
        // read fields
        double currentX = x;
        double currentY = y;
        
        if(!sl.validate(stamp)){
            stamp = sl.readLock();
            try{
                double currentX = x;
                double currentY = y;
            }finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX*currentX + currentY*currentY);
    }
}
```