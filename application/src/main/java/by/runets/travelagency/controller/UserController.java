package by.runets.travelagency.controller;

import by.runets.travelagency.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
	/*@PostMapping(value = "/login", produces = "application/json")
	public String checkLogin(@RequestBody UserDTO userDTO) {
		log.error(userDTO + "");
		return "homepage";
	}*/
}
