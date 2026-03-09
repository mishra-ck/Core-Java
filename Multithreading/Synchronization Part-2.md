## In this section, we will se multiple scenarios of sycnhronized keywords 

### Understanding why synchronized keyword is needed in multiple threaded programs

```java
public class ThreadDemoSynchronized2 {

	public static void main(String[] args) 
	{
		Display d1 = new Display();
		Display d2 = new Display();
		
		// creating 2 threads which will execute on same object 
		MyThread14 t1 = new MyThread14(d1, "Dhoni");
		MyThread14 t2 = new MyThread14(d2, "Yuvraj");
		
		t1.start();
		t2.start();		

	}

}

Even though wish() is synchronized we will get irregular output  because threads are operating in different java objects.

Output:
Good morining : Good morining : Dhoni
Good morining : Yuvraj
Good morining : Dhoni
Good morining : Yuvraj
Good morining : Dhoni
Good morining : Yuvraj
Good morining : Dhoni
Good morining : Yuvraj
Good morining : Dhoni
Yuvraj
public class ThreadDemoSynchronized2 {

	public static void main(String[] args) 
	{
		Display d1 = new Display();
		Display d2 = new Display();
		
		// creating 2 threads which will execute on same object 
		MyThread14 t1 = new MyThread14(d1, "Dhoni");
		MyThread14 t2 = new MyThread14(d2, "Yuvraj");
		
		t1.start();
		t2.start();		

	}

}

Even though wish() is synchronized we will get irregular output  because threads are operating in different java objects.

Output:
Good morining : Good morining : Dhoni
Good morining : Yuvraj
Good morining : Dhoni
Good morining : Yuvraj
Good morining : Dhoni
Good morining : Yuvraj
Good morining : Dhoni
Good morining : Yuvraj
Good morining : Dhoni
Yuvraj

```

### Conclusion:
1. If multiple threads are operating on same java objects then synchronization is required.
2. If multiple threads are operating multiple java objects then synchronization is not required.

### Class Level Lock:

Every class in java has a unique lock which is nothing but class level lock.

If a thread wants to execute a static synchronized method then thread required class level lock. Once thread got class level lock 
then it is allowed to execute any static synchronized method of that class.
Once the method execution completes automatically thread releases the lock.

While a thread executing static synchronized method the remaining threads are not allowed to execute any static synchronized method
of that class simultaneously but remaining threads are allowed to execute  the following methods simultaneously.
1. Normal static methods
2. synchronized instance methods

<img width="389" height="297" alt="image" src="https://github.com/user-attachments/assets/5f8290f1-48da-4b6b-8f41-56dfb855585c" />

Let's see this with an example:

```java
public class ThreadDemoSynchronized2 {

	public static void main(String[] args) 
	{
		Display d1 = new Display();
		Display d2 = new Display();
		
		// creating 2 threads which will execute on same object 
		MyThread14 t1 = new MyThread14(d1, "Dhoni");
		MyThread14 t2 = new MyThread14(d2, "Yuvraj");
		
		t1.start();
		t2.start();		

	}

}
class Display
{
	// synchronized method 
	//public synchronized void wish(String name)
	public static synchronized void wish(String name)
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


// with static synchronized method output will be
/*
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



