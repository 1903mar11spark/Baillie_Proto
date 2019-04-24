package com.revature.beans;

import java.sql.Date;

public class Requests {

	private int requestId;
	private int employeeId;
	private int managedBy;
	private Date rqDate;
	private String rqType;
	private double rqAmt;
	private String status;
	private String image;
	private String info;
	//private SerialBlob image; need to also import the blob in the case of it's use
	
	public Requests() {
		super();
		
	}
	
	public Requests(int requestId) {
		super();
		this.requestId = requestId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(int managedBy) {
		this.managedBy = managedBy;
	}

	public Date getRqDate() {
		return rqDate;
	}

	public void setRqDate(Date rqDate) {
		this.rqDate = rqDate;
	}

	public String getRqType() {
		return rqType;
	}

	public void setRqType(String rqType) {
		this.rqType = rqType;
	}

	public double getRqAmt() {
		return rqAmt;
	}

	public void setRqAmt(double rqAmt) {
		this.rqAmt = rqAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
//9
	public Requests(int requestId, int employeeId, int managedBy, Date rqDate, String rqType, double rqAmt,
			String status, String image, String info) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.managedBy = managedBy;
		this.rqDate = rqDate;
		this.rqType = rqType;
		this.rqAmt = rqAmt;
		this.status = status;
		this.image = image;
		this.info = info;
	}
//7
	public Requests(int rq, int em, Date date, String type, double amt, String stat, String info) {
		super();
		this.requestId = rq;
		this.employeeId = em;
		this.rqDate = date;
		this.rqType = type;
		this.rqAmt = amt;
		this.status = stat;
		this.info = info;
	}
	//3
	public Requests(int em, String type, String info) {
		super();
		this.employeeId = em;
		this.rqType = type;
		this.info = info;
	}
	//4
	public Requests(int em, String type, double amt, String info) {
		super();
		this.employeeId = em;
		this.rqType = type;
		this.rqAmt = amt;
		this.info = info;
	}

	public Requests(String img) {
		super();
		this.image = img;
		// TODO Auto-generated constructor stub
	}
//number 2 -- 8
	public Requests(int rq, int em, Date date, String type, double amt, String img, String stat, String info) {
		super();
		this.requestId = rq;
		this.employeeId = em;
		this.rqDate = date;
		this.rqType = type;
		this.rqAmt = amt;
		this.image = img;
		this.status = stat;
		this.info = info;
	}

	

	public Requests(int req_id, double amt, String pic, String status, String type, String info) {
		super();
		this.requestId = req_id;
		this.rqAmt = amt;
		this.status = status;
		this.info = info;
		this.image = pic;
	}

	public Requests(int rq, int em, java.sql.Date date, double amt, String stat, String info) {
		super();
		this.requestId = rq;
		this.employeeId = em;
		this.rqDate = date;
		this.rqAmt = amt;
		this.status = stat;
		this.info = info;
	}

	@Override
	public String toString() {
		return "Requests [requestId=" + requestId + ", employeeId=" + employeeId + ", managedBy=" + managedBy
				+ ", rqDate=" + rqDate + ", rqType=" + rqType + ", rqAmt=" + rqAmt + ", status=" + status + ", image="
				+ image + ", info=" + info + "]";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	

}

