package basic.concepts;

public class MethodOverloading2 {
    public static void main(String[] args) {

        //4. same argument is accepted in 2 methods, in this case old concept wins
        m1(10); //

        // 5. Case when automatic promotion is not done automatically
        m2(10,10.5f); // perfect match works fine
        // m2(10,10); // in this case automatic promotion is not done CE : no suitable method found for m2(int,int)


    }
    static void m1(int x){
        System.out.println("inside m1(int x) method");
    }
    static void m1(int... x){
        System.out.println("inside m1(int... x) method");
    }

    static void m2(int x, float y){
        System.out.println("inside method m2(int x, float y)");
    }
    static void m2(float x, int y){
        System.out.println("inside method m2(float x, int y)");
    }


}
