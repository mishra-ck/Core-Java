package basic.concepts;

public class MethodOverloading1 {
    public static void main(String[] args) {
        short x = 5 ;
        int y = 10 ;
        double z = 9.0;
        // 1. Automatic promotion
        m1(x);

        // 2. Exact match always has the highest priority
        m1(y) ;  // in m1(int y) method

        // 3. overloaded methods have child and parent relation and null is passed then child is called
        // if there is no relation, CE error raise --> reference to m2 is ambiguous
        m2(null);

    }
    static void m1(double x){
        System.out.println("in m1(double x) method ");
    }
    static void m1(int y){
        System.out.println("in m1(int y) method");
    }

    static void m2(String str){
        System.out.println("inside m2(String str) method");
    }
    static void m2(Object obj){
        System.out.println("inside m2(Object obj) method");
    }
}
