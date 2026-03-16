package basic.concepts;

public class MethodOverloading3 {
    public static void main(String[] args) {
        // 6. In Overloading method resolution is always taken care by reference type only
        Parent1 p = new Child1();
        p.m1();  // at compile time parent class method will be called
       // p.m2();  // cannot resolve method m2() in Parent1
    }
}
class Parent1{
    void m1(){
        System.out.println("inside parent-1 m1() method");
    }
}
class  Child1 extends Parent1{
    void m1(){
        System.out.println("inside child-1 m1() method");
    }
    void m2(){
        System.out.println("inside child-1 m2() method");
    }
}
