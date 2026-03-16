package concurrency.threadlocal.Part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolRunnable {
    public static void main(String[] args) {
        PrintJob[] jobList = {
                new PrintJob("Ram"), new PrintJob("Shyam"), new PrintJob("Sita"),
                new PrintJob("Geeta"),new PrintJob("Shiva")
        };
        ExecutorService service = Executors.newFixedThreadPool(3);  // 3 threads in pool
        for(PrintJob job: jobList){
            service.submit(job);  // submit job for execution
        }
        service.shutdown();  // service shutdown after execution completed

    }
}
class PrintJob implements Runnable{
    String jobName ;
    PrintJob(String name){
        this.jobName = name ;
    }
    @Override
    public void run() {
        System.out.println(jobName + " --> Job started by thread --> " + Thread.currentThread().getName());
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println("Exception occurred: "+ e);
        }
        System.out.println(jobName + " --> job completed by thread --> " + Thread.currentThread().getName());
    }
}
