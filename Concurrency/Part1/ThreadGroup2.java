package concurrency.threadlocal.Part1;

// Important Methods present in ThreadGroup class
public class ThreadGroup2 {
    public static void main(String[] args) throws Exception {
        ThreadGroup g1 = new ThreadGroup("g1");
        Thread t1 = new Thread(g1,"first thread");
        Thread t2 = new Thread(g1, "second thread");

        g1.setMaxPriority(3);
        Thread t3 = new Thread(g1,"third thread");
        System.out.println(t1.getPriority());
        System.out.println(t2.getPriority());
        System.out.println(t3.getPriority());

        ThreadGroup pg = new ThreadGroup("Parent group");
        ThreadGroup cg = new ThreadGroup(pg, "Child group");
        MyThread t4 = new MyThread(pg,"Thread-1");
        MyThread t5 = new MyThread(pg,"Thread-2");
        t4.start();
        t5.start();
        pg.list(); // list threads and thread sub-groups present in this group
        System.out.println(pg.activeCount());  // active threads = 2
        System.out.println(pg.activeGroupCount());  // active group = 1

        Thread.sleep(10000);  // now both child threads completed their life cycle
        System.out.println(pg.activeCount());  // active threads = 0
        System.out.println(pg.activeGroupCount());  // active group = 1

    }

}
class MyThread extends  Thread{
    MyThread(ThreadGroup g, String name){
        super(g,name);
    }
    public void run(){
        System.out.println("Child Thread");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            System.out.println("Exception occured");
        }
    }
}