import java.util.*;
import java.util.stream.*;

public class StreamDemo3 {
    public static void main(String[] args) {
        List<Emp> list = new ArrayList<>();
        
        list.add(new Emp("Raju", 25, 30000, "Tester"));
        list.add(new Emp("Bheem", 27, 35000, "Programmer"));
        list.add(new Emp("Chutki", 45, 35000, "Programmer"));
        list.add(new Emp("Kaaliya", 35, 35000, "Programmer"));
        list.add(new Emp("Jaggu", 45, 30000, "Tester"));
        list.add(new Emp("Sheela", 25, 40000, "Manager"));
        list.add(new Emp("Riya", 25, 40000, "Manager"));
        list.add(new Emp("Karishma", 25, 55000, "Programmer"));
        list.add(new Emp("Preeti", 22, 40000, "Tester"));
        list.add(new Emp("Raani", 30, 65000, "Manager"));
        list.add(new Emp("Saroj", 37, 90000, "Programmer"));
        list.add(new Emp("Leela", 36, 55000, "Programmer"));    

        Map<Boolean, List<Emp>> m1 = list.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 30));
        System.out.println(m1); // printing whole map

        System.out.println("\n***JUNIOR EMPLOYEES (UNDER 30)***");
        System.out.println(m1.get(false));
        System.out.println("\n***SENIOR EMPLOYEES (ABOVE 30)***");
        System.out.println(m1.get(true));

        System.out.println("-----------------------------------");

        Map<Boolean, Long> m2 = list.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 30, Collectors.counting()));
        System.out.println(m2);

        System.out.println("-----------------------------------");

        Map<String, List<Emp>> m3 = list.stream()
                .collect(Collectors.groupingBy(Emp::getDesig));
        System.out.println(m3);

        System.out.println("-----------------------------------");

        Map<String, Long> m4 = list.stream()
                .collect(Collectors.groupingBy(Emp::getDesig, Collectors.counting()));
        System.out.println(m4);

        System.out.println("-----------------------------------");

        Map<String, List<String>> m5 = list.stream()
                .collect(Collectors.groupingBy(Emp::getDesig, 
                        Collectors.mapping(Emp::getName, Collectors.toList())));
        System.out.println(m5);    
    }
}

class Emp {
    private String name;
    private String desig;
    private int age;
    private int salary;

    public Emp(String name, int age, int salary, String desig) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.desig = desig;
    }

    @Override
    public String toString() {
        return "(" + name + "," + age + "," + salary + "," + desig + ")";
    }

    public String getName() {
        return name;
    }

    public String getDesig() {
        return desig;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }
}
