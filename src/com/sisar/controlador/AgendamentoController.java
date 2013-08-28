package com.sisar.controlador;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import com.sisar.dao.AgendamentoDAO;
import com.sisar.dao.UsuarioDAO;
import com.sisar.modelo.Agendamento;
import com.sisar.modelo.Cardapio;
import com.sisar.modelo.Usuario;

public class AgendamentoController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DataModel model;
	AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
	private Agendamento agendamento;
	
	public DataModel getTodas() throws SQLException{
		model = new ListDataModel(agendamentoDAO.getTodosHib());
		return model;
	}
	
	public String novo(){
		this.agendamento=new Agendamento();
		return "novo";
	}
	
	public String insert() throws SQLException{
	
		java.sql.Date sqlDate = new java.sql.Date(agendamento.getData().getTime());
		agendamento.setData(sqlDate);
		FacesContext fc = FacesContext.getCurrentInstance();  
		HttpSession session2 = (HttpSession) fc.getExternalContext().getSession(false);  
		String usuarioNome  = (String) session2.getAttribute("usuario");
		UsuarioDAO dao = new UsuarioDAO();
		agendamento.setIdUsuario(dao.getOneByNomeHib(usuarioNome).getIdUsuario());
		
		Agendamento a = null;
		a=agendamentoDAO.validaAgendamentoHib(agendamento);
		FacesContext context = FacesContext.getCurrentInstance();
		if(a!=null){
			FacesMessage message = new FacesMessage("Horário Indisponivel!");
			context.addMessage("hora", message);
			return "falha";
		}else{
			agendamentoDAO.inserirHib(agendamento);
			return "insert";
		}
		
		
	}
	
	public String excluir(){
		Agendamento agendamento = (Agendamento)model.getRowData();
		agendamentoDAO.excluirHib(agendamento);
		return "excluir";
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	public String getUserName(int id){
		UsuarioDAO dao = new UsuarioDAO();
		try {
			String nome= dao.getOneByIdHib(id).getNome();
			return nome;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "nada";
	}
	
	public DataModel getTodasPorUsuario() throws SQLException{
		model = new ListDataModel(agendamentoDAO.getTodosByUsuarioHib());
		return model;
	}


}
