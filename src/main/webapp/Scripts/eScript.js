let user = {};

window.onload = function() {
    populateUser();
    pendingView();
    resolvedView();


    n =  new Date();
    y = n.getFullYear();
    m = n.getMonth() + 1;
    d = n.getDate();
    document.getElementById("date").innerHTML = m + "/" + d + "/" + y;
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

function pendingView(){
	fetch("http://localhost:8084/Proto/pRequests").then(function(response) {
        console.log(response);
		return response.json();
	}).then(function(data) {
		if (data.session === null) {
			window.location = "http://localhost:8084/Proto/employeehome";
		} else {
			console.log(data);
		   for (let i = 0 ; i < data.length; i ++) {
			var table =  document.getElementById("epTable");
			let row = table.insertRow(0);
			let requestId = row.insertCell(0);
			// let man =  row.insertCell(1); //not in dAO imp
			let date =  row.insertCell(1);
			let type = row.insertCell(2);
			let amt = row.insertCell(3);
			let stat =  row.insertCell(4);
			let info = row.insertCell(5);
		
			requestId.setAttribute('scope', 'row');
			requestId.innerHTML = data[i].requestId;
			
			// man.setAttribute('scope', 'row');
			// man.innerHTML = data[i].managedBy; //same

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

 function resolvedView(){
	fetch("http://localhost:8084/Proto/rRequests").then(function(response) {
        console.log(response);
		return response.json();
	}).then(function(data) {
		if (data.session === null) {
			window.location = "http://localhost:8084/Proto/employeehome";
		} else {
			console.log(data);
		   for (let i = 0 ; i < data.length; i ++) {
			var table =  document.getElementById("reTable");
			let row = table.insertRow(0);
			let requestId = row.insertCell(0);
			// let man =  row.insertCell(1);
			let date =  row.insertCell(1);
			let type = row.insertCell(2);
			let amt = row.insertCell(3);
			let stat =  row.insertCell(4);
			let info = row.insertCell(5);
		
			requestId.setAttribute('scope', 'row');
			requestId.innerHTML = data[i].requestId;
			
			// man.setAttribute('scope', 'row');
			// man.innerHTML = data[i].managedBy;

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