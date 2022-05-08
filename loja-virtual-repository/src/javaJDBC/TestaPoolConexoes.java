package javaJDBC;
import java.sql.SQLException;

public class TestaPoolConexoes {
//testando a pool de conexão	
	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();

		for (int i = 0; i < 20; i++) {
			connectionFactory.recuperarConexao();
			System.out.println("Conexão de número: " + i);
		}

	}

}
