package com.sisar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sisar.modelo.Item;
import com.sisar.modelo.Usuario;
import com.sisar.utils.HibernateUtil;


public class ItemDAO {
	
	
	//HIBERNATE
	//-------------------------------------------------------------------------------------------------------------------------
		private Session session;
		
		public void inserirHib(Item item) {
			
			session = HibernateUtil.getInstance();
			Transaction tx = null;
			try {
				
				tx = session.beginTransaction();
				session.save(item);
				tx.commit();
				
				
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}finally{
				session.close();
			}
		}
		
		public List<Item> getTodosHib() throws SQLException{
			session = HibernateUtil.getInstance();
			List list = session.createQuery("from Item").list();
			return list;
			
		}
		
		public Item getOneByNomeHib(String nome) throws SQLException{
			session = HibernateUtil.getInstance();
			Transaction tx = null;
			Item item = null;
			try {
				
				tx = session.beginTransaction();
				List<Item> itens = session.createQuery("from Item as i where i.nome = :nome").setString("nome", nome).list();   
				for (Iterator<Item> iter = itens.iterator(); iter.hasNext();)    
				  item = iter.next();    				  
				tx.commit();	
				
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}finally{
				session.flush();
				session.close();
			}
			return item;
			
		}
		
		
		/*public void atualizar(Usuario usuario){
			session = HibernateUtil.getInstance();
			Transaction tx = null;
			
			try {
				tx = session.beginTransaction();
				session.update(usuario);
				tx.commit();
				
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}finally{
				session.close();
			}
		}*/
		
		
	//----------------------------------------------------------------------------------------------------------	
		
		
		
		
		
		
		
	BDMySql bdMySql = BDMySql.getInstance();
	
	public int insert(Item item) {
		
		String sql = new String();

		sql = "INSERT INTO item (nome,categoria) values('"+item.getNome()+"','"+item.getCategoria()+"');";
			
		
		return bdMySql.executarSQL(sql);
	}
	
	public Item getOne(int id) throws SQLException{
		String sql="select * from item where idItem="+id;
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Item item=null;
		if(rs.next()){
			item=new Item();
			item.setIdItem(rs.getInt("iditem"));
			item.setNome(rs.getString("nome"));
			item.setCategoria(rs.getString("categoria"));
					
		}
		return item;
	}
	
	public Item getOneByNome(String nome) throws SQLException{
		String sql="select * from item where nome='"+nome+"'";
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Item item=null;
		if(rs.next()){
			item=new Item();
			item.setIdItem(rs.getInt("iditem"));
			item.setNome(rs.getString("nome"));
			item.setCategoria(rs.getString("categoria"));
					
		}
		return item;
	}
	
	public  List<Item> getAllByNome(String nome) throws SQLException{
		String sql="select * from item where nome like '"+nome+"%'";
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		List<Item> itens = new ArrayList<Item>();
		while(rs.next()){
			Item item=new Item();
			item.setIdItem(rs.getInt("idItem"));
			item.setNome(rs.getString("nome"));
			item.setCategoria(rs.getString("categoria"));
			itens.add(item);
					
		}
		return itens;
	}
	
	public List<Item> getAll() throws SQLException{
		String sql="select * from item";
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		List<Item> itens = new ArrayList<Item>();
		while(rs.next()){
			Item item=new Item();
			item.setIdItem(rs.getInt("idItem"));
			item.setNome(rs.getString("nome"));
			item.setCategoria(rs.getString("categoria"));
			itens.add(item);
					
		}
		return itens;
	}
	
	public int update(Item item){
		String sql = new String();
		sql= "update item set nome='"+ item.getNome()+"', categoria='"+ item.getCategoria()+ "' where iditem = " + item.getIdItem(); 
		
		return bdMySql.executarSQL(sql);
	}

}
