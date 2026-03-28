SEMAPHORE - Controls access to a pool of resources
---
A Semaphore is a concurrency tool that controls how many threads can access a shared resource simultaneously. 
It works by maintaining a count of permits — threads must acquire a permit before entering and release it when done.

The classic real-world analogy: a parking lot with N spaces. Only N cars can enter at once. 
If the lot is full, the next car waits at the gate until someone leaves and frees a space.

### There are two flavours:

1. A counting semaphore with permits > 1 limits concurrent access to N threads (e.g., a connection pool of 5). 
2. A binary semaphore with 1 permit acts like a mutex — only one thread at a time.

### Understanding using examples:

```java 

Semaphore semaphore = new Semaphore(3); // semaphore with 3 permits
Semaphore fairSemaphore = new Semaphore(3,true); // semaphore with 3 permits FIFO order

// acquire permit-block if not available
semaphore.acquire();  // acquire permits
try{
 // fetch DB connection details
 executeQuery(connection);
 conection.close();
}finally{
    connection.release(); // release permit(s) acquired    
}

// Non-blocking try
if(connection.tryAcquire()){
    try{
        // use resources 
     }
     finally{ connection.release(); }
}else{
    return fallback(); // do if resource not available 
}

// Try with TIMEOUTS
if (connection.tryAcquire(500, TimeUnit.MILLISECONDS)) { /* ... */ }


```