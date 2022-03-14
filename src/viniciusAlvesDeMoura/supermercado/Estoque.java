package viniciusAlvesDeMoura.supermercado;
import java.util.ArrayList;
import java.util.Vector;
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
	
	public void comprar(int cod, int quant, double preco) {
		Produto p = pesquisar(cod);
		if(p != null) {
			p.compra(quant, preco);
		}
	}
	
	public double vender(int cod, int quant) {
		Produto p = pesquisar(cod);
		if(p != null) {
			return p.venda(quant);
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
}
