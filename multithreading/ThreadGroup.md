Based on functionality we can group threads into a single unit which is nothing but Thread group i.e. thread group contains a group of threads.
In addition to thread group can also contain sub-thread group.
---
### The main advantage of maintaining thread group is we can perform common operation easily.
```
✅ Every thread in java belongs to some group.
✅ Main thread belongs to main group.
✅ Every thread group in java is the child group of system group either directly or indirectly.
✅ Hence system group acts as root for all thread group in java.
```
### System group contains several system level threads like
1. Finalizer
2. Reference handler
3. Signal Dispatcher
4. Attach listener

ThreadGroup is a java class, present in java.lang package and it the direct child class of Object class.

### Constructors :
```
1. ThreadGroup g = new ThreadGroup(String gname); -->  creates a new thread group with given name.
   The parent of this new group is the thread group of currently executing thread.
	 ThreadGroup g1 = new ThreadGroup("First Group") ;
2. ThreadGroup g = new ThreadGroup(ThreadGroup parentGroup,String groupName) ;  --> creates a new thread group with given group name,
   The parent of this new thread group is  specified parent group.
   ThreadGroup g2 = new ThreadGroup(g1,"Second Group") ;
```
**Important methods of ThreadGroup class** :
```

1. String getName() -->  returns name of the thread group
2. Int getMaxPriority() --> returns max priority thread of ThreadGroup
3. Void setMaxPriority(int p) -->  to set maximum priority of thread group , default max. priority is 10. threads in the thread group that have 		already higher priority wont be affected but for newly added thread changed max priority is applicable.
4. ThreadGroup getParent() --> returns parent group of current thread
5. Void list() --> it prints info about thread group to the console
6. Int activeCount(). --> returns number of active threads present in the thread group
7. Int activeGroupCount() --> returns number of active groups present in the current thread group
8. Int enumerate(Thread[] t). --> to copy all active thread of this thread group into provided Thread array. In this case sub-thread group 				threads will also be considered.
9. Int enumerate(ThreadGroup[] g) --> to copy all active sub-thread groups into thread group array.
10. Boolean isDaemon() --> to check Thread group is Daemon or not
11. Void setDaemon(boolean b) --> change the nature of thread group as Daemon or not
12. Void interrupt() --> to interrupt all waiting/sleeping threads present in the thread group.
13. Void destroy() --> to destroy thread group and its sub-thread group.

```
### ThreadGroup hierarchy:
<img width="535" height="551" alt="image" src="https://github.com/user-attachments/assets/0fd6a7a7-6a86-48e6-bc7a-c84c88bee35e" />

### Daemon threads present in system group :
```

1. Reference Handler : this thread puts objects that are no longer needed into the queue to be processed by the Finalizer thread.
2. Finalizer : this thread performs finalizations for objects that no longer need to release system resources.
3. Signal Dispatcher : this thread handles signals sent by the operating system to the JVM.
4. Monitor Ctrl-Break : is a key combination to key thread dump at Java application console.
5. Common-Cleaner : Cleaner manages a set of object references and corresponding cleaning actions.

```
