import java.util.*;
public class CollectionDemo
{
	public static void main(String[] args)
	{
		Vector v1 = new Vector();
		
		v1.add("Hello");
		v1.add(new Date());
		v1.add(new Integer(2));
		v1.add(new Double(23.33));
		v1.add(new Character('c'));

		Enumeration e = v1.elements();
		while(e.hasMoreElements())
		{
			System.out.println(e.nextElement());
			
		}
		
		System.out.println("-----------------------------------------------------------------");

		HashSet h1 = new HashSet();
	
		//TreeSet h1 = new TreeSet();
		//LinkedHashSet h1 = new LinkedHashSet();
		//ArrayList h1 = new ArrayList();
		//LinkedList h1 = new ArrayList();
		h1.add("1111");
		h1.add("111111");
		h1.add("2");
		h1.add("2");
		h1.add(1);
		h1.add(2);
		h1.add(40000000000000L);
		h1.add(4);
		//h1.addFirst(22); //LinkedList feature not available in ArrayList
		//h1.addLast(222);//LinkedList feature not available in ArrayList
		h1.add(3);		
		Iterator i = h1.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}


		//HashTable obj = new HashTable();
		HashMap obj = new HashMap();

		obj.put("222","Madhav");
		obj.put("224","Manan");
		obj.put("224","karan");

		

		Set s = obj.entrySet();
		Iterator it = s.iterator();
		
		while(it.hasNext())
		{
			System.out.println("ID: "+it.getKey());
			System.out.println("Name: "+it.getValue());
			
		}

	}
}