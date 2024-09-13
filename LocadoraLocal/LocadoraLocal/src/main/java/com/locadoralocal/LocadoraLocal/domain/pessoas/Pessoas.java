package com.locadoralocal.LocadoraLocal.domain.pessoas;

public class Pessoas {

    private String nome;
    private String cpf;
    private int anoNascimento;
    private String sexo;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Pessoas() {

    }

    public Pessoas(String nome, String cpf, int anoNascimento, String sexo, boolean ativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.anoNascimento = anoNascimento;
        this.sexo = sexo;
        this.ativo = ativo;
    }

    public void adicionar() {

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
