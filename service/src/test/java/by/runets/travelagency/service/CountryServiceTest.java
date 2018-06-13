package by.runets.travelagency.service;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.repository.IRepository;
import by.runets.travelagency.repository.impl.CountryRepository;
import by.runets.travelagency.service.impl.CountryService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CountryServiceTest {
	private IRepository<Country, Integer> repository = mock(CountryRepository.class);
	private IService<Country, Integer> service = new CountryService(repository);
	
	@Test
	public void testRead () {
		when(repository.read(any(Integer.class))).thenReturn(Optional.of(new Country<Integer>()));
		
		Country<Integer> country = new Country<>(1, "213", null, null);
		assertThat(service.read(country.getId()), is(notNullValue()));
	}
	
	
	@Test
	public void testUpdate () {
		Country<Integer> newCountry = new Country<>(1, "Belarus freedom", null, null);
		service.update(newCountry);
		
		verify(repository, times(1)).update(newCountry);
	}
	
	@Test
	public void testCreate () {
		Country<Integer> newCountry = new Country<>(6, "Belarus freedom", null, null);
		service.create(newCountry);
		
		verify(repository, times(1)).create(newCountry);
	}
	
	@Test
	public void testDelete () {
		Country<Integer> deleteCountry = new Country<>(1, "Belarus", null, null);
		service.delete(deleteCountry);
		
		verify(repository, times(1)).delete(deleteCountry);
	}
	
	@Test
	public void testReadAll () {
		when(repository.readAll()).thenReturn(new ArrayList<Optional<Country>>());
		
		assertThat(service.readAll(), is(notNullValue()));
	}
/*
	@After
	public void tearDown() {
		ctx.close();
	}*/
}
