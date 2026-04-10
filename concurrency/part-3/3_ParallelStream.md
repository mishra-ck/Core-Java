Parallel Streams 
---
### 1. How to parallel streams work internally

When we call `.parallelStream()` on a collection, internally Java does:
```
1. Wraps the collection's `Spliterator` in a parallel pipeline descriptor.
2. Submits the pipeline as a `ForkJoinTask` to the Common ForkJoinPool.
3. The pool uses `work-stealing` — each thread maintains a deque of tasks; idle threads steal from the tail of busier threads' deques.
4. Each thread processes independently, then results are combined (via the combiner in `reduce()` or the downstream collector in `collect()`).

NOTE: Parallel streams are best for `CPU-bound, stateless, non-ordered` operations on `large in-memory collections`. 
They are a poor fit for IO-bound work or small datasets.
```
### 2. Basic Syntax
```java
class ParallelStreamConcepts{
    public static void main(String[] args) {

        // Example of transactions schema validation and duplicate detection
        List<PaymentResult> results = payments.parallelStream()
                .filter(Payment::isValid)
                .map(engine::process)
                .collect(Collectors.toList());
        
        // We can convert mid-stream
        List<PaymentResult> results = payments.stream()
                .parallel()
                .filter(Payment::isValid)
                .sequential()  // switch back if one must be serial
                .sorted()
                .collect(Collectors.toList());
        
    }
}
```