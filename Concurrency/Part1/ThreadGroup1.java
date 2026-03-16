package concurrency.threadlocal.Part1;

// ThreadGroup introduction
public class ThreadGroup1 {
    public static void main(String[] args) {
        // name of thread group --> main
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        // parent name of main thread group --> system
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());

        // Constructors
        ThreadGroup g1 = new ThreadGroup("first group");
        System.out.println(g1.getParent().getName()); // parent group of g1 thread group -- main
        // Creating child thread group
        ThreadGroup g2 = new ThreadGroup(g1, "second group");
        System.out.println(g2.getParent().getName()); // first group

        // creating thread as a part of a thread group
        Thread t1 = new Thread(g1, "first thread");
    }

}
