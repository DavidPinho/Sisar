package com.sisar.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sisar.modelo.Agendamento;
import com.sisar.modelo.Cardapio;
import com.sisar.modelo.Usuario;
import com.sisar.utils.HibernateUtil;

public class AgendamentoDAO {
	
	
	//HIBERNATE
	//-------------------------------------------------------------------------------------------------------------------------
		private Session session;
		
		public void inserirHib(Agendamento agendamento) {
					
			session = HibernateUtil.getInstance();
			Transaction tx = null;
			try {
				
				tx = session.beginTransaction();
				session.save(agendamento);
				tx.commit();
				
				
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}finally{
				session.close();
			}
		}
		
		public List<Agendamento> getTodosHib() throws SQLException{
			session = HibernateUtil.getInstance();
			List list = session.createQuery("from Agendamento").list();
			return list;
			
		}
		
		public List<Agendamento> getTodosByUsuarioHib() throws SQLException{
			Transaction tx = null;
			Usuario usuario = null;
			
			
			FacesContext fc = FacesContext.getCurrentInstance();  
			HttpSession session2 = (HttpSession) fc.getExternalContext().getSession(false);  
			String usuarioNome  = (String) session2.getAttribute("usuario");
			UsuarioDAO dao = new UsuarioDAO();
			usuario = dao.getOneByNomeHib(usuarioNome);
			
			session = HibernateUtil.getInstance();
			List<Agendamento> agendamentos=null;
			
			try {
				
				tx = session.beginTransaction();
				agendamentos = session.createQuery("from Agendamento as u where u.idUsuario = :id").setInteger("id", usuario.getIdUsuario()).list();   			  
				tx.commit();	
				
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}finally{
				session.flush();
				session.close();
			}
			return agendamentos;
			
		}
		
		public void excluirHib(Agendamento agendamento) {
			
			session = HibernateUtil.getInstance();
			Transaction tx = null;
			try {
				
				tx = session.beginTransaction();
				session.delete(agendamento);
				tx.commit();
				
				
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}finally{
				session.close();
			}
		}
			
		
		public Agendamento validaAgendamentoHib(Agendamento agendamento) throws SQLException{
			session = HibernateUtil.getInstance();
			Transaction tx = null;
			Agendamento agenda = null;
			try {
				
				tx = session.beginTransaction();
				Query q = session.createQuery("from Agendamento as u where u.data = ? and u.hora = ?");
				q.setParameter(0, agendamento.getData());
				q.setParameter(1, agendamento.getHora());
				List<Agendamento> agendamentos = q.list();   
				for (Iterator<Agendamento> iter = agendamentos.iterator(); iter.hasNext();)    
				  agenda = iter.next();    				  
				tx.commit();	
				
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			}finally{
				session.flush();
				session.close();
			}
			return agenda;
			
		}
		
		
		

		
	//----------------------------------------------------------------------------------------------------------	
		
		
		

}
