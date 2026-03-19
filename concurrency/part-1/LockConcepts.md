### Understanding Lock interface and its implementations

✅ Lock : unlike `synchronized`, which is a block of code. Lock is an Object, we can call `lock()` and 
    `unlock()` to start and stop locks working.
NOTE : Always put `unlock()` method inside finally block to avoid deadlock.
```java 
Lock l = ...;
l.lock();
    try {
        // access the resource protected by this lock  
    }
    finally { l.unlock();  }
```

### Core Implementation of Lock Interface:
    --✅ ReentrantLock
    --✅ ReentrantReadWriteLock
    --✅ StampedLock

