<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MeetMe Login Page</title>
	<style type="text/css">
		.errorblock {
			color: #ff0000;
			background-color: #ffEEEE;
			border: 3px solid #ff0000;
			padding: 8px;
			margin: 16px;
		}
	</style>
</head>

<body onload="document.f.j_username.focus();">
	<h3>Login with Username and Password</h3>
	<c:if test="${not empty error}">
		<div class="errorblock">Your login attempt was unsuccessful,
			try again. Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
	</c:if>

	<form name="f" action="<c:url value="j_spring_security_check" />"
		method="POST">
		<table>
			<tr>
				<td>Email ID:</td>
				<td><input type="text" name="j_username" value=""></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="j_password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input name="submit" type="submit"
					value="Submit" /></td>
			</tr>
			<tr>
				<td colspan="2"><input name="reset" type="reset" /></td>
			</tr>
		</table>
	</form>
</body>
</html>