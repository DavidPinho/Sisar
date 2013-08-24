package com.sisar.servlet;

import java.awt.Button;
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

//ESSA AQUI SÓ TAVA TESTANDO, FIZ NADA AINDA
public class NavBarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavBarServlet() {
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
		String opcao = request.getParameter("opcao");

		RequestDispatcher rd =null;
				
		if(opcao=="Home"){
		  rd = request.getRequestDispatcher("/faces/login.xhtml");
		  rd.forward(request, response);
		}else if(opcao=="Cardápio"){
			 rd = request.getRequestDispatcher("/teste.jsp");
			  rd.forward(request, response);
		}
		
	}

}
