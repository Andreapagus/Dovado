<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<jsp:useBean id ="regBean" scope="request" class="logic.model.RegBean" />
<jsp:setProperty name="regBean" property="*" />

<!DOCTYPE html>

	<%	if(!session.isNew()) {
		
		response.sendRedirect("homeLogin.jsp");
	}%>
	

<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
</head>
<body>
<h1>Register Form Page</h1>

<%
	if(request.getParameter("regForm")!= null){ //controllo la richiesta ricevuta, se all'interno è presente un parametro login vuol dire che arrivo a questa pagina tramite la pressione del bottone login, quindi ne consegue che i dati username e password sono pieni e quindi posso andare avanti
		if(regBean.validate()){ 
%>
			<jsp:forward page="login.jsp"/>
<%
		} else{
%>
		<p style="color:red;"> ${regBean.getError()}</p>
<%		
		};
	}
%>
  <form action="register.jsp" method="POST">    
        <label>
       
        <b>Email 
        </b>    
        </label>    
        
        <input type="email" id="email" name="email"  placeholder="Useremail" required>    
        <p style="color:red;">${exist}</p>
        
        <label>
        <b>User Name     
        </b>    
        </label>    
        <input  type="text" id="username" name="username"  placeholder="Username" pattern=".{4,}" maxlength="15" title="massimo 15 caratteri, minimo 4" required >    
      
        
		<br><br>
        <label><b>Password     
        </b>    
        </label>    
        <input id="password" type="Password" name="password"  placeholder="Password" pattern=".{8,}" maxlength="20" title="La password deve contenere almeno 8 cratteri e deve contenere numeri, lettere e un carattere tra:{',','.','&','!','?'}" required>    
        <br><br>    
        
        <label><b>Conferma Password   
        </b>    
        </label>    
        
        <input id="password2" type="Password" name="password2"  placeholder="Password" pattern=".{8,}" maxlength="20" onkeyup="compare()" required >    
        <br><br>
        
        <h2 id="pswText" style="display:none">Passowrd DIVERSE</h2>
       
       
		
	        <p style="color:red;">${wrongLogin}</p>
		
        
        <input type="submit" name="regForm"  value="Register Here">       
        <br><br>    
        
    </form>     
</body>
</html>