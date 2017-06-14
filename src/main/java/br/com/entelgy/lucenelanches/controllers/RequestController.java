package br.com.entelgy.lucenelanches.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.entelgy.lucenelanches.controllers.parsers.FormToRequest;
import br.com.entelgy.lucenelanches.controllers.parsers.FormToSnack;
import br.com.entelgy.lucenelanches.models.Request;
import br.com.entelgy.lucenelanches.models.Snack;
import br.com.entelgy.lucenelanches.repositories.CheeseRepository;
import br.com.entelgy.lucenelanches.repositories.FillingRepository;
import br.com.entelgy.lucenelanches.repositories.RequestRepository;
import br.com.entelgy.lucenelanches.repositories.SaladRepository;
import br.com.entelgy.lucenelanches.repositories.SauceRepository;
import br.com.entelgy.lucenelanches.repositories.SnackRepository;
import br.com.entelgy.lucenelanches.repositories.SpiceRepository;
import br.com.entelgy.lucenelanches.repositories.TypeOfBreadRepository;
import br.com.entelgy.lucenelanches.repositories.TypeOfSnackRepository;

@Controller
public class RequestController {
	
	@Autowired
	private SnackRepository snackRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private TypeOfBreadRepository typeOfBreadRepository;
	
	@Autowired
	private CheeseRepository cheeseRepository;
	
	@Autowired
	private FillingRepository fillingRepository;
	
	@Autowired
	private SaladRepository saladRepository;
	
	@Autowired
	private TypeOfSnackRepository typeOfSnackRepository;
	
	@Autowired
	private SauceRepository sauceRepository;
	
	@Autowired
	private SpiceRepository spiceRepository;
	
	
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public String index() {
        return "request";
  }
	
	@RequestMapping(value="/orders/show/{id}", method=RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		Request req = requestRepository.findOne(id);
		model.addAttribute("request", req);
		return "finish.request";
  }
	
	@RequestMapping(value="/orders", method=RequestMethod.POST)
	@Transactional
	public String create( HttpServletRequest request, 
    HttpServletResponse response, Model model ){
		
		Integer requestSize = Integer.parseInt( request.getParameter("request-size") );
		
		Request req = null;
		
		for (int i = 0; i < requestSize; i++) {
			if(req == null){
				req = requestRepository.save( new FormToRequest(request, i).parse() );
			}
			Snack snack = new FormToSnack(request, 
					req, 
					i, 
					typeOfBreadRepository, 
					cheeseRepository, 
					fillingRepository, 
					saladRepository, 
					typeOfSnackRepository, 
					sauceRepository, 
					spiceRepository).parse();
			snackRepository.save(snack);
			
		}
	
		model.addAttribute("request", req);
		
		return "redirect:/orders/show/" + req.getId();
		
	}  
	

}
