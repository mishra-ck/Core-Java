### Atomic Variables (Compare-And-Swap)

Atomic Class use CAS:Compare And Swap - a hardware instruction, not locks to ensure Atomicity.


```java
import java.util.concurrent.atomic.AtomicInteger;

class SafeCounter implements Runnable {
    private AtomicInteger count = new AtomicInteger(0);
    
    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            count.incrementAndGet();  // Atomic operation
        }
    }
    public int getCount() { return count.get(); }
}

public class AtomicUseCases{

    public static void main(String[] args) {
        /** ------  AtomicInteger -----*/
        AtomicInteger counter = new AtomicInteger(0);

        counter.incrementAndGet();    // ++counter 
        counter.getAndIncrement();    // counter++ 
        counter.addAndGet(5);         // counter += 5 
        counter.getAndAdd(5);         // counter += 5 
        counter.set(100);             // counter = 100
        counter.get();                // read current
        counter.compareAndSet(100, 200);

        /** ------- AtomicReference ------ */
        AtomicReference<String> atomicRef = new AtomicReference<>("initial");
        atomicRef.set("value");
        String prev = atomicRef.getAndSet("newValue");
        atomicRef.compareAndSet("newValue", "updated");

        /** Thread-safe minimum update ,no lock needed  */
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        min.updateAndGet(current -> Math.min(current, newValue));

        AtomicLong totalRequests = new AtomicLong(0L);
        totalRequests.incrementAndGet();

        /**  ------ AtomicBoolean ------*/
        AtomicBoolean initialized = new AtomicBoolean(false);
        if (initialized.compareAndSet(false, true)) {  // One-time initialization 
            init();  // called exactly once across all threads
        }

        /**LongAdder / DoubleAdder — Java 8+ — for high contention */
        LongAdder requestCount = new LongAdder();
        requestCount.increment();    // fast under contention
        requestCount.add(5);
        long total = requestCount.sum(); // aggregate
        
        /** Atomic Array classes */
        AtomicIntegerArray intArray = new AtomicIntegerArray(10);
        intArray.set(0, 100);
        intArray.incrementAndGet(0);
        intArray.compareAndSet(0, 101, 200);
        
        
    }
}
        

```