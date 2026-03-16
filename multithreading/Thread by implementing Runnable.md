Defining Thread by implementing Runnable interface:

We can define a thread by implementing Runnable interface



 
Runnable interface present in java.lang package and it contains only one method ,run() method.
	public void run()

Example:
public class ThreadDemo5 {

	public static void main(String[] args) 
	{
		MyRunnable1 r = new MyRunnable1() ;
		
		// Since runnable interface has only one method : run() 
		// we need to define a Thread object and pass our target runnable object in it
		
		Thread t = new Thread(r);  // passed Target runnable
		t.start();  // starting child thread
		
		System.out.println("Main Thread");  // executed by MAIN thread

	}

}

class MyRunnable1 implements Runnable
{
	public void run()
	{
		System.out.println("In child thread");  // executed by CHILD thread
	}
}

NOTE: we will get mixed output and can't tell exact output.

//OUTPUT: one possible
/**
 * Main Thread
In child thread
 */


CASE STUDY :

MyRunnable r = new MyRunnable() ;
Thread t1 = new Thread( ) ;
Thread t2 = new Thread( r ) ;

CASE 1:  t1.start() ;
A new thread will be created and which is responsible for the execution of Thread class run() method, which has empty implementation.

CASE 2:  t1.run() ;
No new thread will be created and Thread class run() method will be executed just like a normal method call.

CASE 3: t2.start() ;
A new thread will be created which is responsible for the execution of  MyRunnable class run() method.

CASE 4 : t2.run() ;
 A new thread won’t be created and MyRunnable run() will be executed just like a normal method call.

CASE 5 : r.start() ;
We will get compile time error saying  MyRunnable class doesn't have start capability 
CE: cannot find symbol
symbol : method start() 
location : class MyRunnable

CASE 6 : r.run() ;
NO new thread will be created and MyRunnable run() method will be executed like  normal method call.

NOTE: Which approach is best  to define a thread?

Among two ways of defining a thread , Implements Runnable approach is recommended.

In the first approach , our class always extends Thread class, there is no chance of extending any other class. Hence we are missing Inheritance benefits.

but in the 2 approach while implementing Runnable interface we can extend any other class, Hence we won't miss any inheritance benefits.

Because of above reason implementing Runnable interface is recommended than extending Thread class.




