package by.runets.travelagency.controller;

import by.runets.travelagency.dto.SearchTourDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@GetMapping("/")
	public String start () {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginPage (@RequestParam(value = "error", required = false) String error,
													 @RequestParam(value = "logout", required = false) String logout,
													 Model model) {
		model.addAttribute("error", error != null);
		model.addAttribute("logout", logout != null);
		return "login";
	}
	
	@GetMapping("/homepage")
	public String homePage () {
		return "homepage";
	}
}
