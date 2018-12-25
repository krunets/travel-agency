package by.runets.travelagency.util.config;

import by.runets.travelagency.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
@EnableCaching
@ComponentScan(basePackages = "by.runets.travelagency.*")
public class ServiceBeanConfig {
  @Bean
  public Class<Country> countryClass() {
    return Country.class;
  }

  @Bean
  public Class<Hotel> hotelClass() {
    return Hotel.class;
  }

  @Bean
  public Class<Review> reviewClass() {
    return Review.class;
  }

  @Bean
  public Class<Tour> tourClass() {
    return Tour.class;
  }

  @Bean
  public Class<User> userClass() {
    return User.class;
  }

  @Bean
  public Class<Room> roomClass() {
    return Room.class;
  }

  @Bean
  public Class<TransferType> transferTypeClass() {
    return TransferType.class;
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("countries")));
    return cacheManager;
  }
}
