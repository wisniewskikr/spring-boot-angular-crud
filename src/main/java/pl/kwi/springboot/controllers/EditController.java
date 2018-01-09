package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.EditCommand;
import pl.kwi.springboot.entities.UserEntity;
import pl.kwi.springboot.services.LdapService;

@Controller
@RequestMapping(value="/edit")
public class EditController {
	
	@Autowired
	private LdapService ldapService;
	
	@RequestMapping(value="/{cn}")
	public String displayPage(@PathVariable String cn, @ModelAttribute("command")EditCommand command) {
		command.setName(ldapService.readUser(cn).getName());
		return "edit";
	}
	
	@RequestMapping(value="/handle-button-update", method=RequestMethod.POST)
	public String handleButtonEdit(@ModelAttribute("command")EditCommand command) {
		ldapService.updateUser(new UserEntity(command.getCn(), command.getName()));
		return "redirect:/list";
	}

}