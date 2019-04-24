package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employees;

public class SessionServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5095172352805670544L;
	

	public SessionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("employeeId") != null) {
			try {
				
				System.out.println("session out/in?");
				int userId = Integer.parseInt(session.getAttribute("employeeId").toString());
				String firstname = session.getAttribute("firstName").toString();
				String lastname = session.getAttribute("lastName").toString();
				String title = session.getAttribute("title").toString();
				String email = session.getAttribute("email").toString();
				String man = session.getAttribute("manager").toString();
				
				System.out.println(session.getAttribute("lastName")+"here we are");
				
				Employees u = new Employees(userId, firstname, lastname, title, email, man);
				System.out.println(u);
				String resp = new ObjectMapper().writeValueAsString(u);
				response.getWriter().write(resp);
				
				System.out.println(resp + "servelt session 1");
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"session\":null}");
				System.out.println(session + "SESSION SERVLET2");
			}
		} else {
			response.getWriter().write("{\"session\":null}");
			System.out.println(session + "SESSION SERVLET3");
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
