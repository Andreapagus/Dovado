<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h1>Login Page</h1>
  <form action=${pageContext.request.contextPath}/login method="POST">    
        <label><b>Username     
        </b>    
        </label>    
        <input type="text" name="Uname"  placeholder="Username">    
        <br><br>    
        <label><b>Password     
        </b>    
        </label>    
        <input type="Password" name="Password"  placeholder="Password">    
        <br><br>    
        <input type="submit" name="logButton"  value="Login ">       
        <br><br>    
        
      <!--   Implementa Dimenticata  <a href="#">Password</a>   -->    
    </form>   
    <p>Se ancora non sei registrato clicca <a href="register.jsp"> qui </a></p>
      
</body>
</html>