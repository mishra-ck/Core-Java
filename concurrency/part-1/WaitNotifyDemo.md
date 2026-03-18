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