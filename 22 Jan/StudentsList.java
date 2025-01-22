import java.util.*;

class Student {
    int rollNo;
    String name;
    int standard;

    public Student(int rn, String n, int s) {
        rollNo = rn;
        name = n;
        standard = s;
    }

    public String toString() {
        return "Roll No: " + rollNo + "\nName: " + name + "\nStandard: " + standard + "\n";
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return rollNo == student.rollNo && standard == student.standard;
    }

    public int hashCode() {
        return Objects.hash(standard);
    }
}

class NameSorter implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

class RollNoSorter implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.rollNo, s2.rollNo);
    }
}

class StandardWiseSorter implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.standard, s2.standard);
    }
}

class RollNoDesc implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return Integer.compare(s2.rollNo, s1.rollNo);
    }
}

public class StudentsList {
    public static void main(String[] args) {
        TreeSet<Student> students = new TreeSet<>(new RollNoDesc());

        students.add(new Student(11, "Pintu", 5));
        students.add(new Student(22, "Sanju", 5));
        students.add(new Student(33, "Manju", 5));
        students.add(new Student(44, "Rita", 5));
        students.add(new Student(55, "Sarita", 5));

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
