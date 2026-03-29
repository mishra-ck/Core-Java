BlockingQueue & Producer-Consumer 
---
Before BlockingQueue, While implementing producer-consumer problem in-order to manager states manually
`wait()` and `notify()` were used.BlockingQueue handles all of this automatically.

### Key Methods:
put(E e): Adds an element to the queue. If the queue is full, the thread pauses (blocks) until space becomes available.
take(): Retrieves and removes the head of the queue. If the queue is empty, the thread pauses (blocks) until an element becomes available.

