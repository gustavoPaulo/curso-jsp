<%@page import="beans.BeanCursoJsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cadastro de Usuário</title>

<link rel="shortcut icon" href="resources/img/cadastroUsuario.ico">
<link href="resources/bootstrap/css/cadastro.css" rel="stylesheet" media="screen">
<link href="resources/bootstrap/css/cadastroTabela.css" rel="stylesheet" media="screen">

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13yQuiTwLAQgxVSNgt4=" crossorigin="anonymous"></script>

</head>
<body>

	<%-- Botao de voltar --%>
	<form action="acessoliberado.jsp">
		<div>
			<input type="submit" class="btn btn-danger" value="Voltar">
		</div>
	</form>
	<%-- FIM -> Botao de voltar --%>
    

		<h1 align="center">Cadastro de Usuário</h1>
				
		<h4 align="center" style="color: orange;">${msg}</h4>

    <form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;" enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<table align="center">
						
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${user.id}"></td>
						
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep" onblur="consultaCep();" value="${user.cep}" placeholder="Informe o CEP" maxlength="20"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login" value="${user.login}" maxlength="50"></td>
						
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua" value="${user.rua}" maxlength="100"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha" value="${user.senha}" maxlength="20"></td>
						
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro" value="${user.bairro}" maxlength="100"></td>
					</tr>
					
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" value="${user.nome}" maxlength="100"></td>
						
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade" value="${user.cidade}" maxlength="50"></td>
					</tr>
					
					<tr>
						<td>E-mail:</td>
						<td><input type="text" id="email" name="email" value="${user.email}" placeholder="Informe um e-mail válido" maxlength="50"></td>
						
						<td>Estado:</td>
						<td><input type="text" id="uf" name="uf" value="${user.uf}" maxlength="20"></td>
					</tr>
					
					<tr>
						<td>Sexo:</td>
						<td>
							<input type="radio" id="sexo" name="sexo" 
							<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									
									if(user.getSexo().equalsIgnoreCase("masculino")){
										out.print("checked=\"checked\"");
										out.print(" ");
									}
								}
							%>
							value="masculino">Masculino</input>
							
							
							<input type="radio" id="sexo" name="sexo" 
							<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									
									if(user.getSexo().equalsIgnoreCase("feminino")){
										out.print("checked=\"checked\"");
										out.print(" ");
									}
								}
							%>
							
							value="feminino">Feminino</input>
						</td>
						
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge" value="${user.ibge}" maxlength="50"></td>
					</tr>	
			
					<tr>
						<td>Foto:</td>
						<td><input type="file" id="foto" name="foto"></td>
						
						<td>Ativo:</td>
						<td><input type="checkbox" id="ativo" name="ativo" 
							<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									
									if(user.isAtivo()){
										out.print("checked=\"checked\"");
										out.print(" ");
									}
								}
							%>
						></td>					
					</tr>
		
					<tr>
						<td>Curriculo:</td>
						<td><input type="file" id="curriculo" name="curriculo"></td>
						

						<td>Perfil:</td>
						<td><select id="perfil" name="perfil" style="width: 186px">
							<option value="nao_informado" >Selecione...</option>
							<option value="padrao" 
							<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									
									if(user.getPerfil().equalsIgnoreCase("padrao")){
										out.print("selected=\"selected\"");
										out.print(" ");
									}
								}
							%>
							>Padrão</option>
							
							<option value="administrador"
							<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									
									if(user.getPerfil().equalsIgnoreCase("administrador")){
										out.print("selected=\"selected\"");
										out.print(" ");
									}
								}
							%>
							>Administrador</option>
							
							<option value="visitante" 
							<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									
									if(user.getPerfil().equalsIgnoreCase("visitante")){
										out.print("selected=\"selected\"");
										out.print(" ");
									}
								}
							%>
							>Visitante</option>
						</select></td>

					</tr>
					
				</table> 
				
				<br>
				<div align="center">
					<input type="submit" value="Salvar">
					<input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">
				</div>
				
			</li>
		</ul>
	</form>
	
	
	
<!-- INICIO -> Filtro Usuário -->	
	<form method="post" action="servletPesquisa">
		<ul class="form-style-1">
			<li>
				<table >
					<tr>
						<td>Descrição:</td>
						<td><input type="text" id="descricaoconsulta" name="descricaoconsulta"></td>
						<td><input type="submit" value="Pesquisar"></td>
					</tr>			
				</table>
			</li>
		</ul>
	</form>
