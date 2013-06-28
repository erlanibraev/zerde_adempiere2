/**
 * 
 */
package main.org.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.org.model.Budget;
import main.org.model.CitiesDAO;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


/**
 * @author V.Sokolov
 *
 */
public class AjaxTest extends Budget implements ServletRequestAware,ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5415893652365801576L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/* 
	 */
	@Override
	public String execute() throws Exception {
		
		CitiesDAO citiesDAO = new CitiesDAO();
		String searchText = request.getParameter("getCities");
		String cities = citiesDAO.getCitiesByName(searchText);
		response.setContentType("application/xml");
		response.getWriter().write(cities);
		
		return SUCCESS;
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
