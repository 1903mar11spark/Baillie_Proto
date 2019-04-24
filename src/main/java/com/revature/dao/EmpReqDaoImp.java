package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employees;
import com.revature.beans.Requests;
import com.revature.util.ConnectionsUtil;

public class EmpReqDaoImp implements EmpReqDao{
	@Override
	public List<Employees> getAllEmpMan() {
		List<Employees> a = new ArrayList<>();
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "SELECT E1.EMPLOYEE_ID AS EMPLOYEENO, E1.FIRSTNAME AS EMPLOYEE, E2.FIRSTNAME AS SUPERVISOR FROM EMPLOYEES E1 INNER JOIN EMPLOYEES E2 ON E1.EMPLOYEE_ID = E2.REPORTSTO";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int empId = rs.getInt("EMPLOYEENO");
				String manager = rs.getString("SUPERVISOR");
				String firstname = rs.getString("EMPLOYEE");
				a.add(new Employees(empId, manager, firstname));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} 
		return a;
	}
	
	//FIX LATER TO SHOW NAMES NOT JUST ID NUMBERS
	@Override
	public List<Requests> getAllRequests() {
		List<Requests> a = new ArrayList<>();
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "SELECT * FROM REQUESTS";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int rq = rs.getInt("REQUEST_ID");
				int em = rs.getInt("EMPLOYEE_ID");
				int man = rs.getInt("MANAGEDBY");
				Date date = rs.getDate("RQ_DATE");
				String type = rs.getString("RQ_TYPE");
				double amt = rs.getDouble("RQ_AMT");
				String stat = rs.getString("REQUEST_STATUS");
				String image = rs.getString("RQ_IMAGE");
				String info = rs.getString("INFO");
				a.add(new Requests(rq, em, man, date, type, amt, stat, image, info));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} 
		return a;
	}
