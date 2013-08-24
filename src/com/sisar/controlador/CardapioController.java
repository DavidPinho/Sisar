package com.sisar.controlador;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.smartcardio.Card;

import com.sisar.dao.CardapioDAO;
import com.sisar.dao.CardapioItemDAO;
import com.sisar.dao.ItemDAO;
import com.sisar.modelo.Cardapio;
import com.sisar.modelo.CardapioItem;
import com.sisar.modelo.Item;
import com.sisar.modelo.Usuario;

public class CardapioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataModel model;
	private CardapioDAO cardapioDao = new CardapioDAO();
	private ItemDAO itemDao = new ItemDAO();
	private CardapioItemDAO cardapioItemDao = new CardapioItemDAO();
	private Cardapio cardapio2;
	private Item itemCardapio;
	private String guarnicao1;
	private String guarnicao2;
	private String salada;
	private String proteina;
	private String vegetariano;
	private String suco;
	private String sobremesa;
	
	public DataModel getTodas() throws SQLException{
		model = new ListDataModel(cardapioDao.getTodosHib());
		return model;
	}
	
	public CardapioController(){
		cardapio2=new Cardapio();
		Date data2 = new Date();
		cardapio2.setData(data2);
		
	}
	
	
	public String excluir(){
		Cardapio cardapio = (Cardapio)model.getRowData();
		cardapioDao.deleteCardapioItem(cardapio.getIdCardapio());
		cardapioDao.excluirHib(cardapio);
		//cardapioDao.delete(cardapio.getIdCardapio());
		return "excluir";
	}
	
	public String novo(){
	
		guarnicao1 = new String();
		guarnicao2= new String();
		salada= new String();
		proteina= new String();
		vegetariano= new String();
		suco= new String();
		sobremesa= new String();
		cardapio2 = new Cardapio();
		return "novo";
	}
	
	public String insert() throws SQLException{
		Cardapio c=null;
		java.sql.Date sqlDate = new java.sql.Date(cardapio2.getData().getTime());
		cardapio2.setData(sqlDate);
		CardapioItem cardapioItem = new CardapioItem();
		c = cardapioDao.getOneByData(sqlDate, cardapio2.getTurno());
		
		if(c!=null){
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("Cardápio já cadastrado nesta data e turno");
			context.addMessage("popupCal", message);
			return "falha";
		}
 else {
			cardapioDao.insert(cardapio2);
			int idCardapio = cardapioDao.getOneByData(sqlDate, cardapio2.getTurno()).getIdCardapio();
			Item item = itemDao.getOneByNome(guarnicao1);
			cardapioItem.setIdCardapio(idCardapio);
			cardapioItem.setIdItem(item.getIdItem());
			cardapioItemDao.insert(cardapioItem);

			item = itemDao.getOneByNome(guarnicao2);
			cardapioItem.setIdItem(item.getIdItem());
			cardapioItemDao.insert(cardapioItem);

			item = itemDao.getOneByNome(salada);
			cardapioItem.setIdItem(item.getIdItem());
			cardapioItemDao.insert(cardapioItem);

			item = itemDao.getOneByNome(proteina);
			cardapioItem.setIdItem(item.getIdItem());
			cardapioItemDao.insert(cardapioItem);

			item = itemDao.getOneByNome(vegetariano);
			cardapioItem.setIdItem(item.getIdItem());
			cardapioItemDao.insert(cardapioItem);

			item = itemDao.getOneByNome(suco);
			cardapioItem.setIdItem(item.getIdItem());
			cardapioItemDao.insert(cardapioItem);

			item = itemDao.getOneByNome(sobremesa);
			cardapioItem.setIdItem(item.getIdItem());
			cardapioItemDao.insert(cardapioItem);

			return "insert";
		}
	}
	
	public List<String> complete(String query) throws SQLException {  
		
		List<Item> resultsItem = new ArrayList<Item>();  
		List<String> results = new ArrayList<String>();  
		resultsItem = itemDao.getAllByNome(query); 
		for (Item item : resultsItem) {
			results.add(item.getNome());
		}
      
        return results;  
    }  
	
	public String getMenu(){
		FacesContext context = FacesContext.getCurrentInstance();
		String viewId = context.getViewRoot().getViewId();
		ViewHandler handler = context.getApplication().getViewHandler();
		UIViewRoot root = handler.createView(context, viewId);
		root.setViewId(viewId);
		context.setViewRoot(root);
		return "lista";
	}
	
	public DataModel getitens() throws SQLException{
		
			java.sql.Date sqlDate = new java.sql.Date(cardapio2.getData().getTime());
			cardapio2.setData(sqlDate);
			model = new ListDataModel(cardapioDao.getItens(sqlDate, cardapio2.getTurno()));
			return model;
		
	}
	
	public String avaliar(){
		Item item = (Item)model.getRowData();
		setItemCardapio(item);
		return "avaliar";
	}

	public Cardapio getCardapio2() {
		return cardapio2;
	}

	public void setCardapio2(Cardapio cardapio) {
		this.cardapio2 = cardapio;
	}
	
	public String getGuarnicao1() {
		return guarnicao1;
	}


	public void setGuarnicao1(String guarnicao1) {
		this.guarnicao1 = guarnicao1;
	}


	public String getGuarnicao2() {
		return guarnicao2;
	}


	public void setGuarnicao2(String guarnicao2) {
		this.guarnicao2 = guarnicao2;
	}


	public String getSalada() {
		return salada;
	}


	public void setSalada(String salada) {
		this.salada = salada;
	}


	public String getProteina() {
		return proteina;
	}


	public void setProteina(String proteina) {
		this.proteina = proteina;
	}


	public String getVegetariano() {
		return vegetariano;
	}


	public void setVegetariano(String vegetariano) {
		this.vegetariano = vegetariano;
	}


	public String getSuco() {
		return suco;
	}


	public void setSuco(String suco) {
		this.suco = suco;
	}


	public String getSobremesa() {
		return sobremesa;
	}


	public void setSobremesa(String sobremesa) {
		this.sobremesa = sobremesa;
	}

	public Item getItemCardapio() {
		return itemCardapio;
	}

	public void setItemCardapio(Item itemCardapio) {
		this.itemCardapio = itemCardapio;
	}

	

}
