package br.com.entelgy.lucenelanches.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.entelgy.lucenelanches.models.Snack;
import br.com.entelgy.lucenelanches.models.TypeOfSnack;
import br.com.entelgy.lucenelanches.repositories.SnackRepository;
import br.com.entelgy.lucenelanches.repositories.TypeOfSnackRepository;

@Controller
public class SnackModelController {
	
	@Autowired
	private SnackRepository snackRepository;
	
	@Autowired
	private TypeOfSnackRepository typeOfSnackRepository;
	
	@RequestMapping("/snacks_model")
  public @ResponseBody List<Snack> index() {
		TypeOfSnack type = typeOfSnackRepository.findByDescription("model");
		return this.snackRepository.findByType(type);
  }
	
	@RequestMapping("/snack_model")
  public @ResponseBody Snack show(Long id) {
		return this.snackRepository.findOne(id);
  }

}