///think about where the sql things are 
	@Override
	public List<Requests> getEmpReqFromMan(int employeeId) {
		List<Requests> a = new ArrayList<>();
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "SELECT * FROM REQUESTS WHERE MANAGEDBY=? AND REQUEST_STATUS='PENDING'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int rq = rs.getInt("REQUEST_ID");
				int em = rs.getInt("EMPLOYEE_ID");
				int man = rs.getInt("MANAGEDBY");
				Date date = rs.getDate("RQ_DATE");
				String type = rs.getString("RQ_TYPE");
				double amt = rs.getDouble("RQ_AMT");
				String stat = rs.getString("REQUEST_STATUS");
				String image = rs.getString("RQ_IMAGE");
				String info = rs.getString("INFO");
				System.out.println("ran through here");
				a.add(new Requests(rq, em, man, date, type, amt, stat,image, info));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} 
		return a;
	}

	@Override
	public List<Requests> getEmpReq(int empId, int manId) {
		List<Requests> a = new ArrayList<>();
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "SELECT * FROM REQUESTS WHERE EMPLOYEE_ID = ? AND MANAGEDBY = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empId);
			pstmt.setInt(2, manId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int rq = rs.getInt("REQUEST_ID");
				int em = rs.getInt("EMPLOYEE_ID");
				Date date = rs.getDate("RQ_DATE");
				String type = rs.getString("RQ_TYPE");
				double amt = rs.getDouble("RQ_AMT");
				String stat = rs.getString("REQUEST_STATUS");
				String info = rs.getString("INFO");
				a.add(new Requests(rq, em, date, type, amt, stat, info));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} 
		return a;
	
	}

	@Override
	public List<Requests> getReciepts() {
			List<Requests> a = new ArrayList<>();
			try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
				String sql = "SELECT RQ_IMAGE FROM REQUESTS";
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String img = rs.getString("RQ_IMAGE");
					a.add(new Requests(img));
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			} 
			return a;
	}

	@Override
	public boolean createRequest(int employeeId, String type, double amount, String info) {//removed double amount
			try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "INSERT INTO REQUESTS (EMPLOYEE_ID, RQ_TYPE, RQ_AMT, INFO) VALUES (?,?,?,?)";	
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			pstmt.setString(2, type);
			pstmt.setDouble(3, amount);
			pstmt.setString(4, info);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();	
		}
		return false;
	}

	
	
	@Override //#2 
	public List<Requests> getPendingRequests(int employeeId) {
		List<Requests> a = new ArrayList<Requests>();
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "SELECT REQUEST_ID, EMPLOYEE_ID, RQ_DATE, RQ_TYPE, RQ_AMT, "
					+ "REQUEST_STATUS, RQ_IMAGE, INFO FROM REQUESTS WHERE EMPLOYEE_ID= ? "
					+ "AND REQUEST_STATUS='PENDING'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int rq = rs.getInt("REQUEST_ID");
				int em = rs.getInt("EMPLOYEE_ID");
				Date date = rs.getDate("RQ_DATE");
				String type = rs.getString("RQ_TYPE");
				double amt = rs.getDouble("RQ_AMT");
				String stat = rs.getString("REQUEST_STATUS");
				String img = rs.getString("RQ_IMAGE");
				String info = rs.getString("INFO");
				a.add(new Requests(rq, em, date, type,  amt, img, stat, info));
				System.out.println("pending req from dao"+a);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} 
		return a;
	}
	

	@Override
	public List<Requests> getResolvedRequests(int employeeId) {
		List<Requests> a = new ArrayList<Requests>();
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "SELECT REQUEST_ID, EMPLOYEE_ID, RQ_DATE, RQ_TYPE, RQ_AMT,"
					+ " REQUEST_STATUS, RQ_IMAGE, INFO FROM REQUESTS WHERE EMPLOYEE_ID= ? "
					+ "AND REQUEST_STATUS='RESOLVED'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int rq = rs.getInt("REQUEST_ID");
				int em = rs.getInt("EMPLOYEE_ID");
				Date date = rs.getDate("RQ_DATE");
				String type = rs.getString("RQ_TYPE");
				double amt = rs.getDouble("RQ_AMT");
				String stat = rs.getString("REQUEST_STATUS");
				String img = rs.getString("RQ_IMAGE");
				String info = rs.getString("INFO");
				a.add(new Requests(rq, em, date, type,  amt, img, stat, info));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} 
		return a;
	}

	@Override 
	public Employees getEmployeeInfo(int employeeId) {
		Employees emp = new Employees();
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "SELECT EMPLOYEE_ID, TITLE, FIRSTNAME, LASTNAME, EMAIL, REPORTSTO FROM EMPLOYEES WHERE EMPLOYEE_ID=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Successful for retreving info from EmpReqDao");
			while (rs.next()) {
				int empId = rs.getInt("EMPLOYEE_ID");
				String title = rs.getString("TITLE");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				int reportsTo = rs.getInt("REPORTSTO");
				String email = rs.getString("EMAIL");
				emp = new Employees(empId, firstName, lastName, title, reportsTo, email);
			}
			
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();	
		}
		return emp;
	}

	@Override //honestly it works in sql but for whatever reason is tempermental so shrug
	public boolean updateEmployeeInfo(Employees emp) {
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "{call SP_EMP_UPDATE(?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(1, emp.getEmployeeId());
			System.out.println(emp.getEmployeeId());
			cs.setString(2, emp.getFirstName());
			cs.setString(3, emp.getLastName());
			cs.setString(4, emp.getEmail());
			System.out.println(emp.getEmail());
			cs.execute();
			
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();	
		}
		return false;
	}		
	
	@Override
	public boolean updateEmps(Employees emp) {
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "UPDATE EMPLOYEES SET FIRSTNAME = ?, LASTNAME = ?, EMAIL = ? WHERE EMPLOYEE_ID = ?";	
			PreparedStatement pstmt = con.prepareStatement(sql);
			System.out.println(emp.getEmail());
			pstmt.setString(1, emp.getFirstName());
			pstmt.setString(2, emp.getLastName());
			pstmt.setString(3, emp.getEmail());
			pstmt.setInt(4, emp.getEmployeeId());
			pstmt.executeUpdate();
			System.out.println("have ya made it lassie?");
			return true;
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();	
		}
		return false;
	}		

	@Override
	public boolean resolveRequest(int requestId, String status) {
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "UPDATE REQUESTS SET REQUEST_STATUS = ? WHERE REQUEST_ID=?";	
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, requestId);
			pstmt.executeUpdate();
			System.out.println("Successful for requests");
			return true;
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();	
		}
		return false;
	}

	@Override
	public int getLogin(String username, String password) {
		PreparedStatement pstmt = null;
	
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "SELECT EMPLOYEE_ID FROM LOGIN WHERE USERNAME=? AND UPASSWORD=?";
			pstmt = con.prepareStatement(sql);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int empId = rs.getInt("EMPLOYEE_ID");
					System.out.println("Your emp from dao is" + empId);
					return empId;
				}else {
					System.out.println("Doesn't match bruv");
					return 0;
			} 
		}catch (SQLException | IOException e) {
			e.printStackTrace();
		} 
		return 1; 
		
	}
	
	@Override
	public String getManager(int employeeId) {
		PreparedStatement pstmt = null;
		String manName = "";
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "SELECT E1.FIRSTNAME AS EMPLOYEE, E2.FIRSTNAME AS SUPERVISOR FROM EMPLOYEES E1 INNER JOIN EMPLOYEES E2 ON E2.EMPLOYEE_ID = E1.REPORTSTO WHERE E1.EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, employeeId);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					manName = rs.getString("SUPERVISOR");
					System.out.println("Your man for emp from dao is" + manName);
					return manName;
				} 
		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return "No Manager";
	}



	@Override //AFTER MVP
	public boolean registerEmployee(Employees emp) {
		try (Connection con = ConnectionsUtil.getConnectionFromFile()) {
			String sql = "INSERT INTO EMPLOYEES (FIRSTNAME, LASTNAME, EMAIL, TITLE, REPORTSTO) VALUES (?,?,?,?,?)";	
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, emp.getFirstName());
			pstmt.setString(2, emp.getLastName());
			pstmt.setString(3, emp.getEmail());
			pstmt.setString(4, emp.getTitle());
			pstmt.setInt(5, emp.getReportsTo());
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();	
		}
		return false;
	}
}
