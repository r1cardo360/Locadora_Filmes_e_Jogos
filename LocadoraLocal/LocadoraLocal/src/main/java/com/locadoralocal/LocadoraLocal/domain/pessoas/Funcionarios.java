package com.locadoralocal.LocadoraLocal.domain.pessoas;

import com.locadoralocal.LocadoraLocal.RegraDeNegocioException;

import java.util.Scanner;

public class Funcionarios extends Pessoas {

	private String funcaoFuncionario;
	Scanner teclado = new Scanner(System.in);

	public Funcionarios() {
		super();
	}

	public Funcionarios(String nome, String cpf, int anoNascimento, char sexo, boolean ativo, String funcaoFuncionario) {
		super(nome, cpf, anoNascimento, sexo, ativo);
		this.funcaoFuncionario = funcaoFuncionario;
	}

	@Override
	public void apagar() {
		super.apagar();
		int opcao;

		do {
			System.out.println("=============================================");
			System.out.println("======== Apagar Produto ou Pessoa? ==========");
			System.out.println("======== 1- Produto ====== 2- Pessoa ========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();
			try {
				switch (opcao) {
					case 1:
						apagarProduto();
						break;
					case 2:
						apagarPessoa();
						break;
				}
			} catch (RegraDeNegocioException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		} while (opcao == 0);
	}

	private void apagarPessoa() {
	}

	private void apagarProduto() {
	}

	@Override
	public void adicionarProdutoPessoa() {
		super.adicionarProdutoPessoa();


		int opcao;
		do {
			System.out.println("=============================================");
			System.out.println("======= Adicionar Produto ou Pessoa? ========");
			System.out.println("======== 1- Produto ====== 2- Pessoa ========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();
			try {
				switch (opcao) {
					case 1:
						addProduto();
						break;
					case 2:
						addPessoa();
						break;
				}
			} catch (RegraDeNegocioException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		} while (opcao == 0);
	}

	@Override
	public void ativar() {
		super.ativar();
		int opcao;

		do {
			System.out.println("=============================================");
			System.out.println("========= Ativar Pessoa ou Produto? =========");
			System.out.println("======== 1- Produto ====== 2- Pessoa ========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");

			opcao = teclado.nextInt();
			try {
				switch (opcao) {
					case 1:
						ativarProduto();
						break;
					case 2:
						ativarPessoa();
						break;
				}
			} catch (RegraDeNegocioException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		} while (opcao == 0);
	}

	@Override
	public void inativar() {
		super.inativar();
		int opcao;

		do {
			System.out.println("=============================================");
			System.out.println("======== Inativar Pessoa ou Produto? ========");
			System.out.println("======== 1- Produto ====== 2- Pessoa ========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");

			opcao = teclado.nextInt();

			try {
				switch (opcao) {
					case 1:
						inativarProduto();
						break;
					case 2:
						inativarPessoa();
						break;
				}
			} catch (RegraDeNegocioException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}while (opcao == 0);
	}

	@Override
	public void listar() {
		super.listar();
		int opcao;

		do{
		System.out.println("=============================================");
		System.out.println("========= Listar Pessoa ou Produto? =========");
		System.out.println("======== 1- Produto ====== 2- Pessoa ========");
		System.out.println("=============================================");

		opcao = teclado.nextInt();
			try{
				switch (opcao){
					case 1:
						listarProduto();
						break;
					case 2:
						listarPessoa();
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}while (opcao == 0);
	}

	public void devolucao(){

		int opcao;
		do{
			System.out.println("=============================================");
			System.out.println("======== Insira o numero da locação: ========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();

		}while(opcao == 0);
	}
}
