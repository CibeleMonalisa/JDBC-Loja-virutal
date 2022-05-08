package javaJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conexao = connectionFactory.recuperarConexao();

		PreparedStatement stm = conexao.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
		// retorna true quando o resultado do select for uma lista
		// quando não retornar nada, ele retorna false (insert, update etc)
		stm.execute();
		// pegar essa lista gerada com o select:
		ResultSet rst = stm.getResultSet();
		// pegando produtos da lista:
		while (rst.next()) {
			Integer id = rst.getInt("ID");
			System.out.println(id);
			String nome = rst.getString("NOME");
			System.out.println(nome);
			String descricao = rst.getString("DESCRICAO");
			System.out.println(descricao);
		}

		rst.close();
		stm.close();
		conexao.close();

	}

}
