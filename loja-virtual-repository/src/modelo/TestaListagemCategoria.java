package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.CategoriaDAO;
import javaJDBC.ConnectionFactory;

public class TestaListagemCategoria {

	public static void main(String[] args) throws SQLException {

		try (Connection conexao = new ConnectionFactory().recuperarConexao()) {
			CategoriaDAO categoriaDao = new CategoriaDAO(conexao);
			List<Categoria> listaDeCategorias = categoriaDao.listarCategoriaComProduto();
			listaDeCategorias.stream().forEach(lc -> {
				System.out.println(lc.getNome());
				for (Produto produto : lc.getProdutos()) {
					System.out.println(lc.getNome() + " - " + produto.getNome());
				}
			});
		}

	}

}
