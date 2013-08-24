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

import com.sisar.dao.UsuarioDAO;
import com.sisar.modelo.Usuario;
import com.sisar.utils.Message;


public class CadUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CadUsuarioServlet() {
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
		String login = request.getParameter("txt_nome");
		String senha = request.getParameter("txt_senha");
		RequestDispatcher rd =null;
		String msg = "";
		boolean valida=true;
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		if((login.isEmpty())|| (login.equals(""))||(senha.isEmpty())|| (senha.equals("")) ){
			valida = false;
			msg = "Campo Obrigatório não preenchido!";
		}
		else{
			Usuario user = null;
			try {
				user = usuarioDao.getOneByName(login);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(user!=null){
				valida=false;
				msg="Usuário já Cadastrado!";
			}
			else{
				Usuario usuario = new Usuario();
				usuario.setSenha(senha);
				usuario.setNome(login);
				int r = usuarioDao.insert(usuario);
		
				if(r!=Message.SUCCESS_MENSAGE){
					valida=false;
					msg="Problema durante o Cadastro!";
				}
			}
		}
		if(valida){
			rd = request.getRequestDispatcher("/login.jsp");
			msg="Cadastro Realizado com Sucesso!";
			request.setAttribute("msg", msg);
			rd.forward(request, response);
		}
		else{
			  rd = request.getRequestDispatcher("/cadastraUsuario.jsp");
			  request.setAttribute("msg", msg);
			  rd.forward(request, response);
		}
	}

}
