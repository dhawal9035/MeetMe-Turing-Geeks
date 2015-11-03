<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Welcome to MeetMe!</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
	width: 490px;
}

.successblock {
	color: green;
	background-color: #d9edf7;
	border: 3px solid green;
	padding: 8px;
	margin: 16px;
	width: 450px;
}

.row-centered {
	text-align: center;
}

.row-right {
	text-align: right;
}

body {
	background-image: url("http://i.imgur.com/gHE631x.jpg");
	background-color: #cccccc;
}

.col-centered {
	display: inline-block;
	float: none;
	text-align: left;
	margin-right: -4px;
}

.col-right {
	display: inline-block;
	float: none;
	text-align: right;
	margin-left
	=-4px;
}

.vertical-center-container {
	min-height: 100%;
	min-height: 100vh;
	/*display: flex;*/
	align-items: center;
	justify-content: center;
	/*justify-content: center;*/
	flex-direction: column;
}
</style>
<c:if test="${not empty error}">
	<div class="errorblock">Your login attempt was unsuccessful,try
		again. Caused :
		${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
</c:if>
<c:if test="${not empty userNotFound}">
	<div class="errorblock">You are not registered with us. Please
		click on Need Account to continue.</div>
</c:if>
<c:if test="${not empty updated}">
	<div class="successblock">Your password has been updated. Please
		wait for a while befo<!--  -->re logging in.</div>
</c:if>
<c:if test="${not empty checkMail}">
	<div class="successblock">Password reset link has been sent to
		your Email ID. Please check.</div>
</c:if>
<c:if test="${not empty processError}">
	<div class="errorblock">There was an error processing your
		request. Please try again.</div>
</c:if>
</head>

<body onload="document.f.j_username.focus();">
	<div class="vertical-align-container well row row-centered">
		<h2>Login To Schedule Great Meetings!</h2>
		<form role="form" name="f"
			action="<c:url value="j_spring_security_check" />" method="POST">
			<div class="row row-centered">
				<div
					class="form-group has-warning has-feedback has-success col-lg-3 col-centered">
					<input type="email" name="j_username" class="form-control"
						id="email" placeholder="Enter your Email ID">
				</div>
			</div>
			<div class="row row-centered">
				<div
					class="form-group has-warning has-feedback has-success col-lg-3 col-centered">
					<input type="password" name="j_password" class="form-control"
						id="pwd" placeholder="Enter password">
				</div>
			</div>
			<div class="row ">
				<div class="checkbox col-sm-3 col-centered">
					<label><input type="checkbox" checked
						name="_spring_security_remember_me">Remember me</label>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-3 col-right ">
					<button type="submit" class="btn btn-success col-sm-3">Login</button>
				</div>
			</div>

			<!-- <div class="row">
				<div class="col-sm-7 col-right ">
					<a href="../meetme/forgotPassword">Forgot Password?</a>
				</div>
			</div> -->

			<div class="row row-centered">
				<div class="col-lg-13 col-right">
					<button type="button"
						class="btn btn-link btn-info btn-lg col-sm-2 " data-toggle="modal"
						data-target="#ForgotPasswordModal">Forgot Password?</button>
				</div>
			</div>

			<div class="row row-centered">
				<div class="col-lg-2 col-right">
					<button type="button"
						class="btn btn-link btn-info btn-lg col-sm-2 " data-toggle="modal"
						data-target="#SignUpModal">Need Account?</button>
				</div>
			</div>
		</form>
	</div>

	<%-- <form action="/facebook" method="POST"> --%>
		<div class="row row-centered">
			<div class="col-lg-2 col-right">
				<a href="<c:url value="/facebook" />">Login with Facebook</a>
				<a href="<c:url value="/twitter" />">Login with Twitter</a>
				<%-- <a href="<c:url value="/linkedin" />">Login with LinkedIn</a> --%>
				<!-- <input type="image" id="saveForm" src="../images/facebook.png" alt="Submit Form"> -->	
			</div>
		</div>
	<%-- </form> --%>
	<div id="ForgotPasswordModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h2 class="modal-title">Forgot your password? No worries!</h2>
				</div>
				<div class="modal-body text-align:center">
					<form:form role="form" action="resetPassword" method="POST" commandName="userForm">
						<div class="row">
							<div
								class="form-group has-warning has-feedback has-success col-lg-8 col-lg-offset-2 center-block col-centered">
								<form:input type="email" class="form-control" path="email" placeholder="Enter Email" />
							</div>
						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-success col-sm-2"
								value="Submit" />
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>


	<div id="SignUpModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h2 class="modal-title">Sign Up For A Better Way To Schedule
						Meetings</h2>
				</div>
				<div class="modal-body text-align:center">
					<form:form role="form" action="register" method="post"
						commandName="userForm">
						<div class="row">
							<div
								class="form-group has-warning has-feedback has-success col-lg-8 col-lg-offset-2 center-block col-centered">
								<form:input type="Text" class="form-control" path="firstName"
									placeholder="Enter First Name" />
							</div>
						</div>
						<div class="row">
							<div
								class="form-group has-warning has-feedback has-success col-lg-8 col-lg-offset-2 center-block col-centered">
								<form:input type="Text" class="form-control" path="lastName"
									placeholder="Enter Last Name" />
							</div>
						</div>
						<div class="row">
							<div
								class="form-group has-warning has-feedback has-success col-lg-8 col-lg-offset-2 center-block col-centered">
								<form:input type="email" class="form-control" path="email"
									placeholder="Enter Email" />
							</div>
						</div>

						<div class="row">
							<div
								class="form-group has-warning has-feedback has-success col-lg-8 col-lg-offset-2 center-block col-centered">
								<form:password class="form-control" path="password"
									placeholder="Choose password" />
							</div>
						</div>

						<!-- <div class="row">
						<div class="form-group has-warning has-feedback has-success col-lg-8 col-lg-offset-2 center-block col-centered">
							<input type="pwd" class="form-control" id="signup_re_enter_pwd" placeholder="Re-enter Password">
						</div>
					</div> 
		
					<div class="row">
						<div class="checkbox col-sm-3 ">
							<label><input type="checkbox">I Accept The Meet Me Terms and Conditions</label>
						</div>
					</div> -->

						<div class="modal-footer">
							<input type="submit" class="btn btn-success col-sm-2"
								value="Submit" />
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>