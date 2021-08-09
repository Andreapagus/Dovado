<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html>


<%	if(session.isNew()) {
		
		response.sendRedirect("login.jsp");
	} else {
		session.setMaxInactiveInterval(10);	
	}%>


    <%@ page import = "java.io.*,java.util.*" %>

    <% application.setAttribute( "titolo" , "HomeLogin"); %>

	<jsp:include page="Navbar.jsp" />

<h1>HOME ${sessionScope.user.getUsername()}</h1>

</body>
</html>