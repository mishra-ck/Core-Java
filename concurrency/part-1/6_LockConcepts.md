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
    ✅ ReentrantLock
    ✅ ReentrantReadWriteLock
    ✅ StampedLock

1. ReentrantLock : `Reentrant` means if a thread can re-acquire the same lock again, will increase the hold count.
```java 

class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}

```

2. ReentrantReadWriteLock : It is good for avoiding `Starvation`, has two parts
    Read Lock : many threads can read at once
    Write Lock : One thread can write and one thread can read while writing

```java

class SharedList {
    private final List<String> list = new ArrayList<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final var readLock = rwLock.readLock();
    private final var writeLock = rwLock.writeLock();

    public void add(String item) {
        writeLock.lock();
        try {
            list.add(item);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }
}

```
3. StampedLock : A capability-based lock with three modes for controlling read/write access. 
    The state of a StampedLock consists of a version and mode. There are three modes - Writing / Reading / Optimistic Reading.
```java

class Point {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) { // an exclusively locked method
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }
}
```