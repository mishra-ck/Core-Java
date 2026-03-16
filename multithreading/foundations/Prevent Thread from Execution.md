### We can prevent a thread execution by using the following methods:

1. `yield()`
2. `join()`
3. `sleep()`


````
1. yield() method:
	
This method  pauses execution of current  thread to give the chance for waiting thread of same priority , if there is no waiting thread or all waiting threads have low priority then same thread can continue it's execution.
Ex: long batsman required this method

If multiple threads are waiting with same priority then which waiting thread will get the chance to execute we can't tell it depends on thread scheduler.

The thread which is yielded, when it will get the chance once again to execute it depends on Thread scheduler. We can't tell exactly when it will get chance again.

syntax : public static  native void yield()

Life cycle of a thread when  it calls yield() ;

Example: 
	
	public class ThreadDemoYield {
	
		public static void main(String[] args)
		{
			MyThread9 t = new MyThread9();
			t.start();
			
			for(int i = 0 ;  i < 5 ; i++)
			{
				System.out.println("In main thread");
			}
	
		}
	
	}
	
	class MyThread9 extends Thread
	{
		public void run()
		{
			for(int i = 0 ; i < 5 ; i++)
			{
				System.out.println("In child thread");
				Thread.yield();    // calling yield()
			}
		}
	}
	Output: one possible
	In main thread
	In main thread
	In child thread
	In child thread
	In child thread
	In child thread
	In child thread
	In main thread
	In main thread
	In main thread
	
	
	NOTE: 
	In the above program , if we are commenting line(yield() calling) both threads will executed simultaneously and we can't expect who will complete first.
	If we are not commenting() then child always calls yield() method because of that main thread will get chance more number of times and the chance of completing main thread first is high.

	Some platforms won't provide proper support for yield method .

------------------------------------------------------------------------------------------------------------------
  
	2. join() method :
	
	If a thread wants to wait until completion of some other thread then we should go for join() method.
		For example , if a thread t1 wants to wait until completing t2 thread then t1 
		has to call t2.join() 
	If t1 executes t2.join() then immediately will enter into waiting state until t2 completes.
	Once t2 completes then t1 can continue its's execution.
	
	Example: Analogy 

	Wedding card printing thread(t2) has to wait until venue fixing thread(t1) completion, hence t2 has to call t1.join().
	Wedding cards distribution(t3) has to wait until wedding card printing thread(t2) completion , hence t3 has to call t2.join() .
	
	A thread can have any number of Join calls.
	
	Syntax of join() : 
	1. public final void join() throws InterruptedException
	2. public final void join(long millisecond) throws InterruptedException
	3. public final void join(long millisecond , int nanosecond) throws InterruptedException
	
	NOTE: Every join method throws InterruptedException which is checked Exception.
	Hence compulsory we should this exception either by using try and catch or by throws keyword, otherwise we will get  compile time error.
	
	
	Example:
	
	public class ThreadDemoJoin {
	
		public static void main(String[] args) throws InterruptedException
		{
			MyThread10 t = new MyThread10();
			t.start();
			
			// main wants to join child thread t by clling join()
			//t.join();  // join after seeta completes, executed by main thread so main will wait child to complete 
			t.join(600) ;
			for(int i =0 ;i < 5 ; i++)
			{
				System.out.println("Rama Thread");
			}
	
		}
	
	}
	class MyThread10 extends Thread
	{
		public void run()
		{
			for(int i = 0; i< 5 ; i++)
			{
				System.out.println("Seeta thread");
				try 
				{
					Thread.sleep(2000);
				}
				catch(InterruptedException e){ }
				
			}
		}
	}
	
	
	/*
	 IN case of join() output is:
	 Seeta thread
	Seeta thread
	Seeta thread
	Seeta thread
	Seeta thread
	Rama Thread
	Rama Thread
	Rama Thread
	Rama Thread
	Rama Thread
	 
	In case we pass argument in join():
	Seeta thread
	Rama Thread
	Rama Thread
	Rama Thread
	Rama Thread
	Rama Thread
	Seeta thread
	Seeta thread
	Seeta thread
	Seeta thread
	 
	 
	 */
	
	
	CASE 2 : Waiting of child thread until completing main thread.
	
	class ThreadDemoJoin2 {
	
		public static void main(String[] args)
		{
			MyThread11.mt = Thread.currentThread() ;
			
			MyThread11 t = new MyThread11() ;
			t.start();
			
			for(int i = 0 ; i < 5 ; i++)
			{
				System.out.println("Main Thread");
			}
		}
	}
	
	class MyThread11 extends Thread
	{
		static Thread mt ;
		public void run()
		{
			try 
			{
				mt.join();
			}catch(InterruptedException e) { }
			
			for(int i = 0 ; i < 5 ; i++)
			{
				System.out.println("Child Thread");
			}
		}
	}
	
	In the above example child class call join() on main thread object, hence child thread has to wait until completing main thread. 
	In this case o/p is 
	
	/*
	Main Thread
	Main Thread
	Main Thread
	Main Thread
	Main Thread
	Child Thread
	Child Thread
	Child Thread
	Child Thread
	Child Thread
	
	 */
	
	
	
	CASE 3: If main thread calls join() on child thread object and child thread calls join() on main thread object , then both threads will wait forever and the program will be paused(this is something like deadlock).
	
	Ex: 
	class ThreadDemoJoin2 {
	
		public static void main(String[] args) throws InterruptedException
		{
			MyThread11.mt = Thread.currentThread() ;
			
			MyThread11 t = new MyThread11() ;
			t.start();
			t.join();
			for(int i = 0 ; i < 5 ; i++)
			{
				System.out.println("Main Thread");
			}
		}
	}
	
	class MyThread11 extends Thread
	{
		static Thread mt ;
		public void run()
		{
			try 
			{
				mt.join();
			}catch(InterruptedException e) { }
			
			for(int i = 0 ; i < 5 ; i++)
			{
				System.out.println("Child Thread");
			}
		}
	}
	
	CASE 4:  If thread calls join() on the same thread it-self, then the program will be stuck (this is something like dead-lock). In this case thread has to wait infinite amount of time.
	
	Ex:
	class Test{
	public static void main(String[] args){
		Thread.currentThread().join() ;
	}
	}

