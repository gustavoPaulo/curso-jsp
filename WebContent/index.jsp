<%@page import="beans.BeanCursoJsp"%>
<%@page import="dao.DaoLogin"%>
<%@page import="dao.DaoUsuario"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Java Web</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="shortcut icon" href="resources/img/inicio.ico">
<link href="resources/bootstrap/css/estilo.css" rel="stylesheet" media="screen">

</head>
<body>
	<div class="login-page">
		<div class="form">
			<form action="loginServlet" method="post" class="login-form" onsubmit="return validarLogin() ? true : false;">
				<h2>Inicie sua sessão</h2>
				
				<input type="text" id="login" name="login" placeholder="Login">
				<input type="password" id="senha" name="senha" placeholder="Senha">

				<button type="submit" class="btn btn-primary" value="Entrar">Entrar</button>
								
				<h4 align="right"><a href="novoUsuario.jsp">Cadastrar-se</a></h4>
				<h4 align="right"><a href="esqueceuSenha.jsp">Esqueceu a Senha?</a></h4>
				
			</form>
		</div>
	</div>
	
	
	
<script type="text/javascript">
	function validarLogin() {
		if(document.getElementById("login").value == ''){
			alert('Informe o Login!');
			return false;	
		}
		else if(document.getElementById("senha").value == ''){
			alert('Informe a Senha!');
			return false;	
		}
		
		return true;
	}
	
</script>

</body>

<script type="text/javascript">

$(document).ready(function() {

		$.get("loginServlet", function(response) {
			
			alert(reponse);
		});
		
});
</script>
			
</html>