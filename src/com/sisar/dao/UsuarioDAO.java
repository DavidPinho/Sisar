package com.sisar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sisar.modelo.Usuario;
import com.sisar.utils.HibernateUtil;



public class UsuarioDAO {
	
	
//HIBERNATE
//-------------------------------------------------------------------------------------------------------------------------
	private Session session;
	
	public void inserirHib(Usuario usuario) {
		
		session = HibernateUtil.getInstance();
		Transaction tx = null;
		try {
			
			tx = session.beginTransaction();
			session.save(usuario);
			tx.commit();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
	}
	
	public List<Usuario> getTodosHib() throws SQLException{
		session = HibernateUtil.getInstance();
		List list = session.createQuery("from Usuario").list();
		return list;
		
	}
	
	public Usuario getOneByNomeHib(String nome) throws SQLException{
		session = HibernateUtil.getInstance();
		Transaction tx = null;
		Usuario usuario = null;
		try {
			
			tx = session.beginTransaction();
			List<Usuario> usuarios = session.createQuery("from Usuario as u where u.nome = :nome").setString("nome", nome).list();   
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
		
	}
	
	
	public void atualizarHib(Usuario usuario){
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
	}
	
	
	public Usuario getOneByIdHib(int idUsuario) throws SQLException{
		session = HibernateUtil.getInstance();
		Transaction tx = null;
		Usuario usuario = null;
		try {
			
			tx = session.beginTransaction();
			List<Usuario> usuarios = session.createQuery("from Usuario as u where u.idUsuario = :idUsuario").setInteger("idUsuario", idUsuario).list();   
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
		
	}
	
	
	
	
//----------------------------------------------------------------------------------------------------------	
	
	
	
	
	BDMySql bdMySql = BDMySql.getInstance();
	
	
	//Mysql
	public int insert(Usuario usuario) {
		
		String sql = new String();

		sql = "INSERT INTO usuario (nome,senha) values('"+usuario.getNome()+"','"+usuario.getSenha()+"');";
			
		
		return bdMySql.executarSQL(sql);
	}
	
	
	public Usuario getOne(int id) throws SQLException{
		String sql="select * from usuario where idusuario="+id;
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Usuario usuario=null;
		if(rs.next()){
			usuario=new Usuario();
			usuario.setIdUsuario(rs.getInt("idusuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setSenha(rs.getString("senha"));
					
		}
		return usuario;
		
	}
	
	public Usuario getOneByName(String nome) throws SQLException{
		String sql="select * from usuario where nome='"+nome+"'";
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Usuario usuario=null;
		if(rs.next()){
			usuario=new Usuario();
			usuario.setIdUsuario(rs.getInt("idusuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setSenha(rs.getString("senha"));
					
		}
		return usuario;
		
	}
	
	public Usuario verificaLogado(Usuario user) throws SQLException{
		String sql="select * from usuario where nome='"+user.getNome()+"' and senha = '"+user.getSenha()+"'";
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Usuario usuario=null;
		if(rs.next()){
			usuario=new Usuario();
			usuario.setIdUsuario(rs.getInt("idusuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setSenha(rs.getString("senha"));
					
		}
		return usuario;
	}
	
	public Vector<Usuario> getAll() throws SQLException{
		String sql="select * from usuario";
		ResultSet rs=bdMySql.executarBuscaSQL(sql);
		Vector<Usuario> usuarios = new Vector<Usuario>();
		while(rs.next()){
			Usuario usuario=new Usuario();
			usuario.setIdUsuario(rs.getInt("idusuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setSenha(rs.getString("senha"));
			usuarios.add(usuario);
					
		}
		return usuarios;
	}
	
	public int update(Usuario usuario){
		String sql = new String();
		sql= "update usuario set nome='"+ usuario.getNome()+"', senha='"+ usuario.getSenha()+ "' where idusuario = " + usuario.getIdUsuario(); 
		
		return bdMySql.executarSQL(sql);
	}

}
