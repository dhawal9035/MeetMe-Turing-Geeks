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
				$("#modal-body").load('mycalendarevent.jsp'); //load up modal-body content

			    
			},
			editable: true,
			
		  });
		  
		  $('#calendar').fullCalendar({
			    events: '/myfeed.php'
			}); 

		  
	});
    </script>

</head>

<body>


	<div class="container" style="padding-left: 120px;padding-top: 0px;">
		<hr>
		<div id="calendar"></div>
	</div>

</body>

</html>
