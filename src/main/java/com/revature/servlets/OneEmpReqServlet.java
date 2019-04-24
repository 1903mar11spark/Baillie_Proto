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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.beans.Employees;
import com.revature.beans.Requests;
import com.revature.dao.EmpReqDaoImp;

/**
 * Servlet implementation class OneEmpReqServlet
 */
@WebServlet("/EmpReq2")
public class OneEmpReqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpReqDaoImp erd = new EmpReqDaoImp();
	Employees emp = new Employees();
	private ObjectMapper om;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneEmpReqServlet() {
    	super();
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
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
				request.getParameter("username");
				String title = "";
				title = emp.getTitle();
				if (!title.equals("TEMP")){
					List<Requests> reqList = new ArrayList<Requests>();
					reqList = erd.getPendingRequests(userId);	
					String resp = new ObjectMapper().writeValueAsString(reqList);
					response.getWriter().write(resp);
				
				System.out.println(resp + "all requests bruv");
				} 
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"pRequests\":null}");
				
			}
		} else {
			response.getWriter().write("{\"pRequests\":null}");
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
