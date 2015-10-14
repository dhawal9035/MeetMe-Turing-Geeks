<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="document.f.email.focus()">
	<h2>Forgot Password</h2>
	<form name="f" action="../forgotPassword" method="POST">
		<table>
			<tr>
				<td>Email ID:</td>
				<td><input type="text" name="email" value=""></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" type="submit" value="Submit"/></td>
			</tr>
		</table>
	</form>
</body>
</html>