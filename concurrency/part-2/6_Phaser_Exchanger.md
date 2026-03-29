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

NOTE : This is useful in producer-consumer setup.

```java 

Exchanger<List<String>> exchanger = new Exchanger<>();

// producer thread
Thread producer = new Thread( () ->{
        List<String> fullBuffer = new ArrayList<>();
        while(true){
            fillEmptyBuffer(fullBuffer);
            try{
                List<String> emptyBuffer = exchanger.exchange(fullBuffer);
                fullBuffer = emptyBuffer; // now has empty buffer
                emptyBuffer.clear();
        }catch(InterruptedException e){ break;}
        }
    }
);

// consumer thread
Thread consumer = new Thread(() -> {
    List<String> emptyBuffer = new ArrayList<>();
        while (true) {
            try {
                List<String> fullBuffer = exchanger.exchange(emptyBuffer);
                processFullBuffer(fullBuffer);
                emptyBuffer = fullBuffer;
                emptyBuffer.clear();
        } catch (InterruptedException e) { break; }
    }
});

```
### Sample Problems 
```java 

// -----  PHASER  ------
import java.util.concurrent.Phaser;
import java.util.concurrent.Exchanger;

public class PhaserExample {

    public static void main(String[] args) {
        // Create a Phaser with 1 registered party (the main thread itself)
        // This prevents the phaser from advancing before we finish creating our worker threads.
        Phaser phaser = new Phaser(1);

        System.out.println("Starting the multi-stage pipeline...");

        // Start 3 worker threads
        for (int i = 1; i <= 3; i++) {
            // Dynamically register a new party for each thread we create
            phaser.register();
            new Thread(new Worker(phaser, "Worker-" + i)).start();
        }

        // The main thread is done setting up. It deregisters itself so the workers
        // can proceed without waiting for the main thread anymore.
        phaser.arriveAndDeregister();
    }

    // A Runnable representing our worker task
    static class Worker implements Runnable {
        private final Phaser phaser;
        private final String name;

        public Worker(Phaser phaser, String name) {
            this.phaser = phaser;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                // PHASE 0: Download
                System.out.println(name + " is downloading data...");
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println(name + " finished downloading. Waiting for others...");
                // Signal arrival and wait for all other registered threads to finish Phase 0
                phaser.arriveAndAwaitAdvance();

                // PHASE 1: Process
                System.out.println(name + " is processing data...");
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println(name + " finished processing. Waiting for others...");
                // Signal arrival and wait for all other registered threads to finish Phase 1
                phaser.arriveAndAwaitAdvance();

                // PHASE 2: Save
                System.out.println(name + " is saving data to database...");
                Thread.sleep((long) (Math.random() * 1000));

                // Final phase complete. The thread deregisters as it has no more work.
                System.out.println(name + " is completely done. Deregistering.");
                phaser.arriveAndDeregister();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

//  --------  Exchanger -----

public class ExchangerExample {

    public static void main(String[] args) {
        // Create an Exchanger that swaps objects of type String
        Exchanger<String> dropPoint = new Exchanger<>();

        // Spy 1: Has Top Secret Blueprints
        Thread spy1 = new Thread(() -> {
            String myIntel = "Top Secret Blueprints";
            System.out.println("Spy 1 arrives at drop point with: " + myIntel);
            try {
                // Offer myIntel, block until the other spy arrives, then receive their intel
                String receivedIntel = dropPoint.exchange(myIntel);
                System.out.println("Spy 1 leaves drop point with: " + receivedIntel);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Spy 2: Has Encryption Keys
        Thread spy2 = new Thread(() -> {
            String myIntel = "Encryption Keys";
            // Let's make Spy 2 a little late to show Spy 1 waiting
            try { Thread.sleep(2000); } catch (InterruptedException e) {}

            System.out.println("Spy 2 arrives at drop point with: " + myIntel);
            try {
                // Offer myIntel, match with Spy 1, and receive their intel
                String receivedIntel = dropPoint.exchange(myIntel);
                System.out.println("Spy 2 leaves drop point with: " + receivedIntel);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        spy1.start();
        spy2.start();
    }
}


```