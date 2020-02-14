package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/servletPesquisa")
public class ServletPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	String descricaoPesquisa = request.getParameter("descricaoconsulta");
    	
    	if(descricaoPesquisa != null && !descricaoPesquisa.trim().isEmpty()) {
    		
    		try {
			
    			List<BeanCursoJsp> pesquisaUsuario = daoUsuario.pesquisa(descricaoPesquisa);
    			
    			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", pesquisaUsuario);
				view.forward(request, response);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else {
    		
			try {
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
    	}
	}

}
