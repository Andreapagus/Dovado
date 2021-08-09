<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%	if(session.isNew()) {
		
		response.sendRedirect("login.jsp");
	} else {
		session.setMaxInactiveInterval(10);	
	}%>
	
    <%@ page import = "java.io.*,java.util.*" %>

    <% application.setAttribute( "titolo" , "Create Activity"); %>

	<jsp:include page="Navbar.jsp" />

	<!-- non serve mettere un body perché viene incluso dentro navbar -->
	<h1> pagina per creare attività</h1>

  <h2>Giova se leggi qua, qua dentro ci va la pagina create activity, dal commento fino a body </h2>
</body>
</html>
