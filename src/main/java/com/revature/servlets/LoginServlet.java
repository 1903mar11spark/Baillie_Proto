package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employees;
import com.revature.beans.Login;
import com.revature.service.AuthService;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7870632768497535433L;
	private AuthService as = new AuthService();
	
	public LoginServlet() {
        super();
    }
	///COME BACK AND UTILIZE DOA METHODS
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(5*60); //ho ho getting it to go out in 5 min
		System.out.println(session.getId());
	
		String myMan = "";
		Login login = new Login(request.getParameter("username"), request.getParameter("password"));
		Employees emp = as.isValidUser(login);
		myMan = as.getMan(login);
		System.out.println(myMan);
		
		if (emp != null) {
				String title = emp.getTitle();
			if (title != "" ) {
				if(title.equals("TEMP")) {
					session.setAttribute("employeeId", emp.getEmployeeId());
					session.setAttribute("firstName", emp.getFirstName());
					session.setAttribute("lastName", emp.getLastName());
					session.setAttribute("title", emp.getTitle());
					session.setAttribute("email", emp.getEmail());
					session.setAttribute("manager", myMan);
					System.out.println("Login achieved for emp");
					response.sendRedirect("employeehome");//url pattern
				}else {
					session.setAttribute("employeeId", emp.getEmployeeId());
					session.setAttribute("firstName", emp.getFirstName());
					session.setAttribute("lastName", emp.getLastName());
					session.setAttribute("title", emp.getTitle());
					session.setAttribute("email", emp.getEmail());
					session.setAttribute("manager", myMan);
					System.out.println("Login achieved for man");
					response.sendRedirect("ManagerHome");
				}
			}
		}else{
				response.sendRedirect("Login");
				//check file path
			}
		}
	}
	
   


