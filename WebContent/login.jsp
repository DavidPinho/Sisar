<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logar no sistema</title>
<!-- Bootstrap -->

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>

<body>

	<div class="row"
	style="padding: 8px; font-size: 20px">
	<img src="images/sisar-icon.png" width="10px" height="20px"
		style="margin-left: 20px" />
	</div>

	<div class="well well-small marge_15" align="center" style="color: white;">
		Informe o seu Login e Senha
	</div>

	<div class="well well-small marge_15" align="center">

		<div class="row-fluid">
			<form action="LoginServlet"  method="post">
			    <table border="1" width="30%" align="center" >
					<tr>
						<td style="background-color: white">Login</td>
						<td><input  type="text" name="login">
					</tr>
					<tr>
						<td style="background-color: white">Senha</td>
						<td><input type="password" name="senha">
					</tr>
					<tr>
						<td><input type="button" onclick="location.href='cadastraUsuario.jsp'" value="Cadastrar">
						<td><input type="submit" value="Entrar">
					</tr>
					
				</table>
			</form>
			<h3 style="text-align:center"><% 
				String msg = (String) request.getAttribute("msg");
				if(msg!=null)
					out.print(msg);
			%></h3>
		</div>
	</div>
</body>
</html>
