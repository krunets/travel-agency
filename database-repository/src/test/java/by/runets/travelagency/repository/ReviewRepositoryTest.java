package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.impl.ReviewRepository;
import by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
@ActiveProfiles(profiles = "development")
@SqlGroup({
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/schema.sql", "classpath:db/init-data.sql"}),
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/drop.sql")
})
public class ReviewRepositoryTest {
  @Autowired
  @Qualifier("ReviewRepository")
  private ReviewRepository repository;

  @Test
  public void testCreate() {
    Review expected =
        new Review(10, "testContent", new User(1, null, null, null, null));
    repository.create(expected);
    Review actual = repository.read((int) expected.getId()).get();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testReadById() {
    Optional<Review> expected = Optional.of(new Review(1, "Content 1", null));
    Optional<Review> actual = repository.read(1);

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testReadAll() {
    List<Optional<Review>> expected =
        new ArrayList(
            Arrays.asList(
                Optional.of(new Review(1, "Content 1", null)),
                Optional.of(new Review(2, "Content 2", null)),
                Optional.of(new Review(3, "Content 3", null))));
    List<Optional<Review>> actual = repository.readAll();

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testDeleteById() {
    Optional<Review> expected = repository.read(1);
    Assert.assertNotNull(expected);

    repository.delete(expected.get());

    Optional<Review> actual = repository.read(1);

    Assert.assertEquals(Optional.empty(), actual);
  }

  @Test
  public void testUpdate() {
    Review expected = repository.read(1).get();

    expected.setContent("testContent");
    repository.update(expected);

    Review actual = repository.read(1).get();

    Assert.assertEquals(actual, expected);
  }
}
