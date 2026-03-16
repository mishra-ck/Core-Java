package concurrency.threadlocal.Part1;

public class ThreadLocalDemo1 {
    public static void main(String[] args) {
        ThreadLocal<String> tl = new ThreadLocal();
        System.out.println(tl.get()); // null
        tl.set("chanchal");
        System.out.println(tl.get());  // chanchal
        tl.remove();
        System.out.println(tl.get());  // null
    }
}
