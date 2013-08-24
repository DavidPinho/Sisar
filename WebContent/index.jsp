<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	
	//HttpSession session = ((HttpServletRequest)(request)).getSession();
    HttpServletResponse res = (HttpServletResponse) response;   
	
	String logado = (String)((HttpServletRequest)(request)).getSession().getAttribute("usuario");
	
	if(logado==null){
		request.setAttribute("msg", "Faça o login no sistema");
		res.sendRedirect(((HttpServletRequest)(request)).getContextPath() + "/login.jsp"); 
	}else{
		if(logado.equals("admin")){
			res.sendRedirect("/faces/avaliacoes.xhtml");
		}
	}
	
	%>

</body>
</html>