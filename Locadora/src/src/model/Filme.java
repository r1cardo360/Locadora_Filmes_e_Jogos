package src.model;

public class Filme extends Produto {
	
	private int nota;
	
	public Filme(String nomeFilme, int classificacaoFilme, int anoLancamentoFilme, String ativoFilme, int notaFilme) {
		super(nomeFilme, classificacaoFilme, anoLancamentoFilme, ativoFilme);
		this.setNota(notaFilme);
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
}
