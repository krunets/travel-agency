package by.runets.travelagency.util.config;


import by.runets.travelagency.dto.CountryDTO;
import by.runets.travelagency.entity.Country;
import by.runets.travelagency.service.IService;
import by.runets.travelagency.util.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.List;
import java.util.Locale;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "by.runets.travelagency.*")
public class WebAppConfig extends WebMvcConfigurerAdapter {
	public static final int DEFAULT_PAGINATION_SIZE = 10;
	public static final int DEFAULT_COUNTRY_PAGINATION_SIZE = 500;
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
		List<Country> countries = countryService.readAll(DEFAULT_COUNTRY_PAGINATION_SIZE);
		return countryConverter.convert(countries);
	}
	
	@Bean
	public CookieLocaleResolver localeResolver () {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		localeResolver.setCookieName("my-locale-cookie");
		localeResolver.setCookieMaxAge(3600);
		return localeResolver;
	}
	
	@Bean
	public MessageSource messageSource () {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	@Bean
	public LocaleChangeInterceptor localeInterceptor () {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}
	
	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(localeInterceptor());
	}
	
	@Bean
	public RedirectStrategy redirectStrategy () {
		return new DefaultRedirectStrategy();
	}
}
