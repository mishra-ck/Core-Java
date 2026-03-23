PROBLEM 3: Implement a Thread-safe Stack
---

```java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeStack {
    private final Deque<T> deque = new ArrayDeque<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    
    public void push(T item){
        lock.lock();
        try{
            deque.push(item);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
}

```