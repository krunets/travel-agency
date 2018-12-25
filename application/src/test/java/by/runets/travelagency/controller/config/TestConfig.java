package by.runets.travelagency.controller.config;


import by.runets.travelagency.converter.Converter;
import by.runets.travelagency.converter.impl.CountryCodeToCountryConverter;
import by.runets.travelagency.entity.Country;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TestConfig {
  @Bean
  public Converter<List<Country>, List<Country>> countryCodeToCountryConverter() {
    return new CountryCodeToCountryConverter();
  }
}
