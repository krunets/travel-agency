package by.runets.travelagency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PageController {
	@GetMapping("/")
	public String start () {
		return "redirect:login";
	}
	
	
	@GetMapping("/login")
	public String loginPage () {
		return "login";
	}
	
	@GetMapping("/homepage")
	public String homePage () {
		return "homepage";
	}
}
