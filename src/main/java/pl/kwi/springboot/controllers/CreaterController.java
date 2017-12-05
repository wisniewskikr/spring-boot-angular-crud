package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.CreateCommand;
import pl.kwi.springboot.db.entities.UserEntity;
import pl.kwi.springboot.services.UserService;

@Controller
@RequestMapping(value="/create")
public class CreaterController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String displayPage() {
		return "create";
	}
	
	@RequestMapping(value="/handle-button-create", method=RequestMethod.POST)
	public String handleButtonCreate(@ModelAttribute("command")CreateCommand command) {
		userService.createUser(new UserEntity(command.getName()));
		return "redirect:/list";
	}

}