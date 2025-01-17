
import java.util.*;


class Biker implements Runnable {

    private String name; // Name of the biker

    private int speed; // Speed in meters per second

    private int raceDistance; // Total distance to be covered in the race

    private int distanceCovered = 0; // Distance covered so far by the biker

    private static final Object lock = new Object(); // Synchronization lock for race start

    private static boolean raceStarted = false; // Flag to indicate when the race starts

    private static final List<String> rankings = new ArrayList<>(); // Store rankings as bikers finish


    public Biker(String name, int speed, int raceDistance) {

        this.name = name;

        this.speed = speed;

        this.raceDistance = raceDistance;

    }


    public String getName() {

        return name; // since name we have kept private and we need name for ranking

    }


    public void run() {

        synchronized (lock) {

            try {

                // All bikers must wait till racestarted flag is turned true by main thread

                while (!raceStarted) {

                    lock.wait();

                }

            } catch (Exception e) {

                System.out.println(name + " interrupted while waiting to start the race.");

            }

        }


        System.out.println(name + " started the race with a speed of " + speed + " m/s!");


        try {

            // Showing live ranking every second

            while (distanceCovered < raceDistance) {

                Thread.sleep(1000); // Progress is updated every 1 second

                distanceCovered += speed; // Update distance covered

                if (distanceCovered > raceDistance) {

                    distanceCovered = raceDistance; // Cap the distance to total race distance

                }

                System.out.println(name + " has covered " + distanceCovered + " meters.");

            }


            synchronized (rankings) {

                rankings.add(name); // Add biker's name to rankings when they finish

            }


        } catch (Exception e) {

            System.out.println(name + " was interrupted during the race.");

        }

    }


    public int getDistanceCovered() {

        return distanceCovered; // Return the distance covered till now

    }


    public static void startRace() {

        synchronized (lock) {

            raceStarted = true; // Start the race

            lock.notifyAll(); // Notify all threads waiting for the race to start as all threads have been

                              // created

        }

    }


    public static boolean isRaceFinished(List<Biker> bikers) {

        synchronized (rankings) {

            if (rankings.size() == bikers.size()) // means all bikers have been added to rankings so game over else

                                                  // still going

            {

                return true;

            }

            return false;

        }

    }


    public static List<String> getRankings() {

        // we must synchronize this else if other threads take control at that time then

        // maintaining rankings would be difficult

        synchronized (rankings) {

            return new ArrayList<>(rankings); // Return the final rankings

        }

    }

}


public class BikeRaceAssignment {

    public static void main(String[] args) {

        final int raceDistance = 1000; // Total race distance in meters

        String[] names = { "Madhav", "Sanat", "Karan", "Jonathan", "Atul", "Aditya", "Sumit",

                "Pushpender", "Jatin", "Jasprit", "Dhoni", "Virat", "Rohit", "Rahul", "Rishabh",

                "Shreyas", "Shikhar", "Hardik", "Ravindra", "Ravichandran" };


        System.out.println("---------- Welcome To Blazing Bikers Game made by Madhav Jha ----------");

        System.out.println("Enter the number of bikers in the game:");

        Scanner scanner = new Scanner(System.in);

        int numBikers = scanner.nextInt();


        Thread[] bikerThreads = new Thread[numBikers]; // Array to hold biker threads

        List<Biker> bikers = new ArrayList<>(); // List to hold Biker objects

        Random random = new Random(); // Random object to generate random values


        // Create and start threads for each biker

        for (int i = 0; i < numBikers; i++) {

            String name = names[random.nextInt(names.length)]; // Randomly select a name

            int speed = random.nextInt(100, 200); // Randomly select a speed between 10 and 20 m/s

            Biker biker = new Biker(name, speed, raceDistance); // Create a new Biker

            bikers.add(biker); // Add biker to the list

            bikerThreads[i] = new Thread(biker, name); // Create a thread for the biker

            bikerThreads[i].start(); // Start the thread

        }


        System.out.println("Hold tight! Bikers are almost ready... Match starts in:");

        for (int i = 5; i > 0; i--) {

            System.out.println(i + " seconds...");

            try {

                Thread.sleep(1000); // Countdown delay

            } catch (Exception e) {

                System.out.println("Main thread interrupted during countdown.");

            }

        }


        System.out.println(

                "<<<<<<<<------------------------------ GOOOO!!!!!!!! -------------------------------->>>>>>>>");


        System.out.println("Race is about to start!");

        Biker.startRace(); // Start the race, and make the raceStarted boolean true


        // Live Match Progress can be viewed by regular printing of rankings

        while (!Biker.isRaceFinished(bikers)) {

            System.out.println("\n--- Race Progress ---");

            for (Biker biker : bikers) {

                System.out.println(biker.getDistanceCovered() + " meters covered by " + biker.getName());

            }

            try {

                Thread.sleep(1000); // Update progress every 1 second

            } catch (Exception e) {

                System.out.println("Main thread interrupted during progress monitoring.");

            }

        }


        // Wait for all biker threads to finish and then print the final rankings

        for (Thread thread : bikerThreads) {

            try {

                thread.join();

            } catch (Exception e) {

                System.out.println("Main thread interrupted while waiting for bikers to finish in join method.");

            }

        }


        System.out.println("\n--- Final Rankings ---");

        List<String> rankings = Biker.getRankings();

        for (int i = 0; i < rankings.size(); i++) {

            System.out.println((i + 1) + " - " + rankings.get(i));

        }


        System.out.println("All bikers have finished the race!");

    }

}