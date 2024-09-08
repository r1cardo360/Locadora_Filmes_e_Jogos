package src.model;

public class Produto {
	
	private String nome;
	private int classificacao;
	private int anoLancamento;
	private String active; 
	
	public Produto(String nome_produto, int classificacaoProduto, int anoLancamentoProduto, String activeProduto) {
		this.nome = nome_produto;
		this.classificacao = classificacaoProduto;
		this.anoLancamento = anoLancamentoProduto;
		this.active = activeProduto;
	}
	
	public String getNomeProduto() {
		return nome;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nome = nomeProduto;
	}
	
	public int getClassificacaoProduto() {
		return classificacao;
	}
	public void setClassificacaoProduto(int classificacaoProduto) {
		this.classificacao = classificacaoProduto;
	}
	
	public int getAnoLancamentoProduto() {
		return anoLancamento;
	}
	public void setAnoLancamentoProduto(int anoLancamentoProduto) {
		this.anoLancamento = anoLancamentoProduto;
	}
	
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	
}
