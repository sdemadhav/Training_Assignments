import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo2 {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(11, "Rajesh");
        map.put(12, "Mahesh");
        map.put(13, "Suresh");
        map.put(21, "Chandan");
        map.put(22, "Kundan");
        map.put(23, "Kumar");
        map.put(31, "Ramniranjan");
        map.put(32, "Ashvini");

	System.out.println("-----Printing All the Keys in The Map-----");
        map.entrySet().stream().map(Map.Entry::getKey).forEach(System.out::println);

	System.out.println("-----Printing All the Values in The Map-----");
        map.entrySet().stream().map(Map.Entry::getValue).forEach(System.out::println);

        System.out.println("-----Printing all the keys in the map which are greater than 30------");
        map.entrySet().stream().filter(me -> me.getKey() > 30).forEach(System.out::println);

        List<Integer> l1 = Arrays.asList(1, 2, 3);
        List<Integer> l2 = Arrays.asList(4, 5, 6);
        List<Integer> l3 = Arrays.asList(7, 8, 9, 10, 11);

        Set<Integer> s1 = new TreeSet<>();
        s1.add(12);
        s1.add(13);
        s1.add(14);

        // List of Lists and Set, so we are storing as type Collection(Generic type of all collection objects)
        List<Collection<Integer>> list = Arrays.asList(l1, l2, l3, s1);
        List<Integer> flatList = list.stream()
                		     .flatMap(cl -> cl.stream())
                		     .map(x -> x * 2)
                		     .collect(Collectors.toList());

	System.out.println("-----Printing the falttened out list of collection of List and Set and all numbers doubled-----");
        System.out.println(flatList);

        // --------------What if we have a collection of a list, set, and Map. Then how do we work on them as a single stream of elements-------------------
        List<Object> mixedCollection = new ArrayList<>();

        Map<String, Integer> mixedMap = new HashMap<>();
        mixedMap.put("one", 1);
        mixedMap.put("two", 2);
        mixedMap.put("three", 3);

        mixedCollection.add(l1);
        mixedCollection.add(l2);
        mixedCollection.add(l3);
        mixedCollection.add(s1);
        mixedCollection.add(mixedMap); // this collection stores 3 Lists, 1 set, and 1 Map.

        // Flatten the mixed collection into a single stream and perform operations
        List<Integer> result = mixedCollection.stream()
                			      .flatMap(obj -> {
                    				if (obj instanceof Map) {
                        				return ((Map<?, Integer>) obj).values().stream();
                    				} else if (obj instanceof Set) {
                        				return ((Set<Integer>) obj).stream();
                    				} else if (obj instanceof List) {
                        				return ((List<Integer>) obj).stream();
                    				} else {
                        				return Stream.empty();
                    				}
                				})
                			      .map(x -> x * 2) // Example operation: double each number
                			      .collect(Collectors.toList());

        System.out.println("Flattened and processed result of a mixed collection of : " + result);
    }
}
 