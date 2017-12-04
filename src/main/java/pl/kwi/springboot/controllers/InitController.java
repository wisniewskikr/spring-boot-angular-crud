package pl.kwi.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class InitController {

	@RequestMapping
	public String index() {
		return "redirect:list";
	}
	
}