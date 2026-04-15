StampedLock - introduced in java 8 to overcome shortcomings of ReentrantReadWriteLock
---

Uses `capability-based` lock that uses `long` value(Stamp) to represent the 
state of the lock.
---
### Unlike standard locks(read-write), StampedLock offers 3 distinct modes
1. Writing: Works like a mutex, only one thread can hold.
2. Reading: Multiple threads can read at a time, but they block any thread trying to write.
3. Optimistic Reading: It returns a stamp without actually acquiring lock.We perform read and then `validate` if a write happened in meantime.
   if no write occurred, we save overhead of heavy lock.

### Coordinate System
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
### High Performance Cache

```java
import java.util.HashMap;
import java.util.concurrent.locks.StampedLock;

class HighPerformanceCache {
    
   private final Map<String, String> cache = new HashMap<>();
   private final StampedLock lock = new StampedLock();
   
   public String get(String key){
       //1. Optimistic read
       long stamp = lock.tryOptimisticRead();
       String value = cache.get(key);
       //2. Validate
      if(!lock.validate(stamp)){
         //3. Validation failed, someone wrote to cache
         // fallback to pessimistic read
         stamp = lock.readLock();
         try{
             value = cache.get(key);
         }finally {
             lock.unlockRead();
         }
      }
       return value;
   }
   public void put(String key, String value){
       long stamp = lock.writeLock();
       try {
           cache.put(key,value);
       }finally {
           lock.unlockWrite(stamp);
       }
   }
}
```