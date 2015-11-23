<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/fullcalendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/fullcalendar.min.css" />
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
    var word = $('<span> to </span>');
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
	$( newDatePair ).append(word);
	$( newDatePair ).append(space2);
	$( newDatePair ).append(input4);
	$( newDatePair ).append(newLine);
	var id = "datePair"+counter;
	/* $('.datePair .time').timepicker({
	    'showDuration': true,
	    'timeFormat': 'H:i:s'
	}); */

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
    			 top.location.href = "${pageContext.request.contextPath}/preferredTime";
    			 alert('Email has been sent to you and all the users with the preferred and probable meeting times.');
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

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"> Meet Me!</a>
		</div>
		<div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath}/success">Home</a>
			<li><a href="#">Contact Us</a>
			<li><a href="#">Settings</a>
			<li><a href="#">Profile</a>
			<li><button type="button" class="btn btn-success pull-right" aria-label="profile">
				<span class="glyphicon  glyphicon-user" style="vertical-align:middle, horizontal-align:right" aria-hidden="true">
				</span>
			</button>
			<li><a href = "j_spring_security_logout"> Logout </a></li>
		</ul>
		</div>
	</div>
</nav>
<br>
<br>
<br>
<br>

	<div class="col-md-2" style=" padding-top: 20px;">
		<ul class="nav nav-pills nav-stacked" >
			<li><a href="${pageContext.request.contextPath}/schedule">My Calendar</a>
			<c:set var="googleUSer" value="${isGoogleUSer}"></c:set>
			<c:if test="${not googleUSer}">
				<li><a href="${pageContext.request.contextPath}/calendar">Manual Schedule</a>			
			</c:if>
			
			<li><a href="${pageContext.request.contextPath}/meetingTime">Get Meeting Time</a>
			<c:if test="${googleUSer}">
				<li class="active"><a href="${pageContext.request.contextPath}/googleEvent">Event For MeetMe Users</a>			
			</c:if>
			
		</ul>
	</div>
<div class="container">
<h2>Create your event</h2>
	<form:form id="calendarSubmit" role="form" method="POST" action="/meetme/googleCreateEvent" commandName="calendar" class="form-horizontal">
	<div class="form-group" style=" margin-left: 0px; width: 800px; height: 65px; padding-top: 7px;margin-bottom: 0px;">
		<label class="control-label col-sm-2" for="event-name" style="text-align: center;">Event Name:</label>
			<div class="col-sm-4" style="padding-left: 75px; padding-right: 0px; width: 395px;">
				<form:input type="text" path="eventName" placeholder="Enter Event Name" class="form-control" />
			</div>
	</div>
	<div class="form-group" style="width: 1000px;margin-bottom: 0px;">
		<label class="control-label col-sm-2" for="event-description">Event Description:</label>
			<div class="col-sm-4" style="width: 380px; padding-left: 40px;">				
				<form:textarea rows="3" cols="50" path="eventDescription" placeholder="Give Event Description" class="form-control"/>
			</div>
	</div>
	<div class="row" style="padding-top: 25px; padding-bottom: 25px;">
		<label class="control-label col-sm-2" for="event-name" style="width: 250px;padding-left: 0px;">Time Slots:</label>
			<div class="col-md-8" style="padding-left: 90px;width: 555px;">
				<div id='TextBoxesGroup'>
					<div id="Div0">
						<div id="datePair" class="datePair">
							<input name="startDate0" id="startDate0" type="text" class="date start" /> to
							<input name="endDate0" id="endDate0" type="text" class="date end"/>
						</div>
					</div>
					<input name="counter" type="hidden" id="counter" value="1" />
				</div> 
			</div>					
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="event-name" style="width: 290px;">Required People: </label>
			<div class="col-sm-4" style=" width: 393px; padding-left: 48px;">		
				<form:textarea rows="3" cols="50" path="guestRequiredEmail" placeholder="Enter Email ID's of people who are required to attend the meeting" class="form-control" />
			</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="event-name" style="width: 290px;">Optional People: </label>
			<div class="col-sm-4" style=" width: 393px; padding-left: 48px;">		
				<form:textarea rows="3" cols="50" path="guestOptionalEmail" placeholder="Enter Email ID's of people who are optional for the meeting" class="form-control" />
			</div>
	</div>
		<button type="button" id="submit" class="btn btn-success">Done!</button> 
	</form:form> 
</div>
</body>
</html>