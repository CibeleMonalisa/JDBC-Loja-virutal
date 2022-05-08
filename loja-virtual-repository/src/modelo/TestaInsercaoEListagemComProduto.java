package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ProdutoDAO;
import javaJDBC.ConnectionFactory;

public class TestaInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {

		Produto p1 = new Produto("Cômoda", "feito em madeira");

		try(Connection conexao = new ConnectionFactory().recuperarConexao()) {
			ProdutoDAO produtoDao = new ProdutoDAO(conexao);
			produtoDao.salvarProduto(p1);
			
			List<Produto> listaDeProdutos = produtoDao.listarProduto();
			listaDeProdutos.stream().forEach(lp -> System.out.println(lp));

		}

	}

}
