// Vínicius Alves de Moura, março de 2022. Programação Orientada a Objeto UFPI


package viniciusAlvesDeMoura.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Date;

public class Estoque {
	Vector<Produto> produtos = new Vector<Produto>();

	public void incluir(Produto p) {
		produtos.add(p);
		
	}
	
	public Produto pesquisar(int cod) {
		for(int i = 0; i < produtos.size(); i++) {
			if(produtos.get(i).getCodigo() == cod) {
				return produtos.get(i);
			}
		}
		return null;
	}
	
	public void comprar(int cod, int quant, double preco, Date validade) {
		Produto p = pesquisar(cod);
		Date muitoTempoDepois = new Date();
		muitoTempoDepois.setYear(muitoTempoDepois.getYear() + 998);
		if(p != null) {
			p.compra(quant, preco);
			
			// Caso o produto seja perecível e o usuário entre com uma vazia (null)
			// é adicionada uma validade de 999 anos a contar de hoje.
			if(p.isPerecivel()) {
				if(validade == null) {
					validade = muitoTempoDepois;
				}
				((ProdutoPerecivel) p).incluirLote(quant, validade);
			}
			
		}
	}
	
	public double vender(int cod, int quant) {
		Produto p = pesquisar(cod);
		
		if(p != null) {
			if(p.isPerecivel()) {
				if((p.getQuantidade() - ((ProdutoPerecivel) p).vencidos()) >= quant){
					((ProdutoPerecivel) p).vendaLote(quant);
					return p.venda(quant);
				}
			}else {
				return p.venda(quant);
			}
		}
		return -1;
	}
	
	public ArrayList<Produto> estoqueAbaixoDoMinimo() {
		ArrayList<Produto> min = new ArrayList<>();
		for(int i = 0; i < produtos.size(); i++) {
			if(produtos.get(i).getQuantidade() < produtos.get(i).getEstoqueMinimo()) {
				min.add(produtos.get(i));
			}
		}
		return min;
	}
	
	// Método que retorna os produtos vencidos
	// o método verifica se o produto é perecível, se não for retorna zero
	// se for, chama o método vencidos() da classe ProdutoPerecível
	public int vencidos(int cod) {
		Produto p = pesquisar(cod);		
		
		if(p.isPerecivel()) {
			return ((ProdutoPerecivel) p).vencidos();
		}else {
			return 0;
		}
	}
}
