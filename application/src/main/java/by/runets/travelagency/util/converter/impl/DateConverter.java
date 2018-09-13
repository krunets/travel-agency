package by.runets.travelagency.util.converter.impl;

import by.runets.travelagency.util.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateConverter implements Converter<LocalDate, String> {
	@Override
	public LocalDate convert (String date) {
		String[] splittedDate = date.split("/");
		
		String month = splittedDate[0];
		String day = splittedDate[1];
		String year = splittedDate[2];
		
		return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
	}
}
