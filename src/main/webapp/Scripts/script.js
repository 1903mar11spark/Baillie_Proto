
 let user = {};



 window.onload = function() {
	 populateUser();
	 manViews();
	 reqViews();
	 empPendingViews();
	 selectEmpPendingViews();

	 n =  new Date();
	 y = n.getFullYear();
	 m = n.getMonth() + 1;
	 d = n.getDate();
	 document.getElementById("formDate").innerHTML = m + "/" + d + "/" + y;
 }

 function populateUser() {
 	//send GET request to SessionServlet to obtain session information
 	fetch("http://localhost:8084/Proto/session").then(function(response) {
 		return response.json();
 	}).then(function(data) {
 		if (data.session === null) {
 			window.location = "http://localhost:8084/Proto/Login";
 		} else {
			user = data;
			document.getElementById("title").innerText=user.title;
 			document.getElementById("firstName").innerText = user.firstName;
			document.getElementById("lastName").innerText = user.lastName;
			document.getElementById("empId").innerText = user.employeeId;
			document.getElementById("email").innerText = user.email;
			document.getElementById("manager").innerText = user.manager;
			 
 			
 		}
 	});

 }
//view all managers and employees
 function manViews(){
	fetch("http://localhost:8084/Proto/ManViews").then(function(response) {
		return response.json();
	}).then(function(data) {
		if (data.session === null) {
			window.location = "http://localhost:8084/Proto/ManagerHome";
		} else {
			console.log(data);
		   for (let i = 0 ; i < data.length; i ++) {
			var table =  document.getElementById("empManTable");
			let row = table.insertRow(0);
			let employeeId = row.insertCell(0);
			let firstName =  row.insertCell(1);
			let manager =  row.insertCell(2);

			employeeId.setAttribute('scope', 'row');
			employeeId.innerHTML = data[i].employeeId;

			firstName.setAttribute('scope', 'row');
			firstName.innerHTML = data[i].firstName;
			manager.setAttribute('scope', 'row');
			manager.innerHTML = data[i].manager;

			console.log(data[i].employeeId);
		}
	}
	});
 }

//view all Pending Requests from emp they manage
function empPendingViews(){
	fetch("http://localhost:8084/Proto/pRQMan").then(function(response) {
		return response.json();
	}).then(function(data) {
		if (data.session === null) {
			window.location = "http://localhost:8084/Proto/ManagerHome";
		} else {
			console.log(data);
		   for (let i = 0 ; i < data.length; i ++) {
			var table =  document.getElementById("pRQTable");
			let row = table.insertRow(0);
			let requestId = row.insertCell(0);
			let employeeId = row.insertCell(1);
			let man =  row.insertCell(2);
			let date =  row.insertCell(3);
			let type = row.insertCell(4);
			let amt = row.insertCell(5);
			let stat =  row.insertCell(6);
			let info = row.insertCell(7);

			requestId.setAttribute('scope', 'row');
			requestId.innerHTML = data[i].requestId;
			
			employeeId.setAttribute('scope', 'row');
			employeeId.innerHTML = data[i].employeeId;
			
			man.setAttribute('scope', 'row');
			man.innerHTML = data[i].managedBy;

			date.setAttribute('scope', 'row');
			date.innerHTML = data[i].rqDate;

			type.setAttribute('scope', 'row');
			type.innerHTML = data[i].rqType;
			
			amt.setAttribute('scope', 'row');
			amt.innerHTML = data[i].rqAmt;
			
			stat.setAttribute('scope', 'row');
			stat.innerHTML = data[i].status;

			info.setAttribute('scope', 'row');
			info.innerHTML = data[i].info;

			console.log(data[i].employeeId);
		}
	}
	});
 }

//view all requests with their status and manager Id
 function reqViews(){
	fetch("http://localhost:8084/Proto/AllRQ").then(function(response) {
		return response.json();
	}).then(function(data) {
		if (data.session === null) {
			window.location = "http://localhost:8084/Proto/ManagerHome";
		} else {
			console.log(data);
		   for (let i = 0 ; i < data.length; i ++) {
			var table =  document.getElementById("rqManTable");
			let row = table.insertRow(0);
			let requestId = row.insertCell(0);
			let employeeId = row.insertCell(1);
			let man =  row.insertCell(2);
			let type = row.insertCell(3);
			let stat =  row.insertCell(4);
			let info = row.insertCell(5);
		
	
			requestId.setAttribute('scope', 'row');
			requestId.innerHTML = data[i].requestId;
			
			employeeId.setAttribute('scope', 'row');
			employeeId.innerHTML = data[i].employeeId;
			
			man.setAttribute('scope', 'row');
			man.innerHTML = data[i].managedBy;

			type.setAttribute('scope', 'row');
			type.innerHTML = data[i].rqType;
			
			stat.setAttribute('scope', 'row');
			stat.innerHTML = data[i].status;

			info.setAttribute('scope', 'row');
			info.innerHTML = data[i].info;

			console.log(data[i].employeeId);
		}
	}
	});
 }

function selectEmpPendingViews(){
	fetch("http://localhost:8084/Proto/EmpReq2").then(function(response) {
		return response.json();
	}).then(function(data) {
		if (data.session === null) {
			window.location = "http://localhost:8084/Proto/ManagerHome";
		} else {
			console.log(data);
		   for (let i = 0 ; i < data.length; i ++) {
			var table =  document.getElementById("selectTable");
			let row = table.insertRow(0);
			let requestId = row.insertCell(0);
			let amt = row.insertCell(1);
			let type = row.insertCell(2);
			let stat =  row.insertCell(3);
			let info = row.insertCell(4);
		
	
			requestId.setAttribute('scope', 'row');
			requestId.innerHTML = data[i].requestId;

			amt.setAttribute('scope', 'row');
			amt.innerHTML = data[i].rqAmt;

			type.setAttribute('scope', 'row');
			type.innerHTML = data[i].rqType;
			
			stat.setAttribute('scope', 'row');
			stat.innerHTML = data[i].status;

			info.setAttribute('scope', 'row');
			info.innerHTML = data[i].info;

			console.log(data[i].employeeId);
		}
	}
	});
}