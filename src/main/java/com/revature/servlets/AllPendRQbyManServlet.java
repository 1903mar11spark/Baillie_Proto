package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employees;
import com.revature.beans.Requests;
import com.revature.dao.EmpReqDaoImp;

/**
 * Servlet implementation class AllPendRQbyManServlet
 */
@WebServlet("/pRQMan")
public class AllPendRQbyManServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpReqDaoImp erd = new EmpReqDaoImp();
	Employees emp = new Employees();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllPendRQbyManServlet() {
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
				int userId = Integer.parseInt(session.getAttribute("employeeId").toString());
				emp = erd.getEmployeeInfo(userId);
				String title = "";
				title = emp.getTitle();
				if (!title.equals("TEMP")){
//sheesh the title of this servlet 
					List<Requests> reqList = new ArrayList<Requests>();
					reqList = erd.getEmpReqFromMan(userId);	
					String resp = new ObjectMapper().writeValueAsString(reqList);
					response.getWriter().write(resp);
				
				System.out.println("All pending request mi amiga");
				} 
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"pRQMan\":null}");
				
			}
		} else {
			response.getWriter().write("{\"pRQMan\":null}");
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
