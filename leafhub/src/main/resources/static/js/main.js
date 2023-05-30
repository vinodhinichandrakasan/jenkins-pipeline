//Delete Rows Dynamically
function removeRow(rowId){
	$('#'+rowId).remove();
}

//Add Rows Dynamically
	$("#addDrugBtn").click(function () {
		$("#drugTable").each(function () {
			var size	=	$('#drugTable tr').length;
			var newRow = jQuery('<tr id="' + (size) + '">' + 
					'<td><select required name="drugType_' + (size) + '" class="required form-control" id="drugType_' + (size) + '">' +
					'<option value="">Select</option>' +
					'<option value="Tablet">Tablet</option>' +
					'<option value="Drop">Drops</option>' +
					'</select></td>' +
					'<td><input type="text" required="true" class="required form-control" name="drugName_' + (size) + '" id="drugName_' + (size) + '"/></td>' +
					'<td><input type="text" required="true" class="required form-control" name="quantity_' + (size) + '" id="quantity_' + (size) + '"/></td>' +
					'<td><input type="text" required="true" class="required form-control" name="frequency_' + (size) + '" id="frequency_' + (size) + '"/></td>' +
					'<td colspan="2"><input required="true" type="text" class="required form-control" name="duration_' + (size) + '" id="duration_' + (size) + '"/></td>' + 
					'<td colspan="2"><select required name="durationType_' + (size) + '" class="required form-control" id="durationType_' + (size) + '">' +
					'<option value="">Select</option>' +
					'<option value="days">Days</option>' +
					'<option value="weeks">Weeks</option>' +
					'<option value="months">Months</option>' +
					'</select></td>' +
					'<td colspan="2"><input type="button" class="btn btn-danger" name="removeBtn_' + (size) + '" id="removeBtn_' + (size) + '"' + 
					'onclick="removeRow('+ (size) +')" value="Remove"/></td>' + 
			'</tr>');
			jQuery("#drugTable").append(newRow);
		});
	});

	
//Toggle Search Filter Text/Date in Visit Search
$("#visitSearchDrop").on('change', function(event) {
	if ( $("#visitSearchDrop").val() == "date" ){
		$("#nameTextDiv").hide();
		$("#dateFromDiv").show();
		$("#dateToDiv").show();
	}
	else{
		$("#nameTextDiv").show();
		$("#dateFromDiv").hide();
		$("#dateToDiv").hide();
	}
});


/*
 * Function to show/hide text box on change of the following Drop Downs:
 * 1. Vision Without Glass
 * 2. Vision With Glass
 * 3. Lens
 * 4. Cornea
 * 5. Pupil
 * 6. Iris
 * 7. Anterior Chamber
 * 8. Remarks
 */
function toggleTextBox( listRightId, listLeftId, rowId, cellRightId, cellLeftId ){
	if ( $("#" + listRightId ).val() == "other" || $("#" + listLeftId ).val() == "other" ){
		// Show Row
		if ( $("#" + listRightId ).val() == "other" && $("#" + listLeftId ).val() == "other" ){
			// Show both cells
			$("#" + cellRightId ).removeClass("hidden");
			$("#" + cellLeftId ).removeClass("col-sm-offset-3");
			$("#" + cellLeftId ).removeClass("hidden");
		}
		else if( $("#" + listRightId ).val() == "other" ){
			// Show right cell and hide left cell
			$("#" + cellRightId ).removeClass("hidden");
			$("#" + cellLeftId ).removeClass("col-sm-offset-3");
			$("#" + cellLeftId ).addClass("hidden");
		}
		else if( $("#" + listLeftId ).val() == "other" ){
			// Show left cell and hide right cell
			$("#" + cellRightId ).addClass("hidden");
			$("#" + cellLeftId ).addClass("col-sm-offset-3");
			$("#" + cellLeftId ).removeClass("hidden");
		}
		$("#" + rowId ).removeClass("hidden");
	}
	else{
		// Hide Row
		$("#" + rowId ).addClass("hidden");
	}
	
	
}

// Toggle Text Box for Vision Without Glass Right
$("#visionWithoutGlassRight").on('change', function(event) {
	toggleTextBox( 'visionWithoutGlassRight', 'visionWithoutGlassLeft', 'visionWithoutGlassRow', 
			'visionWithoutGlassRightCell', 'visionWithoutGlassLeftCell');
});

//Toggle Text Box for Vision Without Glass Left
$("#visionWithoutGlassLeft").on('change', function(event) {
	toggleTextBox( 'visionWithoutGlassRight', 'visionWithoutGlassLeft', 'visionWithoutGlassRow', 
			'visionWithoutGlassRightCell', 'visionWithoutGlassLeftCell');
});


