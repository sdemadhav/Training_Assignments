class A {
    private static A a1 = null;

    A() {
       
        if (this instanceof B) {
            System.out.println("Creating Object of B");
            a1 = this;
        }
        else if(this instanceof A && a1==null)
	{
		a1=this;
		System.out.println("Creating Object of A");
	}
	else
		throw new IntsanceAlreadyExistsException();
    }

    public static A getObject() {
        if (a1 == null) {
            a1 = new A();
            return a1;
        }
	else
	{
		throw new IntsanceAlreadyExistsException();
	}
	
    }
}

class B extends A {
    private static final B b1 = new B();


    private B() {
    }

    public static B getObject() {
        return b1;
    }
}

class IntsanceAlreadyExistsException extends RuntimeException
{
	public IntsanceAlreadyExistsException()
	{
	}
	
	public IntsanceAlreadyExistsException(String msg)
	{
		super(msg);
	}
}

public class SingletonAssignmentSolution {
    public static void main(String[] args) {
        A a1 = new A();
	B b1 = B.getObject();
	B b2 = B.getObject();
        A a2 = new A();
        A a3 = A.getObject();
        A a4 = A.getObject();

	System.out.println(a1);
	System.out.println(a2);
	System.out.println(a3);
	System.out.println(a4);
	System.out.println(b1);

    }
}
