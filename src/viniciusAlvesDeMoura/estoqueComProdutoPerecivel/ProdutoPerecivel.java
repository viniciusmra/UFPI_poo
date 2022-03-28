package viniciusAlvesDeMoura.estoqueComProdutoPerecivel;

import java.util.Date;
import java.util.Vector;

public class ProdutoPerecivel extends Produto{
	Vector <Lote> lotes = new Vector<Lote>();
	
	public ProdutoPerecivel(int cod, String desc, int min, double lucro, Fornecedor forn) {
		super(cod, desc,min,lucro,forn);
		setPerecivel(true);
	}
	
	public void incluirLote(int quantidade, Date validade) {
		Lote lt = new Lote(quantidade, validade);
		lotes.add(lt);
	}
	
	// Retorna o número de produtos vencidos
	public int vencidos() {
		int quantidade = 0;
		Date hoje = new Date();
		for(int i = 0; i < lotes.size(); i++) {
			if(lotes.get(i).getValidade().before(hoje)) {
				quantidade += lotes.get(i).getQuantidade();
			}
		}
		return quantidade;
	}

	// Método para decrementar o lote vendido do Vetor de lotes de um determinado produtoPerecível
	// o método opta por vender primeiro os produtos máis próximos de vencer.
	public void vendaLote(int quantidade) {
		int aux = 0;
		Date hoje = new Date();
		Date muitoTempoDepois = new Date();
		muitoTempoDepois.setYear(hoje.getYear() + 999);
		Lote proxVencimento;
		
		while(quantidade > 0) {
			proxVencimento = new Lote(0, muitoTempoDepois);
			for(int i = 0; i < lotes.size(); i++) {
				if(lotes.get(i).getValidade() != null) {
					if(lotes.get(i).getValidade().after(hoje) && lotes.get(i).getQuantidade() > 0 && lotes.get(i).getValidade().before(proxVencimento.getValidade())) {
						proxVencimento = lotes.get(i);
					}
				}
			}
		
			aux = proxVencimento.getQuantidade();
			if(quantidade >= aux) {
				proxVencimento.setQuantidade(0);
				quantidade = quantidade - aux;
			}else {
				proxVencimento.setQuantidade(aux-quantidade);
				quantidade = 0;
			}
		}
	}
	
	// Função extra (que não foi pedida na atividade) que imprime todos os lotes de um determinado produto
	public void mostrarLotes() {
		for(int i = 0; i < lotes.size(); i++) {
			System.out.println(lotes.get(i).getQuantidade() + " - " + lotes.get(i).getValidade());
		}
	}
	
	public Vector<Lote> getLotes() {
		return lotes;
	}	
}
