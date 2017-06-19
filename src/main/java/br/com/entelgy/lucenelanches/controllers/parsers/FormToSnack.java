package br.com.entelgy.lucenelanches.controllers.parsers;

import java.util.ArrayList;
import java.util.List;

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
		Snack snack = new Snack(req, typeOfBreadParse(), cheeseParse(), doubleCheeseParse(), fillingParse(), 
				doubleFillingParse(), saladParse(), doubleSaladParse(), typeOfSnackParse(), saucesParse(), spicesParse());
		req.getSnacks().add(snack);
		return snack;
	}
	
	public TypeOfBread typeOfBreadParse(){
		return typeOfBreadRepository.findOne(Long.parseLong( request.getParameter("snack[" + index + "][typeOfBread]") ) );
	}
	
	public Cheese cheeseParse(){
		String value = request.getParameter("snack[" + index + "][cheese]");
		return value == null? null : cheeseRepository.findOne( Long.parseLong( request.getParameter("snack[" + index + "][cheese]") ) );
	}
	
	public boolean doubleCheeseParse(){
		return Boolean.parseBoolean( request.getParameter("snack[" + index + "][doubleCheese]") );
	}
	
	public Filling fillingParse(){
		String value = request.getParameter("snack[" + index + "][filling]");
		return value == null? null : fillingRepository.findOne( Long.parseLong( request.getParameter("snack[" + index + "][filling]") ) );
	}
	
	public boolean doubleFillingParse(){
		return Boolean.parseBoolean( request.getParameter("snack[" + index + "][doubleFilling]") );
	}
	
	public Salad saladParse(){
		String value = request.getParameter("snack[" + index + "][salad]");
		return value == null? null : saladRepository.findOne( Long.parseLong( request.getParameter("snack[" + index + "][salad]") ) );
	}
	
	public boolean doubleSaladParse(){
		return Boolean.parseBoolean( request.getParameter("snack[" + index + "][doubleSalad]") );
	}
	
	public TypeOfSnack typeOfSnackParse(){
		return typeOfSnackRepository.findByDescription("normal");
	}
	
	public List<Sauce> saucesParse(){
		String[] values = request.getParameterValues("snack[" + index + "][sauces]");
		if(values != null){
			List<Sauce> sauces = new ArrayList<Sauce>();
			for(String sauceId : values ){
				Sauce sauce = sauceRepository.findOne( Long.parseLong( sauceId ) );
				sauces.add(sauce);
			}
			return sauces;
		}
		return null;
		
	}
	
	public List<Spice> spicesParse(){
		String[] values = request.getParameterValues("snack[" + index + "][spices]");
		if( values != null ){
			List<Spice> spices = new ArrayList<Spice>();
			for(String spiceId : values){
				Spice spice = spiceRepository.findOne( Long.parseLong( spiceId ) );
				spices.add(spice);
			}
			return spices;
		}
		return null;
	}
	

}
