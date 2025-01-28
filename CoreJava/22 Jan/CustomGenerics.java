import java.util.*;

class A {
    public void add(int a, int b) {
        System.out.println("Sum of numbers is :" + (a + b));
    }
    
    public void add(String a, String b) {
        System.out.println("Sum of Strings is :" + (a + b));
    }
}

class MyGenericClass<T> {
    public void add(T a, T b) {
        if (a instanceof Number && b instanceof Number) {
            Number result = ((Number) a).doubleValue() + ((Number) b).doubleValue();
            System.out.println("Sum of numbers is: " + result);
        } else if (a instanceof String && b instanceof String) {
            System.out.println("Sum of Strings is: " + a.toString() + b.toString());
        } else {
            System.out.println("Unsupported type");
        }
    }
}

public class CustomGenerics {
    public static void main(String[] args) {
        A a1 = new A();
        a1.add("good", "morning");
        a1.add(2, 3);
    
        MyGenericClass<String> m1 = new MyGenericClass<String>();
        m1.add("good", "morning");
        
        MyGenericClass<Integer> m2 = new MyGenericClass<Integer>();
        m2.add(2, 3);

        MyGenericClass<Double> m3 = new MyGenericClass<Double>();
        m3.add(2.33, 3.45);
    }
}
