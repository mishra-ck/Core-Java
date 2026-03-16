package concurrency.threadlocal.Part1;
public class ThreadLocalDemo3 {
    public static void main(String[] args) {
        CustomerThread c1 = new CustomerThread("Customer T-1");
        CustomerThread c2 = new CustomerThread("Customer T-2");
        CustomerThread c3 = new CustomerThread("Customer T-3");
        CustomerThread c4 = new CustomerThread("Customer T-4");
        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}

class CustomerThread extends Thread {
 static Integer custId = 0 ;
 ThreadLocal<Integer> tl = new ThreadLocal<>(){
     protected  Integer initialValue(){
         return ++custId ;
     }
 };
    CustomerThread(String name){
        super(name);
    }
    public void run(){
        System.out.println( Thread.currentThread().getName() + "  --> is executing with customer id  --> " + tl.get());
    }
}
