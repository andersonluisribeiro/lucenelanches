package br.com.entelgy.lucenelanches.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.entelgy.lucenelanches.models.Cheese;
import br.com.entelgy.lucenelanches.repositories.CheeseRepository;

@Controller
public class CheeseController {
	
	@Autowired
	private CheeseRepository cheeseRepository;
	
	@RequestMapping("/cheeses")
  public @ResponseBody Iterable<Cheese> index() {
		return this.cheeseRepository.findAll();
  }

}
