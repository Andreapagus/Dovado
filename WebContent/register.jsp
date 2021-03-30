<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Register Form Page</h1>
  <form action=${pageContext.request.contextPath}/register method="POST">    
        <label><b>User Name     
        </b>    
        </label>    
        <input type="text" name="Uname"  placeholder="Username">    
        <p style="color:red;">${exist}</p>
          
		<br><br>
        <label><b>Password     
        </b>    
        </label>    
        <input type="Password" name="Password"  placeholder="Password">    
        <br><br>    
        
        <label><b>Conferma Password   
        </b>    
        </label>    
        
        <input type="Password" name="Password2"  placeholder="Password">    
        <br><br>
        
        <h2>IMPLEMENTARE CONFRONTO!!!</h2>
        
        <input type="submit" name="logButton"  value="Log In Here">       
        <br><br>    
        
    </form>     
</body>
</html>