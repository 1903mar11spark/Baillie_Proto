package com.revature.service;

import com.revature.beans.Employees;
import com.revature.beans.Login;
import com.revature.dao.EmpReqDaoImp;

public class AuthService {
	
	EmpReqDaoImp erd = new EmpReqDaoImp();
	
	public Employees isValidUser(Login login) {
		Employees emp = null;
				String username = login.getUsername();
				String password = login.getPassword();
				//System.out.println(password);
				int employeeId = erd.getLogin(username, password);
				if (employeeId != 0) {
					emp = erd.getEmployeeInfo(employeeId);
					System.out.println(emp + "from auth service ");
				} 
				return emp;
			}
	
	public String getMan(Login login) {
		String man = "";
		String username = login.getUsername();
		String password = login.getPassword();
		int employeeId = erd.getLogin(username, password);
		man = erd.getManager(employeeId);
		System.out.println(man + "manager form authserv");
		return man;
	}
	
}
