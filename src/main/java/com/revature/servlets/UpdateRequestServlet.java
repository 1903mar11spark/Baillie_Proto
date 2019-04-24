package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmpReqDaoImp;

/**
 * Servlet implementation class UpdateRequestServlet
 */
@WebServlet("/upRequest")
public class UpdateRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       EmpReqDaoImp erd = new EmpReqDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRequestServlet() {
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
		HttpSession session = request.getSession(false);
		System.out.println("UPDATE SERVLET");//NEED TO ADD ERROR HANDLING
		if (session != null && session.getAttribute("employeeId") != null) {
			try {
				String rqId = request.getParameter("rqId").toString();
				int reqId = Integer.parseInt(rqId);
				String stat = request.getParameter("status").toString();
				//need to add some error handling -- especially with throwing of rqId if not found
				Boolean a = erd.resolveRequest(reqId, stat);
				System.out.println(a);
				if(a == true) {
				System.out.println("Update Successful via Servlet");
				response.sendRedirect("ManagerHome");
				} else {
					System.out.println("Incorrect Request No.");
					response.sendError(404, "REQUEST ID NOT FOUND");
				}
			} catch (Exception e) {
				System.out.println("out of the box");
				e.printStackTrace();
			}
		}
	}

}
