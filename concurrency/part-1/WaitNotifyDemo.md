### Practice wait() / notify() & notifyAll() methods :

```java

import java.util.LinkedList;
import java.util.Queue;

/**  Classic example of Producer-Consumer */

public class BufferQueue {
    private final Queue<Object> queue = new LinkedList<>();
    private final int capacity;
    
    public BufferQueue(int capacity){
        this.capacity = capacity;
    }
    // Add item in queue 
    public synchronized void putItem(Object item)
    throws InterruptedException{
        while(queue.size() == capacity){
            wait();  // wait if queue is full
        }
        queue.add(item);
        notifyAll();
    }
    
    public synchronized Object takeItem() throws InterruptedException{
        while(queue.isEmpty()){
            wait();  // wait if queue is empty
        }
        Object item = queue.poll();
        notifyAll();
        return  item;
    }
}

```

```java 
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**  Bounded Buffer Using Generics */

public class BoundedBuffer<T> {
    
    private final Lock lock = new ReentrantLock();
    private final Condition isFull = lock.newCondition();  // Queue Full
    private final Condition isEmpty = lock.newCondition(); // Queue Empty

    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;
    
    public void put(T item) throws InterruptedException{
        lock.lock();
        try{
            while(queue.size() == capacity){
                isFull.await();  // wait until signal or interrupt
            }
            queue.add(item);
            isEmpty.signal();  // more targeted than notifyAll() 
        }finally {
            lock.unlock();  // releases lock
        }
    }
    
    public T take() throws InterruptedException{
        lock.lock();
        try{
            while(queue.isEmpty()){
                isEmpty.await();
            }
            T item = queue.poll();
            isFull.signal();  // only wake producer, not other waiter threads
            return item;
        }finally {
            lock.unlock();
        }
    }

}

```