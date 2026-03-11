## The Threads which are executing in the background are called Daemon Threads.

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

1. It is impossible to change daemon nature of Main Thread because it is already started by JVM at beginning.
2. Whenever last non-daemon terminates automatically all daemon threads will be terminated irrespective of their positions.

### Example-1

```java

public class ThreadDemoDaemonThread extends Thread{

	public static void main(String[] args) 
	{
		// main is always non-daemon thread
		System.out.println(Thread.currentThread().isDaemon()); // false
		
		// trying to set main thread as daemon thread
		// RE:  java.lang.IllegalThreadStateException
		//Thread.currentThread().setDaemon(true);
		
		MyThread20 t = new MyThread20() ;
		// MyThread is non-daemon because parent thread is non-daemon
		System.out.println(t.isDaemon());  // false
		
		// but we can change it to daemon thread
		t.setDaemon(true);
		System.out.println(t.isDaemon());   // true
		
	}

}

class MyThread20 extends Thread 
{
	
}


```
### Example-2

```java
public class ThreadDemoDaemonThread extends Thread{

	public static void main(String[] args) 
	{
		// main is always non-daemon thread
		System.out.println(Thread.currentThread().isDaemon()); // false
		
		// trying to set main thread as daemon thread
		// RE:  java.lang.IllegalThreadStateException
		//Thread.currentThread().setDaemon(true);
		
		MyThread20 t = new MyThread20() ;
		// MyThread is non-daemon because parent thread is non-daemon
		System.out.println(t.isDaemon());  // false
		
		// but we can change it to daemon thread
		t.setDaemon(true);
		System.out.println(t.isDaemon());   // true
		t.start();
		System.out.println("End of main thread");
		
	}

}

class MyThread20 extends Thread 
{
	public void run()
	{
		for(int i = 0 ; i< 10 ; i++)
		{
			System.out.println("In child thread");
			
			try
			{
				Thread.sleep(2000);
			}catch(InterruptedException e) {}
		}
	}
}


```
