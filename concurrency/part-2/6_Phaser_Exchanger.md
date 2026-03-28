PHASER - flexible barrier (supersedes CountDownLatch + CyclicBarrier)
---

```java 

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