package br.com.entelgy.lucenelanches.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.entelgy.lucenelanches.models.Salad;
import br.com.entelgy.lucenelanches.repositories.SaladRepository;

@Controller
public class SaladController {
	
	@Autowired
	private SaladRepository saladRepository;
	
	@RequestMapping("/salads")
  public @ResponseBody Iterable<Salad> index() {
		return this.saladRepository.findAll();
  }

}
