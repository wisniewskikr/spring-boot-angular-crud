package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.DeleteCommand;
import pl.kwi.springboot.services.UserService;

@Controller
@RequestMapping(value="/delete")
public class DeleteController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{id}")
	public String displayPage(@PathVariable Long id, @ModelAttribute("command")DeleteCommand command) {
		command.setName(userService.readUser(id).getName());
		return "delete";
	}
	
	@RequestMapping(value="/handle-button-delete", method=RequestMethod.POST)
	public String handleButtonEdit(@ModelAttribute("command")DeleteCommand command) {
		userService.deleteUser(command.getId());
		return "redirect:/list";
	}

}