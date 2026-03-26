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
### TRANSFORMATION - `thenApply`, `thenAccept`, `thenRun`
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
### CHAINING - `thenCombine`, `allOf`, `anyOf`
```java 

CompletableFuture<String> price = CompletableFuture
                        .supplyAsync(() -> fetchPrice());
CompletableFuture<String> stock = CompletableFuture
                        .supplyAsync(() -> fetchStock());

// now combine result of both 
CompletableFuture<String> bothCombined = price.thenCombine(
        stock, (p,s) -> "Price: "+ p + " , Stock: "+ s );

// wait for all to complete 
CompletableFuture<Void> all = CompletableFuture.allOf(
        fetchUser(), fetchOrders(), fetchSms());
all.thenRun( () -> System.out.println("All data loaded.."));

// Collect result form allOf()
List<CompletableFuture<String>> futures = List.of(
        CompletableFuture.supplyAsync(() -> "A"),
        CompletableFuture.supplyAsync(() -> "B"),
        CompletableFuture.supplyAsync(() -> "C"),
        );

CompletableFuture<List<String>> allResult = CompletableFuture
        .allOf(futures.toArray(new CompletableFuture[0]))
        .thenApply( v -> futures.stream()
        .map(CompletableFuture::join)  
        .collect(Collectors.toList())); 

// First to complete - anyOf()
CompetableFuture<Object> fatest = CompletableFuture.anyOf(
        queryOne(), queryTwo(), queryThree() );

String result = (String)fastest.join();

```