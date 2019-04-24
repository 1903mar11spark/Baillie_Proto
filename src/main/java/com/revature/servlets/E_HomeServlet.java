package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmpReqDaoImp;

public class E_HomeServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6194868912151390015L;
	private EmpReqDaoImp erd;
	// this is our service class whose methods we will call

	//from Bear demo

	  public E_HomeServlet() {
		  	erd = new EmpReqDaoImp();
			
	    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Home Servlet Reached");
		request.getRequestDispatcher("E_Home.html").forward(request, response);
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request,response);
		}
	
}
