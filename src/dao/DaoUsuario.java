package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {

		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) {

		try {
			
			String sql = "insert into usuario(login, senha, nome, email, cep, rua, bairro, cidade, uf, ibge, fotoBase64, contentType, curriculoBase64, contentTypeCurriculo, fotoBase64Miniatura, ativo, sexo, perfil)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getEmail());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getUf());
			insert.setString(10, usuario.getIbge());
			
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContentTypeCurriculo());
			
			insert.setString(15, usuario.getFotoBase64Miniatura());
			insert.setBoolean(16, usuario.isAtivo());
			
			insert.setString(17, usuario.getSexo());
			insert.setString(18, usuario.getPerfil());
			
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
	
	public List<BeanCursoJsp> pesquisa(String descricaoconsulta) throws SQLException {
		
		String sql = "select * from usuario where login <> 'gustavo' and nome like '%" + descricaoconsulta + "%' ";
		return pesquisaUsuario(sql);
	}
	
		
	public List<BeanCursoJsp> listar() throws Exception {

		String sql = "select * from usuario where login <> 'gustavo'";
		return pesquisaUsuario(sql);
	}
	

	private List<BeanCursoJsp> pesquisaUsuario(String sql) throws SQLException {
		
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultset = statement.executeQuery();

		while (resultset.next()) {

			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultset.getLong("id"));
			beanCursoJsp.setLogin(resultset.getString("login"));
			beanCursoJsp.setSenha(resultset.getString("senha"));
			beanCursoJsp.setNome(resultset.getString("nome"));
			beanCursoJsp.setEmail(resultset.getString("email"));
			beanCursoJsp.setCep(resultset.getString("cep"));
			beanCursoJsp.setRua(resultset.getString("rua"));
			beanCursoJsp.setBairro(resultset.getString("bairro"));
			beanCursoJsp.setCidade(resultset.getString("cidade"));
			beanCursoJsp.setUf(resultset.getString("uf"));
			beanCursoJsp.setIbge(resultset.getString("ibge"));
			beanCursoJsp.setFotoBase64(resultset.getString("fotoBase64"));
			beanCursoJsp.setContentType(resultset.getString("contentType"));
			beanCursoJsp.setCurriculoBase64(resultset.getString("curriculoBase64"));
			beanCursoJsp.setContentTypeCurriculo(resultset.getString("contentTypeCurriculo"));
			beanCursoJsp.setFotoBase64Miniatura(resultset.getString("fotoBase64Miniatura"));
			beanCursoJsp.setAtivo(resultset.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultset.getString("sexo"));
			beanCursoJsp.setPerfil(resultset.getString("perfil"));
			
			listar.add(beanCursoJsp);

		}
		
		return listar;
	}

	public void delete(String id) {

		try {

			String sql = "delete from usuario where id = '" + id + "' and login <> 'gustavo'";
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

	public BeanCursoJsp consultar(String id) throws Exception {

		String sql = "select * from usuario where id = '" + id + "' and login <> 'gustavo'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();

			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setEmail(resultSet.getString("email"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setUf(resultSet.getString("uf"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
			beanCursoJsp.setFotoBase64(resultSet.getString("fotoBase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotoBase64Miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contentType"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculoBase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contentTypeCurriculo"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));
			
			return beanCursoJsp;
		}

		return null;
	}
	

	public void atualizar(BeanCursoJsp usuario) {

		try {

			StringBuilder sql = new StringBuilder();
			
			sql
			.append("UPDATE usuario SET login=?, senha=?, email=?, nome=?, cep=?, rua=?")
			.append(" ,bairro=?, cidade=?, uf=?, ibge=?, ativo=?, sexo=?, perfil=?");
			
			if(usuario.isAtualizarImage()) {
				sql.append(" ,fotobase64=?, contenttype=?");
			}
			
			if(usuario.isAtualizarPdf()) {
				sql.append(" ,curriculobase64 = ?, contenttypecurriculo = ?");
			}
			
			if(usuario.isAtualizarImage()) {
				
				sql.append(" ,fotobase64miniatura = ?");
			}
			
			sql.append(" WHERE id = " + usuario.getId());

			
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getEmail());
			preparedStatement.setString(5, usuario.getCep());
			preparedStatement.setString(6, usuario.getRua());
			preparedStatement.setString(7, usuario.getBairro());
			preparedStatement.setString(8, usuario.getCidade());
			preparedStatement.setString(9, usuario.getUf());
			preparedStatement.setString(10, usuario.getIbge());
			preparedStatement.setBoolean(11, usuario.isAtivo());
			preparedStatement.setString(12, usuario.getSexo());
			preparedStatement.setString(13, usuario.getPerfil());
			
			if(usuario.isAtualizarImage()) {
				
				preparedStatement.setString(14, usuario.getFotoBase64());
				preparedStatement.setString(15, usuario.getContentType());
			}
			
			if(usuario.isAtualizarPdf()) {
				
				if(usuario.isAtualizarPdf() && !usuario.isAtualizarImage()) {
					
					preparedStatement.setString(14, usuario.getCurriculoBase64());
					preparedStatement.setString(15, usuario.getContentTypeCurriculo());
				}else {
				
				preparedStatement.setString(16, usuario.getCurriculoBase64());
				preparedStatement.setString(17, usuario.getContentTypeCurriculo());
				
				}
			}else {
				
				if(usuario.isAtualizarImage()) {
					
					preparedStatement.setString(16, usuario.getFotoBase64Miniatura());
				}
			}
			
			if(usuario.isAtualizarImage() && usuario.isAtualizarPdf()) {
				
				preparedStatement.setString(18, usuario.getFotoBase64Miniatura());
			}
			
			preparedStatement.executeUpdate();
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

	public boolean validarLogin(String login) throws Exception {

		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/*Verifica se já existe um usuario com o mesmo login, se não ele deixa criar e retorna True*/
		}

		return false;
	}
	
	public boolean validarSenha(String senha) throws Exception {

		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}
	
	
	public boolean validarLoginUpdate(String login, String id) throws Exception {

		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}
	
	
	public boolean validarSenhaUpdate(String senha, String id) throws Exception {

		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "' and id <> " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

}
