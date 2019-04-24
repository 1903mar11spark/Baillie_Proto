package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employees;
import com.revature.beans.Requests;

public class main {
	
	public static void main(String[] args) {
		EmpReqDaoImp erd = new EmpReqDaoImp();
//		List<Requests> bearList = erd.getEmpReqFromMan(8);
		int a = 73;
		String b = "PENDING";
		erd.resolveRequest(a,b);
		
		System.out.println(erd.resolveRequest(a,b));
	;
//		for(Requests b : bearList) {
//				System.out.println(b);
//		}
	}
}
