package by.runets.travelagency.controller;

import by.runets.travelagency.entity.Country;
import by.runets.travelagency.service.IService;
import by.runets.travelagency.util.config.WebAppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/*@Slf4j
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "production")
@ContextConfiguration(classes = WebAppConfig.class)*/
public class Test {
	@Autowired
	private IService<Country, Long> countryService;
	
/*	@org.junit.Test
	public void testReadAll() {
		log.info(countryService.readAll() + " ");
	}*/
}
