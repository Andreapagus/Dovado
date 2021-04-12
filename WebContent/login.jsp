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
  <p style="color:red;">${sessionExpired}</p>
  <form action=${pageContext.request.contextPath}/login method="POST">    
        <label><b>Email     
        </b>    
        </label>    
        <input type="email" name="Uemail"  placeholder="Username">    
        <br><br>    
        <label><b>Password     
        </b>    
        </label>    
        <input type="Password" name="Password"  placeholder="Password">    
        <br>    
        <p style="color:red;">${wrongLogin}</p>
        <input type="submit" name="logButton"  value="Login ">       
        <br><br>    
        
      <!--   Implementa Dimenticata  <a href="#">Password</a>   -->    
    </form>   
    <p>Se ancora non sei registrato clicca <a href="register.jsp"> qui </a></p>
      
</body>
</html>