package by.runets.travelagency.controller;

import by.runets.travelagency.dto.UserDTO;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {
	@Autowired
	private IUserService<User, Long> userService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping(value = "/registration")
	public String registerUserAccount (@Valid @ModelAttribute("registrationForm") UserDTO userDTO, Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "registration";
		}
		
		User user = modelMapper.map(userDTO, User.class);
		boolean login = userService.registerUserAccount(user);
		if (login) {
			return "index";
		} else {
			model.addAttribute("login_error", true);
			return "registration";
		}
	}
}
