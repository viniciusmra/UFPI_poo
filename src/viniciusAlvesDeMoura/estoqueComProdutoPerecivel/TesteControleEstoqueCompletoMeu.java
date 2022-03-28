package viniciusAlvesDeMoura.estoqueComProdutoPerecivel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class TesteControleEstoqueCompletoMeu {
	@Test
	public void teste_retorn() {
		  Fornecedor forn = new Fornecedor(1, "Forn1");
		  Estoque estoque = new Estoque();
		  
		  
		  Produto prod1 = new Produto(40, "Alguma_Coisa", 2, 1.0, forn);
		  estoque.incluir(prod1);
		  estoque.comprar(40, -5, 50.0, null);
		  double val = estoque.vender(40, 5);
		  
		  //Teste pra v� compra negativa e testar o retorno de -1 na venda
		  Produto z = estoque.pesquisar(40);
		  assertEquals(-5, z.getQuantidade());
		  assertEquals(-1, val, 0.001);
		  
		  //Testar o pesquisar
		  Produto x = estoque.pesquisar(100);
		  assertEquals(null, x);
	}
	
	@Test
	public void testeCompraCom_e_SemMes() {	  
		  Fornecedor f1 = new Fornecedor(1, "Forn1");
		  Produto p1 = new ProdutoPerecivel(1, "coca-cola", 5, 0.5, f1);
		  
		  Estoque est = new Estoque();
		  
		  Date hoje = new Date();
		  Date mes1 = new Date();
		  mes1.setMonth(hoje.getMonth() + 1);
		  Date mes2 = new Date();
		  mes2.setMonth(hoje.getMonth() + 2);
		  
		  est.incluir(p1);
		  est.comprar(1, 10, 2, mes1);	  
		  Produto outro = est.pesquisar(1);
		  assertEquals(1, outro.getCodigo());
		  assertEquals(10, outro.getQuantidade(), 0.001);
		  assertEquals(2, outro.getPrecoDeCompra(), 0.001);
		  assertEquals(3.0, outro.getPrecoDeVenda(), 0.001);
		  
		  est.comprar(1, 10, 4, null);	
		  
		  assertEquals(4.5, outro.getPrecoDeVenda(), 0.001);
		  assertEquals(20, outro.getQuantidade(), 0.001);
		  
		  est.vender(1, 8);
		  assertEquals(12, outro.getQuantidade(), 0.001);
		  
		  Date mes3 = new Date();
		  mes3.setMonth(hoje.getMonth() - 3);
		  est.comprar(1, 5, 3.0, mes3);
		  
		  assertEquals(5, est.vencidos(1));
		  assertEquals(17, outro.getQuantidade(), 0.001);
		  assertEquals(4.5, outro.getPrecoDeVenda(), 0.001);
		  
		  est.vender(1, 10);
		  assertEquals(17, outro.getQuantidade());
		  assertEquals(5, est.vencidos(1));
	}
	
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

	}
	
	@Test
	public void testarVenda(){
	  Fornecedor f1 = new Fornecedor(1, "Forn1");
	  Produto p1 = new Produto(1, "coca-cola", 5, 0.5, f1);
	  
	  Estoque est = new Estoque();
	  
	  est.incluir(p1);
	  est.comprar(1, 10, 2.0, null);
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
	  est.comprar(1, 10, 2, null);	  
	  Produto outro = est.pesquisar(1);
	  assertEquals(1, outro.getCodigo());
	  assertEquals(10, outro.getQuantidade(), 0.001);
	  assertEquals(2, outro.getPrecoDeCompra(), 0.001);
	  assertEquals(3.0, outro.getPrecoDeVenda(), 0.001);
	  est.comprar(1, 10, 4, null);	 
	  assertEquals(4.5, outro.getPrecoDeVenda(), 0.001);
	}
	
	@Test
	public void testarCompraProdutosPereciveis(){
	  Fornecedor f1 = new Fornecedor(1, "Forn1");
	  Produto p1 = new Produto(1, "coca-cola", 5, 0.5, f1);
	  Produto p2 = new ProdutoPerecivel(2, "Arroz Maria", 10, 0.2, f1);
	  
	  Estoque est = new Estoque();
	  Date hoje = new Date();
	  
	  Date mes1 = new Date();
	  mes1.setMonth(hoje.getMonth() + 1);
	  
	  Date mes2 = new Date();
	  mes2.setMonth(hoje.getMonth() + 2);

	  est.incluir(p1);
	  est.incluir(p2);

	  est.comprar(1, 10, 2.0, null);
	  Produto outro = est.pesquisar(1);
	  assertEquals(1, outro.getCodigo());
	  assertEquals(10, outro.getQuantidade(), 0.001);
	  assertEquals(2, outro.getPrecoDeCompra(), 0.001);
	  assertEquals(3.0, outro.getPrecoDeVenda(), 0.001);
	  est.comprar(1, 10, 4, null);	 
	  assertEquals(4.5, outro.getPrecoDeVenda(), 0.001);
	  
	  
	  est.incluir(p2);
	  est.comprar(2, 10, 2, mes1);	  
	  est.comprar(2, 15, 4, mes2);	  
	  est.comprar(2, 25, 6, mes2);	  
	  Produto maisUm = est.pesquisar(2);
	  assertEquals(2, maisUm.getCodigo());
	  assertEquals(50, maisUm.getQuantidade(), 0.001);
	  assertEquals(4.6, maisUm.getPrecoDeCompra(), 0.001);
	  assertEquals(5.52, maisUm.getPrecoDeVenda(), 0.001);

	}

	@Test
	public void testarVendaProdutosPereciveis(){
	  Fornecedor f1 = new Fornecedor(1, "Forn1");
	  Produto p2 = new ProdutoPerecivel(2, "Arroz Maria", 10, 0.2, f1);
	  
	  Estoque est = new Estoque();
	  Date hoje = new Date();
	  Date mes1 = new Date();
	  mes1.setMonth(hoje.getMonth() + 1);
	  Date mes2 = new Date();
	  mes2.setMonth(hoje.getMonth() + 2);
	  Date mes3 = new Date();
	  mes3.setMonth(hoje.getMonth() + 3);

	  est.incluir(p2);

	  //Compra de 3 lotes de produtos com vencimentos diferentes
	  est.comprar(2, 10, 2, mes1);	  
	  est.comprar(2, 15, 4, mes2);	  
	  est.comprar(2, 25, 6, mes3);	  
	  Produto maisUm = est.pesquisar(2);
	  assertEquals(50, maisUm.getQuantidade());
	  assertEquals(4.6, maisUm.getPrecoDeCompra(), 0.001);
	  assertEquals(5.52, maisUm.getPrecoDeVenda(), 0.001);

	  // Deveria vender 10 do lote com vencimento no mes1 e 5 do mes2
	  double val = est.vender(2, 15);
	  assertEquals(35, maisUm.getQuantidade());
	  assertEquals(82.8, val, 0.001);
	  
	  // Deveria vender 5 do lote com vencimento no mes2 e 15 do mes3
	  val = est.vender(2, 20);
	  assertEquals(15, maisUm.getQuantidade());
	  assertEquals(110.4, val, 0.001);
	  
	  // Deveria vender 5 do lote com vencimento no mes2 e 15 do mes3
	  val = est.vender(2, 10);
	  assertEquals(5, maisUm.getQuantidade());
	  assertEquals(55.20, val, 0.001);	  

	  // Deveria não vender
	  val = est.vender(2, 20);
	  assertEquals(5, maisUm.getQuantidade());
	  assertEquals(-1, val, 0.001);	  
    }
	
	@Test
	public void testarVendaProdutosPereciveisInvalido(){
	  Fornecedor f1 = new Fornecedor(1, "Forn1");
	  Produto p2 = new ProdutoPerecivel(2, "Arroz Maria", 10, 0.2, f1);
	  
	  Estoque est = new Estoque();
	  Date hoje = new Date();
	  Date mes1 = new Date();
	  mes1.setMonth(hoje.getMonth() + 1);
	  Date mes2 = new Date();
	  mes2.setMonth(hoje.getMonth() - 2);
	  Date mes3 = new Date();
	  mes3.setMonth(hoje.getMonth() - 3);

	  est.incluir(p2);

	  // Compra de um lote de produtos com validade ok
	  est.comprar(2, 10, 2, mes1);	  
	  
	  // Compra de 2 lotes de produtos vencidos
	  est.comprar(2, 15, 4, mes2);	  
	  est.comprar(2, 25, 6, mes3);	  
	  
	  Produto maisUm = est.pesquisar(2);
	  assertEquals(50, maisUm.getQuantidade());
	  assertEquals(4.6, maisUm.getPrecoDeCompra(), 0.001);
	  assertEquals(5.52, maisUm.getPrecoDeVenda(), 0.001);

	  // Deveria vender 10 do lote com vencimento no mes1
	  double val = est.vender(2, 10);
	  assertEquals(40, maisUm.getQuantidade());
	  assertEquals(55.2, val, 0.001);
	  
	  // Não Deveria vender pois o resto é vencido
	  val = est.vender(2, 20);
	  assertEquals(40, maisUm.getQuantidade());
	  assertEquals(-1, val, 0.001);
	  
	  // Deveria informar a quantidade de itens vencidos
	  assertEquals(40, est.vencidos(2));
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
	  est.comprar(1, 1, 2.5, null);
	  est.incluir(p2);
	  est.comprar(2, 1, 2.5, null);
	  est.incluir(p3);
	  est.comprar(3, 1, 2.5, null);
	  est.incluir(p4);
	  est.comprar(4, 400, 2.5, null);
	  est.incluir(p5);
	  est.comprar(5, 500, 2.5, null);
	  
	  ArrayList<Produto> minimos = est.estoqueAbaixoDoMinimo();
	  for (Produto prod : minimos) {
		if (prod != null) {
		  assertTrue(prod.getQuantidade() < prod.getEstoqueMinimo());
		}
	  }
	  assertEquals(3, minimos.size());
	}
	
}
