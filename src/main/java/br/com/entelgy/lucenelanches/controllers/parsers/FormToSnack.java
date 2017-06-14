package br.com.entelgy.lucenelanches.controllers.parsers;

import javax.servlet.http.HttpServletRequest;

import br.com.entelgy.lucenelanches.models.Cheese;
import br.com.entelgy.lucenelanches.models.Filling;
import br.com.entelgy.lucenelanches.models.Request;
import br.com.entelgy.lucenelanches.models.Salad;
import br.com.entelgy.lucenelanches.models.Sauce;
import br.com.entelgy.lucenelanches.models.Snack;
import br.com.entelgy.lucenelanches.models.Spice;
import br.com.entelgy.lucenelanches.models.TypeOfBread;
import br.com.entelgy.lucenelanches.models.TypeOfSnack;
import br.com.entelgy.lucenelanches.repositories.CheeseRepository;
import br.com.entelgy.lucenelanches.repositories.FillingRepository;
import br.com.entelgy.lucenelanches.repositories.SaladRepository;
import br.com.entelgy.lucenelanches.repositories.SauceRepository;
import br.com.entelgy.lucenelanches.repositories.SpiceRepository;
import br.com.entelgy.lucenelanches.repositories.TypeOfBreadRepository;
import br.com.entelgy.lucenelanches.repositories.TypeOfSnackRepository;

public class FormToSnack {
	
	private HttpServletRequest request;
	private Request req;
	private Integer index;
	private TypeOfBreadRepository typeOfBreadRepository;
	private CheeseRepository cheeseRepository;
	private FillingRepository fillingRepository;
	private SaladRepository saladRepository;
	private TypeOfSnackRepository typeOfSnackRepository;
	private SauceRepository sauceRepository;
	private SpiceRepository spiceRepository;

	public FormToSnack(HttpServletRequest request, Request req, Integer index,
			TypeOfBreadRepository typeOfBreadRepository, CheeseRepository cheeseRepository,
			FillingRepository fillingRepository, SaladRepository saladRepository, TypeOfSnackRepository typeOfSnackRepository,
			SauceRepository sauceRepository, SpiceRepository spiceRepository) {
		super();
		this.request = request;
		this.req = req;
		this.index = index;
		this.typeOfBreadRepository = typeOfBreadRepository;
		this.cheeseRepository = cheeseRepository;
		this.fillingRepository = fillingRepository;
		this.saladRepository = saladRepository;
		this.typeOfSnackRepository = typeOfSnackRepository;
		this.sauceRepository = sauceRepository;
		this.spiceRepository = spiceRepository;
	}

	public Snack parse(){
		TypeOfBread typeOfBread = typeOfBreadRepository.findOne(Long.parseLong( request.getParameter("snack[" + index + "][typeOfBread]") ) );
		Cheese cheese = cheeseRepository.findOne( Long.parseLong( request.getParameter("snack[" + index + "][cheese]") ) );
		Filling filling = fillingRepository.findOne( Long.parseLong( request.getParameter("snack[" + index + "][filling]") ) );
		Salad salad = saladRepository.findOne( Long.parseLong( request.getParameter("snack[" + index + "][salad]") ) );
		TypeOfSnack typeOfSnack = typeOfSnackRepository.findByDescription("normal");
		Snack snack = new Snack(req, typeOfBread, cheese, filling, salad, typeOfSnack);
		
		for(String sauceId : request.getParameterValues("snack[" + index + "][sauces]")){
			Sauce sauce = sauceRepository.findOne( Long.parseLong( sauceId ) );
			snack.getSauces().add(sauce);
		}
		
		for(String spiceId : request.getParameterValues("snack[" + index + "][spices]")){
			Spice spice = spiceRepository.findOne( Long.parseLong( spiceId ) );
			snack.getSpices().add(spice);
		}
		
		req.getSnacks().add(snack);
		
		return snack;
	}
	
	

}
