package com.locadoralocal.LocadoraLocal.domain.pessoas;

public class Dependente extends Clientes {

    private int grauParentesco;

    public Dependente(String nome, String cpf, int anoNascimento, char sexo, boolean ativo, int grauParentesco) {
        super(nome, cpf, anoNascimento, sexo, ativo);
        this.grauParentesco = grauParentesco;
    }

}
