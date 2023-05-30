//Add Visit AJAX Call
function addVisitDetails(){
	var url	=	window.location;
	var drugData	=	[];
	var size	=	$('#drugTable tr').length;
	for ( var i = 1 ; i < size ; i++ ){
		drugData.push({
			drugType		:	document.getElementById('drugType_'+i).value,
			drugName		:	document.getElementById('drugName_'+i).value,
			quantity		:	document.getElementById('quantity_'+i).value,
			frequency		:	document.getElementById('frequency_'+i).value,
			duration		:	document.getElementById('duration_'+i).value,
			durationType	:	document.getElementById('durationType_'+i).value
		});
	}
	var visitData	=	{	
			sphDistRight 			: $('#sphDistRight').val(),
			sphDistLeft 			: $('#sphDistLeft').val(),
			cylDistRight 			: $('#cylDistRight').val(),
			axisDistRight 			: $('#axisDistRight').val(),
			vaDistRight 			: $('#vaDistRight').val(),
			cylDistLeft 			: $('#cylDistLeft').val(),
			axisDistLeft 			: $('#axisDistLeft').val(),
			vaDistLeft 				: $('#vaDistLeft').val(),
			sphNearRight 			: $('#sphNearRight').val(),
			sphNearLeft 			: $('#sphNearLeft').val(),
			cylNearRight 			: $('#cylNearRight').val(),
			axisNearRight 			: $('#axisNearRight').val(),
			vaNearRight 			: $('#vaNearRight').val(),
			cylNearLeft 			: $('#cylNearLeft').val(),
			axisNearLeft 			: $('#axisNearLeft').val(),
			vaNearLeft 				: $('#vaNearLeft').val(),
			remarksRight			: $('#remarksRight').val(),
			remarksRightDesc		: $('#remarksRightDesc').val(),
			remarksLeft				: $('#remarksLeft').val(),
			remarksLeftDesc			: $('#remarksLeftDesc').val(),
			anteriorSeg 			: $('#anteriorSeg').val(),
			posteriorSeg 			: $('#posteriorSeg').val(),
			iopRight 				: $('#iopRight').val(),
			iopLeft 				: $('#iopLeft').val(),
			complaints				: $('#complaints').val(),
			keraK1 					: $('#keraK1').val(),
			keraK2 					: $('#keraK2').val(),
			axialLength 			: $('#axialLength').val(),
			iolPower 				: $('#iolPower').val(),
			surgicalPlan 			: $('#surgicalPlan').val(),
			admissionDate 			: $('#admissionDate').val(),
			surgeryDate 			: $('#surgeryDate').val(),
			dischargeDate 			: $('#dischargeDate').val(),
			diagnosis 				: $('#diagnosis').val(),
			surgicalProc 			: $('#surgicalProc').val(),
			investigations 			: $('#investigations').val(),
			anaesthetist 			: $('#anaesthetist').val(),
			surgeon 				: $('#surgeon').val(),
			followUp 				: $('#followUp').val(),
			drugs					: drugData,
			
			// New Fields Below
			
			visionWithoutGlassRight 		: $('#visionWithoutGlassRight').val(),
			visionWOGlassRightDesc 			: $('#visionWOGlassRightDesc').val(),
			visionWithoutGlassLeft 			: $('#visionWithoutGlassLeft').val(),
			visionWOGlassLeftDesc 			: $('#visionWOGlassLeftDesc').val(),
			visionWithGlassRight 			: $('#visionWithGlassRight').val(),
			visionWGlassRightDesc 			: $('#visionWGlassRightDesc').val(),
			visionWithGlassLeft 			: $('#visionWithGlassLeft').val(),
			visionWGlassLeftDesc 			: $('#visionWGlassLeftDesc').val(),
			lensRight 						: $('#lensRight').val(),
			lensRightDesc 					: $('#lensRightDesc').val(),
			lensLeft 						: $('#lensLeft').val(),
			lensLeftDesc 					: $('#lensLeftDesc').val(),
			corneaRight 					: $('#corneaRight').val(),
			corneaRightDesc 				: $('#corneaRightDesc').val(),
			corneaLeft 						: $('#corneaLeft').val(),
			corneaLeftDesc 					: $('#corneaLeftDesc').val(),
			pupilRight 						: $('#pupilRight').val(),
			pupilRightDesc 					: $('#pupilRightDesc').val(),
			pupilLeft 						: $('#pupilLeft').val(),
			pupilLeftDesc 					: $('#pupilLeftDesc').val(),
			irisRight 						: $('#irisRight').val(),
			irisRightDesc 					: $('#irisRightDesc').val(),
			irisLeft 						: $('#irisLeft').val(),
			irisLeftDesc 					: $('#irisLeftDesc').val(),
			antChamberRight 				: $('#antChamberRight').val(),
			antChamberRightDesc 			: $('#antChamberRightDesc').val(),
			antChamberLeft 					: $('#antChamberLeft').val(),
			antChamberLeftDesc 				: $('#antChamberLeftDesc').val(),
			fundusRight 					: $('#fundusRight').val(),
			fundusLeft 						: $('#fundusLeft').val(),
			swellingRight 					: $('#swellingRight').val(),
			swellingLeft 					: $('#swellingLeft').val(),
			rednessRight 					: $('#rednessRight').val(),
			rednessLeft 					: $('#rednessLeft').val(),
			conjRednessRight 				: $('#conjRednessRight').val(),
			conjRednessLeft 				: $('#conjRednessLeft').val(),
			conjTearRight 					: $('#conjTearRight').val(),
			conjTearLeft 					: $('#conjTearLeft').val(),
			ocularSurgeryRight 				: $('#ocularSurgeryRight').val(),
			ocularSurgeryLeft 				: $('#ocularSurgeryLeft').val(),
			sacPBl 							: $('input[name=sacPBl]:checked').val(),
			urine 							: $('#urine').val(),
			bloodPressureVal1 				: $('#bloodPressureVal1').val(),
			bloodPressureVal2 				: $('#bloodPressureVal2').val(),
			bloodSugarFasting 				: $('#bloodSugarFasting').val(),
			bloodSugarPp 					: $('#bloodSugarPp').val()
			
	}
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify(visitData),
		dataType : 'json',
		success : function(result) {
			if( result.status == "Done"){
				//clearForm();
				window.location.href = result.data + '?result=1';
				$("#msg").html("<strong>" + "Visit Added Successfully!</strong>");
			}else{
				$("#msg").html("<strong>" + "Something Went wrong</strong>");
			}
			console.log("Whole Result : " + JSON.stringify(result));
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}


//Validation Starts
$(document).ready(function() { 

	$("#regForm").validate({
		rules	:	{
			name		:	"required",
			answer		:	"required",
			userName	:	"required",
			role		:	"required",
			secQuest	:	"required",
			password	:	{
				required	:	true,
				minlength	:	5
			},
			cnfPassword	:	{
				required	:	true,
				equalTo		:	"#password"
			}
		},
		messages	:	{
			name	:	{
				required	:	"Please enter your name"
			},
			answer	:	{
				required	:	"Please enter an answer"
			},
			userName	:	{
				required	:	"Please enter a username"
			},
			password	:	{
				required	:	"Please enter a password",
				minlength	:	"Your password must be at least 5 characters long"
			},
			cpassword	:	{
				required	:	"Please enter a password",
				equalTo		:	"Please enter the same password as above"
			}
		}
	});

	jQuery.validator.addMethod("lettersonly", function(value, element) {
		return this.optional(element) || /^[a-z]+$/i.test(value);
	}, "Letters only please"); 


	//Date Validation
	var dateCheck = function( date1, date2 ) {
		if ( !isNaN(Date.parse(date1)) && !isNaN(Date.parse(date2)) ){
			var dateOne = new Date(date1),
			dateTwo = new Date(date2);

			// Check Year
			if ( dateTwo.getFullYear() >= dateOne.getFullYear()){
				// Check Month
				if ( dateTwo.getMonth() >= dateOne.getMonth() ){
					// Check Date
					if ( dateTwo.getDate() >= dateOne.getDate()){
						return true;
					}
					else
						return false;
				}
				else
					return false;
			}
			else
				return false;
		}
		else {
			return true;
		}
	};

	jQuery.validator.addMethod("dateCheck1", function(value, element) {
		return dateCheck( $('#admissionDate').val(), $('#surgeryDate').val() );
	}, "Admission Date must be earlier than Surgery Date");
	
	jQuery.validator.addMethod("dateCheck2", function(value, element) {
		return dateCheck( $('#admissionDate').val(), $('#surgeryDate').val() );
	}, "Surgery Date must be later than Admission Date");
	
	jQuery.validator.addMethod("dateCheck3", function(value, element) {
		return dateCheck( $('#admissionDate').val(), $('#dischargeDate').val() );
	}, "Admission Date must be earlier than Discharge Date");
	
	jQuery.validator.addMethod("dateCheck4", function(value, element) {
		return dateCheck( $('#admissionDate').val(), $('#dischargeDate').val() );
	}, "Discharge Date must be later than Admission Date");
	
	jQuery.validator.addMethod("dateCheck5", function(value, element) {
		return dateCheck( $('#surgeryDate').val(), $('#dischargeDate').val() );
	}, "Surgery Date must be earlier than Discharge Date");
	
	jQuery.validator.addMethod("dateCheck6", function(value, element) {
		return dateCheck( $('#surgeryDate').val(), $('#dischargeDate').val() );
	}, "Discharge Date must be later than Surgery Date");

	$("#addVisit").validate({
		rules	:	{
			axisDistRight	:	{
				range	:	[1, 180],
				digits	:	true
			},
			axisDistLeft	:	{
				range	:	[1, 180],
				digits	:	true
			},
			axisNearRight	:	{
				range	:	[1, 180],
				digits	:	true
			},
			axisNearLeft	:	{
				range	:	[1, 180],
				digits	:	true
			},
			iop	:	{
				digits		:	true,
				rangelength	:	[1, 3]
			},
			keraK1	:	{
				number		:	true,
				rangelength	:	[1, 6]
			},
			keraK2	:	{
				number		:	true,
				rangelength	:	[1, 6]
			},
			axialLength	:	{
				number		:	true,
				rangelength	:	[1, 6]
			},
			iolPower	:	{
				number		:	true,
				rangelength	:	[1, 6]
			},
			followUp	:	{
				dateCheck1	:	true,
				dateCheck3	:	true
			},
			admissionDate	:	{
				dateCheck1	:	true,
				dateCheck3	:	true
			},
			surgeryDate	:	{
				dateCheck2	:	true,
				dateCheck5	:	true
			},
			dischargeDate	:	{
				dateCheck4	:	true,
				dateCheck6	:	true
			}, 
		},
		messages	:	{
			axisDistRight	:	{
				range	:	"Please enter a value between 1 to 180"
			},
			axisDistLeft	:	{
				range	:	"Please enter a value between 1 to 180"
			},
			axisNearRight	:	{
				range	:	"Please enter a value between 1 to 180"
			},
			axisNearLeft	:	{
				range	:	"Please enter a value between 1 to 180"
			}
		},
		submitHandler	:	function () {
			addVisitDetails();
		}
	});

	$("#patientForm").validate({
		rules	:	{
			firstName	:	{
				required	:	true,
				lettersonly	:	true
			},
			middleName	:	{
				lettersonly	:	true
			},
			lastName	:	{
				required	:	true,
				lettersonly	:	true
			},
			address	:	{
				required	:	true
			},
			age	:	{
				required	:	true
			},
			email	:	{
				email	:	true
			},
			gender	:	"required"
		}
	});

});	