package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factoy.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDao {

	/*
	 * CRUD c:create r:read u:update d:delete
	 */

	public void save(Contato contato) {

		String sql = " INSERT INTO contatos2"
				+ "(nome,idade,datacadastro) "
				+ "VALUES "
				+ "(?,?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// criar uma conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMysql();

			// criamos uma preparedStatement , para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// adicionar os valores que sao esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// executar a query
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fechar as conexoes
			try {
				if (pstm != null) {
					pstm.close();

				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	}

	public void update(Contato contato) {
		String sql = "UPDATE contatos2 SET nome = ?, idade = ?, datacadastro = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// criar conexao com o banco
			conn = ConnectionFactory.createConnectionToMysql();

			// criar a classe para execuatr a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

// adicionar os valores para atualizar 

			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getId());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// qual o id do registro que deseja atualizar?
			pstm.setInt(4, contato.getId());

			// executar a query
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();

				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteByID(int id) {
		String sql = "DELETE FROM contatos2 WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstm != null) {
					pstm.close();

				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Contato> getContatos() {
		String sql = "SELECT *FROM contatos2 ";

		List<Contato> contatos = new ArrayList<Contato>();

		Connection conn = null;
		PreparedStatement pstm = null;
		// classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMysql();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato contato = new Contato();

				// recuperar o id
				contato.setId(rset.getInt("id"));
				// recuperar o nome
				contato.setNome(rset.getString("nome"));
				// recuperar a idade
				contato.setId(rset.getInt("idade"));
				// recuperar a data de cadastro
				contato.setDataCadastro(rset.getDate("datacadastro"));

				contatos.add(contato);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rset != null) {
					rset.close();

				}
				if (pstm != null) {
					pstm.close();

				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return contatos;
	}
}
