## Synchronization Concept
```
1. Synchronized is the modifier applicable only for methods and blocks but not for classes and variables.
2. If multiple threads are trying to operate simultaneously on same java object then there may be a chance of data inconsistency problem,
   to overcome this problem we should go for synchronized keyword.'
3. If a method or block is declared as synchronized then at a time only one thread is allowed to execute or block on the given object so that data inconsistency problem will be resolved.
4. The main advantage of synchronized keyword is we can resolve data inconsistency problems but the main dis-advantage of synchronized keyword is it increases waiting time of threads and creates performance problems.
   Hence if there is no specific requirement then it is not recommended to use synchronized keyword.
5. Internally synchronization concept is implemented by using lock. Every object in java has a unique lock.
   Whenever we are using synchronized keyword then only lock concept will come into the picture.
6. If a thread wants to execute synchronized method on the given object first it has to get lock of that object, once thread got the lock then it is allowed to execute any synchronized method on that object.
   Once method execution completes automatically thread releases the lock.
7. Acquiring and releasing lock internally taken care by JVM, and programmer not responsible for this activity.

NOTE: While a thread executing a synchronized method on a given object the remaining threads are not allowed to execute any synchronized method simultaneously on the same object.
But remaining threads are allowed to execute non-synchronized methods simultaneously.

```
### For every object:

<img width="604" height="322" alt="image" src="https://github.com/user-attachments/assets/e452e0ab-08bf-4eb5-8b3f-1a4dd2e08d62" />
