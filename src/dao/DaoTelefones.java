package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.BeanTelefones;
import connection.SingleConnection;


public class DaoTelefones {
	
	private Connection connection;

	public DaoTelefones() {

		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanTelefones telefone) {

		try {
		
			String sql = "insert into telefone(usuario, numero, tipo) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setLong(1, telefone.getUsuario());
			insert.setString(2, telefone.getNumero());
			insert.setString(3, telefone.getTipo());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}
	
	public List<BeanTelefones> listar(Long user) throws Exception {

		List<BeanTelefones> listar = new ArrayList<BeanTelefones>();
		
		String sql = "select * from telefone where usuario = '" + user + "'";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultset = statement.executeQuery();

		while (resultset.next()) {

			BeanTelefones telefone = new BeanTelefones();
			telefone.setId(resultset.getLong("id"));
			telefone.setUsuario(resultset.getLong("usuario"));
			telefone.setNumero(resultset.getString("numero"));
			telefone.setTipo(resultset.getString("tipo"));
						
			listar.add(telefone);

		}
		return listar;
	}
	
	
	public void delete(String id) {

		try {

			String sql = "delete from telefone where id = '" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}
}
