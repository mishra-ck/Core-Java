BlockingQueue & Producer-Consumer 
---
Before BlockingQueue,while implementing producer-consumer problems to manage states manually
`wait()` and `notify()` methods were invoked.BlockingQueue handles all of this automatically.

### Key Methods:

put(E e): Adds an element to the queue. If the queue is full, the thread pauses (blocks) until space becomes available.
take(): Retrieves and removes the head of the queue. If the queue is empty, the thread pauses (blocks) until an element becomes available.

```java

public record DataPacket(int id, String payload) {}

public class ProducerConsumerSystem{

    public static void main(String[] args) {
        BlockingQueue<DataPacket> queue = new ArrayBlockingQueue<>(3);

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    DataPacket packet = new DataPacket(i, "Data-" + i);
                    queue.put(packet);   // put() will block here if the queue already has 3 items
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                Thread.sleep(500); // wait to fill queue

                for (int i = 1; i <= 5; i++) {
                    DataPacket packet = queue.take(); // take() will block here if the queue is empty
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producerThread.start();
        consumerThread.start();
    }
}

```