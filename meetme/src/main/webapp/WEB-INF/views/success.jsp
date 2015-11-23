<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title> Dashboard</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.3/jquery-ui.min.js"></script>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/moment.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/js/fullcalendar.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/fullcalendar.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/fullcalendar.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/fullcalendar.min.css" />
	
	<script type="text/javascript">
	 $(document).ready(function(){ 
		 var doc1 = [{"id":"1","title":"New Event","start":"2015-11-01T00:01:00+05:30","end":"2015-01-14T00:01:00+05:30","allDay":false}]
		function initializeCalendar(doc){
			var dates = doc.devicelist.device
			$('#calendar').fullCalendar({
				header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                selectable: true,
				   events: dates
				});
		}
		var document;
		 $.ajax({
            url: '${pageContext.request.contextPath}/calendarFetch',
            dataType: "json",
            data: {
            },
            success: function(doc) {
            	document = doc;
        		if(doc == null || doc==undefined || doc.devicelist.device.length === 0){
        			 $.ajax({
        		            url: '${pageContext.request.contextPath}/googleFetch',
        		            dataType: "json",
        		            data: {
        		            },
        		            success: function(doc2) {
        		            	document = doc2;
        		            	console.log("Data is"+JSON.stringify(doc2));
        		                initializeCalendar(doc2);
        		            }
        		        }); 
        		} else {
        			console.log("Data is"+JSON.stringify(doc));
                    initializeCalendar(doc);        			
        		}
            }
        });  
		
		  var date = new Date();
		  var d = date.getDate();
		  var m = date.getMonth();
		  var y = date.getFullYear();
		  
		  /* var doc1 = [{"id":"1","title":"New Event","start":"2015-11-01T00:01:00+05:30","end":"2015-01-14T00:01:00+05:30","allDay":false}]
			function initializeCalendar(doc2){
				var dates1 = doc2.devicelist.device
				$('#calendar').fullCalendar({
					   events: dates1
					});
			} */
			
			
			
			 /*  var date = new Date();
			  var d = date.getDate();
			  var m = date.getMonth();
			  var y = date.getFullYear(); */
		 		
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
				<li class="active"><a href="#">Home</a>
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
			<li class="active"><a href="#">My Calendar</a>
			<li><a href="${pageContext.request.contextPath}/calendar">Manual Schedule</a>
			<li><a href="${pageContext.request.contextPath}/meetingTime">Get Meeting Time</a>
			<li><a href="${pageContext.request.contextPath}/googleEvent">Event For MeetMe Users</a>
			<li><a href="${pageContext.request.contextPath}/calendarFetch"> Users</a>
		</ul>
	</div>

	<div id="eventModal" class="modal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4>Create your event</h4>
				</div>
				
				<div class="modal-body">
					<form:form id="calendarSubmit" role="form" method="POST" action="/meetme/calendarEvent" commandName="calendar" class="form-horizontal">
	<div class="form-group">
		<label class="control-label col-sm-2" for="event-name">Event Name:</label>
			<div class="col-sm-10">
				<form:input type="text" path="eventName" id="event_name" placeholder="Enter Event Name" class="form-control" />
			</div>
			</div>
	
	<div class="form-group">
		<label class="control-label col-sm-2"  for="event-name">Event Description:</label>
			<div class="col-sm-10">				
				<form:textarea rows="4" cols="50" id="event_description" path="eventDescription" placeholder="Give Event Description" class="form-control"/>
			</div>
	</div>	
			
				<input type="Button" value="Done!" id="submit" class="btn btn-success col-sm-12"> 
	</form:form> 	
						
					
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close
					</button>
				</div>
			</div>
		</div>		
	</div>
	<div class="container" style="padding-left: 120px;padding-top: 0px;">
		<hr>
		<div id="calendar"></div>
	</div>

</body>

</html>
