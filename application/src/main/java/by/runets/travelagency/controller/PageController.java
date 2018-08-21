package by.runets.travelagency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PageController {
	@GetMapping({"/login", "/"})
	public String loginPage () {
		return "login";
	}
	
	@GetMapping("/home")
	public String homePage () {
		return "homepage";
	}
}
