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
