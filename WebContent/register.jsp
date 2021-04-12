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
        <label>
       
        <b>Email 
        </b>    
        </label>    
        <input type="email" name="Uemail"  placeholder="Useremail" required>    
        <p style="color:red;">${exist}</p>
          
        <b>User Name     
        </b>    
        </label>    
        <input type="text" name="Uname"  placeholder="Username" required>    
      
        
		<br><br>
        <label><b>Password     
        </b>    
        </label>    
        <input id="Password" type="Password" name="Password"  placeholder="Password" required>    
        <br><br>    
        
        <label><b>Conferma Password   
        </b>    
        </label>    
        
        <input id="Password2" type="Password" name="Password2"  placeholder="Password" onkeyup="compare()" required >    
        <br><br>
        
        <h2 id="pswText" style="display:none">Passowrd DIVERSE</h2>
       
        <script type="text/javascript">
			function compare(){
			var a=document.getElementById("Password");
			var b=document.getElementById("Password2");
			var c=name1.value;
			var d=name2.value;
			if (c!=d){
				document.getElementById("pswText").style.display='block';
			}
			else{
				document.getElementById("pswText").style.display ='none';
			}
		}
		</script>
		
		
        
        <input type="submit" name="logButton"  value="Log In Here">       
        <br><br>    
        
    </form>     
</body>
</html>