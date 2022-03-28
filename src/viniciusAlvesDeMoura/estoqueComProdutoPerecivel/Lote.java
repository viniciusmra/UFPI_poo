package viniciusAlvesDeMoura.estoqueComProdutoPerecivel;
import java.util.Date;

public class Lote {
	private Date validade;
	private int quantidade;
	
	public Lote(int quantidade, Date validade) {
		this.quantidade = quantidade;
		this.validade = validade;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
