package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employees;
import com.revature.dao.EmpReqDaoImp;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
	EmpReqDaoImp erd = new EmpReqDaoImp();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1094051845418505772L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Served at: ").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("requestServlet");
		HttpSession session = request.getSession(false);
		System.out.println(session.getId());
		try {
		if (session != null && session.getAttribute("employeeId") != null) {
			Boolean a;
			String title = "";
			Employees emp = new Employees();
			System.out.println("Request Servlet Reached");
			double amount = Double.parseDouble(request.getParameter("rqAmt").toString());
			String info = request.getParameter("rqInfo").toString();
			String type = request.getParameter("rqType").toString();
			String eId = session.getAttribute("employeeId").toString();
			int idE = Integer.parseInt(eId);
			a = erd.createRequest(idE, type, amount, info);
			
			emp = erd.getEmployeeInfo(idE);
			title = emp.getTitle();
			
			if (!title.equals("TEMP")){
				if(a == true) {
					System.out.println("Request Successful");
					response.sendRedirect("ManagerHome");
				}else {
					//show alert employee was sucessfully been created
					response.sendRedirect("ManagerHome");
				}
			} else {
				if(a == true) {
					System.out.println("Request Successful");
					response.sendRedirect("employeehome");
				}else {
					//show alert employee was sucessfully been created
					response.sendRedirect("employeehome");
				}
			}
			}	
		}catch (Exception e){
				e.printStackTrace();
				System.out.println("out of the Request loop mi amiga");
				response.sendRedirect("ManagerHome");
			}

		}
	}
	




