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
import com.revature.dao.EmpReqDaoImp;

/**
 * Servlet implementation class ManViews
 */
@WebServlet("/ManViews")
public class ManViews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       EmpReqDaoImp erd = new EmpReqDaoImp();
       Employees emp = new Employees();
       private ObjectMapper om;
    /**
     * 
     * om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
     * @see HttpServlet#HttpServlet()
     */
    public ManViews() {
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
				String title = "";
				title = emp.getTitle();
				if (!title.equals("TEMP")){
					List<Employees> empList = new ArrayList<Employees>();
					empList = erd.getAllEmpMan();	
					System.out.println(empList);
					String resp = om.writeValueAsString(empList);
					response.getWriter().write(resp);
				
				System.out.println("man view HAS BEEN ACHEIEVED");
				} 
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"ManViews\":null}");
				System.out.println(session + "MAN VIEW ");
			}
		} else {
			response.getWriter().write("{\"ManViews\":null}");
			System.out.println(session + "MAN VIEW");
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
