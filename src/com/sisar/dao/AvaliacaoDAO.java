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

import com.sisar.modelo.Avaliacao;
import com.sisar.modelo.Item;
import com.sisar.modelo.Usuario;
import com.sisar.utils.HibernateUtil;


public class AvaliacaoDAO {
	
	

	//HIBERNATE
	//-------------------------------------------------------------------------------------------------------------------------
		private Session session;
		
		
		public List<Avaliacao> getTodosHib() throws SQLException{
			session = HibernateUtil.getInstance();
			List list = session.createQuery("from Avaliacao").list();
			return list;
			
		}
		
		
		public void inserirHib(Avaliacao avaliacao) {
			
			session = HibernateUtil.getInstance();
			Transaction tx = null;
			try {
				
				tx = session.beginTransaction();
				session.save(avaliacao);
				tx.commit();
				
				
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}finally{
				session.close();
			}
		}
		
	
	
	/*
	public List<Avaliacao> getTodasHib() throws SQLException{
		session = HibernateUtil.getInstance();
		Transaction tx = null;
		Usuario usuario = null;
		try {
			
			tx = session.beginTransaction();
			List<Avaliacao> avaliacoes = session.createQuery("from item inner join avaliacao on avaliacao.iditem= item.iditem inner join usuario on usuario.idusuario=avaliacao.idusuario").list();   
			for (Iterator<Usuario> iter = usuarios.iterator(); iter.hasNext();)    
			  usuario = iter.next();    				  
			tx.commit();	
			
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.flush();
			session.close();
		}
		return usuario;
		
	}*/
	
//-----------------------------------------------------------------------------------------------------	
	
	BDMySql bdMySql = BDMySql.getInstance();
	
	public int insert(Avaliacao avaliacao) {
		
		String sql = new String();

		sql = "INSERT INTO avaliacao (comentario,nota,data,idUsuario,IdItem) values('"+avaliacao.getComentario()+"',"+avaliacao.getNota()+",'"+avaliacao.getData()+"',"+avaliacao.getIdUsuario()+","+avaliacao.getIdItem()+");";
			
		
		return bdMySql.executarSQL(sql);
	}
	
	public Avaliacao getOne(int id) throws SQLException{
		String sql="select * from avaliacao where idAvaliacao="+id;
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Avaliacao avaliacao=null;
		if(rs.next()){
			avaliacao=new Avaliacao();
			avaliacao.setComentario(rs.getString("comentario"));
			avaliacao.setNota(rs.getInt("nota"));
			avaliacao.setData(rs.getDate("data"));
			avaliacao.setIdUsuario(rs.getInt("idUsuario"));
			avaliacao.setIdItem(rs.getInt("idItem"));
					
		}
		return avaliacao;
	}
	
		
	public List<Avaliacao> getAll() throws SQLException{
		String sql="select * from avaliacao";
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		List<Avaliacao> avaliacoes= new ArrayList<Avaliacao>();
		while(rs.next()){
			Avaliacao avaliacao=new Avaliacao();
			avaliacao.setIdAvaliacao(rs.getInt("idavaliacao"));
			avaliacao.setComentario(rs.getString("comentario"));
			avaliacao.setNota(rs.getInt("nota"));
			avaliacao.setData(rs.getDate("data"));
			avaliacao.setIdUsuario(rs.getInt("idusuario"));
			avaliacao.setIdItem(rs.getInt("iditem"));
			avaliacoes.add(avaliacao);
					
		}
		return avaliacoes;
	}
	
	public int update(Avaliacao avaliacao){
		String sql = new String();
		sql= "update avaliacao set comentario='"+ avaliacao.getComentario()+"', nota='"+ avaliacao.getNota()+ "', Data='"+ avaliacao.getData()+ "', idUsuario='"+ avaliacao.getIdUsuario()+ "', idItem='"+ avaliacao.getIdItem()+ "' where idAvaliacao = " + avaliacao.getIdAvaliacao(); 
		
		return bdMySql.executarSQL(sql);
	}

}
