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
<title>Time Slots</title>
<script type="text/javascript">
$(document).ready(function(){
	alert('We have sent an email to each participant with these timings!');	
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
			<li><a href="${pageContext.request.contextPath}/calendar">Manual Schedule</a>
			<li class="active"><a href="${pageContext.request.contextPath}/meetingTime">Get Meeting Time</a>
			<li><a href="${pageContext.request.contextPath}/googleEvent">Event For MeetMe Users</a>
		</ul>
	</div>
<div class="container">
	<table class="table table-striped" style="width: 750px;" align="left">
			<thead>
				<tr>
					<th>Rank</th>
					<th>Timings(Note that Rank 1 is the preferred time/slot for the meeting.)</th>
					
				</tr>
			</thead>
			<tbody>
				<c:set var="count" value="0" scope="page" />
				<c:forEach var="request" items="${reverseOutput}" varStatus="theCount">
					<tr>
						<td>
 							<h4 class="text-primary">${theCount.count}</h4>
						</td>
						<td>
							<h4 class="text-primary">${request}</h4>
						</td>
							
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>