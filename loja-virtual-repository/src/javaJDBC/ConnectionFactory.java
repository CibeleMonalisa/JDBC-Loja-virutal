package javaJDBC;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

//essa classe cria a conexao com pool de conexões para abrir varias conexoes para varios usuarios sem sobrecarregar o sistema
//pois tem um limite

public class ConnectionFactory {

	public DataSource dataSource;

	// abrindo uma pool de conexoes para evitar sobrecarregar o banco de dados
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("");
		
		comboPooledDataSource.setMaxPoolSize(15); //aceita 15 conexões abertas no máximo
		//pesquisar como fazer uma pool de conexões dinamica
		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexao() throws SQLException {
		return this.dataSource.getConnection();

	}
}