<!-- FIM -> Filtro Usuário -->	
	
	
	
	<h2 align="left">Lista de Usuários</h2>
		
	<table class="rwd-table">

		<tr>
    		<th nowrap>Código</th>
    		<!-- <th nowrap>Login</th> -->
    		<th nowrap>Foto</th>
    		<th nowrap>Curriculo</th>
    		<th nowrap>Nome</th>
    		<th nowrap>E-mail</th>
    		<!-- <th nowrap>Cep</th> -->
    		<th nowrap>Endereço</th>
    		<!-- <th nowrap>Bairro</th> -->
    		<!-- <th nowrap>Cidade</th> -->
    		<!-- <th nowrap>Estado</th> -->
    		<!-- <th nowrap>IBGE</th>  -->
  		</tr>
  		
		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td><c:out value="${user.id}"></c:out></td>
				<!-- <td><c:out value="${user.login}"></c:out></td> -->
				
				<c:if test="${user.fotoBase64Miniatura.isEmpty() == false}">
					<td><a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"> <img src='<c:out value="${user.fotoBase64Miniatura}"/>' title="Foto" width="32px" height="32px" /></a></td>
				</c:if>
				<c:if test="${user.fotoBase64Miniatura == null}">
					<td><img src="resources/img/imagemUserTabela.png" title="Sem Foto" width="41px" height="36px"></td>
				</c:if>
				
				<c:if test="${user.curriculoBase64.isEmpty() == false}">
					<td><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img src="resources/img/pdf.png"  title="Curriculo" width="35px" height="40px"></a></td>
				</c:if>
				<c:if test="${user.curriculoBase64 == null}">
					<td><img src="resources/img/semPdf.png"  title="Sem Curriculo" width="32px" height="32px"></td>
				</c:if>
				
				<td><c:out value="${user.nome}"></c:out></td>
				<td><c:out value="${user.email}"></c:out></td>
				<!-- <td><c:out value="${user.cep}"></c:out></td> -->
				<td><c:out value="${user.rua}"></c:out></td>
				<!-- <td><c:out value="${user.bairro}"></c:out></td> -->
				<!-- <td><c:out value="${user.cidade}"></c:out></td> -->
				<!-- <td><c:out value="${user.uf}"></c:out></td> -->
				<!-- <td><c:out value="${user.ibge}"></c:out></td> -->

				<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><img src="resources/img/telefones.png" title="Telefone" width="20px" height="20px"></a></td>
				<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img src="resources/img/editar.jpg" title="Editar" width="20px" height="20px"></a></td>
				<td><a href="salvarUsuario?acao=delete&user=${user.id}" onclick="return confirm('Deseja realmente Excluir este usuário?');" ><img src="resources/img/excluir.png" title="Excluir" width="20px" height="20px"></a></td>
			</tr>
		</c:forEach>
	</table>


<script type="text/javascript">
	function validarCampos() {
		if(document.getElementById("login").value == ''){
			alert('Informe o Login!');
			return false;	
		}
		else if(document.getElementById("senha").value == ''){
			alert('Informe a Senha!');
			return false;	
		}
		else if(document.getElementById("nome").value == ''){
			alert('Informe o Nome!');
			return false;	
		}
		else if(document.getElementById("email").value == ''){
			alert('Informe o E-mail!');
			return false;	
		}
		else if(document.getElementById("sexo").value == ''){
			alert('Informe o Sexo!');
			return false;	
		}
		
		return true;
	}
	
	function consultaCep() {
		
		var cep = $("#cep").val();

	
		//Consulta o webservice viacep.com.br/
        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

            if (!("erro" in dados)) {
               
            	//Atualiza os campos com os valores da consulta.
                $("#rua").val(dados.logradouro);
                $("#bairro").val(dados.bairro);
                $("#cidade").val(dados.localidade);
                $("#uf").val(dados.uf);
                $("#ibge").val(dados.ibge);
            	
            } //end if.
            else {
               
            	//Se não encontrar o CEP, limpa os dados.
                $("#cep").val('');
            	$("#rua").val('');
                $("#bairro").val('');
                $("#cidade").val('');
                $("#uf").val('');
                $("#ibge").val('');
                
                alert("CEP não encontrado.");
            }
        });
	}

</script>


</body>
</html>