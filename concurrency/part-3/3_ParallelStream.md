Parallel Streams 
---
### 1. How to parallel streams work internally

When we call `.parallelStream()` on a collection, internally Java does:
```
1. Wraps the collection's `Spliterator` in a parallel pipeline descriptor.
2. Submits the pipeline as a `ForkJoinTask` to the Common ForkJoinPool.
3. The pool uses `work-stealing` — each thread maintains a deque of tasks; idle threads steal from the tail of busier threads' deques.
4. Each thread processes independently, then results are combined (via the combiner in `reduce()` or the downstream collector in `collect()`).
```

