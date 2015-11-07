<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule Events</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Datepair.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.datepair.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.timepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/jquery.timepicker.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/bootstrap-datepicker.css" />
<script>
$(document).ready(function(){
	var counter = 1;
	$('.datePair .time').timepicker({
	    'showDuration': true,
	    'timeFormat': 'H:i:s'
	});

	$('.datePair .date').datepicker({
	    'format': 'yyyy-mm-dd',
	    'autoclose': true
	});

	var datePairval = document.getElementById('datePair');
    var datepair = new Datepair(datePairval);

     $("#addButton").click(function () {	
	/* if(counter>10){
            alert("Only 10 textboxes allow");
            return false;
	}
	 var newDiv = $(document.createElement('div'))
	     .attr("id", 'Div' + counter); */
	var newDiv = $('<div id="Div'+ counter +'">');
    var newDatePair = $("<div id='datePair"+counter+"'  class='datePair'>");
    var input1 = $('<input name="startDate'+ counter +'" id="startDate'+ counter +'" type="text" class="date start"/>');
    var input2 = $('<input name="startTime'+ counter +'" id="startTime'+ counter +'" type="text" class="time start ui-timepicker-input" />');
    var word = $('<span> to </span>');
    var input3 = $('<input name="endTime'+ counter +'" id="endTime'+ counter +'" type="text" class="time end ui-timepicker-input" />');
    var input4 = $('<input name="endDate'+ counter +'" id="endDate'+ counter +'" type="text" class="date end"/>');
	/* newDiv.after().html('<div id="datePair">');            
	newDiv.after().html('<input class= "date start" type="text" id="startDate'+ counter +'" />');
	newDiv.after().html('<input class= "time start ui-timepicker-input" type="text" id="startTime' + counter + '" />');
	newDiv.after().html('<input class= "time end" type="text" id="endTime' + counter + '" />');
	newDiv.after().html('<input class= "date end" type="text" id="endDate' + counter + '" />');
	newDiv.after().html('</div>');  */
	$( "#TextBoxesGroup" ).append( newDiv);
	$(newDiv).append( newDatePair);
	$( newDatePair ).append( input1);
	$( newDatePair ).append( input2);
	$( newDatePair ).append(word);
	$( newDatePair ).append( input3);
	$( newDatePair ).append( input4);
	var id = "datePair"+counter;
	$('.datePair .time').timepicker({
	    'showDuration': true,
	    'timeFormat': 'H:i:s'
	});

	$('.datePair .date').datepicker({
	    'format': 'yyyy-mm-dd',
	    'autoclose': true
	});
	var datePairval = document.getElementsByClassName('datePair');
	var datepair;
	for (var i=0;i<datePairval.length;i+=1){
		datepair = new Datepair(datePairval[i]);
	}
//    var datepair = new Datepair(datePairval);

	/*  newDatePair, input1, input2,input3,input4,newDatePairEnd,newDivEnd ); */
	//newDiv.appendTo("#TextBoxesGroup");
	counter++;
	$("#counter").val(counter);
     });

     $("#removeButton").click(function () {
	if(counter==1){
          alert("No more event timings to remove");
          return false;
       }   
        
	counter--;
	$("#counter").val(counter);
			
        $("#Div" + counter).remove();
			
     });
		
      $("#getButtonValue").click(function () {
		
	var msg = '';
	for(i=1; i<counter; i++){
   	  msg += "\n Event #" + i + " : " + $('#textbox' + i).val();
	}
    	  alert(msg);
     });
     var frm = $('#calendarSubmit');
     $("#submit").click(function(){
    	 $.ajax({
    		 type:frm.attr('method'),
    		 url:frm.attr('action'),
    		 data:frm.serialize(),
    		 success: function(data){
    			 top.location.href = '/meetme/success';
    			 alert('Email has been sent with the meeting Invite');
    		 }
    	 });
     });
  });
  </script>
</head>
<body>
	<form:form id="calendarSubmit" role="form" method="POST" action="/meetme/manualCalendar" commandName="calendar">
		<table>
			<tr>
				<td>Event Name:</td>
				<td><form:input type="text" path="eventName" placeholder="Enter Event Name" /> </td>
			</tr>
			<tr>
				<td>Event Description</td>
				<td><form:textarea rows="4" cols="50" path="eventDescription" placeholder="Enter Event Description" /> </td>
			</tr>
			<tr>
				<td>Probable Date and Time of Event</td>
				<td>
					<div id='TextBoxesGroup'>
						<div id="Div0">
							<div id="datePair" class="datePair">
								<input name="startDate0" id="startDate0" type="text" class="date start" />
								<input name="startTime0" id="startTime0" type="text" class="time start ui-timepicker-input"  /> to
								<input name="endTime0" id="endTime0" type="text" class="time end ui-timepicker-input" />
								<input name="endDate0" id="endDate0" type="text" class="date end"/>
							</div>
						</div>
						<input name="counter" type="hidden" id="counter" value="1" />
					</div> 
				</td>
			</tr>
			<tr>
				<td><input type='button' value='Add More Event Timings' id='addButton'> </td>
				<td><input type='button' value='Remove the Last Added Event Time' id='removeButton'></td> 
			</tr>
			<tr>
				<td>Enter Email ID separated by a comma</td>
				<td><form:textarea rows="4" cols="50" path="guestEmail" placeholder="Enter Email ID's of people to be invited" /> </td>
			</tr>
			<tr>
				<td colspan="2"><center><input type="Button" value="Submit" id="submit"> </center> </td>
			</tr>
		</table>
	</form:form> 
</body>
</html>