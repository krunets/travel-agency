package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CountryRepositoryTest {
  private GenericXmlApplicationContext ctx;
  private IDatabaseRepository<Country, Integer> repository;

  @Before
  public void setup() {
    ctx = new GenericXmlApplicationContext();
    ctx.getEnvironment().setActiveProfiles("database", "development");
    ctx.load("database-bean-config.xml");
    ctx.refresh();
    repository = (IDatabaseRepository<Country, Integer>) ctx.getBean("countryRepository");
  }

  @Test
  public void testCreate() {
    Country<Integer> expected = new Country<>(10, "testCountryName", null, null);
    repository.create(expected);
    Country actual = repository.read(10).get();

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testReadById() {
    Country<Integer> expected = new Country<Integer>(1, "Belarus", null, null);
    Country actual = repository.read(1).get();

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testReadAll() {
    List<Optional<Country>> actual = repository.readAll();

    List<Optional<Country>> expected =
        new ArrayList<>(
            Arrays.asList(
                Optional.of(new Country<Integer>(1, "Belarus", null, null)),
                Optional.of(new Country<Integer>(2, "Usa", null, null)),
                Optional.of(new Country<Integer>(3, "France", null, null)),
                Optional.of(new Country<Integer>(4, "Italy", null, null))));

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void deleteById() {
    Country<Integer> expected = new Country<>(1, "", null, null);
    repository.delete(expected);

    Assert.assertEquals(Optional.empty(), repository.read(1));
  }

  @Test
  public void updateTest() {
    Country expected = new Country<>(1, "newName", null, null);
    repository.update(expected);
    Country actual = repository.read(1).get();

    Assert.assertEquals(expected, actual);
  }

  @After
  public void tearDown() {
    ctx.close();
  }
}
