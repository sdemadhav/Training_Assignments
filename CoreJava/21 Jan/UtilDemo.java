import java.util.*;

public class UtilDemo
{
	public static void main(String[] args)
	{
		Date d1 = new Date();
		System.out.println(d1);
		System.out.println(d1.getTime());
		//System.out.println(d1.setTime(32456));
		Date d2 = new Date();
		d2.setTime(-9381227392222L);
		System.out.println(d2);
		
		d1.setMonth(48);
		System.out.println(d1);
		System.out.println(1900+d1.getYear());
		
		System.out.println("-------------------------");
		Stack s1 = new Stack();
		s1.push("111"); //pushing string
		s1.push("121");
		s1.push("131");
		s1.push("141");
		s1.push("151");
		s1.push(121);  //pushing integer
		System.out.println(s1.pop()); //removes the top of stack element
		System.out.println(s1.peek());
		
		/*while(s1.peek()!=null)
		{
			System.out.println(s1.pop());	
		}*/
		System.out.println(s1.peek());
		System.out.println(s1.search("121"));
	
		//Calendar c1 = new Calendar(); //cant create a direct object of Calendar as its an abstract class so we must create an object of a class that implements it like GregorianCalendar does
		
		BitSet bs1 = new BitSet(16);
		BitSet bs2 = new BitSet(16);
		BitSet bs3 = new BitSet(16);
		
		System.out.println(bs1);//empty
		
		for(int i=0 ; i<16 ; i++)
		{
			if(i%3==0)
				bs1.set(i);
			if(i%4==0)
				bs2.set(i);
		}
		System.out.println(bs1);
		System.out.println(bs2);
		//bs1.and(bs2);  //uncomment to see AND operation
		//System.out.println(bs1);
	
		bs1.or(bs2);	//uncomment to see OR operation

		System.out.println(bs1);

	}
}