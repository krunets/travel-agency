package by.runets.travelagency.controller;

import by.runets.travelagency.dto.CountryListDTO;
import by.runets.travelagency.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CountryController {
  @Autowired
  private ICountryService countryService;

  @GetMapping(value = "/country/read/all")
  public @ResponseBody
  CountryListDTO readAllTest(@RequestParam String countryName) {
    return new CountryListDTO(countryService.filterByCriteria(countryName));
  }
}
