package viniciusAlvesDeMoura.estoqueComProdutoPerecivel;

import java.util.Vector;

public class Produto {
	private int codigo;
	private String descricao;
	private double precoDeCompra;
	private double precoDeVenda;
	private double lucro;
	private int quantidade;
	private int estoqueMinimo;
	private Fornecedor forn;
	private boolean perecivel = false;
	
	
	public Produto(int cod, String desc, int min, double lucro, Fornecedor forn) {
		this.codigo = cod;
		this.descricao = desc;
		this.estoqueMinimo = min;
		this.lucro = lucro;
		this.forn = forn;
	}
	
	void compra(int quant, double val) {
		this.precoDeCompra = (this.quantidade*this.precoDeCompra + quant*val)/(this.quantidade + quant);
		this.quantidade += quant;
		this.precoDeVenda = this.precoDeCompra + this.precoDeCompra*this.lucro;
	}
	
	double venda(int quant) {
		if(quant <= this.quantidade) {
			this.quantidade -= quant;
			return quant*this.precoDeVenda;
		} else {
			return -1;
		}
	}
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String desc) {
		this.descricao = desc;
	}

	public double getPrecoDeCompra() {
		return precoDeCompra;
	}

	public void setPrecoDeCompra(double precoDeCompra) {
		this.precoDeCompra = precoDeCompra;
	}

	public double getPrecoDeVenda() {
		return precoDeVenda;
	}

	public void setPrecoDeVenda(double precoDeVenda) {
		this.precoDeVenda = precoDeVenda;
	}

	public double getLucro() {
		return lucro;
	}

	public void setLucro(double lucro) {
		this.lucro = lucro;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setquantidade(int quant) {
		this.quantidade = quant;
	}

	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(int min) {
		this.estoqueMinimo = min;
	}

	public Fornecedor getForn() {
		return forn;
	}

	public void setForn(Fornecedor forn) {
		this.forn = forn;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void setPerecivel(boolean b) {
		perecivel = b;
	}
	public boolean isPerecivel() {
		return perecivel;
	}
}
