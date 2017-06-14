package br.com.entelgy.lucenelanches.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.entelgy.lucenelanches.models.Spice;
import br.com.entelgy.lucenelanches.repositories.SpiceRepository;

@Controller
public class SpiceController {
	
	@Autowired
	private SpiceRepository spiceRepository;
	
	@RequestMapping("/spices")
  public @ResponseBody Iterable<Spice> index() {
		return this.spiceRepository.findAll();
  }

}
