package com.sisar.controlador;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import com.sisar.dao.UsuarioDAO;
import com.sisar.modelo.Item;
import com.sisar.modelo.Usuario;

public class UsuarioController implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario2;
	UsuarioDAO userDao = new UsuarioDAO();
	private DataModel model;
	String nomeAux=null;

	public DataModel getTodas() throws SQLException{
		//model = new ListDataModel(userDao.getAll());
		model = new ListDataModel(userDao.getTodosHib());
		return model;
	}
	
	public String logout(){
		FacesContext fc = FacesContext.getCurrentInstance();    
	    HttpSession session = (HttpSession)fc.getExternalContext().getSession(true);    
	    session.invalidate();     
	    return "logout"; 
	}
	
	
	public String novo(){
		this.usuario2=new Usuario();
		return "novo";
	}
	
	public String editar(){
		Usuario user = (Usuario)model.getRowData();
		setUsuario2(user);
		nomeAux=user.getNome();
		return "editar";
	}
	
	public String update() throws SQLException{
		Usuario userAux=null;
		
		if(!nomeAux.equals(usuario2.getNome()))
			//userAux = userDao.getOneByName(usuario2.getNome());
			userAux = userDao.getOneByNomeHib(usuario2.getNome());
		
		if(userAux!=null){
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Usuário já cadastrado");
			context.addMessage("nome", message);
			return "falha";
		}else{
			//userDao.update(usuario2);
			userDao.atualizarHib(usuario2);
			return "update";
		}
		
	
	}
	
	public String insert() throws SQLException{
		Usuario u = null;
		//u=userDao.getOneByName(usuario2.getNome());
		u=userDao.getOneByNomeHib(usuario2.getNome());
		FacesContext context = FacesContext.getCurrentInstance();
		if(u!=null){
			FacesMessage message = new FacesMessage("Usuário já cadastrado");
			context.addMessage("nome", message);
			return "falha";
		}else{
			//userDao.insert(usuario2);
			userDao.inserirHib(usuario2);
			return "insert";
		}
		
	}


	public Usuario getUsuario2() {
		return usuario2;
	}


	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}


}
