/**
 * 
 */
package main.org.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import main.org.model.Budget;
import main.org.model.CitiesDAO;

/**
 * @author V.Sokolov
 *
 */
public class ActionSearch extends Budget implements ServletRequestAware,ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7518262388411186370L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/*
	 */
	@Override
	public String execute() throws Exception {
		
		CitiesDAO citiesDAO = new CitiesDAO();
		String searchText = request.getParameter("getCities");
		String cities = citiesDAO.getCitiesByName(searchText);
		response.setContentType("text/xml;charset=UTF-8"); 
		response.getWriter().write(cities);
		
		return null;
	}
	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	public HttpServletRequest getServletRequest(){
		return request;
	}

	public void setServletResponse(HttpServletResponse response){
		this.response = response;
	}

	public HttpServletResponse getServletResponse(){
		return response;
	}
		

}
