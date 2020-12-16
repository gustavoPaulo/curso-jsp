package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import beans.BeanCursoJsp;
import dao.DaoNovoUsuario;

@WebServlet("/novoUsuario")
public class NovoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoNovoUsuario daoNovoUsuario = new DaoNovoUsuario();
    
    public NovoUsuario() {
        super();
    
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	doPost(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); 
    	System.out.println(sdf.format(new Date()));
    	
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		
		BeanCursoJsp usuario = new BeanCursoJsp();
		
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		
		try {
			
			if (login != null){
				
				daoNovoUsuario.salvar(usuario);
				
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				view.forward(request, response);
				
			}else {
				
				request.setAttribute("msg", "Erro 404!");				
				
				RequestDispatcher view = request.getRequestDispatcher("/novoUsuario.jsp");
				view.forward(request, response);

			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	}