<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter New Password:</title>
</head>
<body>
<h2>Enter New Password </h2>
	<form:form role="form" method="POST" action="/meetme/updatePassword" commandName="userForm">
		<table>
			<tr>
				<td>Please enter your email ID to confirm</td>
				<td><form:input type="email" class="form-control" path="email" placeholder="Enter Email" /> </td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:password class="form-control" path="password" placeholder="Choose password" /> </td>
			</tr>
			<%-- <tr>
				<td>Retype-Password</td>
				<td><form:password class="form-control" path="password"
									placeholder="Choose password" /> </td>
			</tr> --%>
			<tr>
				<td colspan="2"><center><input type="Submit" value="Submit"> </center> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>