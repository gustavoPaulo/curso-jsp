package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.BeanProduto;
import dao.DaoProduto;


@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public Produto() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {

			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
			String produto = request.getParameter("produto");

			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			
			
			if (acao.equalsIgnoreCase("delete")) {

				daoProduto.delete(produto);

				request.setAttribute("produtos", daoProduto.listar());
				
			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProduto beanProduto = daoProduto.consultar(produto);
				
				request.setAttribute("produto", beanProduto);
				
			} else if (acao.equalsIgnoreCase("listartodos")) {

				request.setAttribute("produtos", daoProduto.listar());
			}
			
			request.setAttribute("categorias", daoProduto.listaCategoria());
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			String categoria = request.getParameter("categoria_id");

			
			BeanProduto produto = new BeanProduto();
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setNome(nome);
			produto.setCategoria_id(Long.parseLong(categoria));
			
			produto.setQuantidade(quantidade);
			
			if(valor != null && !valor.isEmpty()) {
				
				String valorProduto = valor.replaceAll("\\.",""); //10500,10 -> 10500,10
				valorProduto = valorProduto.replaceAll("\\,", "."); //10500.10
				
				produto.setValor(Double.parseDouble(valorProduto));
			
			}
			
			try {
				String msg = null;
				boolean podeInserir = true;
				
								
				if(nome == null || nome.isEmpty()) {
					
					msg = "Nome do produto deve ser informado";
					request.setAttribute("produto", produto);
				}
				else if(quantidade == null || quantidade.isEmpty()) {
					
					msg = "A quantidade deve ser informada";
					request.setAttribute("produto", produto);
				}
				else if(valor == null || valor.isEmpty()) {
					
					msg = "O valor deve ser informado";
					request.setAttribute("produto", produto);
				}
				
				
				
				//Valida Nome
				else if(id == null || id.isEmpty() && !daoProduto.validarNome(nome)) {
					
					request.setAttribute("msg", "Produto já existente!");
					request.setAttribute("produto", produto);
				}
				if(msg != null) {
					
					request.setAttribute("msg", msg);
				}
				
				
				//Salva Novo
				else if (id == null || id.isEmpty() && daoProduto.validarNome(nome)) {

					daoProduto.salvar(produto);
					request.setAttribute("msg", "Salvo com Sucesso!");
				}

				
				//Valida para atualizar
				if (id != null && !id.isEmpty()) {
					
					//Valida LOGIN para atualizar diferente do que ja existe
					if(!daoProduto.validarNomeUpdate(nome, id)) {
						
						request.setAttribute("msg", "Produto já existente no sistema.");
						request.setAttribute("produto", produto);
					}
					else {
						daoProduto.atualizar(produto);	
						request.setAttribute("msg", "Atualizado com Sucesso!");
						}
					}
					
				if(!podeInserir) {
					request.setAttribute("produto", produto);
				}
							
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("categorias", daoProduto.listaCategoria());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
