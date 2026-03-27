SEMAPHORE
---
A Semaphore is a concurrency tool that controls how many threads can access a shared resource simultaneously. 
It works by maintaining a count of permits — threads must acquire a permit before entering and release it when done.

The classic real-world analogy: a parking lot with N spaces. Only N cars can enter at once. 
If the lot is full, the next car waits at the gate until someone leaves and frees a space.

### There are two flavours:

1. A counting semaphore with permits > 1 limits concurrent access to N threads (e.g., a connection pool of 5). 
2. A binary semaphore with 1 permit acts like a mutex — only one thread at a time.

