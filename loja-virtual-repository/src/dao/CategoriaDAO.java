package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Produto;

public class CategoriaDAO {

	private Connection conexao;

	public CategoriaDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public List<Categoria> listarCategoria() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();

		String sql = "SELECT ID, NOME FROM CATEGORIA";

		try (PreparedStatement stm = conexao.prepareStatement(sql)) {
			stm.execute();

			try (ResultSet rst = stm.getResultSet()) {
				while (rst.next()) {
					Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));

					categorias.add(categoria);
				}
			}
		}
		return categorias;

	}

	public List<Categoria> listarCategoriaComProduto() throws SQLException {
		Categoria ultima  = null; 
		List<Categoria> categorias = new ArrayList<>();

		String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN"
				+ " PRODUTO P ON C.ID = P.CATEGORIA_ID";

		try (PreparedStatement stm = conexao.prepareStatement(sql)) {
			stm.execute();

			try (ResultSet rst = stm.getResultSet()) {
				while (rst.next()) {
					if(ultima == null || !ultima.getNome().equals(rst.getString(2))) {
						Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
								ultima = categoria;
								categorias.add(categoria);		
					}
				}
				Produto	produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
				ultima.adicionar(produto);
			}
		}
		return categorias;

	}

}
