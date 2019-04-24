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
 * Servlet implementation class TwoEmpReqServlet
 */
@WebServlet("/EmpReq")
public class TwoEmpReqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Employees emp = new Employees();
    EmpReqDaoImp erd = new EmpReqDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwoEmpReqServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("ManagerHome").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("employeeId") != null) {
			try {
				List<Requests> reqList = new ArrayList<Requests>();
				String rqId = request.getParameter("empReqId").toString();
				int empId = Integer.parseInt(rqId);
				int manId = Integer.parseInt(session.getAttribute("employeeId").toString());
				System.out.println("EMPLOYEE ID" + empId);
				System.out.println("MANAGER ID" + manId);
				reqList = erd.getEmpReq(manId, empId);	
				;
				System.out.println("CHECK" + reqList);
				System.out.println("Emp Req Servlet LAST");
				
				for(Requests b : reqList) {
					String resp = new ObjectMapper().writeValueAsString(b);
					response.getWriter().write(resp);
					System.out.println("CHECK" + reqList);
					System.out.println("Emp Req Servlet LAST");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("{\"EmpReq\":null}");
		}
		}else {
		response.getWriter().write("{\"EmpReq\":null}");
		}
	}
}

