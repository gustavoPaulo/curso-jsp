<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Novo Usuário</title>
<link rel="shortcut icon" href="resources/img/inicio.ico">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</head>

<body>
	<%-- Botao de voltar --%>
	<form action="index.jsp" style="text-align: left;">
		<div>
			<input type="submit" class="btn btn-danger" value="Cancelar">
		</div>
	</form>
	<%-- FIM -> Botao de voltar --%>

	<h1 style="text-align:center;">É novo aqui?</h1>
	<script src="https://code.jquery.com/jquery.js"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
    
    
    <form action="novoUsuario" method="post" id="novoUsuario" onsubmit="return validarCampos() ? true : false;">
    	<br>
    	<br>
    	<table align="center">
					<tr>
						<td>Usuário:</td>
						<td><input type="text" id="login" name="login"></td>
					</tr>
					<tr>	
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"></td>
					</tr>
					<tr>	
						<td>E-mail:</td>
						<td><input type="text" id="email" name="email"></td>
					</tr>
    	</table>
    	
    	<br>
    	<div align="center">
    		<input type="submit" class="btn btn-primary" value="Salvar">
    	</div>
    	
    </form>	
    	
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	
    
<script type="text/javascript">
	function validarCampos() {
		if(document.getElementById("login").value == ''){
			alert('Informe o Login!');
			return false;	
		}
		if(document.getElementById("senha").value == ''){
			alert('Informe a Senha!');
			return false;	
		}
		if(document.getElementById("email").value == ''){
			alert('Informe um E-mail válido!');
			return false;	
		}
		
		alert('Usuário Salvo com Sucesso!');
		return true;
	}
</script>
    
</body>
</html>