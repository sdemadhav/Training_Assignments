class ObjectCreationException extends RuntimeException {
    ObjectCreationException() {
        super();
    }
    ObjectCreationException(String msg) {
        super(msg);
    }
}

sealed class A permits B {
    private static A a1 = null;

    protected A() {
        System.out.println("Creating an object of class A via Early Initialization");
    }

    public static A getObject() {
        if (a1 == null) {
            a1 = new B();  
        }
        return a1;  
    }
}

final class B extends A {
    private static B b1 = null;

    protected B() {
        System.out.println("Creating an object of class B via Early Initialization");
    }


    public static B getObject() {
        if (b1 == null) {
            b1 = new B(); 
        }
        return b1;  
    }
}

public class SingletonAssignment {
    public static void main(String[] args) {
        try {
            A a1 = A.getObject();  // This will create an object of A (which internally creates an object of B)
            B b1 = B.getObject();   // This will return the same instance of B (no new instance)

            // These will throw exceptions since both A and B are singletons
            A a2 = A.getObject();  // This will return the same instance of A (which is a B object)
            B b2 = B.getObject();  // This will return the same instance of B

        } catch (ObjectCreationException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
