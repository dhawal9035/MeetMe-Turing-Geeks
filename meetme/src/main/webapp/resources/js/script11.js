$(document).ready(function() {
	$(function() {

	$("#username_error_message").hide();
	$("#email_error_message").hide();
	$("#password_error_message").hide();
	$("#repassword_error_message").hide();

	var error_username = false;
	var error_email = false;
	var error_password = false;
	var error_repassword = false;
	
	$("#signup_firstname").focusout(function() {
		check_username();
	});
	$("#signup_email").focusout(function(){
		check_emailid();
	});
	$("#signup_password").focusout(function(){
		check_password();
	});
	$("#signup_re_enter_pwd").focusout(function(){
		check_repassword();
	});
	
	function check_username() {
		var username_length = $("#signup_firstname").val().length;
		if(username_length < 2 || username_length > 20) {
			$("#username_error_message").html("First Name cannot be null and should not exceed 20 characters");
			$("#username_error_message").show();
			error_username = true;
		} else {
			$("#username_error_message").hide();
		}
	}
	
	function check_emailid() {
		var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
		if(pattern.test($("#signup_email").val())) {
			$("#email_error_message").hide();
		} else {
			$("#email_error_message").html("Invalid email address. Plese re-enter Email");
			$("#email_error_message").show();
			error_email = true;
		}	
	}
	
	function check_password() {	
		var password_length = $("#signup_password").val().length;
		if(password_length < 6) {
			$("#password_error_message").html("Password should be at least 8 characters");
			$("#password_error_message").show();
			error_password = true;
		} else {
			$("#password_error_message").hide();
		}
	}
	
	function check_repassword() {
		var password = $("#signup_password").val();
		var retype_password = $("#signup_re_enter_pwd").val();
		if(password !=  retype_password) {
			$("#repassword_error_message").html("Passwords don't match!!");
			$("#repassword_error_message").show();
			error_repassword = true;
		} else {
			$("#repassword_error_message").hide();
		}
	}
	
	$("#terms_check").on('click', function() {
		
		error_username = false;
		error_email = false;
		error_password = false;
		error_repassword = false;
											
		check_username();
		check_emailid();
		check_password();
		check_repassword();
		
	    if (!jQuery("#checkbox").is(":checked")) {
	        alert("You should agree to terms and conditions of MeetME! ");
	        return false;
	    } 
	     if(error_username==false && error_email==false && error_password==false && error_repassword==false){
	    	 return true;
	     }
	    else 
	    	{
	    	alert('Please fill all the fields Properly!!')
	    	return false;
	    	}
	});
	
		
});
});

	
	
     
	


	