package br.com.entelgy.lucenelanches.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.entelgy.lucenelanches.models.TypeOfBread;
import br.com.entelgy.lucenelanches.repositories.TypeOfBreadRepository;

@Controller
public class TypeOfBreadController {
	
	@Autowired
	private TypeOfBreadRepository typeOfBreadRepository;
	
	@RequestMapping("/types_of_bread")
  public @ResponseBody Iterable<TypeOfBread> index() {
		return this.typeOfBreadRepository.findAll();
  }

}
