package by.runets.travelagency.util.aspect;

import by.runets.travelagency.util.LoggingAspect;
import by.runets.travelagency.util.config.DevelopmentDatabaseBeanConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DevelopmentDatabaseBeanConfig.class)
public class LoggingAspectTest {
	@Autowired
	private LoggingAspect loggingAspect;
	
	@Test
	public void testLog() {
		
	}
}
