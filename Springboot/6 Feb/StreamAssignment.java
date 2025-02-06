import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;
import java.util.stream.Collectors;

public class StreamAssignment {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list = RecordGenerator.generateRecords();

        //Lets sort the list and keep it for future
        list.sort(Comparator.comparingDouble(Student::getPercentage));
        
        System.out.println("Welcome to Stream Assignment");
        
        do{
            System.out.println("1. Find the Number of Students in Each Standard");
            System.out.println("2.Find male and female students");
            System.out.println("3.Find the pass percentage");
            System.out.println("4.Find the Top 3 Students");
            System.out.println("5.Find the Top Scorers School-wise");
            System.out.println("6.Find the average age of male and female students");
            System.out.println("7.Find the total fees collected School-wise");
            System.out.println("8.Find the total Fees pending School-wise");
            System.out.println("9. Find the total number of students");
            
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.println("--------------------------------------------------------");
                    Map<Integer, Long> standardWiseCount =list.stream().collect(Collectors.groupingBy(Student::getStandard,Collectors.counting()));
                    System.out.println("Standard Wise Count:-");
                    standardWiseCount.forEach((standard, count) -> {
                        System.out.println("Standard: " + standard);
                        System.out.println("Count: " + count);
                        System.out.println();
                    });
                    
                    
                    break;
                case 2:
                    System.out.println("--------------------------------------------------------");
                    Map<String, Long> genderWiseCount =list.stream().collect(Collectors.groupingBy(Student::getGender,Collectors.counting()));
                    System.out.println("Standard Wise Count:-");
                    genderWiseCount.forEach((standard, count) -> {
                        System.out.println("Standard: " + standard);
                        System.out.println("Count: " + count);
                        System.out.println();
                    });
                    break;
                case 3:
                    System.out.println("--------------------------------------------------------");
                    System.out.println("1. School Wise");
                    System.out.println("2. Overall");
                    System.out.println("3. Exit");
                    System.out.println("Enter Choice:- ");
                    int choice1 = sc.nextInt();
                    do {
                        System.out.println("1. School Wise");
                        System.out.println("2. Overall");
                        System.out.println("3. Exit");
                        System.out.println("Enter Choice:- ");
                        choice1 = sc.nextInt();

                        Map<String, Map<Boolean, Long>> schoolwisePassFailCount =
                                    list.stream()
                                            .collect(Collectors.groupingBy(
                                                    Student::getSchoolName,
                                                    Collectors.partitioningBy(s -> s.getPercentage() >= 40, Collectors.counting())
                                            ));
                        if (choice1 == 1) {                          
                
                            // Now in that map we can get the pass percentage
                            Map<String, Double> schoolwisePassPercentage =
                                    schoolwisePassFailCount.entrySet().stream()
                                            .collect(Collectors.toMap(
                                                    Map.Entry::getKey,
                                                    e -> {
                                                        long passCount = e.getValue().get(true);
                                                        long total = e.getValue().values().stream().mapToLong(Long::longValue).sum();
                                                        return (total == 0) ? 0 : (double) passCount / total * 100;
                                                    }
                                            ));
                
                                    schoolwisePassPercentage.forEach((school, percentage) -> {
                                        System.out.println("School: " + school);
                                        System.out.println("Pass Percentage: " + percentage + "%");
                                        System.out.println();
                            });
                
                        } else if (choice1 == 2) {
                                        
                            long totalPass = schoolwisePassFailCount.values().stream()
                                    .mapToLong(c -> c.getOrDefault(true, 0L))
                                    .sum();
                            long totalFail = schoolwisePassFailCount.values().stream()
                                    .mapToLong(c -> c.getOrDefault(false, 0L))
                                    .sum();
                            long totalStudents = totalPass + totalFail;
                            double overallPassPercentage = (totalStudents == 0) ? 0 : (double) totalPass / totalStudents * 100;
                            double overallFailPercentage = (totalStudents == 0) ? 0 : (double) totalFail / totalStudents * 100;
                            System.out.println("Overall Pass Percentage: " + overallPassPercentage + "%");
                            System.out.println("Overall Fail Percentage: " + overallFailPercentage + "%");
                            System.out.println("Total Students: " + totalStudents);
                            System.out.println();
                        }
                    } while (choice1 != 3);
                    break;
            
                case 4:
                    System.out.println("--------------------------------------------------------");
                    List<Student> top3Students = list.stream()
                            .sorted((s1, s2) -> Float.compare(s2.getPercentage(), s1.getPercentage()))
                            .limit(3)
                            .collect(Collectors.toList());
                    
                    System.out.println("Top 3 Students based on Percentage");
                    top3Students.forEach(s -> {
                        System.out.println("Roll No: " + s.getRollNo());
                        System.out.println("Name: " + s.getName());
                        System.out.println("Percentage: " + s.getPercentage());
                        System.out.println();
                    });
                    
                    break;
                case 5:
                    System.out.println("Find the Top Scorers School-wise");
                    Map<String, Student> schoolwiseTopScorer =
                            list.stream()
                                    .collect(Collectors.toMap(
                                            Student::getSchoolName,
                                            s -> s,
                                            (s1, s2) -> s1.getPercentage() > s2.getPercentage() ? s1 : s2,
                                            LinkedHashMap::new
                                    ));
                    
                    schoolwiseTopScorer.forEach((school, student) -> {
                        System.out.println("School: " + school);
                        System.out.println("Roll No: " + student.getRollNo());
                        System.out.println("Name: " + student.getName());
                        System.out.println("Percentage: " + student.getPercentage());
                        System.out.println();
                    });
                    break;
                case 6:
                    System.out.println("--------------------------------------------------------");
                    
                    break;
                case 7:
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Find the total fees collected School-wise");
                    Map<Integer, Long> schoolWiseCollectedFees =list.stream().collect(Collectors.groupingBy(Student::getSchoolName,Collectors.summingInt(Student::)));
                    System.out.println("Standard Wise Count:-");
                    genderWiseCount.forEach((standard, count) -> {
                        System.out.println("Standard: " + standard);
                        System.out.println("Count: " + count);
                        System.out.println();
                    });
                    break;
                case 8:
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Find the total fees collected School-wise");
                    Map<Integer, Long> schoolWisePendingFees =list.stream().collect(Collectors.groupingBy(Student::getSchoolName,Collectors.summingInt(Student::get)));
                    System.out.println("Standard Wise Count:-");
                    genderWiseCount.forEach((standard, count) -> {
                        System.out.println("Standard: " + standard);
                        System.out.println("Count: " + count);
                        System.out.println();
                    });
                    break;
                case 9:
                    System.out.println("--------------------------------------------------------");
                        System.out.println("Total Students in the system: "+list.size());
                    break;
                case 10:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");        
            }
        } while (true);
    }
}
class Student extends Fees
{
    private int rollNo;
    private String name;
    private float percentage;
    private int standard;
    private String schoolName;
    private String gender;
    
