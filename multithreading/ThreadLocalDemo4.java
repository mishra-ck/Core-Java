package concurrency.threadlocal.Part1;
public class ThreadLocalDemo4 {
    public static void main(String[] args) {
        ParentThread pt = new ParentThread();
        pt.start();
    }
}
class ParentThread extends Thread{
    public static InheritableThreadLocal tl = new InheritableThreadLocal(){
      public Object childValue(Object p){
          return "CC" ;
      }
    };
    public void run(){
        tl.set("PP");
        System.out.println("Parent thread value --> " + tl.get());
        ChildThread ct = new ChildThread();
        ct.start();
    }
}
class ChildThread extends Thread{
    @Override
    public void run() {
        System.out.println("Child thread value --> " + ParentThread.tl.get());
    }
}