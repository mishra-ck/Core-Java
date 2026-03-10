**If two threads are waiting for each other forever, such type of infinite waiting is called Dead Lock**

`synchronized` keyword is the only reason for dead lock situation hence while using synchronized keyword we have to take special care.

There are no resolution techniques for deadlock but several prevention techniques are available.

Let's take an example to understand it further:

```java
public class ThreadDemoDeadLock  extends Thread
{
	A a = new A() ;
	B b = new B() ;
	
	public void m1()
	{
		this.start();  // this will be executed by main thread
		a.d1(b);
	}
	
	public void run()
	{
		b.d2(a);
	}
	
	public static void main(String[] args)
	{
		ThreadDemoDeadLock d = new ThreadDemoDeadLock() ;
		d.m1();
	}
	
}


class A 
{
	public synchronized void d1(B b)
	{
		System.out.println(" Thread1 starts execution of d1() ");
		
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
		
		System.out.println("Thread1 trying to call B's last() ");
		b.last();
	}
	public synchronized void last()
	{
		System.out.println("last() of A class");
	}
}

class B 
{
	public synchronized void d2(A a)
	{
		System.out.println(" Thread2 starts execution of d2() ");
		
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
		
		System.out.println("Thread1 trying to call A's last() ");
		a.last();
	}
	public synchronized void last()
	{
		System.out.println("last() of B class");
	}
}

```

```
// Output while using synchronized at all 4 method in class A and B
/*
 Thread1 starts execution of d1() 
 Thread2 starts execution of d2() 
Thread1 trying to call B's last() 
Thread1 trying to call A's last() 
*/


// Output while using normal last()  method
/*
 Thread1 starts execution of d1() 
 Thread2 starts execution of d2() 
Thread1 trying to call A's last() 
last() of A class
Thread1 trying to call B's last() 
last() of B class

*/

```

NOTE:
```
In the above program if we remove at least one synchronized then the program won't enter into deadlock situation , 
hence synchronized keyword is only reason for deadlock situation.
Due to this while using synchronized keyword we have to take special care.
```

Deadlock vs Starvation:
```
Long waiting of q thread where waiting never ends is called Dead lock, 
Whereas long waiting of thread where waiting  ends at certain point is called Starvation.
Ex: Low priority thread has to wait until completing 
all high priority threads, it may be long waiting but ends at certain point which is nothing but starvation.<img width="597" height="216" alt="image" src="https://github.com/user-attachments/assets/73b4417f-1136-47a2-9459-9d88c55269e5" />
```

