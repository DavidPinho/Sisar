package com.sisar.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class BDMySql {


	private static BDMySql singleton = null;
	private Connection con;

	public static BDMySql getInstance() {
		if (singleton == null) {
			singleton = new BDMySql();
		}
		return singleton;
	}

	private BDMySql() {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sisar", "root", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet executarBuscaSQL(String sql) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			
			return null;
		}

	}

	public int executarSQL(String sql) {
		try {

			Statement st = con.createStatement();
			
			st.executeUpdate(sql);
			st.close();
			return 0;

		} catch (Exception e) {
			return 1;
		}
	}

	public void fecharConexao() {
		try {
			con.close();
		} catch (Exception e) {

		}

	}
	
	@Override
	protected void finalize() throws Throwable {
		fecharConexao();
		super.finalize();
	}

}
