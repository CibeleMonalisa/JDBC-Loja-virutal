package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Produto;

public class ProdutoDAO {
	private Connection conexao;

	public ProdutoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void salvarProduto(Produto produto) throws SQLException {
		
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

		try (PreparedStatement stm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());

			stm.execute();

			try (ResultSet rst = stm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}

		}
	}
	
	public List<Produto> listarProduto() throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
		
		try(PreparedStatement stm = conexao.prepareStatement(sql)){
			stm.execute();
			
			try(ResultSet rst = stm.getResultSet()) {
				while(rst.next()) {
					Produto produto = new Produto (rst.getInt(1), rst.getString(2), rst.getString(3));
					
					produtos.add(produto);
				}
			}
		}
		return produtos;
	
	}

	public List<Produto> buscar(Categoria lc) throws SQLException {
List<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";
		
		try(PreparedStatement stm = conexao.prepareStatement(sql)){
			stm.setInt(1, lc.getId());
			stm.execute();
			
			try(ResultSet rst = stm.getResultSet()) {
				while(rst.next()) {
					Produto produto = new Produto (rst.getInt(1), rst.getString(2), rst.getString(3));
					
					produtos.add(produto);
				}
			}
		}
		return produtos;
	}
	

}
