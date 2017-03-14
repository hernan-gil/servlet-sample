<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Login Form with Email Password Link</title>
	<link rel="stylesheet" media="screen" href="css/login.css">
	<script src="js/jquery.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script>
	$(function() {
		// highlight
		var elements = $("input[type!='submit'], textarea, select");
		elements.focus(function() {
			$(this).parents('li').addClass('highlight');
		});
		elements.blur(function() {
			$(this).parents('li').removeClass('highlight');
		});

		$("#forgotpassword").click(function() {
			$("#password").removeClass("required");
			$("#login").submit();
			$("#password").addClass("required");
			return false;
		});

		$("#login").validate()
	});
	</script>
</head>
<body>
<div id="page">
	<div id="header">
		<h1>Login</h1>
	</div>
	<div id="content">
		<p id="status"></p>
		<form action="LoginController" method="post" id="login">
	<% if (null != request.getAttribute("msj")) { %>
	<div id="error">
		<h1><%= request.getAttribute("msj")%></h1>
	</div>
	<% }
	request.setAttribute("msj", new String("")); 
	%>

		<input type="hidden" value="login" name="action"/>
			<fieldset>
				<legend>User details</legend>
				<ul>
					<li>
						<label for="email">
							<span class="required">Email address</span>
						</label>
						<input id="email" name="email" class="text required email" type="text">
						<label for="email" class="error">This must be a valid email address</label>
					</li>
					<li>
						<label for="password">
							<span class="required">Password</span>
						</label>
						<input name="password" type="password" class="text required" id="password" minlength="4" maxlength="20">
					</li>
					<li>
						<label class="centered info"><a id="forgotpassword" href="#">Email my password...</a>
						</label>
					</li>
				</ul>
			</fieldset>
			<fieldset class="submit">
				<input type="submit" class="button" value="Login...">
			</fieldset>
			<div class="clear"></div>
		</form>
	</div>
</div>
</body>
</html>