import java.util.*;
public class CollectionDemo2
{
	public static void main(String[] args)
	{
		List list = new LinkedList();
	
		list.add("111");
		list.add("333");
		list.add("222");
		list.add("555");
		list.add("999");
		list.add("444");
		
		System.out.println("\nOriginal Order:");
		System.out.println(list);
		
		Collections.sort(list);
		System.out.println("\nSorted Order:");
		System.out.println(list);
		
		Collections.reverse(list);
		System.out.println("\nReversing List:");
		System.out.println(list);
		
		
		
		
		
		
	}
}