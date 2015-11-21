<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset = "utf-8"/>
		<title> Bootstrap the CDN way </title>
		<!-- Latest compiled and minified CSS -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href = "${pageContext.request.contextPath}/resources/stylesheets/style1.css">
		
	</head>
	<body>
		<div class = "container-fluid">
			<div class = "navbar navbar-default">
				<div class=" navbar-header col-sm-4 col-md-6 col-lg-4 col-lg-offset-0" id="header1">
					<a class="navbar-brand" href="#">
						<img src="${pageContext.request.contextPath}/resources/images/earth1.png"/>
					</a>
						
				</div>
				<div class=" navbar-header col-sm-8 col-md-6 col-lg-8" id="header2">
					<ul class="nav navbar-nav">
						<li id = "list1"> <a href="#" > <b>Home</b></a></li>
						<li id = "list2"> <a href="#"> <b>Schedule a Meeting</b></a></li>
						<li id = "list3"> <a href="#"> <b>Calendar</b></a></li>
						<li> 
							<div class="dropdown">
								<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">User
								<span class="caret"></span></button>
								<ul class="dropdown-menu">
									<li><a href="#">About</a></li>
									<li><a href="#">Setting</a></li>
									<li><a href="#">Log out</a></li>
								</ul>
							</div>
						</li>
					</ul>
					
				</div>
				
			</div>
			
			<div class="row">
				<div class="col-lg-12 col-lg-offset-0 col-md-12 col-md-offset-0">
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
						
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						</ol>
						 
						
						<div class="carousel-inner">
							<div class="item active">
								<img src="${pageContext.request.contextPath}/resources/images/meeting2.jpg" alt="...">
								<div class="carousel-caption">
									<h3>Caption Text</h3>
								</div>
							</div>
							<div class="item">
								<img src="${pageContext.request.contextPath}/resources/images/meeting3.jpg" alt="...">
								<div class="carousel-caption">
									<h3>Caption Text</h3>
								</div>
							</div>
							<div class="item">
								<img src="${pageContext.request.contextPath}/resources/images/meeting4.jpg	" alt="...">
								<div class="carousel-caption">
									<h3>Caption Text</h3>
								</div>
							</div>
						</div>
							
					</div>
				</div>
			</div>
		</div>
  
  
				
		
		
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"> </script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
	<script type ="text/javascript">
		$(document).ready(function(){
			
					
			
			$("#list1").mouseover(function(){
			
				$(this).css('text-decoration','underline');
				
				
			});
			
			$("#list1").mouseleave(function(){
				$(this).css('text-decoration','none');
			});
			
			$("#list2").mouseover(function(){
			
				$(this).css('text-decoration','underline');
				
			});
			
			$("#list2").mouseleave(function(){
				$(this).css('text-decoration','none');
			});
			
			
			$("#list3").mouseover(function(){
			
				$(this).css('text-decoration','underline');
				
			});
			
			$("#list3").mouseleave(function(){
				$(this).css('text-decoration','none');
			});
			
		});
	</script>
	
	</body>
</html>