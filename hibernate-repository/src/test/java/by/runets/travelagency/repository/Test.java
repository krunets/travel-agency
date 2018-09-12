package by.runets.travelagency.repository;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

public class Test {
	@org.junit.Test
	public void t() {
		System.out.println(LocalTime.now().hashCode());
	}
}
