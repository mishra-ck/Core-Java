package concurrency.threadlocal.Part1;

public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        ThreadLocal<String> tl = new ThreadLocal<>(){
          public String initialValue(){
              return "abc";
          }
        };
        System.out.println(tl.get()); // abc
        tl.set("chanchal");
        System.out.println(tl.get()); // chanchal
        tl.remove();
        System.out.println(tl.get()); // abc
    }
}
