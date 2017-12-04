package pl.kwi.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.ListCommand;


@Controller
@RequestMapping(value="/list")
public class ListController {

	@RequestMapping
	public String displayPage(@ModelAttribute("command")ListCommand command) {
		return "list";
	}

}