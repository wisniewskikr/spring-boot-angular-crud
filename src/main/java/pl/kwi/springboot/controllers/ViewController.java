package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwi.springboot.commands.ViewCommand;
import pl.kwi.springboot.services.UserService;

@Controller
public class ViewController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/view/{id}")
	public String displayPage(@PathVariable Long id, @ModelAttribute("command")ViewCommand command) {
		command.setName(userService.readUser(id).getName());
		return "view";
	}

}