import java.util.*;
import java.util.Date;
import java.time.*;
import static java.time.Month.*;


public class DateAndTime
{
	public static void main(String[] args)
	{
		LocalDate ld = LocalDate.now();
		System.out.println("Current Date: "+ld);
		
		LocalTime lt = LocalTime.now();
		System.out.println("Current Time: "+lt);

		LocalDateTime ldt = LocalDateTime.now();
		System.out.println("Current Date & Time: "+ldt);
	
		//Shows the local date by replacing the month with the specified month
		System.out.println(ld.withMonth(7));
		//Shows the local date by replacing the month with the specified month
		System.out.println(ld.withMonth(Month.MARCH.getValue()));
		
		//Specific date printing (import the static class Month)
		LocalDate d2 = LocalDate.of(1983,SEPTEMBER, 17);
		System.out.println(d2);

		LocalTime l2 = LocalTime.now(ZoneId.of("Japan"));
		System.out.println(l2);

		//Using a set to store all the Zone
		Set s = ZoneId.getAvailableZoneIds();
		s.forEach(System.out::println);
	}	
}


//Research about Joda Date and Time: joda.org