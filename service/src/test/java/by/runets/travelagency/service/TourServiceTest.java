package by.runets.travelagency.service;

import by.runets.travelagency.config.ServiceTestConfig;
import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.repository.impl.TourRepository;
import by.runets.travelagency.service.impl.TourService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
public class TourServiceTest {
	@Mock
	private TourRepository repository;
	@InjectMocks
	private TourService service;
	
	@Test
	public void testRead() {
		when(repository.read(anyInt())).thenReturn(Optional.of(new Tour()));
		
		assertThat(service.read(anyInt()), is(notNullValue()));
	}
	
	@Test
	public void testReadAll() {
		when(repository.readAll()).thenReturn(new ArrayList<Optional<Tour>>());
		
		assertThat(service.readAll(), is(notNullValue()));
	}
	
	@Test
	public void testCreate() {
		Tour<Integer> tour = new Tour<Integer>(123, "Photo1", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null);
		
		service.create(tour);
		verify(repository, times(1)).create(tour);
	}
	
	@Test
	public void testUpdate() {
		Tour<Integer> tour = new Tour<Integer>(1, "Photo12", LocalDate.parse("2018-07-13"), Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null);
		
		service.update(tour);
		verify(repository, times(1)).update(tour);
	}
	
	@Test
	public void testDelete() {
		Tour<Integer> tour = new Tour<Integer>(1, "Photo1", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null);
		
		service.delete(tour);
		verify(repository, times(1)).delete(tour);
	}
}
