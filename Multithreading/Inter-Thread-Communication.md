## Inter-Thread Communication:

**Two threads can communicate with each other by using `wait()` , `notify()` and `notifyAll()` methods.**

The thread which is expecting updation is responsible to call wait() method, then immediately the thread will enter into waiting state.

The thread which is responsible to perform updation, after performing  updation it is responsible to call notify() method , 
then waiting will get that notification and continue its execution  with those updated items.

**NOTE: CONCLUSIONS**
1. wait() , notify() and notifyAll() methods are present in Object class.
2. wait() , notify() and notifyAll() present in Object class but not in Thread class because thread can call these methods on any java bject.
3. To call wait(), notify() or notifyAll()  methods on any object, Thread should be owner of that object i.e. the Thread should have lock of     that object that is the thread should be inside synchronized area.
4. Hence wait() , notify() and notifyAll() only form synchronized area otherwise we will get runtime exception saying:       		             **IllegalMonitorStateException**
5. IF a thread calls wait() on any object it immediately release the lock of that particular object and enters into waiting state.
6. If a thread call notify() on any object , it releases the lock of that object but may not immediately.
7. Except wait() , notify() and notifyAll() , there is no other method where thread releases the lock.

<img width="325" height="208" alt="image" src="https://github.com/user-attachments/assets/061f291b-fe95-431e-bc3e-53b036ed3de1" />


**SYNTAX** - wait() method:
1. public final void wait() throws InterruptedException
2. public final native void wait(long ms) throws InterruptedException
3. public final void wait(long ms , int ns) throws InterruptedException
4. public final native void notify() 
5. public final native void notifyAll()
	
**NOTE**:
Every wait() method throws InterruptedException which is checked exception hence whenever we are using wait method compulsory we should handle this InterruptedException either by try and catch or by throws keyword.
Otherwise we will get compile time error.

**Different Thread Execution Scenarios:**

**CASE-1: No Thread sleeps during execution:**

```java
public class ThreadInterThreadComm1 {

	public static void main(String[] args) throws InterruptedException
	{
		ThreadA t = new ThreadA() ;
		t.start();
		
		synchronized(t)
		{
			System.out.println("Main thread trying to call wait() method");
			t.wait() ;  // 1
		}
		// 4
		System.out.println("Main thread got notification");
		//5
		System.out.println(t.total);
	}

}

class ThreadA extends Thread
{
	int total = 0 ;
	 
	public void run()
	{
		synchronized(this)
		{
			// 2
			System.out.println("Child thread starts calculation");
			for(int i = 1 ; i <= 100 ; i++)
			{
				total = total + i ;
			}
			
			System.out.println("Child thread gives notification");
			this.notify();  // 3
		}
	}
	
}


// Output
/*
Main thread trying to call wait() method
Child thread starts calculation
Child thread gives notification
Main thread got notification
5050

*/

```

**CASE-2: When a Thread sleeps and wait for notification and notification was sent early by notifying thread**

```java
public class ThreadInterThreadComm1 {

	public static void main(String[] args) throws InterruptedException
	{
		ThreadA t = new ThreadA() ;
		t.start();
		
		Thread.sleep(10000);  // main thread went for sleeep for 10 sec
		synchronized(t)
		{
			System.out.println("Main thread trying to call wait() method");
			t.wait(1000) ;  // main thread wait for 1 sec notification after sleep
		}
		System.out.println("Main thread got notification");
		System.out.println(t.total);
	}

}

class ThreadA extends Thread
{
	int total = 0 ;
	 
	public void run()
	{
		synchronized(this)
		{
			System.out.println("Child thread starts calculation");
			for(int i = 1 ; i <= 100 ; i++)
			{
				total = total + i ;
			}
			
			System.out.println("Child thread gives notification");
			this.notify();
		}
	}
	
}



// Output :

Child thread starts calculation
Child thread gives notification
Main thread trying to call wait() method
Main thread got notification
5050

```

**Difference Between  `notify()` and `notifyAll()`**

We can use notify() to give the notification for only one waiting thread, if multiple threads are waiting then only one thread will be notified and the remaining threads have to wait for further notifications.

Which thread will be notified we can't tell , it depends on JVM(Thread scheduler).

We can use notifyAll() to give notification for all waiting threads of a particular object  even though multiple threads are notified but execution will be performed one-by-one because threads requires lock  and only  one lock is available on that object.


**NOTE: **

On which object we are calling wait() method thread require lock of that particular object from example if we are calling wait() on s1 then we have to get lock of s1 call but not on s2 object.
 
**Valid:**
```
synchronized(s1)
{
	///
	s1.wait() ;
}
```
**Invalid:**
```
synchronized(s1)
{
	///
	s2.wait() ;
}
```






