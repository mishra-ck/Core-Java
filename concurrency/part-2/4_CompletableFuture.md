CompletableFuture
---
CompletableFuture was introduced to fix the fundamental blocking issue of Future, while in future
we can get the result only by calling `get()`.
Now CompletableFuture let us fetch results without blocking anything via `callback`

### CREATION 
```java

// Async computation - uses ForkJoinPool.commonPool() by default
CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> fetchData());
CompletableFuture<String> cf2 = CompletableFuture.runAsync(() -> doWork());

// With Custom Executor - always use in production
ExecutorService pool = Executors.newFixedThreadPool(10);
CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(
        () -> fetchData(),pool);

```
