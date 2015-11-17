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
	
	<style>
		.fc {
			direction: ltr;
			text-align: left;
		}
	
		.fc table {
			border-collapse: collapse;
			border-spacing: 0;
			}
	
		html .fc,
		.fc table {
			font-size: 1em;
			}
	
		.fc td,
		.fc th {
			padding: 0;
			vertical-align: top;
			}

		.fc-header td {
			white-space: nowrap;
			}

		.fc-header-left {
			width: 25%;
			text-align: left;
			}
	
		.fc-header-center {
			text-align: center;
			}
			
		.fc-header-right {
			width: 25%;
			text-align: right;
			}
			
		.fc-header-title {
			display: inline-block;
			vertical-align: top;
			}
			
		.fc-header-title h2 {
			margin-top: 0;
			white-space: nowrap;
			}
			
		.fc .fc-header-space {
			padding-left: 10px;
			}
			
		.fc-header .fc-button {
			margin-bottom: 1em;
			vertical-align: top;
			}
			
		
		.fc-header .fc-button {
			margin-right: -1px;
			}
			
		.fc-header .fc-corner-right,  /* non-theme */
		.fc-header .ui-corner-right { /* theme */
			margin-right: 0; /* back to normal */
			}
			
					
		.fc-header .fc-state-hover,
		.fc-header .ui-state-hover {
			z-index: 2;
			}
			
		.fc-header .fc-state-down {
			z-index: 3;
			}

		.fc-header .fc-state-active,
		.fc-header .ui-state-active {
			z-index: 4;
			}


	
		.fc-content {
			clear: both;
			zoom: 1; /* for IE7, gives accurate coordinates for [un]freezeContentHeight */
			}
			
		.fc-view {
			width: 100%;
			overflow: hidden;
			}

		.fc-widget-header,    
		.fc-widget-content {  
			border: 1px solid #ddd;
			}
			
		.fc-state-highlight {  
			background: #fcfcfc;
			}
	
		.fc-cell-overlay { 
			background: #bcccbc;
			opacity: .3;
			filter: alpha(opacity=30); 
			}


		.fc-button {
			position: relative;
			display: inline-block;
			padding: 0 .6em;
			overflow: hidden;
			height: 1.9em;
			line-height: 1.9em;
			white-space: nowrap;
			cursor: pointer;
		}


		.fc-text-arrow {
			margin: 0 .1em;
			font-size: 2em;
			font-family: "Courier New", Courier, monospace;
			vertical-align: baseline; /* for IE7 */
			}


			
		/*
		  button states
		  borrowed from twitter bootstrap (http://twitter.github.com/bootstrap/)
		*/

		.fc-state-default {
			background-color: #f5f5f5;
			}

		.fc-state-hover,
		.fc-state-down,
		.fc-state-active,
		.fc-state-disabled {
			color: #333333;
			background-color: #e6e6e6;
			}

		.fc-state-hover {
			color: #333333;
			text-decoration: none;
			background-position: 0 -15px;
			-webkit-transition: background-position 0.1s linear;
			   -moz-transition: background-position 0.1s linear;
				 -o-transition: background-position 0.1s linear;
					transition: background-position 0.1s linear;
			}

		.fc-state-down,
		.fc-state-active {
			background-color: #cccccc;
			background-image: none;
			outline: 0;
			box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
			}

		.fc-state-disabled {
			cursor: default;
			background-image: none;
			opacity: 0.55;
			filter: alpha(opacity=65);
			box-shadow: none;
			}


		.fc-event-container > * {
			z-index: 8;
			}

		.fc-event-container > .ui-draggable-dragging,
		.fc-event-container > .ui-resizable-resizing {
			z-index: 9;
			}
			 
		.fc-event {
			border: 1px solid #3a87ad; /* default BORDER color */
			background-color: #3a87ad; /* default BACKGROUND color */
			color: #fff;               /* default TEXT color */
			font-size: .85em;
			cursor: default;
			}

		a.fc-event {
			text-decoration: none;
			}
			
		a.fc-event,
		.fc-event-draggable {
			cursor: pointer;
			}
			
		.fc-rtl .fc-event {
			text-align: right;
			}

		.fc-event-inner {
			width: 100%;
			height: 100%;
			overflow: hidden;
			}
			
		.fc-event-time,
		.fc-event-title {
			padding: 0 1px;
			}
			
		.fc .ui-resizable-handle {
			display: block;
			position: absolute;
			z-index: 99999;
			overflow: hidden; /* hacky spaces (IE6/7) */
			font-size: 300%;  /* */
			line-height: 50%; /* */
			}
	
	

		.fc-event-hori {
			border-width: 1px 0;
			margin-bottom: 1px;
			}

		.fc-ltr .fc-event-hori.fc-event-start,
		.fc-rtl .fc-event-hori.fc-event-end {
			border-left-width: 1px;
			}

		.fc-ltr .fc-event-hori.fc-event-end,
		.fc-rtl .fc-event-hori.fc-event-start {
			border-right-width: 1px;
			}
	

		.fc-event-hori .ui-resizable-e {
			top: 0           !important; 
			right: -3px      !important;
			width: 7px       !important;
			height: 100%     !important;
			cursor: e-resize;
			}
			
		.fc-event-hori .ui-resizable-w {
			top: 0           !important;
			left: -3px       !important;
			width: 7px       !important;
			height: 100%     !important;
			cursor: w-resize;
			}
	
		.fc-event-hori .ui-resizable-handle {
			_padding-bottom: 14px; /* IE6 had 0 height */
			}
			

		table.fc-border-separate {
			border-collapse: separate;
			}
			
		.fc-border-separate th,
		.fc-border-separate td {
			border-width: 1px 0 0 1px;
			}
			
		.fc-border-separate th.fc-last,
		.fc-border-separate td.fc-last {
			border-right-width: 1px;
			}
			
		.fc-border-separate tr.fc-last th,
		.fc-border-separate tr.fc-last td {
			border-bottom-width: 1px;
			}
			
		.fc-border-separate tbody tr.fc-first td,
		.fc-border-separate tbody tr.fc-first th {
			border-top-width: 0;
			}


		.fc-grid th {
			text-align: center;
			}

		.fc .fc-week-number {
			width: 22px;
			text-align: center;
			}

		.fc .fc-week-number div {
			padding: 0 2px;
			}
			
		.fc-grid .fc-day-number {
			float: right;
			padding: 0 2px;
			}
			
		.fc-grid .fc-other-month .fc-day-number {
			opacity: 0.3;
			filter: alpha(opacity=30); 
			}
	
		.fc-grid .fc-day-content {
			clear: both;
			padding: 2px 2px 1px; /* distance between events and day edges */
			}
			
					
		.fc-grid .fc-event-time {
			font-weight: bold;
			}
			
					
		.fc-rtl .fc-grid .fc-day-number {
			float: left;
			}
			
		.fc-rtl .fc-grid .fc-event-time {
			float: right;
			}
		
		.fc-agenda table {
			border-collapse: separate;
			}
			
		.fc-agenda-days th {
			text-align: center;
			}
			
		.fc-agenda .fc-agenda-axis {
			width: 50px;
			padding: 0 4px;
			vertical-align: middle;
			text-align: right;
			white-space: nowrap;
			font-weight: normal;
			}

		.fc-agenda .fc-week-number {
			font-weight: bold;
			}
			
		.fc-agenda .fc-day-content {
			padding: 2px 2px 1px;
			}
			
					
		.fc-agenda-days .fc-agenda-axis {
			border-right-width: 1px;
			}
			
		.fc-agenda-days .fc-col0 {
			border-left-width: 0;
			}
			
					
		.fc-agenda-allday th {
			border-width: 0 1px;
			}
			
		.fc-agenda-allday .fc-day-content {
			min-height: 34px; /* TODO: doesnt work well in quirksmode */
			_height: 34px;
			}
			
					
		.fc-agenda-divider-inner {
			height: 2px;
			overflow: hidden;
			}
			
		.fc-widget-header .fc-agenda-divider-inner {
			background: #eee;
			}
			
					
		.fc-agenda-slots th {
			border-width: 1px 1px 0;
			}
			
		.fc-agenda-slots td {
			border-width: 1px 0 0;
			background: none;
			}
			
		.fc-agenda-slots td div {
			height: 20px;
			}
			
		.fc-agenda-slots tr.fc-slot0 th,
		.fc-agenda-slots tr.fc-slot0 td {
			border-top-width: 0;
			}

		.fc-agenda-slots tr.fc-minor th,
		.fc-agenda-slots tr.fc-minor td {
			border-top-style: dotted;
			}
			
		.fc-agenda-slots tr.fc-minor th.ui-widget-header {
			*border-top-style: solid; /* doesn't work with background in IE6/7 */
			}
			

		.fc-event-vert {
			border-width: 0 1px;
			}

		.fc-event-vert.fc-event-start {
			border-top-width: 1px;
			}

		.fc-event-vert.fc-event-end {
			border-bottom-width: 1px;
			}
			
		.fc-event-vert .fc-event-time {
			white-space: nowrap;
			font-size: 10px;
			}

		.fc-event-vert .fc-event-inner {
			position: relative;
			z-index: 2;
			}
			
		.fc-event-vert .fc-event-bg { 
			position: absolute;
			z-index: 1;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			background: #fff;
			opacity: .25;
			filter: alpha(opacity=25);
			}
			
		.fc .ui-draggable-dragging .fc-event-bg, 
		.fc-select-helper .fc-event-bg {
			display: none\9; 
			}
			
		
		.fc-event-vert .ui-resizable-s {
			bottom: 0        !important; 
			width: 100%      !important;
			height: 8px      !important;
			overflow: hidden !important;
			line-height: 8px !important;
			font-size: 11px  !important;
			font-family: monospace;
			text-align: center;
			cursor: s-resize;
			}
			
	

	</style>
	<script type="text/javascript">
	$(document).ready(function(){
		  var date = new Date();
		  var d = date.getDate();
		  var m = date.getMonth();
		  var y = date.getFullYear();
		  
		  $('#calendar').fullCalendar({
			header: {
			  left: 'prev,next today',
			  center: 'title',
			  right: 'month,agendaWeek,agendaDay'
			},
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				$('#eventModal').modal('show'); // popping my modal to create event
				$("#modal-body").load('calendar.jsp'); //load up modal-body content

			    $("#submit").bind("click", function(event){ // when you click in a create button inside dialog you should send as parameters start,end,etc
			    	$event_name = $("#event_name").val();
			    	$event_description = $("#event_description").val();
			    	var moment1 = $('#calendar').fullCalendar('getDate');
			        alert("The current date of the calendar is " + moment.format());
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
			events: [
			  {
				title: '',
				start: new Date(y, m, 1)
			  },
			  {
				title: '',
				start: new Date(y, m, d-5),
				end: new Date(y, m, d-2)
			  },
			  {
				id: 999,
				title: '',
				start: new Date(y, m, d-3, 16, 0),
				allDay: false
			  },
			  {
				id: 999,
				title: '',
				start: new Date(y, m, d+4, 16, 0),
				allDay: false
			  },
			  {
				title: '',
				start: new Date(y, m, d, 10, 30),
				allDay: false
			  },
			  {
				title: '',
				start: new Date(y, m, d, 12, 0),
				end: new Date(y, m, d, 14, 0),
				allDay: false
			  },
			  {
				title: '',
				start: new Date(y, m, d+1, 19, 0),
				end: new Date(y, m, d+1, 22, 30),
				allDay: false
			  },
			  {
				title: '',
				start: new Date(y, m, 28),
				end: new Date(y, m, 29),
				url: 'http://google.com/'
			  }
			]
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

	<div class="col-md-2">
		<ul class="nav nav-pills nav-stacked">
			<li class="active"><a href="#">Dashboard</a>
			<li><a href="#">Schedule</a>
			<li><a href="#">My Calender</a>
			<li><a href="#">Import Calenders</a>
			<li><a href="#">Import Contacts</a>
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
	<div class="container">
		<hr>
		<div id="calendar"></div>
	</div>

</body>

</html>
