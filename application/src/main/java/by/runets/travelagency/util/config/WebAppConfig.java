package by.runets.travelagency.util.config;


import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.service.IService;
import by.runets.travelagency.util.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "by.runets.travelagency.*")
public class WebAppConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private IService<Country, Long> countryService;
	@Autowired
	private Converter<List<CountryDTO>, List<Country>> countryConverter;
	
	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver () {
		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
		freeMarkerViewResolver.setCache(true);
		freeMarkerViewResolver.setSuffix(".ftl");
		freeMarkerViewResolver.setPrefix("");
		return freeMarkerViewResolver;
	}
	
	@Bean
	public FreeMarkerConfigurer freemarkerConfig () {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
		return freeMarkerConfigurer;
	}
	
	@Bean
	public List<CountryDTO> countryDTOs () {
		List<Country> countries = countryService.readAll();
		return countryConverter.convert(countries);
	}
}
