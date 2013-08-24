package com.sisar.modelo;


import java.util.Date;
import java.util.List;

public class Cardapio {
	
	private int idCardapio;
	private Date data;
	private int turno;
	private List<Item> itens;
	private List<String> itemNome;
	
	public int getIdCardapio() {
		return idCardapio;
	}
	public void setIdCardapio(int idCardapio) {
		this.idCardapio = idCardapio;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}

}
