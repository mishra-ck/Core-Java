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

For example:
```java
class X
{
   synchronized area
   {
      we are performing operation[add/delete/replace/update]
      where  object state changes
   }
   non-synchronized area
   {
      where object state remains same
      operation[read]
   }
}
```
### Let's see this with proper example :

```java
	public class ThreadDemoSynchronized {
	
		public static void main(String[] args)
		{
			
			Display d = new Display();
			
			// creating 2 threads which will execute on same object 
			MyThread14 t1 = new MyThread14(d, "Dhoni");
			MyThread14 t2 = new MyThread14(d, "Yuvraj");
			
			t1.start();
			t2.start();
		}
	
	}
	
	class Display
	{
		// synchronized method 
		public synchronized void wish(String name)
		{
			for(int i =0 ; i< 5 ; i++)
			{
				System.out.print("Good morining : ");
				try
				{
					Thread.sleep(2000);
				}
				catch(InterruptedException e) {}
				System.out.println(name);
			}
		}
	}
	
	class MyThread14 extends Thread
	{
		Display d ;
		String name ;
		MyThread14(Display d, String name)
		{
			this.d = d;
			this.name = name ;
		}
		public void run()  // job of thread
		{
			d.wish(name);
		}
	}
	
	
	/* Output: Regular
	Good morining : Dhoni
	Good morining : Dhoni
	Good morining : Dhoni
	Good morining : Dhoni
	Good morining : Dhoni
	Good morining : Yuvraj
	Good morining : Yuvraj
	Good morining : Yuvraj
	Good morining : Yuvraj
	Good morining : Yuvraj

```
### NOTE:
If we are not declaring wish()  as synchronized then both threads will be executed simultaneously and hence we will get irregular output.
If we declare wish method as synchronized then at a time only one thread is allowed to execute wish() on the given Display object.
Hence we will get regular output.


