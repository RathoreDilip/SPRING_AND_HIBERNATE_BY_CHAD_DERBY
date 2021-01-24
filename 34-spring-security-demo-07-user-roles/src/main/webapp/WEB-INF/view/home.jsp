<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Luv2code Company Home Page</title>
</head>
<body>
	<h2>Luv2code Company Home Page</h2>
	<hr>
	<p>Welcome to the luv2code company home page!</p>

	<hr>
	<!-- display user name and role -->
	<p>
		User :
		<security:authentication property="principal.username" />
		<br> <br> Role(s) :
		<security:authentication property="principal.authorities" />
	</p>
	
	<security:authorize access="hasRole('MANAGER')">
		
		<!-- Add a link to point to /leaders ... this is for the managers -->
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership
				Meeting</a> (Only for Manager Peeps)
		</p>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		
		<!-- Add a link to point to /systems ... this is for the admins -->
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT System
				Meeting</a> (Only for Admin Peeps)
		</p>
	</security:authorize>
	
	<hr>

	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="post">

		<input type="submit" value="Logout" />

	</form:form>

</body>
</html>