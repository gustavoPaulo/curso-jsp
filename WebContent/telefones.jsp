<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Cadastro de Telefone</title>

<link rel="shortcut icon" href="resources/img/cadastroUsuario.ico">
<link href="resources/bootstrap/css/cadastro.css" rel="stylesheet" media="screen">
<link href="resources/bootstrap/css/cadastroTabela.css" rel="stylesheet" media="screen">

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>


</head>
<body>

		<h1 align="center">Cadastro de Telefones</h1>
				
		<h4 align="center" style="color: orange;">${msg}</h4>
	
	
		<form action="salvarTelefones" method="post" id="formFone">
		
			<ul class="form-style-1">
				<li>
				<table align="center">
						
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${userEscolhido.id}"></td>
					</tr>
					
					<tr>
						<td>Usuário:</td>
						<td><input type="text" readonly="readonly" id="nome" name="nome" value="${userEscolhido.login}"></td>
					</tr>
					
					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero" maxlength="15" class="telefone"></td>
					</tr>
					
					<tr>
						<td>Tipo:</td>
						<td><select id="tipo" name="tipo">
							<option>Casa</option>
							<option>Celular</option>
							<option>Recado</option>
							<option>Alternativo</option>
							<option>Outros</option>
						</select></td>
					</tr>
						
				</table> 
				
				<br>
				<div align="center">
					<input type="submit" value="Salvar">
					<input type="submit" class="btn btn-danger" value="Voltar" onclick="document.getElementById('formFone').action = 'salvarTelefones?acao=voltar'">
				</div>
			</li>
		</ul>
	</form>
	
	<h2 align="left">Lista Telefones</h2>
		
	<table class="rwd-table">

		<tr>
    		<th nowrap>Código</th>
    		<th nowrap>Número</th>
    		<th nowrap>Tipo</th>
  		</tr>
  		
		<c:forEach items="${telefones}" var="fone">
			<tr>
				<td><c:out value="${fone.id}"></c:out></td>
				<td><c:out value="${fone.numero}"></c:out></td>
				<td><c:out value="${fone.tipo}"></c:out></td>

				<td><a href="salvarTelefones?acao=deleteFone&foneId=${fone.id}"><img src="resources/img/excluir.png" title="Excluir" width="20px" height="20px"></a></td>
			</tr>
		</c:forEach>
	</table>


<script type="text/javascript">
	function validarCampos() {
		if(document.getElementById("numero").value == ''){
			alert('Informe o Número do telefone!');
			return false;	
		}
		else if(document.getElementById("tpio").value == ''){
			alert('Informe o Tipo!');
			return false;	
		}
		
		return true;
	}
	
	jQuery("input.telefone")
    .mask("(99) 9999-9999?9")
    .focusout(function (event) {  
        var target, phone, element;  
        target = (event.currentTarget) ? event.currentTarget : event.srcElement;  
        phone = target.value.replace(/\D/g, '');
        element = $(target);  
        element.unmask();  
        if(phone.length > 10) {  
            element.mask("(99) 99999-999?9");  
        } else {  
            element.mask("(99) 9999-9999?9");  
        }  
    });
	
</script>
		
</body>
</html>