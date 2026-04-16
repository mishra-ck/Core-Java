Structured Concurrency - ensures forked subtasks don't outlive their parent scope
---
In traditional concurrency using ExecutorService , subtasks can easily outlive their parents.
Leading to `orphan threads` and resource leaks.
--- 
**Core Problem** - Unstructured Concurrency
In ExecutorService tasks are independent, if main thread fails or interrupted, subtasks keep running in the background.
---
### Why it is risky ?

1. Thread Leaks : Subtasks might run forever if not manually cancelled.
2. Observability : Thread dump will not show any relationship between parent and its child.
3. Error Handling : If one subtask fails, others continue wasting resources even if the result is irrelevant.

---
### The Solution - StructuredTaskScope

StructuredTaskScope ensures that all subtasks finish(pass/fail) before the scope closes.
Workflow:
1. Open Scope: Use try-with-resources.
2. Fork: Start subtasks.
3. Join: Wait for subtasks to finish.
4. Handle Results: Process the output or handle exceptions.

---
### Use Case 1: Shutdown on Failure(`All-or-Nothing` pattern)
```java
/**
 * Use ShutdownOnFailure when you need the results of all subtasks. 
 * If any subtask fails, all other unfinished subtasks are automatically cancelled.
 */
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

public record UserProfile(String name, int orders) { }

class CaseShutdownOnFailure{

    public UserProfile getFullProfile(String userId) throws Exception {
        
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            
            // 1. Fork subtasks (returns Subtask objects)
            StructuredTaskScope.Subtask<String> userName = scope.fork(() -> fetchName(userId));
            StructuredTaskScope.Subtask<Integer> orderCount = scope.fork(() -> fetchOrderCount(userId));

            // 2. Join (waits for all or first failure)
            scope.join();

            // 3. Throw exception if any subtask failed
            scope.throwIfFailed();

            // 4. Access results safely
            return new UserProfile(userName.get(), orderCount.get());
        }
    }    
}

```
### Use Case 2: Shutdown on Success (`First-to-Finish` pattern)
```java
/**
 * Use ShutdownOnSuccess when you need a result from any available source. 
 * The first subtask to succeed cancels all others. 
 */
class CaseShutDownOnSuccess{
    
    public String getFastestServerResponse() throws Exception {
        
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            // Forking multiple redundant calls
            scope.fork(() -> callServer("Server-A"));
            scope.fork(() -> callServer("Server-B"));
            scope.fork(() -> callServer("Server-C"));

            scope.join();

            // Returns the result of the first successful subtask
            return scope.result();
        }
    }
}
```
