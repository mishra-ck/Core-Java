Scoped Values- A safer alternative to `ThreadLocal`, for virtual threads.
---
### Understand why it required ?

To understand `Scoped values`, try to understand `ThreadLocal` first. 
ThreadLocal allows mutability and has lifetime as thread itself. If a thread lives long time so does the data.
Further inheriting ThreadLocal data in child is expensive copying of maps.

### How Scoped Values Work Internally ?

The Mechanism: Immutability and Snapshots,
Unlike ThreadLocal, a ScopedValue is immutable.We don't set a value;we bind a value to a scope.

1. The Binding: When we call `ScopedValue.where(KEY, value).run(...)`, Java creates a `Snapshot` of the current bindings.
2. The Stack: Internally, these bindings are stored in a way that resembles a linked list of maps or a specialized search tree attached to the current Thread object.
3. Lookup: When we call `KEY.get()`, Java looks at the current thread's `scoped value cache`. If it’s not there, it traverses the bindings.
4. Automatic Cleanup: Once the `run()` or `call()` method finishes, the binding is popped off the stack.No explicit removal is required.


   
