package concurrency.threadlocal.Part1;

import java.util.concurrent.*;

public class ThreadPoolCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable[] jobs = {new MyCallable(10), new MyCallable(20), new MyCallable(30),
                new MyCallable(40), new MyCallable(50)};
        ExecutorService service = Executors.newFixedThreadPool(3);
        for(MyCallable job :jobs){
            Future f = service.submit(job) ;
            System.out.println(f.get());
        }
        service.shutdown();
    }
}
class MyCallable implements Callable{
    int num ;
    MyCallable(int num){
        this.num = num ;
    }
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " is responsible to find the sum of first  "+ num + " numbers : ");
        int sum = 0 ;
        for (int i =0 ; i<= num ; i++){
            sum += i ;
        }
        return sum ;
    }
}
