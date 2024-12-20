package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objeto , Long userLogado) throws Exception {
		
		if (objeto.isNovo()) { // Grava um novo objeto
			
		
		String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil) VALUES (?, ?, ?, ?, ?, ?); ";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, objeto.getLogin());
		preparedSql.setString(2, objeto.getSenha());
		preparedSql.setString(3, objeto.getNome());
		preparedSql.setString(4, objeto.getEmail());
		preparedSql.setLong(5, userLogado);
		preparedSql.setString(6, objeto.getPerfil());
		
		preparedSql.execute();
		
		connection.commit();
		} else {
			String sql = "UPDATE public.model_login SET login=?, senha=?, nome=?, email=?, perfil=? WHERE id = "+ objeto.getId() +";";
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			
			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.setString(5, objeto.getPerfil());
			
			preparedSql.executeUpdate();
			
			connection.commit();
		}
		
		return this.consultaUsuario(objeto.getLogin(), userLogado);
		
	}
	
	public ModelLogin consultaUsuario(String login, Long userLogado) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		String sql = "SELECT * FROM model_login WHERE upper(login) = upper('" + login + "') AND useradmin IS false AND usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) /* Se tem resultado */ {
			
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			
		}	
		
		return modelLogin;
	}
	 
public ModelLogin consultaUsuarioID(String id, Long userLogado) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		String sql = "SELECT * FROM model_login WHERE id = ? AND useradmin IS false AND usuario_id = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);
		
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) /* Se tem resultado */ {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
		}	
		
		return modelLogin;
	}
	 
	
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+ login +"') ;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next(); // Para entrar nos resultados SQL
		return resultado.getBoolean("existe");
		
	}
	
	public void deletarUser(String idUser) throws Exception {
		String sql = "DELETE FROM model_login WHERE id = ? AND useradmin IS false;";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
					
		prepareSql.setLong(1, Long.parseLong(idUser));
		
		
		prepareSql.executeUpdate();
		
		connection.commit();
		
		
	}
	
public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE useradmin IS false AND useradmin IS false AND usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) { // Percorrer as linhas de resultado do SQL
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			//modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			
			retorno.add(modelLogin);
		}
		
		
		
		
		return retorno;
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE UPPER(nome) LIKE UPPER(?) AND useradmin IS false AND usuario_id = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome  + "%");
		statement.setLong(2,  userLogado );
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) { // Percorrer as linhas de resultado do SQL
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			//modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			
			retorno.add(modelLogin);
		}
		
		
		
		
		return retorno;
	}
	
public ModelLogin consultaUsuarioLogado(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		String sql = "SELECT * FROM model_login WHERE upper(login) = upper('" + login + "')";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) /* Se tem resultado */ {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
		}	
		
		return modelLogin;
	}
	
public ModelLogin consultaUsuario(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		String sql = "SELECT * FROM model_login WHERE upper(login) = upper('" + login + "') AND useradmin IS false ";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) /* Se tem resultado */ {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
		}	
		
		return modelLogin;
	}
}