    public Student(int rollNo, String name, float percentage, int standard, String schoolName, String gender,int totalFees, int paidFees, int pendingFees)
    {
        super(totalFees, paidFees, pendingFees);
        this.rollNo = rollNo;
        this.name = name;
        this.percentage = percentage;
        this.standard = standard;
        this.gender = gender;
        
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getStandard() {
        return standard;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getGender() {
        return gender;
    }

    public int getTotalFees() {
        return super.getTotalFees();
    }
    public int getPaidFees() {
        return super.getPaidFees();
    }
    public int getPendingFees() {
        return super.getPendingFees();
    }

}

class Fees
{
    private int totalFees;
    private int paidFees;
    private int pendingFees;
    public Fees(int totalFees, int paidFees, int pendingFees)   
    {
        this.totalFees = totalFees;
        this.paidFees = paidFees;
        this.pendingFees = pendingFees;
    }

    public int getTotalFees() {
        return totalFees;
    }

    public int getPaidFees() {
        return paidFees;
    }

    public int getPendingFees() {
        return pendingFees;
    }
}

class RecordGenerator {

    public static List<Student> generateRecords() {
        List<Student> list = new ArrayList<>();
        
        Student s1 =  new Student(1, "Raju", 90, 10, "Delhi Public School", "M", 10000, 7000, 3000);
        Student s2 =  new Student(2, "Bheem", 95, 11, "FrCRCE", "M", 12000, 6000, 6000);
        Student s3 =  new Student(3, "Chutki", 80, 12, "Kendriya Vidyalay", "F", 15000, 7500, 7500);
        Student s4 =  new Student(4, "Kaaliya", 85, 10, "Utpal Sanghvi", "F", 11000, 5500, 5500);
        Student s5 =  new Student(5, "Jaggu", 90, 11, "Ganesh Vidyamandir", "M", 10000, 9000, 1000);
        Student s6 =  new Student(6, "Anjali", 85, 10, "Ganesh Vidyamandir", "F", 12000, 6000, 6000);
        Student s7 =  new Student(7, "Pujender", 90, 11, "Delhi Public School", "M", 13000, 6500, 6500);
        Student s8 =  new Student(8, "Pushpender", 90, 11, "FrCRCE", "M", 14000, 7000, 7000);
        Student s9 =  new Student(9, "Sumit", 90, 11, "Kendriya Vidyalay", "M", 10000, 8000, 2000);
        Student s10 = new Student(10, "Madhav", 99, 11, "Utpal Sanghvi", "M", 12000, 9000, 3000);
        Student s11 = new Student(11, "Rohit", 99, 11, "Ganesh Vidyamandir", "M", 11000, 10000, 1000);
        Student s12 = new Student(12, "Anjali", 85, 10, "Delhi Public School", "F", 12000, 6000, 6000);
        Student s13 = new Student(13, "Mohan", 96, 11, "FrCRCE", "M", 13000, 7000, 6000);
        Student s14 = new Student(14, "Sohan", 92, 12, "Kendriya Vidyalay", "M", 10000, 5000, 5000);
        Student s15 = new Student(15, "Geeta", 78, 10, "Utpal Sanghvi", "F", 11000, 8000, 3000);
        Student s16 = new Student(16, "Meena", 84, 11, "Ganesh Vidyamandir", "F", 9000, 4500, 4500);
        Student s17 = new Student(17, "Kiran", 98, 12, "Delhi Public School", "M", 14000, 7000, 7000);
        Student s18 = new Student(18, "Ramesh", 75, 10, "FrCRCE", "M", 9000, 6000, 3000);
        Student s19 = new Student(19, "Suresh", 79, 11, "Kendriya Vidyalay", "M", 10000, 7500, 2500);
        Student s20 = new Student(20, "Lata", 83, 12, "Utpal Sanghvi", "F", 12000, 9000, 3000);
        Student s21 = new Student(21, "Vinod", 77, 10, "Ganesh Vidyamandir", "M", 11000, 5500, 5500);
        Student s22 = new Student(22, "Sunita", 89, 11, "Delhi Public School", "F", 10000, 9000, 1000);
        Student s23 = new Student(23, "Manoj", 93, 12, "FrCRCE", "M", 12000, 6000, 6000);
        Student s24 = new Student(24, "Anita", 76, 10, "Kendriya Vidyalay", "F", 11000, 8500, 2500);
        Student s25 = new Student(25, "Vijay", 94, 11, "Utpal Sanghvi", "M", 13000, 10000, 3000);
        Student s26 = new Student(26, "Raj", 82, 12, "Ganesh Vidyamandir", "M", 14000, 7000, 7000);
        Student s27 = new Student(27, "Rekha", 88, 10, "Delhi Public School", "F", 10000, 5000, 5000);
        Student s28 = new Student(28, "Naveen", 87, 11, "FrCRCE", "M", 12000, 6000, 6000);
        Student s29 = new Student(29, "Nisha", 91, 12, "Kendriya Vidyalay", "F", 11000, 9000, 2000);
        Student s30 = new Student(30, "Poonam", 86, 10, "Utpal Sanghvi", "F", 9000, 7500, 1500);
        Student s31 = new Student(31, "Amit", 90, 11, "Ganesh Vidyamandir", "M", 11000, 10000, 1000);
        Student s32 = new Student(32, "Preeti", 84, 12, "Delhi Public School", "F", 14000, 6000, 8000);
        Student s33 = new Student(33, "Rita", 83, 10, "FrCRCE", "F", 12000, 8500, 3500);
        Student s34 = new Student(34, "Gaurav", 79, 11, "Kendriya Vidyalay", "M", 10000, 7000, 3000);
        Student s35 = new Student(35, "Nikhil", 81, 12, "Utpal Sanghvi", "M", 11000, 5500, 5500);
        Student s36 = new Student(36, "Sima", 85, 10, "Ganesh Vidyamandir", "F", 13000, 10000, 3000);
        Student s37 = new Student(37, "Neha", 78, 11, "Delhi Public School", "F", 12000, 7500, 4500);
        Student s38 = new Student(38, "Rahul", 92, 12, "FrCRCE", "M", 9000, 4500, 4500);
        Student s39 = new Student(39, "Sunil", 90, 10, "Kendriya Vidyalay", "M", 14000, 7000, 7000);
        Student s40 = new Student(40, "Sneha", 87, 11, "Utpal Sanghvi", "F", 10000, 9000, 1000);
        Student s41 = new Student(41, "Ravi", 89, 12, "Ganesh Vidyamandir", "M", 13000, 6500, 6500);
        Student s42 = new Student(42, "Kajal", 80, 10, "Delhi Public School", "F", 11000, 8500, 2500);
        Student s43 = new Student(43, "Varun", 86, 11, "FrCRCE", "M", 12000, 9000, 3000);
        Student s44 = new Student(44, "Akash", 90, 12, "Kendriya Vidyalay", "M", 10000, 5000, 5000);
        Student s45 = new Student(45, "Suman", 88, 10, "Utpal Sanghvi", "F", 13000, 7500, 5500);
        Student s46 = new Student(46, "Sangeeta", 91, 11, "Ganesh Vidyamandir", "F", 10000, 5000, 5000);
        Student s47 = new Student(47, "Yash", 90, 12, "Delhi Public School", "M", 14000, 8000, 6000);
        Student s48 = new Student(48, "Tina", 84, 10, "FrCRCE", "F", 12000, 9000, 3000);
        Student s49 = new Student(49, "Jatin", 95, 11, "Kendriya Vidyalay", "M", 10000, 7500, 2500);
        Student s50 = new Student(50, "Kavya", 90, 12, "Utpal Sanghvi", "F", 11000, 0, 11000);

        Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41,s42,s43,s44,s45,s46,s47,s48,s49,s50);

        return list;
    }

}
