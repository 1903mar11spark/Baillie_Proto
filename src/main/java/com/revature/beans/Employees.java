package com.revature.beans;

public class Employees {
	private int employeeId; 
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	//private pro_pic
	private int reportsTo;
	private String manager;
	
	public Employees() {
		super();
	}
	
	public Employees(String firstName, String lastName, String email, String title, int reportsTo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.title = title;
		this.reportsTo = reportsTo;
		
	}
	public Employees(int employeeId, String firstName, String manager) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.setManager(manager);
	}

	public Employees(int employeeId, String firstName, String lastName, String title) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
	}
	
	public Employees(String firstName, String lastName, String email, int employeeId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.employeeId = employeeId;
	}
	
	public Employees(int employeeId, String firstName) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
	}
	
	public Employees(int employeeId) {
		super();
		this.employeeId = employeeId;
	}
	
	public Employees(int employeeId, String firstName, String lastName, String title, int reportsTo, String email) {
		super();
		this.employeeId = employeeId;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.reportsTo = reportsTo;
		this.email = email;
	}
	
	public Employees(int employeeId, String firstName, String lastName, String title, String email, String manager) {
		super();
		this.employeeId = employeeId;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.manager = manager;
	}
	

	public Employees(String title, String firstName, String lastName, String email) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Employees(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	
	
	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", title=" + title + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", reportsTo=" + reportsTo + ", manager=" + manager + "]";
	}

	//maybe
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employees other = (Employees) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

}
