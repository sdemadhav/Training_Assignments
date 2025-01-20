import java.util.*;

class Biker implements Runnable {

    private String name;
    private int speed;
    private int raceDistance;
    private int distanceCovered = 0;
    private static final Object lock = new Object();
    private static boolean raceStarted = false;
    private static final List<String> rankings = new ArrayList<>();
    private long startTime;
    private long endTime;
    private long timeTaken;

    public Biker(String name, int speed, int raceDistance) {
        this.name = name;
        this.speed = speed;
        this.raceDistance = raceDistance;
    }

    public String getName() {
        return name;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void run() {
        synchronized (lock) {
            try {
                while (!raceStarted) {
                    lock.wait();
                }
            } catch (Exception e) {
                System.out.println(name + " interrupted while waiting to start the race.");
            }
        }

        startTime = System.currentTimeMillis();
        System.out.println(name + " started the race with a speed of " + speed + " m/s!");

        try {
            while (distanceCovered < raceDistance) {
                Thread.sleep(1000);
                distanceCovered += speed;

                if (distanceCovered > raceDistance) {
                    distanceCovered = raceDistance;
                }

                System.out.println(name + " has covered " + distanceCovered + " meters.");
            }

            endTime = System.currentTimeMillis();
            timeTaken = endTime - startTime;

            synchronized (rankings) {
                rankings.add(name);
            }

        } catch (Exception e) {
            System.out.println(name + " was interrupted during the race.");
        }
    }

    public int getDistanceCovered() {
        return distanceCovered;
    }

    public static void startRace() {
        synchronized (lock) {
            raceStarted = true;
            lock.notifyAll();
        }
    }

    public static boolean isRaceFinished(List<Biker> bikers) {
        synchronized (rankings) {
            return rankings.size() == bikers.size();
        }
    }

    public static List<String> getRankings() {
        synchronized (rankings) {
            return new ArrayList<>(rankings);
        }
    }
}

public class BikeRaceAssignment {

    public static void main(String[] args) {

        final int raceDistance = 1000;

        String[] names = { "Madhav", "Sanat", "Karan", "Jonathan", "Atul", "Aditya", "Sumit",
                "Pushpender", "Jatin", "Jasprit", "Dhoni", "Virat", "Rohit", "Rahul", "Rishabh",
                "Shreyas", "Shikhar", "Hardik", "Ravindra", "Ravichandran" };

        System.out.println("---------- Welcome To Blazing Bikers Game made by Madhav Jha ----------");
        System.out.println("Enter the number of bikers in the game:");

        Scanner scanner = new Scanner(System.in);
        int numBikers = scanner.nextInt();

        Thread[] bikerThreads = new Thread[numBikers];
        List<Biker> bikers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numBikers; i++) {
            String name = names[random.nextInt(names.length)];
            int speed = random.nextInt(100, 200);
            Biker biker = new Biker(name, speed, raceDistance);
            bikers.add(biker);
            bikerThreads[i] = new Thread(biker, name);
            bikerThreads[i].start();
        }

        System.out.println("Hold tight! Bikers are almost ready... Match starts in:");
        for (int i = 5; i > 0; i--) {
            System.out.println(i + " seconds...");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Main thread interrupted during countdown.");
            }
        }

        System.out.println("<<<<<<<<------------------------------ GOOOO!!!!!!!! -------------------------------->>>>>>>>");
	
        System.out.println("Race is about to start!");

        Biker.startRace();

        while (!Biker.isRaceFinished(bikers)) {
            System.out.println("\n--- Race Progress ---");
            for (Biker biker : bikers) {
                System.out.println(biker.getDistanceCovered() + " meters covered by " + biker.getName());
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Main thread interrupted during progress monitoring.");
            }
        }

        for (Thread thread : bikerThreads) {
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println("Main thread interrupted while waiting for bikers to finish in join method.");
            }
        }
	
	FinalReport.showReport(bikers);

        
    }
}

class FinalReport
{
	public static void showReport(List<Biker> bikers)
	{
		System.out.println("\n--- Final Rankings ---");

        	List<String> rankings = Biker.getRankings();

        	for (int i = 0; i < rankings.size(); i++) {
            	String bikerName = rankings.get(i);
            	Biker biker = bikers.stream().filter(b -> b.getName().equals(bikerName)).findFirst().orElse(null);
            	if (biker != null) {
                System.out.println((i + 1) + " - " + bikerName + 
                    " | Start time: " + new Date(biker.getStartTime()) + 
                    " | End time: " + new Date(biker.getEndTime()) + 
                    " | Time taken: " + biker.getTimeTaken() / 1000 + " seconds");
            	}
        }

        System.out.println("All bikers have finished the race!");
	}
	
}