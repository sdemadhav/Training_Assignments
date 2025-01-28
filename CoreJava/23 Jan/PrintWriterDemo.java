import java.io.*;

public class PrintWriterDemo {
    public static void main(String[] args) {
        try {
            //PrintWriter pw = new PrintWriter(System.out); //Writes to console
            //PrintWriter pw = new PrintWriter(new File("abc.txt"));
            PrintWriter pw = new PrintWriter(new FileOutputStream("PrintWriterDemo.java", true));
            pw.println("Hello Everyone, have a wonderful day (coming from PrintWriterDemo.java)");
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Oops! Exception caught in main method...");
            System.out.println(e);
        }
    }
}