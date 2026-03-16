It is the implementation class of `LOCK` interface and it is the direct child class Object class. 

Reentrant means a thread can acquire same lock multiple times without any issue. 
Internally reentrant lock increment thread personal count whenever we call lock method and decrements count value 
whenever thread calls unlock()  and lock will be released whenever count reaches 0.



### Constructors :
1. ReentrantLock l = new ReentrantLock() --> creates an instant of this class
2. ReentrantLock l = new ReentrantLock (Boolean fairness) --> creates reentrant lock with the given fairness policy. If the fairness is true then longest waiting thread can acquire the lock if it is available i.e. follows first come first serve policy. If fairness is false then which waiting thread will get chance can't except. The default for fairness if FALSE.

### Which of the following declarations are equal :
1. ReentrantLock l = new ReentrantLock() ;
2. ReentrantLock l = new ReentrantLock(true);
3. ReentrantLock l = new ReentrantLock(false);
4. All of the above ;
   
Answer :  1 and 3 --> default value is false

### Important methods of ReentrantLock class : 

**Methods from Lock interface** :
1. Void lock() ;
2. Boolean tryLock() ;
3. Boolean tryLock(long x, TimeUnit t);
4. Void lockInterruptibly() ;
5. Void unlock() ;

--------------------------------------------------------------------------------------------------

✅ Int getHoldCount() --> returns number of hold on this lock by current thread.
✅ Boolean isHeldByCurrentThread() --> returns true if an only if lock is held by current thread.
✅ Int getQueueLongth() --> returns the number of threads waiting for the lock
✅ Collection getQueuedThreads --> it returns a collection of threads which are waiting to get the lock.
✅ Boolean hasQueuedThreads() --> returns true If any thread waiting to get the lock .
✅ Boolean isLocked() --> returns true if the lock is acquired by some thread.
✅ Boolean isFair() --> returns true if the fairness policy is set with true value
✅ Thread getOwner() --> returns the thread which acquires the lock.

----------------------------------------------------------------------------------------------------