//Toggle Text Box for Vision With Glass Right
$("#visionWithGlassRight").on('change', function(event) {
	toggleTextBox( 'visionWithGlassRight', 'visionWithGlassLeft', 'visionWithGlassRow', 
			'visionWithGlassRightCell', 'visionWithGlassLeftCell');
});

//Toggle Text Box for Vision With Glass Left
$("#visionWithGlassLeft").on('change', function(event) {
	toggleTextBox( 'visionWithGlassRight', 'visionWithGlassLeft', 'visionWithGlassRow', 
			'visionWithGlassRightCell', 'visionWithGlassLeftCell');
});


//Toggle Text Box for Lens Right
$("#lensRight").on('change', function(event) {
	toggleTextBox( 'lensRight', 'lensLeft', 'lensRow', 
			'lensRightCell', 'lensLeftCell');
});

//Toggle Text Box for Lens Left
$("#lensLeft").on('change', function(event) {
	toggleTextBox( 'lensRight', 'lensLeft', 'lensRow', 
			'lensRightCell', 'lensLeftCell');
});

//Toggle Text Box for Cornea Right
$("#corneaRight").on('change', function(event) {
	toggleTextBox( 'corneaRight', 'corneaLeft', 'corneaRow', 
			'corneaRightCell', 'corneaLeftCell');
});

//Toggle Text Box for Cornea Left
$("#corneaLeft").on('change', function(event) {
	toggleTextBox( 'corneaRight', 'corneaLeft', 'corneaRow', 
			'corneaRightCell', 'corneaLeftCell');
});

//Toggle Text Box for Pupil Right
$("#pupilRight").on('change', function(event) {
	toggleTextBox( 'pupilRight', 'pupilLeft', 'pupilRow', 
			'pupilRightCell', 'pupilLeftCell');
});

//Toggle Text Box for Pupil Left
$("#pupilLeft").on('change', function(event) {
	toggleTextBox( 'pupilRight', 'pupilLeft', 'pupilRow', 
			'pupilRightCell', 'pupilLeftCell');
});

//Toggle Text Box for Iris Right
$("#irisRight").on('change', function(event) {
	toggleTextBox( 'irisRight', 'irisLeft', 'irisRow', 
			'irisRightCell', 'irisLeftCell');
});

//Toggle Text Box for Iris Left
$("#irisLeft").on('change', function(event) {
	toggleTextBox( 'irisRight', 'irisLeft', 'irisRow', 
			'irisRightCell', 'irisLeftCell');
});

//Toggle Text Box for Anterior Right
$("#antChamberRight").on('change', function(event) {
	toggleTextBox( 'antChamberRight', 'antChamberLeft', 'anteriorRow', 
			'anteriorRightCell', 'anteriorLeftCell');
});

//Toggle Text Box for Anterior Left
$("#antChamberLeft").on('change', function(event) {
	toggleTextBox( 'antChamberRight', 'antChamberLeft', 'anteriorRow', 
			'anteriorRightCell', 'anteriorLeftCell');
});

//Toggle Text Box for Remarks Right
$("#remarksRight").on('change', function(event) {
	toggleTextBox( 'remarksRight', 'remarksLeft', 'remarksRow', 
			'remarksRightCell', 'remarksLeftCell');
});

//Toggle Text Box for Remarks Left
$("#remarksLeft").on('change', function(event) {
	toggleTextBox( 'remarksRight', 'remarksLeft', 'remarksRow', 
			'remarksRightCell', 'remarksLeftCell');
});


//Reset Forms
function init() {
	document.getElementById("addVisit").reset();
	document.getElementById("regForm").reset();
}
window.onload = init;


$("#checkDob").click(function(){
	if( $("#checkDob").is(':checked') ){
		$("#divDob").removeClass("hidden");
		$("#age").attr("readonly",true);
	}
	else{
		$("#divDob").addClass("hidden");
		$("#birthDate").val('');
		$("#age").attr("readonly",false);
		$("#age").val('');
	}
});

$("#birthDate").on('change',function(event){
	var dob		=	new Date($("#birthDate").val());
	var today	=	new Date();	
	var age = Math.floor((today-dob) / (365.25 * 24 * 60 * 60 * 1000));
	$("#age").val(age);
});

$("#checkPhone").click(function(){
	if( $("#checkPhone").is(':checked') ){
		$("#divPhone").removeClass("hidden");
		
	}
	else{
		$("#divPhone").addClass("hidden");
		$("#phoneNumber").val('');
	}
});

function setTitleDropDown(patientId, title){
	var id = 'titleEdit' + patientId;
	$('#' + id).val(title); 
}

function setGenderDropDown(patientId, gender){
	var id = 'genderEdit' + patientId;
	$('#' + id).val(gender); 
}
