package br.com.entelgy.lucenelanches.controllers.parsers;

import javax.servlet.http.HttpServletRequest;

import br.com.entelgy.lucenelanches.models.Request;

public class FormToRequest {

	private HttpServletRequest request;
	private Integer index;

	public FormToRequest(HttpServletRequest request, Integer index) {
		super();
		this.request = request;
		this.index = index;
	}

	public Request parse(){
		return new Request(nameParse(), addressParse());
	}
	
	public String nameParse(){
		return request.getParameter("snack[" + index + "][name]");
	}
	
	public String addressParse(){
		return request.getParameter("snack[" + index + "][address]");
	}
	
}
