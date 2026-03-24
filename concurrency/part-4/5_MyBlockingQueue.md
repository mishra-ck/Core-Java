PROBLEM 4: Implement a Blocking Queue from scratch
---
### Internal Working Blocking Queue
![img.png](img.png)

```java

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

private class MyBlockingQueue<T> {

    private final Object[] elements;
    private int head = 0, tail = 0, count = 0;
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock(true); // fair lock
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    
    public MyBlockingQueue(int capacity){
        this.capacity = capacity;
        this.elements = new Object[capacity];
    }
    
    // Add item to the Queue
    public void put(T item) throws InterruptedException{
        lock.lock();
        try{
            while (count == capacity){
                notFull.await();
            }
            elements[tail] = item;
            tail = (tail+1) % capacity ; // circular
            count++ ;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
    
    // Remove item from Queue
    public T take() throws InterruptedException{
        lock.lock();
        try{
            while (count == 0){
                notEmpty.await();
            }
            T item = (T)elements[head];
            elements[head] = null;
            head = (head+1) % capacity;
            count-- ;
            notFull.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }
    // Return size of Queue
    public int size() {
        lock.lock();
        try{ return  count; }
        finally {
            lock.unlock();
        }
    }
}
```