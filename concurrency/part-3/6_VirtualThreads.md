Virtual Threads - Project Loom
---
### Overview of Transition
```
☑️ Earlier: 1 request = 1 platform thread = 1 OS thread.
☑️ OS Threads are expensive(~1MB) in memory and require kernal context switching.
☑️ Blocking I/O: thread sits idle waiting for network/disk/DB.

Virtual Thread Solution:
✅ 1 virtual thread(~1KB) heap, JVM managed, can have millions.
✅ Blocking I/O - JVM parks virtual threads, OS thread(carrier) is freed for other virtual threads.

```
### How it works ?
```
* Mounting: The JVM scheduler picks a Virtual Thread and "mounts" it onto a Carrier Thread. 
            The stack frames of the virtual thread are copied from the Heap to the carrier thread's stack.
* Execution: The code runs normally on CPU.
* Yield: When blocking i/o call happens, the virtual thread yields.
* Unmounting: The JVM takes a snapshot of the current stack and saves it back to the Heap.
             The carrier thread is now free to go do other work.
* Resuming: Once blocking call is done, the scheduler finds an available Carrier thread,
          restores the stack from Heap, and Virtual thread continues where if left out.
```