
Defining a thread:
We can define a thread in the following 2 ways:
	1. By extending Thread class
	2. By implementing Runnable interface
	
	By extending Thread class:
	
	public class ThreadDemo1 {
	
		public static void main(String[] args)   //MAIN thread
		{
			// No. of thread here : 1
			
			MyThread t = new MyThread() ;  //Child Thread initiation 
			
			t.start();   // starting of a thread
			//No. of thread here : 2
			
			// executed by MAIN thread
			for(int i = 0 ; i < 5 ; i ++)
			{
				System.out.println("In MAIN thread execution block");
			}
			
		}
	
	}
	
	class MyThread extends Thread   // creating thread by extending Thread class
	{
		public void run()  // implementing run() of Thread class
		{
			for(int i = 0 ; i < 5 ; i++)     // this FOR loop is JOB of the Thread
			{
				System.out.println("In CHILD thread execution block");
			}
		}
	}
	
	/*  OUTPUT: One Possible output
	 * In MAIN thread execution block
	In MAIN thread execution block
	In MAIN thread execution block
	In MAIN thread execution block
	In CHILD thread execution block
	In CHILD thread execution block
	In CHILD thread execution block
	In CHILD thread execution block
	In CHILD thread execution block
	In MAIN thread execution block
	 * */
	
	
	
	NOTE: Since every thread is independent, we do not have to worry about order of execution of threads
	
	Case 1: Thread Scheduler
	It is the part of JVM .
	
	It is responsible to schedule threads , if multiple threads are waiting to get the chance of execution then in which order threads will be executed is decided by  Thread scheduler.
	We can't expect exact algorithm followed by thread scheduler , it varies from JVM to JVM 
	hence we can't expect thread execution order and exact output.
	Hence whenever situation comes to multithreading there is no guarantee for exact output but we can provide several possible outputs.
	Scheduling algorithms : Time Slicing , Primitive scheduling ,
	
	CASE 2: Difference between t.start()  and t.run() 
	
	In the case of t.start() a new thread will be created which is responsible for the execution of run() method.
	But  in the case of t.run() a new thread will not be created and run() will be executed just like normal method call by  MAIN thread.
	Hence above program if we replace t.start() with t.run() then the output will be:
	
	In CHILD thread execution block
	In CHILD thread execution block
	In CHILD thread execution block
	In CHILD thread execution block
	In CHILD thread execution block
	In MAIN thread execution block
	In MAIN thread execution block
	In MAIN thread execution block
	In MAIN thread execution block
	In MAIN thread execution block
	
	This total output produced by MAIN thread only.
	
	CASE 3: Importance of Thread class start() method 
	Thread class start method is responsible to register the thread with Thread scheduler and all other mandatory activities.
	Hence without executing thread class start() there is no chance of starting a new thread in java, due to this thread class start() is considered as Heart of Multithreading.
	
	start()
	{
	1. Register thread object with thread scheduler
	2. Perform all other mandatory activities
	3. Invoke run() ;
	}
	
	CASE 4 :  Overloading of run() method 
	
	Overloading of run() is always possible but thread class start() can invoke no argument run() method. The other overloaded method we have to call explicitly like a normal method call.
	Example:
	public class ThreadDemo2 {
	
		public static void main(String[] args)
		{
			MyThread2 t = new MyThread2();
			t.start();  // will run on no-arg run() 
			// O/P : In no-arg method
		}
	
	}
	
	class MyThread2 extends Thread 
	{
		public void run()
		{
			System.out.println("In no-arg method");
		}
		public void run(int i)
		{
			System.out.println("In int-arg method");
		}
		
	}
	
	CASE 5 : If we are not overriding run()  method 
	If we are not overriding run() then  thread class run() will be executed which has empty implementation , Hence we won't get any output.

	public class ThreadDemo3 {
	
		public static void main(String[] args) {
			
			MyThread3 t  = new MyThread3() ;
			t.start();
			// NO OUTPUT will be printed as run() of thread class is empty
		}
	
	}
	
	class MyThread3 extends Thread 
	{
		// run() of thread class is not overridden 
		// run() of Thread class will be executed
	}
	
	NOTE: It is highly recommended to override run() otherwise don't go for multithreading concept.
	
	CASE 6 : Overriding of start() method
	
	If we override start()  method then our start() will be executed just like a normal method call and new thread won't be created.
	There will a single thread(MAIN thread)  only which will execute the program.
	NOTE: It is not recommended to override start() method , otherwise don't go for multithreading concept.
	
	Scenario 1:
	public class ThreadDemo4 {
	
		public static void main(String[] args) 
		{
			MyThread4  t = new MyThread4() ;
			t.start();  
			//In start() method
			
			System.out.println("In main method"); // In main method
			
		}
	
	}
	class MyThread4 extends Thread 
	{
		public void start()
		{
			System.out.println("In start() method");
		}
		
		public void run()
		{
			System.out.println("In run() method");
		}
	}
	OUTPUT:
	In start() method
	In main method
	
	Scenario 2:
	public class ThreadDemo4 {
	
		public static void main(String[] args) 
		{
			MyThread4  t = new MyThread4() ;
			t.start();  
			//In start() method
			
			System.out.println("In main method"); // In main method
			
		}
	
	}
	
	class MyThread4 extends Thread 
	{
		public void start()
		{
			// we can call start() of Thread class also
			super.start();
			System.out.println("In start() method");
		}
		
		public void run()
		{
			System.out.println("In run() method");
		}
	}
	 possible output:
	In start() method
	In main method
	In run() method
	
	
	CASE 7 :
	After starting a thread if we are trying to re-start the same thread then we will get run-time exception saying : IllegalThreadStateException .
	
	Thread t = new Thread() ;
	t.start() ;
	…
	..
	t.start() ; // RE: IllegalThreadStateException 
	
	
