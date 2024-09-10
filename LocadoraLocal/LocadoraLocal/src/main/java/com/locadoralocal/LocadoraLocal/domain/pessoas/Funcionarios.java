package com.locadoralocal.LocadoraLocal.domain.pessoas;

import com.locadoralocal.LocadoraLocal.RegraDeNegocioException;
import com.locadoralocal.LocadoraLocal.domain.produtos.Produto;

import java.util.Scanner;

public class Funcionarios extends Pessoas {

	Pessoas pessoas = new Pessoas();
	Produto produto = new Produto();

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
			System.out.println("=============================================");
			System.out.println("========== Insira o id da Pessoa: ===========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			int id = teclado.nextInt();
			System.out.println("=============================================");
			System.out.println("======== Pessoa Ativada com sucesso!=========");
			System.out.println("=============================================");
	}

	private void inativarPessoa() {
			System.out.println("=============================================");
			System.out.println("========== Insira o id da Pessoa: ===========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			int id = teclado.nextInt();
			System.out.println("=============================================");
			System.out.println("======= Pessoa Inativada com sucesso!========");
			System.out.println("=============================================");
	}

	public void listarPessoa() {
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

	private void apagarProduto() {
		int opcao;
		do{
			System.out.println("=============================================");
			System.out.println("========== Insira o id do Produto: ==========");
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

	private void addProduto() {

		System.out.println("=============================================");
		System.out.println("========= Insira o nome do Produto: =========");
		System.out.println("=============================================");
		String nome = teclado.nextLine();
		produto.setNome(nome);
		System.out.println("=============================================");
		System.out.println("==== Insira a classificação do Produto: =====");
		System.out.println("=============================================");
		int classificacao = teclado.nextInt();
		produto.setClassificacao(classificacao);
		System.out.println("=============================================");
		System.out.println("== Insira o ano de lançamento do Produto: ===");
		System.out.println("=============================================");
		int anoLancamento = teclado.nextInt();
		produto.setAnoLancamento(anoLancamento);
		System.out.println("=============================================");
		System.out.println("======== Insira o gênero do Produto: ========");
		System.out.println("=============================================");
		String genero = teclado.nextLine();
		produto.setGenero(genero);
		String tipo;
		do {
			System.out.println("=============================================");
			System.out.println("======== Insira o tipo do protudo: ==========");
			System.out.println("========== F- Filme ====== J- Jogo ==========");
			System.out.println("=============================================");
			tipo = teclado.nextLine().toLowerCase();
			try{
				switch (tipo){
					case "j":
						System.out.println("=============================================");
						System.out.println("======= Jogo cadastrado com sucesso! ========");
						System.out.println("=============================================");
						break;
					case "F":
						System.out.println("=============================================");
						System.out.println("========== Insira a nota do Filme: ==========");
						System.out.println("=============================================");
						double nota = teclado.nextDouble();
						System.out.println("=============================================");
						System.out.println("======= Filme cadastrado com sucesso! =======");
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

	private void ativarProduto() {

			System.out.println("=============================================");
			System.out.println("========= Insira o id do Produto: ===========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			int id = teclado.nextInt();
			System.out.println("=============================================");
			System.out.println("======== Pessoa Ativada com sucesso!=========");
			System.out.println("=============================================");
	}

	private void inativarProduto() {
		System.out.println("=============================================");
		System.out.println("========== Insira o id da Pessoa: ===========");
		System.out.println("============== 0- Para sair =================");
		System.out.println("=============================================");
		int id = teclado.nextInt();
		System.out.println("=============================================");
		System.out.println("======= Pessoa Inativada com sucesso!========");
		System.out.println("=============================================");
	}

	private void listarProduto() {
		int opcao;
		do {
			System.out.println("=============================================");
			System.out.println("====== Deseja listar Filmes ou Jogos? =======");
			System.out.println("======== 1- Filmes ======== 2- Jogos ========");
			System.out.println("================== 0- Sair ==================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();
			if (opcao == 1){
				System.out.println("Query para listar os Filmes");
			} else if (opcao == 2) {
				System.out.println("Query para listar Jogos");
			}else {
				System.out.println("ERRO");
			}
		}while (opcao == 0);
	}

	public void ativarInativarProduto() {
		System.out.println("=============================================");
		System.out.println("=== Qual produto deseja Ativar/Inativar? ====");
		System.out.println("=============================================");
		String nomeProduto = teclado.nextLine();
		System.out.println("=============================================");
		System.out.println("============== Selecione a opção ============");
		System.out.println("======= 1- Ativar ======= 2- Inativar =======");
		System.out.println("=============================================");
		int opcao = teclado.nextInt();

		if (opcao == 1){
			ativarProduto();
		}if (opcao == 2){
			inativarProduto();
		}else {
			System.out.println("=============================================");
			System.out.println("============ Operação inválida ==============");
			System.out.println("=============================================");
		}
	}

	public void ativarInativarPessoa() {
		System.out.println("=============================================");
		System.out.println("=== Qual Pessoa deseja Ativar/Inativar? =====");
		System.out.println("=============================================");
		String nomePessoa = teclado.nextLine();
		System.out.println("=============================================");
		System.out.println("============== Selecione a opção ============");
		System.out.println("======= 1- Ativar ======= 2- Inativar =======");
		System.out.println("=============================================");
		int opcao = teclado.nextInt();

		if (opcao == 1){
			ativarPessoa();
		}if (opcao == 2){
			inativarPessoa();
		}else {
			System.out.println("=============================================");
			System.out.println("============ Operação inválida ==============");
			System.out.println("=============================================");
		}
	}

	public void apagarPessoaProduto() {
		int opcao;
		do {
			System.out.println("=============================================");
			System.out.println("======== Apagar Pessoa ou Produto? ==========");
			System.out.println("======== 1- Pessoa ====== 2- Produto ========");
			System.out.println("================== 0- Sair ==================");
			System.out.println("=============================================");
			opcao = teclado.nextInt();
			if (opcao == 1){
				apagarPessoa();
			} else if (opcao == 2) {
				apagarProduto();
			}
		}while (opcao ==0);

	}

}
