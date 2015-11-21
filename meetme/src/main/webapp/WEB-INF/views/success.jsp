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
	<meta name="viewport" content="width=device-width,default-scale=1">
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
		  var date = new Date();
		  var d = date.getDate();
		  var m = date.getMonth();
		  var y = date.getFullYear();
		  alert("am here");
		  
		  $('#calendar').fullCalendar({
			header: {
			  left: 'prev,next today',
			  center: 'title',
			  right: 'month,agendaWeek,agendaDay'
			},
	
			 events: '${pageContext.request.contextPath}/calendarFetch',
			 
			 
			 
			 
			 
			 
			 
			 
			/* eventSources: [

			               // your event source
			               {
			                   url: '${pageContext.request.contextPath}/calendarFetch', // use the `url` property
			                   color: 'yellow',    // an option!
			                   textColor: 'black'  // an option!
			               }

			               // any other sources...

			           ], */
			
			
/* 			events: function(start, end, timezone, callback) {
		        $.ajax({
		      url: '${pageContext.request.contextPath}/calendarFetch',
		            dataType: "json",

		            data: {
		                start: start.unix(),
		                end: end.unix()
		            },
		            success: function(doc) {
		            	alert("data111");
		                var events = [];
		                $(doc).find('event').each(function() {
		                	alert("data1112222");
		                    events.push({
		                    	
		                        title: $(this).attr('title'),
		                        start: $(this).attr('start') // will be parsed
		                    });
		                });
		                callback(events);
		            }
		        });
		    } */
			
		    

		    
		    
		    
		    
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				$('#eventModal').modal('show'); // popping my modal to create event
				$("#modal-body").load('calendar.jsp'); //load up modal-body content

			    $("#submit").bind("click", function(event){ // when you click in a create button inside dialog you should send as parameters start,end,etc
			    	$event_name = $("#event_name").val();
			    	$event_description = $("#event_description").val();
			    	var moment1 = $('#calendar').fullCalendar('getDate');
			        alert("The current date of the calendar is " + moment1.format());
			    	/* $start_date = $("#startDate0").val();
			    	$start_time = $("#startTime0").val();
			    	$end_date = $("#endDate0").val();
			    	$end_time = $("#endTime0").val();
			    	$mailing_list = $("#mailing_list").val(); */

			            if ($event_name) {

			            /* 	$('#calendar').fullCalendar('renderEvent',
			                {
			                    //id: $id, 
			                    title: $event_name,
			                    /* start: $start_time,
			                    end: $end_time,
			                    allDay: allDay
			                    /* url: "${pageContext.request.contextPath}/dashboard_landing" //look at this code with extra care 
			                },true); */
			                
			                var myCalendar = $('#calendar'); 
			                myCalendar.fullCalendar();
			                var myEvent = {
			                  title:$event_name,
			                  allDay: true,
			                  start: moment1.format(),
			                  end: moment1.format()
			                };
			                myCalendar.fullCalendar( 'renderEvent', myEvent, true );


			            }else{
			            	$('#calendar').fullCalendar('unselect');
			            }
			        $('#eventModal').modal('toggle');// close my dialog

			    });
			},
			editable: true,

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
				<li class="active"><a href="#">Home</a>
				<li><a href="#">Contact Us</a>
				<li><a href="#">Settings</a>
				<li><a href="#">Profile</a>
				<li><button type="button" class="btn btn-success pull-right" aria-label="profile">
					<span class="glyphicon  glyphicon-user" style="vertical-align:middle, horizontal-align:right" aria-hidden="true">
					</span>
				</button>
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
			<li class="active"><a href="#">Schedule</a>
			<li><a href="${pageContext.request.contextPath}/calendar">Manual Schedule</a>
			<li><a href="#">My Calendar</a>
			<li><a href="#">Import Calendars</a>
			<li><a href="${pageContext.request.contextPath}/meetingTime">Get Meeting Time</a>
		</ul>
	</div>

	<div id="eventModal" class="modal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4>Create your event</h4>
				</div>
				
				<div class="modal-body">
					<form:form id="calendarSubmit" role="form" method="POST" action="/meetme/calendarEvent" commandName="calendarInfo" class="form-horizontal">
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
