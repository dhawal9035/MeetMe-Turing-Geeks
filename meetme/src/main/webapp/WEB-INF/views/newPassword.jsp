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
<h2>Enter New Password {email}</h2>
	<form:form role="form" method="post" action="newPassword" commandName="userForm">
		<table>
			<tr>
				<td>Password</td>
				<td><input type="password" id="password"> </td>
			</tr>
			<tr>
				<td>Retype-Password</td>
				<td><input type="password" id="password"> </td>
			</tr>
			<tr>
				<td colspan="2"><center><input type="Submit" value="Save"> </center> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>