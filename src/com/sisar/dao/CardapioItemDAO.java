package com.sisar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.sisar.modelo.CardapioItem;


public class CardapioItemDAO {
	
	BDMySql bdMySql = BDMySql.getInstance();
	
	public int insert(CardapioItem cardapio) {
		
		String sql = new String();

		sql = "INSERT INTO cardapio_item (iditem,idcardapio) values ("+cardapio.getIdItem()+","+cardapio.getIdCardapio()+")";
			
		
		return bdMySql.executarSQL(sql);
	}
	
	public Vector<CardapioItem> getAll() throws SQLException{
		String sql="select * from cardapio_item";
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Vector<CardapioItem> cardapiosItens = new Vector<CardapioItem>();
		if(rs.next()){
			CardapioItem cardapioItem=new CardapioItem();
			cardapioItem.setIdItem(rs.getInt("idIdItem"));
			cardapioItem.setIdCardapio(rs.getInt("idIdCardapio"));
			cardapiosItens.add(cardapioItem);
					
		}
		return cardapiosItens;
	}
}
