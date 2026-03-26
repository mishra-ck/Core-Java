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
### TRANSFORMATION - thenApply, thenAccept, thenRun
```java

CompletableFuture<Integer> length = cf1
        .thenApply(String::length);  // transform result
CompletableFuture<Void> printed = cf1
        .thenAccept(s -> System.out.println(s));  // consumes result
CompletableFuture<Void> finished = cf1
        .thenRun(() -> System.out.println("Task Done.."));  // ignore result

// Async Variants
cf1.thenApplyAsync(s -> transform(s));
cf1.thenApplyAsync(s -> transform(s), pool); // custom executor

```