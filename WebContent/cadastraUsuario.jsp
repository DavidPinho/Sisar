<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuário</title>
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
		Cadastre um Login e Senha
	</div>

	<div class="well well-small marge_15" align="center">

		<div id="formulario" class="row-fluid">
			<form name="form1" method="post" action="CadUsuarioServlet">
			    <table border="1" width="30%" align="center" >
					<tr>
						<td style="background-color: white">Login</td>
						<td><input type="text" name="txt_nome" id="txt_nome">
					</tr>
					<tr>
						<td style="background-color: white">Senha</td>
						<td><input type="password" name="txt_senha" id="txt_senha">
					</tr>
					<tr>
				      <td colspan="2"><label><input type="submit"  style="margin-left:75px; width:145px" name="btn_cadastra" id="btn_cadastra" value="Cadastrar"></label>
				      </td>
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
