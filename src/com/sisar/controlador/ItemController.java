package com.sisar.controlador;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


import com.sisar.dao.ItemDAO;

import com.sisar.modelo.Item;
import com.sisar.modelo.Usuario;


public class ItemController implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item item2;
	private DataModel model;
	private String itemAux=null;


	ItemDAO itemDao = new ItemDAO();
	


	public DataModel getTodas() throws SQLException{
		//model = new ListDataModel(itemDao.getAll());
		model = new ListDataModel(itemDao.getTodosHib());
		return model;
	}
	
	public String insert() throws SQLException{
		
		Item i = null;
		//i=itemDao.getOneByNome(item2.getNome());
		i=itemDao.getOneByNomeHib(item2.getNome());
		FacesContext context = FacesContext.getCurrentInstance();
		if(i!=null){
			FacesMessage message = new FacesMessage("Item já cadastrado");
			context.addMessage("nome", message);
			return "falha";
		}else{
			//itemDao.insert(item2);
			itemDao.inserirHib(item2);
			return "insert";
		}
	
	}
	
	public String update() throws SQLException{
		Item itemAux2=null;
		if(!itemAux.equals(item2.getNome()))
			itemAux2 = itemDao.getOneByNome(item2.getNome());
		FacesContext context = FacesContext.getCurrentInstance();
		if(itemAux2!=null){
			FacesMessage message = new FacesMessage("Item já cadastrado");
			context.addMessage("nome", message);
			return "falha";
		}else{
			itemDao.update(item2);
			return "update";
		}
		
		
	}
	
	public String novo(){
		this.item2=new Item();
		return "novo";
	}
	
	public String editar(){
		Item item3 = (Item)model.getRowData();
		setItem2(item3);
		itemAux = item3.getNome();
		return "editar";
	}

	public Item getItem2() {
		return item2;
	}

	public void setItem2(Item item) {
		this.item2 = item;
	}
	


}
