Executor Framework & Thread Pools
---
### Why java introduced Executor framework ?

There are four core problems that make raw thread outdated :-
1. No thread reuse : New thread per task, means more cost for running app.
2. No thread cap : As number of requests ⬆️, no of threads ⬆️
3. No return values : threads returns void type.
4. No lifecycle control : hard to manage threads during execution.

Executor framework solved all these above problems.

Let's compare the Old vs New Approach :-
```java
// Old Approach - Example of processing order

// Problem 1 & 2: New thread per task, no cap
for (Order order : orders) {
        new Thread(() -> processOrder(order)).start(); // 1000 orders = 1000 threads
}
// Problem 3: No return value — can't get the result
// Problem 4: No way to shut down, cancel, or wait for completion
        
        
// New Approach - Solution to all 4 problems in one setup
ExecutorService executor = Executors.newFixedThreadPool(10); // cap: only 10 threads

List<Future<String>> futures = new ArrayList<>();

for (Order order : orders) {
// Problem 3 solved: Callable returns a value → wrapped in Future
Future<String> future = executor.submit(() -> processOrder(order));
futures.add(future);
}

// Problem 4 solved: lifecycle control — wait for all, then shut down
for (Future<String> future1 : futures) {
    System.out.println(future1.get()); // blocks until result is ready
}

executor.shutdown();
executor.awaitTermination(60, TimeUnit.SECONDS); 


```