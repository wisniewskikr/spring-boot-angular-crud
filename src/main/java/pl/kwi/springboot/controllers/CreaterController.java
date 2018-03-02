package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.CreateCommand;
import pl.kwi.springboot.entities.UserEntity;
import pl.kwi.springboot.services.LdapService;

@Controller
@RequestMapping(value="/create")
public class CreaterController {
	
	@Autowired
	private LdapService ldapService;
	
	@RequestMapping
	public String displayPage() {
		return "create";
	}
	
	@RequestMapping(value="/handle-button-create", method=RequestMethod.POST)
	public String handleButtonCreate(@ModelAttribute("command")CreateCommand command) {
		String cn = ldapService.generateCn();
		ldapService.createUser(new UserEntity(cn, command.getName()));
		return "redirect:/list";
	}

}