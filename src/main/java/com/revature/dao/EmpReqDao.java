package com.revature.dao;

import java.util.List;

import com.revature.beans.Employees;
import com.revature.beans.Requests;

public interface EmpReqDao {

	public List<Employees> getAllEmpMan(); //10 get all employees and their managers

	public List<Requests> getAllRequests(); //9 requests from employees and see who resolved it 

	public List<Requests> getEmpReqFromMan(int employeeId);//7 view all PENDING requests from employees they manage 

	public List<Requests> getEmpReq(int empId, int manId); //11 view a singular rq from a single employee that they manage
	
	public List<Requests> getReciepts(); //8 view images of receipts form all requests
	
	public boolean createRequest(int employeeId, String type, double amount, String info); //1 NEED TO ADD IMAGE
	
	public List<Requests> getPendingRequests(int employeeId); //2 EMPLOYEE PENDING
	
	public List<Requests> getResolvedRequests(int employeeId); //3 EMPLOYEE RESOLVED
	
	public Employees getEmployeeInfo(int employeeId); //4
	
	public boolean updateEmployeeInfo(Employees emp); //5 employee update names/email username = solid 
	
	public boolean resolveRequest(int requestId, String stat); //6 Manager can resolve requests
	
	public int getLogin(String username, String password); //13 get login

	public String getManager(int employeeId);

	boolean updateEmps(Employees emp);
	
	public boolean registerEmployee(Employees emp); //12 register employee -- EXTRA

}