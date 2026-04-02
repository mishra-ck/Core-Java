## Inter-Thread Communication fundamentals :-

````
1. notify() vs notifyAll() 
 
 notify():    wakes ONE random waiting thread
              → can cause starvation if wrong thread wakes
              → faster (O(1))

 notifyAll(): wakes ALL waiting threads (they re-compete for lock)
              → safer (no missed signals)
              → can cause thundering herd (many threads compete)

2. Why wait() releases the lock but sleep() does NOT:
 
 wait(): releases lock + goes to WAITING state
         → other threads can enter synchronized block
         → woken by notify() or notifyAll()

 sleep(): keeps the lock, Just pauses execution
          → other threads CANNOT enter synchronized block
          → woken after timeout

```