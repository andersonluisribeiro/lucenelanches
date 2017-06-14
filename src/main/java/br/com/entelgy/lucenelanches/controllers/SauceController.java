package br.com.entelgy.lucenelanches.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.entelgy.lucenelanches.models.Sauce;
import br.com.entelgy.lucenelanches.repositories.SauceRepository;

@Controller
public class SauceController {

	@Autowired
	private SauceRepository sauceRepository;

	@RequestMapping("/sauces")
	public @ResponseBody Iterable<Sauce> index() {
		return this.sauceRepository.findAll();
	}

}
