<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Response</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/userResponse">
		<table>
			<tr>
				<td>
					Email ID:
				</td>
				<td>
					<input type="text" name="email" id="email">
				</td>
			</tr>
			<tr>
				<td>Event Name:</td>
				<td><input type="text" name="eventName" value="${probableTimings[0].eventName}" readonly/></td>
			</tr>
			<tr>
				<td>Event Description:</td>
				<td>
					<textarea readonly="readonly">${probableTimings[0].eventDescription}</textarea>
					<%-- <input type="text"  name="eventDescription" value="${probableTimings[0].eventDescription}" readonly/> --%>
					<input type="hidden" name="uuid" value="${uuid}">
				</td>
			</tr>
		</table> <br>
		<table>
			<thead>
				<tr>
					<th>Please select your preferred time for the meeting below</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="count" value="0" scope="page" />
				<c:forEach var="request" items="${probableTimings}">
					<tr>
						<td><input type="checkbox" name="timing" value="${request.startTime},${request.endTime}"><c:out value="${request.startTime} to ${request.endTime}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
			<tr>
				<td>
					<input type="submit" value="Submit">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>