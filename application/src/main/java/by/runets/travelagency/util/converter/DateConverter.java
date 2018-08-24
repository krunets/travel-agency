package by.runets.travelagency.util.converter;

import java.time.LocalDate;

public class DateConverter {
	public static LocalDate convert (String date) {
		String[] splittedDate = date.split("/");
		String month = splittedDate[0];
		String day = splittedDate[1];
		String year = splittedDate[2];
		
		return LocalDate.of(new Integer(year), new Integer(month), new Integer(day));
	}
}
