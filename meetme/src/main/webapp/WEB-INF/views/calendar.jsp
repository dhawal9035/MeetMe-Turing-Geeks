<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule Events</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Datepair.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.datepair.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.timepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/jquery.timepicker.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/bootstrap-datepicker.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/mycalendarevent.css" />
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
    var space1 = $('<span> </span>');
    var input2 = $('<input name="startTime'+ counter +'" id="startTime'+ counter +'" type="text" class="time start ui-timepicker-input" />');
    var word = $('<span> to </span>');
    var input3 = $('<input name="endTime'+ counter +'" id="endTime'+ counter +'" type="text" class="time end ui-timepicker-input" />');
    var space2 = $('<span> </span>');
    var input4 = $('<input name="endDate'+ counter +'" id="endDate'+ counter +'" type="text" class="date end"/>');
    var newLine = $('<br/>');
	/* newDiv.after().html('<div id="datePair">');            
	newDiv.after().html('<input class= "date start" type="text" id="startDate'+ counter +'" />');
	newDiv.after().html('<input class= "time start ui-timepicker-input" type="text" id="startTime' + counter + '" />');
	newDiv.after().html('<input class= "time end" type="text" id="endTime' + counter + '" />');
	newDiv.after().html('<input class= "date end" type="text" id="endDate' + counter + '" />');
	newDiv.after().html('</div>');  */
	$( "#TextBoxesGroup" ).append(newDiv);
	$(newDiv).append(newDatePair);
	$( newDatePair ).append(input1);
	$( newDatePair ).append(space1);
	$( newDatePair ).append(input2);
	$( newDatePair ).append(word);
	$( newDatePair ).append(input3);
	$( newDatePair ).append(space2);
	$( newDatePair ).append(input4);
	$( newDatePair ).append(newLine);
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
    			 top.location.href = "${pageContext.request.contextPath}/success";
    			 alert('Email has been sent with the meeting Invite');
    		 }
    	 	 /* /* error: function(data){
    	 		 alert('There was an issue sending the information. Please schedule the meeting again.');
    	 		top.location.href = "${pageContext.request.contextPath}/calendar"; 
    	 	 } */
    	 });
     });
  });
  </script>
</head>
<body>

<div class="container">
<h2>Create your event</h2>
	<form:form id="calendarSubmit" role="form" method="POST" action="/meetme/manualCalendar" commandName="calendar" class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-2" for="event-name">Event Name:</label>
			<div class="col-sm-4">
				<form:input type="text" path="eventName" placeholder="Enter Event Name" class="form-control" />
			</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="event-name">Event Description:</label>
			<div class="col-sm-4">				
				<form:textarea rows="4" cols="50" path="eventDescription" placeholder="Give Event Description" class="form-control"/>
			</div>
	</div>
	<div class="row">
		<label class="control-label col-sm-2" for="event-name">Probable Date and Time:</label>
			<div class="col-md-8">
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
			</div>					
			<div class="col-md-1">
	    		<input type='button' value='Add More' id='addButton' class="btn btn-success">
	    	</div>
	    	<div class="col-md-1"> 
				<input type='button' value='Remove' id='removeButton' class="btn btn-danger">
			</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="event-name">Required People: </label>
			<div class="col-sm-4">		
				<form:textarea rows="4" cols="50" path="guestRequiredEmail" placeholder="Enter Email ID's of people who are required to attend the meeting" class="form-control" />
			</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="event-name">Optional People: </label>
			<div class="col-sm-4">		
				<form:textarea rows="4" cols="50" path="guestOptionalEmail" placeholder="Enter Email ID's of people who are optional for the meeting" class="form-control" />
			</div>
	</div>
		<input type="Button" value="Done!" id="submit" class="btn btn-success"> 
	</form:form> 
</div>
</body>
</html>