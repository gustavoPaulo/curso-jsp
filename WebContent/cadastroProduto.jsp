<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Cadastro de Produto</title>

<link rel="shortcut icon" href="resources/img/cadastroUsuario.ico">
<link href="resources/bootstrap/css/cadastro.css" rel="stylesheet" media="screen">
<link href="resources/bootstrap/css/cadastroTabela.css" rel="stylesheet" media="screen">

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
 
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
<script src="resources/bootstrap/js/jquery.mask.Money.min.js" type="text/javascript"></script> 
    
</head>
<body>

	<%-- Botao de voltar --%>
	<form action="acessoliberado.jsp">
		<div>
			<input type="submit" class="btn btn-danger" value="Voltar">
		</div>
	</form>
	<%-- FIM -> Botao de voltar --%>
	
		<h1 align="center">Cadastro de Produto</h1>
				
		<h4 align="center" style="color: orange;">${msg}</h4>
	
	<form action="salvarProduto" method="post" id="formProduto" onsubmit="return validarCamposProduto() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
				
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${produto.id}" class="field-long"></td>
					</tr>
					
					<tr>
						<td>Categoria:</td>
						<td>
							<select id="categorias" name="categoria_id" style="width: 198px">
								<c:forEach items="${categorias}" var="cat">
									<option value="${cat.id}" id="${cat.id}"
										<c:if test="${cat.id == produto.categoria_id}">
											<c:out value="selected=selected" />											
										</c:if>>
										${cat.nome}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" value="${produto.nome}" class="field-long" maxlength="100"></td>
					</tr>

					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade" name="quantidade" value="${produto.quantidade}" class="field-long" maxlength="7" pattern="([0-9]{7}"></td>
					</tr>
					
					<tr>
						<td>Valor R$:</td>
						<td><input type="text" id="valor" name="valor" value="${produto.valorEmText}" class="field-long" maxlength="12" data-thousands="." data-decimal=","></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar">
						<input type="submit" value="Cancelar" onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'"></td>

					</tr>
					
				</table> 
			</li>
		</ul>
	</form>
	
	<h2>Lista de Produtos</h2>
	
	<table class="rwd-table">
	
		<tr>
    		<th >Código</th>
    		<th >Nome</th>
    		<th >Quantidade</th>
    		<th >Valor</th>
  		</tr>
  		
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td><c:out value="${produto.id}"></c:out></td>
				<td><c:out value="${produto.nome}"></c:out></td>
				<td><c:out value="${produto.quantidade}"></c:out></td>
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${produto.valor}"/></td>

				<td><a href="salvarProduto?acao=delete&produto=${produto.id}" onclick="return confirm('Deseja realmente Excluir este produto?');"><img src="resources/img/excluir.png" title="Excluir" width="20px" height="20px"></a></td>
				<td><a href="salvarProduto?acao=editar&produto=${produto.id}"><img src="resources/img/editar.jpg" title="Alterar" width="20px" height="20px"></a></td>
			</tr>
		</c:forEach>
	</table>


<script type="text/javascript">
	function validarCamposProduto() {
		if(document.getElementById("nome").value == ''){
			alert('Informe o Nome do Produto!');
			return false;	
		}
		else if(document.getElementById("quantidade").value == ''){
			alert('Informe a Quantidade!');
			return false;	
		}
		else if(document.getElementById("valor").value == ''){
			alert('Informe o Valor R$ !');
			return false;	
		}
				
		return true;
	}
	
	
	
</script>


</body>

<script>
$(function() {
    $('#valor').maskMoney();
  })
  
  $(document).ready(function() {
		  $("#quantidade").keyup(function() {
		      $("#quantidade").val(this.value.match(/[0-9]*/));
		  });
		});
</script>


</html>