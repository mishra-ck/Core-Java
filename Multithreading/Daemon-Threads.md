
## The Threads which are executing in the background are called Daemon Threads

Ex:  Garbage Collector , Signal Dispatcher, Attached Listener etc.
---
The main objective of Daemon thread of is to provide support for non-daemon threads-Main Thread.

Ex: If main thread runs with low memory then JVM runs garbage collector to destroy useless objects so that number of free bytes in memory will be improved.
With this free memory main thread  can continue its execution.

Usually daemon threads having low priority but based on our requirement daemon thread can be of high priority also.

**Daemon Related methods**:
```java
We can check daemon nature of a thread by using `isDaemon()` of thread class.
public boolean isDaemon();

We can change Daemon nature of a thread by using `setDaemon()` 
public void setDaemon(boolean b) ;

```
**Default Nature of Threads**:

By default main thread is always non-daemon in nature and for all remaining threads daemon nature will be inherited from Parent to Child 
i.e. Parent is daemon then automatically child thread is also daemon. 
If the parent thread is non-daemon then child thread is also non-daemon.

**NOTE**: 
It is im-possible to change daemon nature of Main Thread because it is already started by JVM at beginning.

