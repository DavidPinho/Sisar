package com.sisar.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sisar.modelo.Avaliacao;
import com.sisar.modelo.Cardapio;
import com.sisar.modelo.Item;
import com.sisar.utils.HibernateUtil;


public class CardapioDAO {
	

	//HIBERNATE
	//-------------------------------------------------------------------------------------------------------------------------
		private Session session;
		
		
		public List<Cardapio> getTodosHib() throws SQLException{
			session = HibernateUtil.getInstance();
			List list = session.createQuery("from Cardapio").list();
			return list;
			
		}
		
		public void excluirHib(Cardapio cardapio) {
			
			session = HibernateUtil.getInstance();
			Transaction tx = null;
			try {
				
				tx = session.beginTransaction();
				session.delete(cardapio);
				tx.commit();
				
				
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}finally{
				session.close();
			}
		}
				
	
//-----------------------------------------------------------------------------------------------------	
	
	
	BDMySql bdMySql = BDMySql.getInstance();
	
	public int insert(Cardapio cardapio) {
		
		String sql = new String();

		sql = "INSERT INTO cardapio (data,turno) values('"+cardapio.getData()+"','"+cardapio.getTurno()+"');";
			
		
		return bdMySql.executarSQL(sql);
	}
	
	public int deleteCardapioItem(int id){
		String sql="delete from cardapio_item where idcardapio="+id;

		return bdMySql.executarSQL(sql);
	}
	
	public int delete(int id){
		String sql="delete from cardapio where idcardapio="+id;

		return bdMySql.executarSQL(sql);
	}
	
	public Cardapio getOne(int id) throws SQLException{
		String sql="select * from cardapio where idcardapio="+id;
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Cardapio cardapio=null;
		if(rs.next()){
			cardapio=new Cardapio();
			cardapio.setIdCardapio(rs.getInt("idcardapio"));
			cardapio.setData(rs.getDate("data"));
			cardapio.setTurno(rs.getInt("turno"));
					
		}
		return cardapio;
	}
	
	public Cardapio getOneByData(Date data, int turno) throws SQLException{
		String sql="select * from cardapio where data='"+data+"' and turno ="+turno;
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Cardapio cardapio=null;
		if(rs.next()){
			cardapio=new Cardapio();
			cardapio.setIdCardapio(rs.getInt("idcardapio"));
			cardapio.setData(rs.getDate("data"));
			cardapio.setTurno(rs.getInt("turno"));
					
		}
		return cardapio;
	}
	
	public List<Item> getItens(Date data, int turno) throws SQLException{
		String sql="select * from item inner join cardapio_item on item.iditem=cardapio_item.iditem inner join cardapio on cardapio.idcardapio=cardapio_item.idcardapio where cardapio.data='"+data+"' and cardapio.turno ="+turno;
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		List<Item> itens = new ArrayList<Item>();
		while(rs.next()){
			Item item =new Item();
			item.setCategoria(rs.getString("item.categoria"));
			item.setNome(rs.getString("item.nome"));
			item.setIdItem(rs.getInt("item.iditem"));
			itens.add(item);
		}
		return itens;
	}
	
	public List<Cardapio> getAll() throws SQLException{
		String sql="select * from cardapio";
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		List<Cardapio> cardapios = new ArrayList<Cardapio>();
		while(rs.next()){
			Cardapio cardapio=new Cardapio();
			cardapio.setIdCardapio(rs.getInt("idCardapio"));
			cardapio.setData(rs.getDate("data"));
			cardapio.setTurno(rs.getInt("turno"));
			cardapios.add(cardapio);
					
		}
		return cardapios;
	}
	
	public int update(Cardapio cardapio){
		String sql = new String();
		sql= "update cardapio set data='"+ cardapio.getData()+"', turno='"+ cardapio.getTurno()+ "' where idCardapio = " + cardapio.getIdCardapio(); 
		
		return bdMySql.executarSQL(sql);
	}

}
