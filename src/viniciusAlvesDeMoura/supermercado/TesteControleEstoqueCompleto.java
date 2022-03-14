package viniciusAlvesDeMoura.supermercado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TesteControleEstoqueCompleto {
	
	@Test
	public void testarCadastroProduto(){
	  Fornecedor f1 = new Fornecedor(1, "Forn1");
	  Produto p1 = new Produto(1, "coca-cola", 5, 0.20, f1);
	  
	  Estoque est = new Estoque();
	  
	  est.incluir(p1);
	  
	  Produto outro = est.pesquisar(1);
	  assertEquals(1, outro.getCodigo());
	  assertEquals("coca-cola", outro.getDescricao());
	  assertEquals(5, outro.getEstoqueMinimo());
	  assertEquals(0, outro.getPrecoDeVenda(), 0.001);
	  assertEquals(0, outro.getPrecoDeCompra(), 0.001);
	  assertEquals(0, outro.getQuantidade(), 0.001);
	  assertEquals(0.2, outro.getLucro(), 0.001);

	  Produto p2 = new Produto(2, "Sabão Omo", 10, 0.5, f1);	  	  
	  est.incluir(p2);
	  
	  outro = est.pesquisar(2);
	  assertEquals(2, outro.getCodigo());
	  assertEquals("Sabão Omo", outro.getDescricao());
	  assertEquals(10, outro.getEstoqueMinimo());
	  assertEquals(0, outro.getPrecoDeCompra(), 0.001);
	  assertEquals(0, outro.getPrecoDeVenda(), 0.001);
	  assertEquals(0, outro.getQuantidade(), 0.001);
	  assertEquals(0.5, outro.getLucro(), 0.001);
	  
	  //meus testes:
	  Produto p = est.pesquisar(25);
	  assertEquals(null, p);
	  

	}
	
	@Test
	public void testarVenda(){
	  Fornecedor f1 = new Fornecedor(1, "Forn1");
	  Produto p1 = new Produto(1, "coca-cola", 5, 0.5, f1);
	  
	  Estoque est = new Estoque();
	  
	  est.incluir(p1);
	  est.comprar(1, 10, 2.0);
	  double val = est.vender(1, 5);
	  
	  Produto outro = est.pesquisar(1);
	  assertEquals(1, outro.getCodigo());
	  assertEquals(5, outro.getQuantidade(), 0.001);
	  assertEquals(15, val, 0.001);
	}
	
	@Test
	public void testarAtualizacaoPrecoDeCompra(){
	  Fornecedor f1 = new Fornecedor(1, "Forn1");
	  Produto p1 = new Produto(1, "coca-cola", 5, 0.5, f1);
	  
	  Estoque est = new Estoque();
	  
	  est.incluir(p1);
	  est.comprar(1, 10, 2);	  
	  Produto outro = est.pesquisar(1);
	  assertEquals(1, outro.getCodigo());
	  assertEquals(10, outro.getQuantidade(), 0.001);
	  assertEquals(2, outro.getPrecoDeCompra(), 0.001);
	  assertEquals(3.0, outro.getPrecoDeVenda(), 0.001);
	  est.comprar(1, 10, 4);	 
	  assertEquals(4.5, outro.getPrecoDeVenda(), 0.001);
	}
	
	
	@Test
	public void testarEstoqueMinimo(){
	  Fornecedor f1 = new Fornecedor(1, "Forn1");
	  Produto p1 = new Produto(1, "coca-cola", 100, 3.5, f1);
	  Produto p2 = new Produto(2, "omo", 200, 3.5, f1);
	  Produto p3 = new Produto(3, "papel", 300, 3.5, f1);
	  Produto p4 = new Produto(4, "sabonete", 400, 3.5, f1);
	  Produto p5 = new Produto(5, "carne", 500, 3.5, f1);
	  
	  Estoque est = new Estoque();
	  
	  est.incluir(p1);
	  est.comprar(1, 1, 2.5);
	  est.incluir(p2);
	  est.comprar(2, 1, 2.5);
	  est.incluir(p3);
	  est.comprar(3, 1, 2.5);
	  est.incluir(p4);
	  est.comprar(4, 400, 2.5);
	  est.incluir(p5);
	  est.comprar(5, 500, 2.5);
	  
	  ArrayList<Produto> minimos = est.estoqueAbaixoDoMinimo();
	  for (Produto prod : minimos) {
		if (prod != null) {
		  assertTrue(prod.getQuantidade() < prod.getEstoqueMinimo());
		}
	  }
	  assertEquals(3, minimos.size());
	}
}
