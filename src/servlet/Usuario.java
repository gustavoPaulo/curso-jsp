package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) { //Deletar Usuário

				daoUsuario.delete(user);
				request.setAttribute("msg", "Usuário deletado com sucesso!");

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				
			} else if (acao != null && acao.equalsIgnoreCase("editar")) { //Editar Usuário

				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);
				
			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) { //Listar Usuário

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				
			}else if(acao != null && acao.equalsIgnoreCase("download")){
				
				BeanCursoJsp usuario = daoUsuario.consultar(user);
				
				if(usuario != null) {
					
					String contentType = "";
					byte[] fileBytes = null;
					
					String tipo = request.getParameter("tipo");
					
					if(tipo.equalsIgnoreCase("imagem")) {
						
						contentType = usuario.getContentType();
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
						
					}else if(tipo.equalsIgnoreCase("curriculo")) {
						
						contentType = usuario.getContentTypeCurriculo();
						fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
					}
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + contentType.split("\\/")[1]);
										
					/*Coloca os bytes em um objeto de entrada 'is' para processar*/
					InputStream is = new ByteArrayInputStream(fileBytes);
					
					/*INICIO -> Resposta para o navegador*/
					int read = 0;
					byte[] bytes = new byte[1024];
					
					OutputStream os = response.getOutputStream();
					
					while((read = is.read(bytes)) != -1) {
						
						os.write(bytes, 0, read);
					}
					
					os.flush();
					os.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");
			String ibge = request.getParameter("ibge");
			String sexo = request.getParameter("sexo");
			String perfil = request.getParameter("perfil");

			
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setUf(uf);
			usuario.setIbge(ibge);
			usuario.setSexo(sexo);
			usuario.setPerfil(perfil);
			
			if(request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) {
				
				usuario.setAtivo(true);
			}else {
				usuario.setAtivo(false);
			}
			
			
			try {
				
				//INÍCIO -> File Update de imagens e PDF
				
				if(ServletFileUpload.isMultipartContent(request)) {
					
					Part imagemFoto = request.getPart("foto");
					
					//Processa Foto
					if(imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
						
						String fotoBase64 = new Base64().encodeBase64String(convertStreamParaByte(imagemFoto.getInputStream()));
					
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());
						
						
						//Inicio Miniatura Imagem
						
							// --> Transformar em um bufferedImage
							byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
							
						    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						    
							// --> Pega o tipo da imagem
							int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB: bufferedImage.getType();
							
							// --> Cria imagem em miniatura
							BufferedImage resizedImage = new BufferedImage(100, 100, type);
							Graphics2D g = resizedImage.createGraphics();
							g.drawImage(bufferedImage, 0, 0, 100, 100, null);
							g.dispose();
							
							// --> Escrever imagem novamente
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							ImageIO.write(resizedImage, "png", baos);
							
							String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
							
							usuario.setFotoBase64Miniatura(miniaturaBase64);
							
						
						//Fim Miniatura Imagem
						
					}else {
						
						usuario.setAtualizarImage(false);
					}	
					
					
					//Processa PDF
					Part curriculoPdf = request.getPart("curriculo");
					
					if(curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
						
						String curriculoBase64 = new Base64().encodeBase64String(convertStreamParaByte(curriculoPdf.getInputStream()));
						
						usuario.setCurriculoBase64(curriculoBase64);
						usuario.setContentTypeCurriculo(curriculoPdf.getContentType());
					}else {
						
						usuario.setAtualizarPdf(false);
					}
				}	
				
				//FIM -> File Update de imagens e PDF
				
				
				String msg = null;
				boolean podeInserir = true;
				
				if(login == null || login.isEmpty()) {
					
					msg = "Login deve ser informado";
					request.setAttribute("user", usuario);
				}
				else if(senha == null || senha.isEmpty()) {
					
					msg = "Senha deve ser informado";
					request.setAttribute("msg", usuario);
				}
				else if(nome == null || nome.isEmpty()) {
					
					msg = "Nome deve ser informado";
					request.setAttribute("user", usuario);
				}
				else if(email == null || email.isEmpty()) {
					
					msg = "E-mail deve ser informado";
					request.setAttribute("user", usuario);
				}
				else if(sexo == null || sexo.isEmpty()) {
					
					msg = "Sexo deve ser informado";
					request.setAttribute("user", usuario);
				}


				
				//Validação para não deixar salvar usuario ou senha que já existe no banco de dados
				else if(id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					
					request.setAttribute("msg", "Login já existente!");
					request.setAttribute("user", usuario);
					
				}else if(id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					
					request.setAttribute("msg", "Senha já atribuida a outro usuário!");
					request.setAttribute("user", usuario);
				}
				
				if(msg != null) {
					
					request.setAttribute("msg", msg);
				}
				
				//Validação para salvar novo usuario
				else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && daoUsuario.validarSenha(senha)) {

					daoUsuario.salvar(usuario);
					request.setAttribute("msg", "Salvo com Sucesso!");
				}
				
				
				if (id != null && !id.isEmpty()) {
					
					//Validação para não permitir atualizar usuario com login ou senha já existente
					if(!daoUsuario.validarLoginUpdate(login, id)) {
						
						request.setAttribute("msg", "Login já existente no sistema.");
						request.setAttribute("user", usuario);
					}
					
					else if (id != null && !id.isEmpty()) {

						if(!daoUsuario.validarSenhaUpdate(senha, id)) {
							
							request.setAttribute("msg", "Senha já atribuida a outro usuário.");
							request.setAttribute("user", usuario);
							
						}else {
							daoUsuario.atualizar(usuario);
							request.setAttribute("msg", "Atualizado com Sucesso!");
						}
					}
					
				}				
							
				if(!podeInserir) {
					request.setAttribute("user", usuario);
				}
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*Método que converte a entrada de fluxo de dados da Imagem para um Array byte[]*/
	private byte[] convertStreamParaByte(InputStream imagem) throws Exception{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int reads = imagem.read();
		
		while(reads != -1) {
			
			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();
	}
}
