package com.locadoralocal.LocadoraLocal.domain.pessoas;

import com.locadoralocal.LocadoraLocal.RegraDeNegocioException;

import java.util.Scanner;

public class Funcionarios extends Pessoas {

	Pessoas pessoas = new Pessoas();

	private String funcaoFuncionario;
	Scanner teclado = new Scanner(System.in);

	public Funcionarios() {
		super();
	}

	public Funcionarios(String nome, String cpf, int anoNascimento, String sexo, boolean ativo, String funcaoFuncionario) {
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

	@Override
	public void adicionar() {
		super.adicionar();

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
		System.out.println("============== 0- Para sair =================");
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

	private void apagarPessoa() {
		int opcao;
		do{
			System.out.println("=============================================");
			System.out.println("========== Insira o id da Pessoa: ===========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();
			System.out.println("=============================================");
			System.out.println("===== Essa ação pode causar alguns erros ====");
			System.out.println("==== no banco de dados, deseja continuar? ===");
			System.out.println("========== S- Sim ========= N- Não ========== ");
			System.out.println("=============================================");

		}while(opcao == 0);
	}

	private void addPessoa() {
		System.out.println("=============================================");
		System.out.println("========= Insira o Nome da Pessoa: ==========");
		System.out.println("=============================================");
		String nome = teclado.nextLine();
		pessoas.setNome(nome);

		System.out.println("=============================================");
		System.out.println("========= Insira o CPF da Pessoa: ===========");
		System.out.println("=============================================");
		String cpf = teclado.nextLine();
		pessoas.setCpf(cpf);

		System.out.println("=============================================");
		System.out.println("====== Insira o Nascimento da Pessoa: =======");
		System.out.println("=============================================");
		int anoNascimento = teclado.nextInt();
		pessoas.setAnoNascimento(anoNascimento);

		String sexo;
		do {
			System.out.println("=============================================");
			System.out.println("========= Insira o Sexo da Pessoa: ==========");
			System.out.println("====== M- Masculino ====== F- Feminino ======");
			System.out.println("=============================================");
			sexo = teclado.nextLine().toLowerCase();
			if (sexo.equals("m") || sexo.equals("f")) {
				pessoas.setSexo(sexo);
				break;
			} else {
				System.out.println("=============================================");
				System.out.println("========== Insira apenas 'M' ou 'F' =========");
				System.out.println("=============================================");
			}
		} while (true);
		String tipo;
		do {
			System.out.println("=============================================");
			System.out.println("============== Insira o tipo: ===============");
			System.out.println("====== C- Cliente ===== F- Funcionário ======");
			System.out.println("=============================================");
			tipo = teclado.nextLine().toLowerCase();
			try{
				switch (tipo){
					case "c":
						System.out.println("=============================================");
						System.out.println("====== Cliente cadastrado com sucesso! ======");
						System.out.println("=============================================");
						break;
					case "F":
						System.out.println("=============================================");
						System.out.println("====== Insira a função do Funcionario: ======");
						System.out.println("=============================================");
						String funcao = teclado.nextLine();
						System.out.println("=============================================");
						System.out.println("==== Funcionário cadastrado com sucesso! ====");
						System.out.println("=============================================");
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}


		}while (true);
	}

	private void ativarPessoa() {
		int opcao;
		do{
			System.out.println("=============================================");
			System.out.println("========== Insira o id da Pessoa: ===========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();
			System.out.println("=============================================");
			System.out.println("======== Pessoa Ativada com sucesso!=========");
			System.out.println("=============================================");

		}while(opcao == 0);
	}

	private void inativarPessoa() {
		int opcao;
		do{
			System.out.println("=============================================");
			System.out.println("========== Insira o id da Pessoa: ===========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();
			System.out.println("=============================================");
			System.out.println("======= Pessoa Inativada com sucesso!========");
			System.out.println("=============================================");

		}while(opcao == 0);
	}

	private void listarPessoa() {
		int opcao;
		do{
			System.out.println("=============================================");
			System.out.println("=== Deseja listar todos ou apenas ativos? ===");
			System.out.println("=============================================");
			System.out.println("======== 1- Todos ======== 2- Ativos ========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();
			try{
				switch (opcao){
					case 1:
						System.out.println("Query para listar todos as pessoas");
						break;
					case 2:
						System.out.println("Query para listar apenas ativo = true");
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}while (opcao == 0);
	}

	//Métodos incompletos
	private void apagarProduto() {
		int opcao;
		do{
			System.out.println("=============================================");
			System.out.println("========== Insira o id do Produto: ==========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();

		}while(opcao == 0);
	}

	private void addProduto() {
	}

	private void ativarProduto() {
	}

	private void inativarProduto() {
	}

	private void listarProduto() {
	}


}
