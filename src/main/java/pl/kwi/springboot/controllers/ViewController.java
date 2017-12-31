package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwi.springboot.commands.ViewCommand;
import pl.kwi.springboot.services.LdapService;

@Controller
@RequestMapping(value="/view")
public class ViewController {
	
	@Autowired
	private LdapService ldapService;
	
	@RequestMapping(value="/{id}")
	public String displayPage(@PathVariable Long id, @ModelAttribute("command")ViewCommand command) {
		command.setName(ldapService.readUser(id).getName());
		return "view";
	}

}