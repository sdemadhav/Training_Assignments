import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.*;
public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
	System.out.println("--------------------------------------------------------------------------------------------------");
        list.add(22);
        list.add(33);
        list.add(44);
        list.add(32);  
        list.add(55);  
        list.add(76);
        list.add(87);
        list.add(99);
        list.add(322);
        list.add(122);
        list.add(145);
        list.add(543);  
        list.add(566);
        list.add(213);
        list.add(222);
        list.add(1344);
        list.add(2121);
        list.add(188);

        Predicate<Integer> p1 = (x) -> x % 2 == 0;

        Stream<Integer> s = list.stream();
        Stream<Integer> en = s.filter(p1);
        List<Integer> elist = en.collect(Collectors.toList());

        System.out.println(elist);

        List<Integer> olist = list.stream().filter(p1.negate()).collect(Collectors.toList());
        System.out.println(olist);

        List<Integer> dlist = list.stream().map(x -> 2 * x).collect(Collectors.toList());
        System.out.println(dlist);

        List<Integer> slist = list.stream().sorted().limit(5).skip(2).collect(Collectors.toList());
        System.out.println(slist);

        Random random = new Random();
        random.ints().limit(5).forEach(System.out::println);

        random.ints().map(Math::abs).limit(5).forEach(System.out::println);

        IntStream is = IntStream.rangeClosed(1, 10); // rangeClosed includes 10. range does NOT include 2nd arg.
        is.forEach(System.out::println);

        OptionalInt result1 = IntStream.range(1, 6).reduce((a, b) -> a * b);
        System.out.println(result1.getAsInt());

        int res1 = Stream.of(1, 2, 3).reduce(10, (a, b) -> a + b);
        System.out.println(res1);

        int res2 = Arrays.asList(1, 2, 3).parallelStream().reduce(10, (a, b) -> a + b, (a, b) -> a * b);
        System.out.println(res2);

	System.out.println("--------------------------------------------------------------------------------------------------");

        IntSummaryStatistics stats = IntStream.rangeClosed(1, 10).summaryStatistics();

        System.out.println(stats); // prints all stats
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Count: " + stats.getCount());
        System.out.println("Average: " + stats.getAverage());
        System.out.println("Sum: " + stats.getSum());
    }
}
