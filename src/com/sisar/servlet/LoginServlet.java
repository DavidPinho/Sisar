package com.sisar.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import com.sisar.dao.UsuarioDAO;
import com.sisar.modelo.Usuario;
import com.sisar.service.Autenticacao;
import com.sisar.service.AutenticacaoServiceLocator;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		RequestDispatcher rd =null;
		String msg = "";
		Usuario user = null;
		boolean valida=true;
		
		if((login.isEmpty())|| (login.equals("a"))||(senha.isEmpty())|| (senha.equals("")) ){
			valida = false;
			msg = "Campo Obrigatório não preenchidooo!!";
		}
		else{
			Usuario usuario = new Usuario();
			usuario.setSenha(senha);
			usuario.setNome(login);
		
			UsuarioDAO usuarioDao = new UsuarioDAO();
			try {
				user = usuarioDao.verificaLogado(usuario);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			if(user==null){
				valida = false;
				msg = "Dados incorretos!";
			}
		}
		if(valida){
		  try {
			Autenticacao autenticacao = new AutenticacaoServiceLocator().getAutenticacao();
			autenticacao.autentica(user.getNome());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  HttpSession sessao = request.getSession();
		  sessao.setAttribute("usuario", user.getNome());
		  if(user.getNome().equals("admin")){
			  rd = request.getRequestDispatcher("/faces/avaliacoes.xhtml");
		  } else{
			  rd =request.getRequestDispatcher("/redirect.jsp");
		  }
		  rd.forward(request, response);
		}
		else{
			 rd = request.getRequestDispatcher("/login.jsp");
			 request.setAttribute("msg", msg);
			  rd.forward(request, response);
		}
		
	}

}
