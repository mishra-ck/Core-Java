## Inter-Thread Communication:

**Two threads can communicate with each other by using `wait()` , `notify()` and `notifyAll()` methods.**

The thread which is expecting updation is responsible to call wait() method, then immediately the thread will enter into waiting state.

The thread which is responsible to perform updation, after performing  updation it is responsible to call notify() method , 
then waiting will get that notification and continue its execution  with those updated items.

**NOTE: CONCLUSIONS**
	1. wait() , notify() and notifyAll() methods are present in Object class.
	2. wait() , notify() and notifyAll() present in Object class but not in Thread class because thread can call these methods on any java object .
	3. TO call wait(), notify() or notifyAll()  methods on any object, Thread should be owner of that object i.e. the Thread should have lock of that object that is the thread should be inside synchronized area.
	4. Hence wait() , notify() and notifyAll() only form synchronized area otherwise we will get runtime exception saying : **IllegalMonitorStateException** 
	5. IF a thread calls wait() on any object it immediately release the lock of that particular object and enters into waiting state.
	6. If a thread call notify() on any object , it releases the lock of that object but may not immediately.
	7. Except wait() , notify() and notifyAll() , there is no other method where thread releases the lock.

------------------------------------------
| Method      | Is thread releases lock? |
------------------------------------------
| yield()     | NO                        |
| join()	    | NO                        |
| sleep()     | NO                        |
| wait() 	    | YES                       |
| notify()    | YES                       |
| notifyAll() |	YES                       |
-------------------------------------------

**SYNTAX**:
1. wait() method:
2. public final void wait() throws InterruptedException
3. public final native void wait(long ms) throws InterruptedException
4. public final void wait(long ms , int ns) throws InterruptedException
5. public final native void notify()
6. public final native void notifyAll()
	
**NOTE**:
Every wait() method throws InterruptedException which is checked exception hence whenever we are using wait method compulsory we should handle this InterruptedException either by try and catch or by throws keyword.
Otherwise we will get compile time error.
