PHASER - Dynamic Stage coordinator (supersedes CountDownLatch + CyclicBarrier)
EXCHANGER - Two thread swap meet
---
### Phaser
Phaser is more flexible, dynamic version of `CountDownLatch` or `CyclicBarrier`.
It is used when we have multiple threads that needs to complete task in distinct "PHASES" or steps.

### How it works ?
1. **Registration**- Threads tell Phaser they are participating(`register()`).
2. **Arrival**- When thread finishes it's current phase,tells the Phaser it has arrived 
   and waiting for others(`arriveAndAwaitADvance()`)
3. **Phases**- Phaser keeps an internal integer counter for current thread phase(starting-0).
    Once all threads arrive, in next phase all waiting threads are released(`arriveAndDeregister()`)

```java 

ExecutorService pool = Executors.newFixedThreadPool(5); // pool
Phaser phaser = new Phaser(1); // 1 = main thread registered

for (int i = 0; i < 5; i++) {
    phaser.register();  // register new party (can be called dynamically)
    pool.submit(() -> {
        // Phase 0
        doPhase0Work();
        phaser.arriveAndAwaitAdvance(); // arrive + wait for all → advance to phase 1

        // Phase 1
        doPhase1Work();
        phaser.arriveAndAwaitAdvance(); // advance to phase 2

        // Phase 2 — done, deregister
        doPhase2Work();
        phaser.arriveAndDeregister();   // arrive + deregister
    });
}

phaser.arriveAndDeregister(); // main thread deregisters
System.out.println("All phases complete");

```
### Exchanger 
Exchanger is a simple synchronization point designed for exactly two threads.
It allows them to safely swap objects in memory.

### How it works ?
1. Thread-1 comes to Exchanger with Object-1 and calls `exhange()`.
2. Thread-1 completes block(pauses) and awaits.
3. Thread-2 arrives at Exchanger with Object-2 and calls `exchanger()`.
4. The Exchanger takes Object-1 from Thread-1 gives to Thread-2 anv vice-versa.
5. Both thread wake up and continue with newly swapped data.