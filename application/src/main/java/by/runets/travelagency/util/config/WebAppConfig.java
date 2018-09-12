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
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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

import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_COUNTRY_PAGINATION_SIZE;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "by.runets.travelagency.*")
@PropertySource("classpath:filepath/filepath.properties")
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
		freeMarkerViewResolver.setContentType("text/html; charset=utf-8");
		freeMarkerViewResolver.setCache(true);
		freeMarkerViewResolver.setSuffix(".ftl");
		freeMarkerViewResolver.setPrefix("");
		return freeMarkerViewResolver;
	}
	
	@Bean
	public FreeMarkerConfigurer freemarkerConfig () {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
		freeMarkerConfigurer.setDefaultEncoding("UTF-8");
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
		localeResolver.setCookieName("my-locale-cookie");
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		localeResolver.setCookieMaxAge(3600);
		return localeResolver;
	}
	
	@Bean
	public MessageSource messageSource () {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
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
	
	@Bean
	public ClassPathTldsLoader classPathTldsLoader () {
		return new ClassPathTldsLoader();
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver () {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSizePerFile(5242880);//5MB
		return resolver;
	}
}
