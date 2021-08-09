<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id ="logBean" scope="request" class="logic.model.LogBean" />
<jsp:setProperty name="logBean" property="*" />
<!DOCTYPE html>

	<%	if(session.isNew()) {
		
		response.sendRedirect("homeLogin.jsp");
	}	
	%>
	
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>

	
<body>
<%
	if(request.getParameter("logForm")!= null){ //controllo la richiesta ricevuta, se all'interno è presente un parametro login vuol dire che arrivo a questa pagina tramite la pressione del bottone login, quindi ne consegue che i dati username e password sono pieni e quindi posso andare avanti
		if(logBean.validate()){ 
			session.setAttribute("user", logBean.getUser());
			session.setMaxInactiveInterval(10);
			
			response.sendRedirect("homeLogin.jsp");

		} else{
%>
		<p style="color:red;"> ${logBean.getError()}</p>
<%		
		};
	}
%>
  <h1>Login Page</h1>
    <form action="login.jsp" method="POST">    
        <label><b>Email     
        </b>    
        </label>    
        <input type="email" name="email"  placeholder="Email" required>    
        <br><br>    
        <label><b>Password     
        </b>    
        </label>    
        <input type="password" name="password"  placeholder="Password" pattern=".{8,}" maxlength="20" required>    
        <br>    
       
        <input type="submit" name="logForm"  value="Login">       
      
       
      <%--   Implementa Dimenticata  <a href="#">Password</a>   --%>    
    </form>   
    <p>Se ancora non sei registrato clicca <a href="register.jsp"> qui </a></p>
      
</body>
</html>