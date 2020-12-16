<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Java Web</title>

<link rel="shortcut icon" href="resources/img/inicio.ico">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</head>
<body>

	<h1 style="text-align:center;">Olá!</h1>
	<br/>
	<br/>
	
	<center>
		<a href="salvarUsuario?acao=listartodos"><img src="resources/img/imgCadastroUsuario.png" title="Cadastrar Usuário" width="150px" height="150px"></a>
		<a href="salvarProduto?acao=listartodos"><img src="resources/img/imgCadastroProduto.png" title="Cadastrar Produto" width="150px" height="150px"></a>	
	</center>

	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<div align="center">
		<input type="submit" class="btn btn-danger" value="Sair" onclick="fecha()" title="Sair do Sistema">
	</div>
		

<script type="text/javascript">
	function fecha(){
		
		var resposta = confirm("Deseja mesmo sair do Sistema?");

		if(resposta){
			
			window.location.href = "index.jsp";
			
		}else{
			
		}
}

</script>



<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	
</body>
</html>