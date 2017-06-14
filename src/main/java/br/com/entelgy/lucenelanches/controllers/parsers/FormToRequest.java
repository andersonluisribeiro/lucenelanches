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
		String name = request.getParameter("snack[" + index + "][name]");
		String address = request.getParameter("snack[" + index + "][address]");
		return new Request(name, address);
	}
	
}
