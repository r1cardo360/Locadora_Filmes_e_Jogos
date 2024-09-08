package com.locadoralocal.LocadoraLocal.domain.pessoas;

public class Pessoas {

	private String nome;
	private String cpf;
	private int anoNascimento;
	private char sexo;
	private boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Pessoas(){

    }

    public Pessoas(String nome, String cpf, int anoNascimento, char sexo, boolean ativo){
		this.nome = nome;
		this.cpf = cpf;
		this.anoNascimento = anoNascimento;
		this.sexo = sexo;
		this.ativo = ativo;
	}

	public void adicionarProdutoPessoa() {

	}

	public void listar() {

	}

	public void ativar() {

	}

	public void inativar() {

	}

	public void apagar() {

	}
}
