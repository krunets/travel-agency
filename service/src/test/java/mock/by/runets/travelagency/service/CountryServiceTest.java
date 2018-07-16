package mock.by.runets.travelagency.service;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.hibernate.impl.CountryRepository;
import by.runets.travelagency.service.impl.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CountryServiceTest {
	@Mock
	private CountryRepository repository;
	@InjectMocks
	private CountryService service;
	
	@Test(expected = ResourceNotFoundException.class)
	public void testReadThrowingException () {
		final long id = 1111;
		when(repository.read(eq(Country.class), anyLong())).thenThrow(ResourceNotFoundException.class);
		
		service.read(id);
	}
	
	@Test
	public void testUpdate () {
		Country newCountry = new Country(1, "Belarus freedom", null, null);
		service.update(newCountry);
		
		verify(repository, times(1)).update(newCountry);
	}
	
	@Test
	public void testCreate () {
		Country newCountry = new Country(6, "Belarus freedom", null, null);
		service.create(newCountry);
		
		verify(repository, times(1)).create(newCountry);
	}
	
	@Test
	public void testDelete () {
		Country deleteCountry = new Country(1, "Belarus", null, null);
		service.delete(deleteCountry);
		
		verify(repository, times(1)).delete(deleteCountry);
	}
	
	@Test
	public void testReadAll () {
		when(repository.readAll(Country.class)).thenReturn(new ArrayList<>());
		assertThat(service.readAll(), is(notNullValue()));
	}
}
