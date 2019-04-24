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
/////FOR EMPLOYEE HOMEPAGE TEMP NOT FALSE
/**
 * Servlet implementation class ResolvedRequestServlet
 */
@WebServlet("/rRequests")
public class ResolvedRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Employees emp = new Employees();
    EmpReqDaoImp erd = new EmpReqDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResolvedRequestServlet() {
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
				if (title.equals("TEMP")){
					
					
					//ho ho totally got that from my other servlet wahahahh
					List<Requests> resList = new ArrayList<Requests>();
					resList = erd.getResolvedRequests(userId);	
					String resp = new ObjectMapper().writeValueAsString(resList);
					response.getWriter().write(resp);
				
				System.out.println("only for non man Resolved");
				} 
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"rRequests\":null}");
				
			}
		} else {
			response.getWriter().write("{\"rRequests\":null}");
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
