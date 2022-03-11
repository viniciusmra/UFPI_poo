package viniciusAlvesDeMoura.supermercado;

public class Produto {
	int cod;
	String desc;
	double precoDeCompra;
	double precoDeVenda;
	double lucro;
	int quant;
	int min;
	Fornecedor forn;
	
	public Produto(int cod, String desc, int min, double lucro, Fornecedor forn) {
		this.cod = cod;
		this.desc = desc;
		this.min = min;
		this.lucro = lucro;
		this.forn = forn;
	}
	
	void compra() {
		
	}
}
