package pl.kwi.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AppController {

	@RequestMapping(value="/")
	public String index() {
		return "redirect:list";
	}
	
	@RequestMapping(value="/list")
	public String list() {
		return "list";
	}
	
	@RequestMapping(value="/create")
	public String create() {
		return "create";
	}
	
	@RequestMapping(value="/view/{id}")
	public String view() {
		return "view";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String edit() {
		return "edit";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete() {
		return "delete";
	}
	
}