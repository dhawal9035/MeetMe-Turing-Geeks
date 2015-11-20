<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Meeting Time</title>
</head>
<body>
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
							<a href="${pageContext.request.contextPath}/getTime/?eventId=${request.eventId}&uuid=${request.uuid}"><label><c:out value="${request.eventId}"></c:out></label>
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