------------------------------------------------------------------------------------------------------------------

	3.sleep() method: 
	 
	If a thread don't want to perform any operation for a particular amount of time, then we should go for sleep() method.
	
	Syntax:
	1. public static native void sleep(long ms) throws InterruptedException
	2. public static void sleep( long ms , int ns)  throws InterruptedException
	
	NOTE : Every sleep method throws InterruptedException, which is checked exception. Hence whenever we are using sleep method compulsory we should handle InterruptedException either by try and catch block or by throws keyword, otherwise we will get compile time error. 
	
	
	
	Example: 
	
	public class ThreadDemoSleep {
	
		public static void main(String[] args) throws InterruptedException 
		{
			
			for(int i = 0 ; i < 5 ; i++)
			{
				System.out.println("Slide number:" + i);
				Thread.sleep(2000) ;
			}
		}
	
	}
	
	
	/*
	Slide number:0
	Slide number:1
	Slide number:2
	Slide number:3
	Slide number:4
	
	 */
	
	HOW a thread can interrupt another thread ?
	A thread can interrupt a sleeping thread or a waiting thread by using interrupt() method of  thread class.
	syntax:  public void interrupt() ;
	
	Example:
	
	public class ThreadDemoInterrupt {
	
		public static void main(String[] args) throws InterruptedException
		{
			MyThread12 t = new MyThread12() ;
			t.start();
			
			t.interrupt();  // this line is executed by main thread
			// it interrupts the child thread 
			for(int i = 0 ; i < 5 ; i++)
			{
				System.out.println("Main thread executing");
			}
			
		}
	
	}
	
	class MyThread12 extends Thread
	{
		public void run()
		{
			try
			{
				for(int i = 0 ; i < 5 ; i++)
				{
					System.out.println("This is lazy thread");
					Thread.sleep(2000);
				}
			}
			catch(InterruptedException e)
			{
				System.out.println("Thread got interrupted...!");
			}
		}
	}
	
	
	If we comment(t.interrupt) then main thread won’t interrupt child  thread , in this case child thread will execute for loop 5 times.
	If we are not commenting (t.interrupt) the line then main thread interrupts child thread in this case o/p is :
	
	
	/* 
	Main thread executing
	Main thread executing
	Main thread executing
	Main thread executing
	Main thread executing
	This is lazy thread
	Thread got interrupted...!
	 */
	
	
	NOTE: 
	Whenever we are calling interrupt method if the target thread is not in sleeping  or waiting step then there is no impact of interrupt call immediately.
	Interrupt will wait until target thread entered into sleeping or waiting state.
	
	If the target thread sleeping or waiting state,  then immediately interrupt call will interrupt the target thread.
	
	If the target thread never enters into sleeping or waiting state in its life time then there is no impact of interrupt call, this is the only case where interrupt call will be wasted.
	
	Example:
	
	public class ThreadDemoInterrupt2 {
	
		public static void main(String[] args)
		{
			MyThread13 t = new MyThread13() ;
			t.start();
			
			t.interrupt();
			
			System.out.println("Main thread executed");
	
		}
	
	}
	
	class MyThread13 extends Thread
	{
		public void run()
		{
			for(int i=0; i<10000 ; i++)
			{
				System.out.println("This is lazy thread..::");
			}
			System.out.println("Want to sleep..do not interrupt");
			try {
				
				Thread.sleep(2000) ;
			}
			catch(InterruptedException e)
			{
				System.out.println("Thread got interrupted..!! ");
			}
		}
	}
	
	
	In the above example interrupt call waited until child thread for loop (10000) times.

	
```
	
