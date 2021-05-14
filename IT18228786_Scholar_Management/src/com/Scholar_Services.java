package com;
import model.Scholar;
import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Scholar_Services
 */
@WebServlet("/Scholar_Services")
public class Scholar_Services extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Scholar scholarObj=new Scholar();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scholar_Services() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 String output = scholarObj.insertresearchstatus(request.getParameter("researchID"), 
			 request.getParameter("progress"), 
			request.getParameter("comment"),
				request.getParameter("approval")); 

	response.getWriter().write(output); 
			}
    
    private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = scholarObj.updatesresearchstatus(paras.get("hidItemIDSave").toString(), 
			 paras.get("researchID").toString(), 
			 paras.get("progress").toString(), 
			 paras.get("comment").toString(), 
			 paras.get("approval").toString()); 
			
	response.getWriter().write(output); 
			} 
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = scholarObj.deleteresearchstatus(paras.get("refID").toString()); 
		response.getWriter().write(output); 
			}

}
