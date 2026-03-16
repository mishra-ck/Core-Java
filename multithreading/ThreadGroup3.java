package concurrency.threadlocal.Part1;

public class ThreadGroup3 {
    public static void main(String[] args) {
        // reference of system group
        ThreadGroup system = Thread.currentThread().getThreadGroup().getParent() ;
        Thread[] t = new Thread[system.activeCount()] ;
        system.enumerate(t) ;

        for(Thread t1 : t){
            System.out.println(t1.getName() + "--->" + t1.isDaemon());
        }
    }
}
/*
 * Output :
 * Reference Handler--->true
 * Finalizer--->true
 * Signal Dispatcher--->true
 * Notification Thread--->true
 * main--->false
 * Monitor Ctrl-Break--->true
 * Common-Cleaner--->true
 */