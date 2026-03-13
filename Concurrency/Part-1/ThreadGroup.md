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
