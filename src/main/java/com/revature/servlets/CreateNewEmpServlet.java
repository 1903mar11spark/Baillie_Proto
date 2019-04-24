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

/**
 * Servlet implementation class CreateNewEmpServlet
 */
@WebServlet("/CreateNewEmp")
public class CreateNewEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpReqDaoImp erd = new EmpReqDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewEmpServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CreateEmpServlet");
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("employeeId") != null) {
			try {
				Boolean created;
				int manager = Integer.parseInt(request.getParameter("manBy").toString()); 
	//			int manager = Integer.parseInt(request.getParameter("option").toString()); tried and true
				String nFirst = request.getParameter("newFirst").toString();
				String nLast = request.getParameter("newLast").toString();
				String email = request.getParameter("newEmail").toString();
				String title = request.getParameter("newTitle").toString();
			
			Employees emp = new Employees(nFirst, nLast, email, title, manager);
			
			created = erd.registerEmployee(emp);
			System.out.println("This is boolean from Create: " + created);
					if(created == true) {
						System.out.println("Employee Creation Successful");
						response.sendRedirect("ManagerHome");
					}else {
						//show alert employee was sucessfully been created
						response.sendRedirect("ManagerHome");
					}
			
				} catch (Exception e){
				e.printStackTrace();
				response.sendRedirect("ManagerHome");
			}
	}
}
}
