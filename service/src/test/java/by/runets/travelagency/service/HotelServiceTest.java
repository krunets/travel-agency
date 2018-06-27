package by.runets.travelagency.service;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.repository.impl.HotelRepository;
import by.runets.travelagency.service.impl.HotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTest {
	@Mock
	private HotelRepository repository;
	@InjectMocks
	private HotelService service;
	
	
	@Test
	public void testRead () {
		when(repository.read(any(Integer.class))).thenReturn(Optional.of(new Hotel<Integer>()));
		
		assertThat(service.read(any(Integer.class)), is(notNullValue()));
	}
	
	@Test
	public void testReadAll () {
		when(repository.readAll()).thenReturn(new ArrayList<Optional<Hotel>>());
		
		assertThat(service.readAll(), is(notNullValue()));
	}
	
	@Test
	public void testUpdate () {
		Hotel<Integer> hotel = new Hotel<Integer>(1, "Marriot1", "123 24 23", 5, new Country<Integer>());
		
		service.update(hotel);
		
		verify(repository, times(1)).update(hotel);
	}
	
	@Test
	public void testDelete () {
		Hotel<Integer> hotel = new Hotel<Integer>(1, "Marriot1", "123 24 23", 5, new Country<Integer>());
		
		service.delete(hotel);
		
		verify(repository, times(1)).delete(hotel);
	}
	
	@Test
	public void testCreate () {
		Hotel<Integer> hotel = new Hotel<Integer>(7, "Marriot2", "123 24 23", 5, new Country<Integer>());
		
		service.create(hotel);
		
		verify(repository, times(1)).create(hotel);
	}
	
}
