<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Meeting Time</title>
<script type="text/javascript">
	
$(document).ready(function(){	
	$('.events').click(function (event){ 
    	event.preventDefault(); 
    	$.ajax({
       	url: $(this).attr('href')
       ,success: function(response) {
    	   if (response !== null && response != undefined && response.trim() == 'no'){
    		   alert('We still have not received a response from each required person. Please check back later')
    	   }
    	   else{
    		   top.location.href = "${pageContext.request.contextPath}/preferredTime";
    	   }
       }
    })
    return false;
	});
});	
	/* function theFunction(){
	if ($(!this).attr('error')) {
	    alert('Exists!');
	}
	} */
	
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
			<li class="active"><a href="${pageContext.request.contextPath}/meetingTime">Get Meeting Time</a>
			<c:if test="${googleUSer}">
				<li><a href="${pageContext.request.contextPath}/googleEvent">Event For MeetMe Users</a>			
			</c:if>
			
		</ul>
	</div>
<div class="container">
	<table class="table table-striped" style="width: 750px;" align="left">
			<thead>
				<tr>
					<th>Event Id</th>
					<th>Event Name</th>
					<th>Event Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="request" items="${allEvents}">
					<tr>
						<td>
							<a class="events" href="${pageContext.request.contextPath}/getTime?eventId=${request.eventId}&uuid=${request.uuid}"><label><c:out value="${request.eventId}"></c:out></label>
								<%-- <input type="hidden" name="time" value="${request.eventId}"> --%>
							</a>
						</td>
						<td>
							<label><c:out value="${request.eventName}"></c:out></label>
							<%-- <input type="hidden" name="${request.eventName}"> --%>
						</td>
						<td>
							<label><c:out value="${request.eventDescription}"></c:out></label>
							<%-- <input type="hidden" name="${request.eventDescription}">
							<input type="hidden" name="${request.uuid}"> --%>
						</td>
							
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>