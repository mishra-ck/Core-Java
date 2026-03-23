PROBLEM 3: Implement a Thread-safe Stack
---

```java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeStack<T> {
    
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
    
    public T pop() throws IllegalAccessException{
        lock.lock();
        try{
            while(deque.isEmpty()){
                notEmpty.await();
            }
            return deque.pop();
        }finally {
            lock.unlock();
        }
    }
    
    public int size(){
        lock.lock();
        try{
            return deque.size(); 
        }
        finally {
            lock.unlock();
        }
    }
    
}

```