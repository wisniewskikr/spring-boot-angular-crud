package pl.kwi.springboot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.ListCommand;
import pl.kwi.springboot.services.UserService;


@Controller
@RequestMapping(value="/list")
public class ListController {
	
	@Autowired
	private UserService userService;

	@RequestMapping
	public String displayPage(@ModelAttribute("command")ListCommand command) {
		command.setUsers(userService.getUserList());
		return "list";
	}

}