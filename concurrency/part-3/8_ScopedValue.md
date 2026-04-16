Scoped Values- A safer alternative to `ThreadLocal`, for virtual threads.
---
---
### Understand why it required ?

To understand `Scoped values`, try to understand `ThreadLocal` first. 
ThreadLocal allows mutability and has lifetime as thread itself. If a thread lives long time so does the data.
Further inheriting ThreadLocal data in child is expensive copying of maps.
---
### How Scoped Values Work Internally ?

The Mechanism: Immutability and Snapshots,
Unlike ThreadLocal, a ScopedValue is immutable.We don't set a value;we bind a value to a scope.

1. The Binding: When we call `ScopedValue.where(KEY, value).run(...)`, Java creates a `Snapshot` of the current bindings.
2. The Stack: Internally, these bindings are stored in a way that resembles a linked list of maps or a specialized search tree attached to the current Thread object.
3. Lookup: When we call `KEY.get()`, Java looks at the current thread's `scoped value cache`. If it’s not there, it traverses the bindings.
4. Automatic Cleanup: Once the `run()` or `call()` method finishes, the binding is popped off the stack.No explicit removal is required.
---

### Using Scoped Value in Concurrency

In traditional concurrency, passing data to a child thread requires explicit copying. 
With Scoped Values and StructuredTaskScope, child threads automatically inherit the bindings of the parent thread. 
Because the values are immutable, there is no risk of data races, and because they are scoped, 
there is no risk of the child thread outliving the data and causing a leak.

---
### Use Case 1 : Define and use a Scoped Value in a single-threaded context.
```java
import java.util.ScopedValue;

public class ScopedValueExample {
    
    // 1. Define the Scoped Value
    private final static ScopedValue<String> USER_ID = ScopedValue.newInstance();

    public static void main(String[] args) {
        // 2. Bind the value to a scope
        ScopedValue.where(USER_ID, "admin_user").run(() -> {
            // Inside this block, the value is accessible
            processRequest();
        });
    }
    
    private static void processRequest() {
        System.out.println("Processing request for: " + USER_ID.get());
        saveToDatabase();
    }

    private static void saveToDatabase() {
        System.out.println("Saving data for user: " + USER_ID.get());
    }
}

```
### Use Case 2: Scoped Value with Virtual Threads
```java
import java.util.ScopedValue;
import java.util.concurrent.StructuredTaskScope;

public class ScopedValueConcurrency {
    
    private final static ScopedValue<String> TRACE_ID = ScopedValue.newInstance();

    public static void main(String[] args) {
        
        ScopedValue.where(TRACE_ID, "TX-999").run(() -> {
            
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                // Forking child tasks (Virtual Threads)
                var subTask1 = scope.fork(() -> {
                    // This child thread automatically sees the parent's TRACE_ID
                    return "Task 1" + TRACE_ID.get() + "finished";
                });

                var subTask2 = scope.fork(() -> {
                    return "Task 2" + TRACE_ID.get() + "finished";
                });

                scope.join().throwIfFailed();
                
                System.out.println(subTask1.get());
                System.out.println(subTask2.get());
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

```