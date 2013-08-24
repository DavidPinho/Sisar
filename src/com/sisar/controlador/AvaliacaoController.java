package com.sisar.controlador;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sisar.dao.AvaliacaoDAO;
import com.sisar.dao.ItemDAO;
import com.sisar.dao.UsuarioDAO;
import com.sisar.modelo.Avaliacao;
import com.sisar.modelo.Item;
import com.sisar.modelo.Usuario;

public class AvaliacaoController implements Serializable{
	

	private Avaliacao avaliacao;
	private Item item;
	
	

	private static final long serialVersionUID = 1L;

	public List<Avaliacao> getTodas() throws SQLException{
		List<Avaliacao> avaliacao = new ArrayList<Avaliacao>();
		AvaliacaoDAO avalia = new AvaliacaoDAO();
		avaliacao = avalia.getTodosHib();
		return avaliacao;
	}
	
	
	public String getItemName(int id){
		ItemDAO dao = new ItemDAO();
		try {
			String nome= dao.getOne(id).getNome();
			return nome;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "nada";
	}
	
	public String getUserName(int id){
		UsuarioDAO dao = new UsuarioDAO();
		try {
			String nome= dao.getOne(id).getNome();
			return nome;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "nada";
	}
	
	public String avaliar(Item item){
		
		avaliacao = new Avaliacao();
		setItem(item);
		//avaliacao.setItemNome(item.getNome());
		
		return "load";
		
	}
	
	public String insert() throws SQLException{
		
		AvaliacaoDAO dao = new AvaliacaoDAO();
		Date data = new Date();
		java.sql.Date sqlDate = new java.sql.Date(data.getTime());
		avaliacao.setData(sqlDate);
		
		avaliacao.setIdItem(item.getIdItem());
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);	
		String logado = (String)session.getAttribute("usuario");
		UsuarioDAO userDao = new UsuarioDAO();
		avaliacao.setIdUsuario(userDao.getOneByNomeHib(logado).getIdUsuario());
		
		//dao.insert(avaliacao);
		dao.inserirHib(avaliacao);
		return "insert";
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	


}
