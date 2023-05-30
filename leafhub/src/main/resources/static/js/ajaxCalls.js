// Delete Patient AJAX Starts
function deleteBtn(patientId){
	ajaxDelPost(patientId);
}

function ajaxDelPost(patientId){
	var url	=	window.location;
	var formData ={
			patientId : patientId
	}
	
	$.ajax({
	    type : "POST",
        contentType : "application/json",
        url : url + "/patientDelete",
        data :JSON.stringify(formData),
        dataType : 'json',
        success : function(result) {
            if(result.status == "Done"){
            
            	$("#msg").html("<strong>" + "Patient Deleted Successfully!</strong>");
            	deleteRow(patientId);
            }else{
            	
            	$("#msg").html("<strong>" + "Something Went wrong</strong>");
            }
            console.log(result);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function deleteRow(patientId){
	$("#tr"+patientId).hide();
} 
//Delete Patient AJAX Ends


//Edit Patient AJAX Starts
function editBtn(patientId,title, firstName, middleName, lastName, gender, email, mobile, phoneNumber){
	ajaxEditPost(patientId,title, firstName, middleName, lastName,gender,email,mobile,phoneNumber);
}

function ajaxEditPost(patientId, title, firstName, middleName, lastName,gender,email,mobile,phoneNumber){
	var url	=	window.location;
	var formData ={
			patientId	:	patientId,
			title		:	title,
			firstName	:	firstName,
			middleName	:	middleName,
			lastName	:	lastName,
			gender		:	gender,
			email		:	email,
			mobile		:	mobile,
			phoneNumber	:	phoneNumber
	}
	
	$.ajax({
	    type : "POST",
        contentType : "application/json",
        url : url + "/patientEdit",
        data :JSON.stringify(formData),
        dataType : 'json',
        success : function(result) {
            if(result.status == "Done"){
            	$("#fullName" + result.data.patientId).text(result.data.title + " " + result.data.firstName + " " + 
            												result.data.middleName + " " + result.data.lastName);
            	$("#gender" + result.data.patientId).text(result.data.gender);
            	
            	// View Modal
            	$("#firstNameView" + result.data.patientId).text(result.data.title + " " + result.data.firstName);
            	$("#middleNameView" + result.data.patientId).text(result.data.middleName);
            	$("#lastNameView" + result.data.patientId).text(result.data.lastName);
            	$("#genderView" + result.data.patientId).text(result.data.gender);
            	//$("#ageView" + result.data.patientId).text(result.data.age); // Currently Non-editable
            	$("#emailView" + result.data.patientId).text(result.data.email);
            	$("#mobileView" + result.data.patientId).text(result.data.mobile);
            	$("#phoneView" + result.data.patientId).text(result.data.phoneNumber);
            	
            	// Edit Modal
            	setTitleDropDown(result.data.patientId, result.data.title);
            	setGenderDropDown(result.data.patientId, result.data.gender);
            	$("#titleEdit" + result.data.patientId).val(result.data.title);
            	$("#firstNameEdit" + result.data.patientId).text(result.data.firstName);
            	$("#middleNameEdit" + result.data.patientId).text(result.data.middleName);
            	$("#lastNameEdit" + result.data.patientId).text(result.data.lastName);
            	$("#genderEdit" + result.data.patientId).val(result.data.gender);
            	//$("#ageEdit" + result.data.patientId).text(result.data.age); // Currently Non-editable
            	$("#emailEdit" + result.data.patientId).text(result.data.email);
            	$("#mobileEdit" + result.data.patientId).text(result.data.mobile);
            	$("#phoneEdit" + result.data.patientId).text(result.data.phoneNumber);
            	setTitleDropDown(result.data.patientId, result.data.title);
            	setGenderDropDown(result.data.patientId, result.data.gender);
            	
            	$("#msg").html("<strong>" + "Patient Details Edited Successfully!</strong>");
            }else{
            	
            	$("#msg").html("<strong>" + "Something Went wrong</strong>");
            }
            console.log(result);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}
//Edit Patient AJAX Ends

//Print Report AJAX Starts
function printReport(visitId){
	var url	=	window.location + "/print/" + visitId;
	alert("In , id : " + visitId);
	alert(url);
	
	$.ajax({
	    type : "POST",
        contentType : "application/pdf",
        url : url,
        success : function(result) {
            alert("Result : " + result);
        	console.log(result);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

//Print Report AJAX Ends