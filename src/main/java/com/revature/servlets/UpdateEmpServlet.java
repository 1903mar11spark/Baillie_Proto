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
 * Servlet implementation class UpdateEmpServlet
 */
@WebServlet("/UpdateEmp")
public class UpdateEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpReqDaoImp erd = new EmpReqDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("you failed");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("employeeId") != null) {
			try {
				Boolean update;
				String title = "";
				
				String eId = session.getAttribute("employeeId").toString();
				int idE = Integer.parseInt(eId);
				
				System.out.println("I am in the Update EpiCenter Come AT ME BRO");
				
				String fName= request.getParameter("firstName");
				String lName = request.getParameter("lastName");
				String email = request.getParameter("email");
				
				Employees emp = new Employees(fName, lName, email, idE);
				update = erd.updateEmps(emp);
				
				System.out.println(erd.updateEmps(emp) + " t/f value");
				
				
				emp = erd.getEmployeeInfo(idE);
				title = emp.getTitle();
				
				if (!title.equals("TEMP")){
					if(update == true) {
					System.out.println("Update Successful via Servlet");
					response.sendRedirect("ManagerHome");
					}else {
						response.sendRedirect("Login");
					}
				} else {
					if(update == true) {
						System.out.println("Update Successful via Servlet");
						response.sendRedirect("employeehome");
						}else {
							response.sendRedirect("Login");
						}
				}
			} catch (Exception e) {
				System.out.println("out of the box");
				e.printStackTrace();
			}
		}
//		doGet(request, response);

	}
}
