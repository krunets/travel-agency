package mock.by.runets.travelagency.service;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.entity.TourType;
import by.runets.travelagency.hibernate.impl.TourRepository;
import by.runets.travelagency.service.impl.TourService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import static integration.by.runets.travelagency.config.DevelopmentDatabaseBeanConfig.DEFAULT_PAGINATION_SIZE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TourServiceTest {
	@Mock
	private TourRepository repository;
	@InjectMocks
	private TourService service;
	
	@Test
	public void testReadAll () {
		when(repository.readAll(Tour.class, DEFAULT_PAGINATION_SIZE)).thenReturn(new ArrayList<>());
		
		assertThat(service.readAll(DEFAULT_PAGINATION_SIZE), is(notNullValue()));
	}
	
	@Test
	public void testCreate () {
		Tour tour = new Tour(123, "Photo1", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null, null, null);
		
		service.create(tour);
		verify(repository, times(1)).create(tour);
	}
	
	@Test
	public void testUpdate () {
		Tour tour = new Tour(1, "Photo12", LocalDate.parse("2018-07-13"), Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null, null, null);
		
		service.update(tour);
		verify(repository, times(1)).update(tour);
	}
	
	@Test
	public void testDelete () {
		Tour tour = new Tour(1, "Photo1", LocalDate.parse("2018-07-17"), Duration.ofDays(10), "description1", new BigDecimal(100), TourType.ADVENTURE, null, null, null, null);
		
		service.delete(tour);
		verify(repository, times(1)).delete(tour);
	}
}
