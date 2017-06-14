package br.com.entelgy.lucenelanches.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.entelgy.lucenelanches.models.Filling;
import br.com.entelgy.lucenelanches.repositories.FillingRepository;

@Controller
public class FillingController {
	
	@Autowired
	private FillingRepository fillingRepository;
	
	@RequestMapping("/fillings")
  public @ResponseBody Iterable<Filling> index() {
		return this.fillingRepository.findAll();
  }

}
