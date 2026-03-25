Callable and Futures
---
### Callable interface: 
Callable is a functional interface, unlike Runnable interface it returns value 
and throws Checked Exception with single  `call()`  method.
We can submit any Callable task in ExecutorService using `submit(callableTask)`

### Future interface:
Future is a wrapper which provides methods to check if the task submitted is done/cancel and 
retrieves results. 

```java 
import java.util.concurrent.*;

public class CallableFutureExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Callable task: sum numbers 1 to 5
        Callable<Integer> sumTask = () -> {
            int sum = 0;
            for (int i = 1; i <= 5; i++) {
                sum += i;
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            }
            return sum;  // Returns 15
        };
        
        Future<Integer> future = executor.submit(sumTask);
        try {
            // Blocks until result ready,give timeout 
            Integer result = future.get(5, TimeUnit.SECONDS);
            System.out.println("Sum result: " + result); 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        
        // Other Future methods
        System.out.println(future.isDone());  // finished ?
        System.out.println(future.isCancelled());  // cancelled ?
        System.out.println(future.cancel(true));  // interrupt running task
        
        // invokeAll() : In case we have multiple Callable Tasks submitted at once.
        List<Callable<String>> tasks = List.of(
                () -> fetchFromDB(),
                () -> callExternalAPI(),
                () -> processFile()
        );
        
        // Without timeout 
        List<Future<String>> futures = pool.invokeAll(tasks);  // blocks until all done
        for (Future<String> f : futures) {
            System.out.println(f.get()); 
        }
        // With timeout:
        List<Future<String>> timeOutFutures = pool.invokeAll(tasks, 30, TimeUnit.SECONDS);
        
        // invokeAny() : submit many, returns first completed one.
        String first = pool.invokeAny(tasks);  // cancels remaining
        System.out.println("First result: " + first);
        
    }
}

```