### The problems with traditional `synchronized` keyword:

1. We are not having any flexibility to  try for a lock without waiting.
2. There is no way to specify maximum waiting time for a thread to get lock so that thread will wait until getting the lock which may creates performance problem and may cause deadlock.
3. If a thread releases a lock then which waiting thread will get that lock we are not having any control on this.
4. There is no API to list all waiting threads for a lock.
5. The `synchronized` keyword compulsory to be used either at method level or within a method, not possible to use across multiple methods.
6. To overcome these problems sun introduced java.util.comcurrent.locks package in 1.5 version,
   it also provide several enhancements to the programmers to provide more control on concurrency.
   
**Lock interface**:

1. Lock Object is similar to implicit lock acquired by a thread to execute a synchronized method of synchronized block.
2. Lock implementation provide more extensive operations than traditional implicit locks.

**Important methods of Lock Interface**:
```java

1. Void lock()
/**
we can use this method to acquire a lock, if lock is already available then immediately current thread will get that lock.
If the lock is not available then it will wait until getting the lock.
It is exactly same behavior of traditional synchronized keyword.
*/

2. Boolean tryLock()
/**
To acquire the lock without waiting , if the lock is available then the thread acquires the lock and returns true and
if the lock is not available then this method returns false and can continue can its execution without waiting.
In this case thread can never enter into waiting state.
*/
 Lock lock = ...;  
 if (lock.tryLock()){
    try {      // manipulate protected state    
    } 
    finally { 
    lock.unlock();    
    }  
  } 
    else 
  {    // perform alternative actions  }

3. Boolean tryLock(long time, TimeUnit unit)
/**
If lock is available then the thread will get the lock and continue the execution.
If the lock is not available then the thread will wait until specified amount of time.
Still if the lock is not available then thread can continue it's execution.
TimeUnit : It is an enum present in Java.util.concurrent package .
*/
	Enum TimeUnit{
		 NANOSECONDS;
		 MICROSECONDS;
		 MILLISECONDS;
		 SECONDS;
		 MINUTES;
		 HOURS;
		 DAYS;
		
	}

4. Void lockInterruptibly()
/** acquires a lock if it is available and returns immediately, If the lock is not available it will wait .
While waiting if the thread is interrupted then won't get the lock.
*/

5. Void unlock()
/**
To releases a lock , to call this method compulsory current thread should be owner of the lock
otherwise we will get Runtime Exception saying IllegalMonitorStateException .
*/

```


