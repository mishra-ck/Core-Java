package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicAndVoliatile {

	volatile int value = 1 ;
	
	public static void main(String[] args) {
	
		AtomicAndVoliatile obj = new AtomicAndVoliatile();
		Runnable r = () ->{
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			obj.value++ ;  // thread-1 updates value
		};
		Thread thread = new Thread(r);
		thread.start();
		
		obj.value++ ;  // updated by main thread
		
		System.out.println(obj.value);
		
		// AtomicInteger can be used in place of synchronized block 
		/*
		synchronized(obj){
			value++ ;
		}
		this can be replaced with AtomicInteger with below methods 
		*/
		
		AtomicInteger atom = new AtomicInteger();
		System.out.println(atom.incrementAndGet());  // 1
		System.out.println(atom.decrementAndGet());  // 0
		System.out.println(atom.addAndGet(5));  // 5
		System.out.println(atom.getAndAdd(-2)); // 5 
		System.out.println(atom.compareAndSet(3, 3)); // true 
		

	}

}
