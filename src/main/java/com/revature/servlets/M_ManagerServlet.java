package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employees;
import com.revature.dao.EmpReqDaoImp;

public class M_ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	EmpReqDaoImp erd = new EmpReqDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public M_ManagerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("manager page reached");
    	request.getRequestDispatcher("M_Homepage.html").forward(request, response);
    	
    }
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}

