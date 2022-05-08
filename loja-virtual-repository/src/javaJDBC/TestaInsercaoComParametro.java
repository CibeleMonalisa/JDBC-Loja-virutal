package javaJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conexao = connectionFactory.recuperarConexao();) {

			conexao.setAutoCommit(false); // o jdbc deixa de assumir o controle das transações
			// colocando o preparedstatement no parenteses do try, nao preciso fechar
			// manualmente a conexao
			try (PreparedStatement stm = conexao.prepareStatement(
					"INSERT INTO PRODUTO (nome, descricao) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);) {

				adicionarVariavel("Smart TV", "45 polegadas", stm);
				adicionarVariavel("Radio", "Radio rosa", stm);

				conexao.commit();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Rollback Executado");
				conexao.rollback(); // para reverter a ação
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		// setar naquela primeira interrogação o nome
		stm.setString(1, nome);
		// setar naquela segunda interrogação a descrição
		stm.setString(2, descricao);

		stm.execute();

		try (ResultSet rst = stm.getGeneratedKeys();) {

			while (rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O id criado foi:" + id);
			}
		}
	}

}
