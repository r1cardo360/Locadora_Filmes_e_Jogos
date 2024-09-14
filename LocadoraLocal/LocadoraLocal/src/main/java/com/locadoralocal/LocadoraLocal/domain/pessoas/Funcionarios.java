package com.locadoralocal.LocadoraLocal.domain.pessoas;

import com.locadoralocal.LocadoraLocal.RegraDeNegocioException;
import com.locadoralocal.LocadoraLocal.domain.locacao.DALLocadora;
import com.locadoralocal.LocadoraLocal.domain.produtos.Filmes;
import com.locadoralocal.LocadoraLocal.domain.produtos.Produto;

import java.util.Scanner;

public class Funcionarios extends Pessoas {

	Pessoas pessoas = new Pessoas();
	Produto produto = new Produto();
	Filmes filmes = new Filmes();

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
		
		teclado.nextLine();
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
		}while (opcao != 0);
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
		do {
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("== Deseja ativar Funcionario ou Cliente? ====");
			System.out.println("====== 1- Funcionario ===== 2- Cliente ======");
			System.out.println("================== 0- Sair ==================");
			System.out.println("=============================================");

			opcao = teclado.nextInt();
			teclado.nextLine();

			if(opcao == 0) {
				break;
			}

			if(opcao != 1 && opcao != 2) {
				System.out.println("Erro: comando inválido. Tente novamente.");
				continue;
			}
			switch (opcao){
				case 1:
					System.out.println("=============================================");
					System.out.println("======== Insira o ID do Funcionario: ========");
					System.out.println("=============================================");
					int idFuncionario = teclado.nextInt();

					break;
				case 2:
					System.out.println("=============================================");
					System.out.println("========== Insira o ID do Cliente: ==========");
					System.out.println("=============================================");
					int idCliente = teclado.nextInt();

					break;

			}

		} while (true);
	}

	private void inativarPessoa() {
		int opcao;
		do {
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("== Deseja inativar Funcionario ou Cliente? ==");
			System.out.println("====== 1- Funcionario ===== 2- Cliente ======");
			System.out.println("================== 0- Sair ==================");
			System.out.println("=============================================");

			opcao = teclado.nextInt();
			teclado.nextLine();

			if(opcao == 0) {
				break;
			}

			if(opcao != 1 && opcao != 2) {
				System.out.println("Erro: comando inválido. Tente novamente.");
				continue;
			}
			switch (opcao){
				case 1:
					System.out.println("=============================================");
					System.out.println("======== Insira o ID do Funcionario: ========");
					System.out.println("=============================================");
					int idFuncionario = teclado.nextInt();

					break;
				case 2:
					System.out.println("=============================================");
					System.out.println("========== Insira o ID do Cliente: ==========");
					System.out.println("=============================================");
					int idCliente = teclado.nextInt();

					break;

			}

		} while (true);
	}

	public void listarPessoa() {
		int opcao, listar;
		do{
			
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("== Deseja listar Clientes ou Funcionarios? ==");
			System.out.println("==== 1- Clientes ======= 2- Funcionários ====");
			System.out.println("================== 0- Sair ==================");
			System.out.println("=============================================");
			
			opcao = teclado.nextInt();
			teclado.nextLine();
			
			if(opcao == 0) {
				break;
			}
			
			if(opcao != 1 && opcao != 2) {
				System.out.println("Erro: comando inválido. Tente novamente.");
				continue;
			}
			
				System.out.println("=============================================");
				System.out.println("=== Deseja listar todos ou apenas ativos? ===");
				System.out.println("=============================================");
				System.out.println("======== 1- Todos ======== 2- Ativos ========");
				System.out.println("============== 0- Para sair =================");
				System.out.println("=============================================");
				
				listar = teclado.nextInt();
				teclado.nextLine();
				
				if(listar == 0) {
					break;
				}

				if(listar != 1 && listar != 2) {
					System.out.println("Erro: comando inválido. Tente novamente.");
					continue;
				}
				
				switch (opcao){
					case 1:
						DALLocadora.clearConsole();
						DALLocadora.listarClientes(listar);
						DALLocadora.pausarConsole();
						break;
					case 2:
						DALLocadora.clearConsole();
						DALLocadora.listarFuncionarios(listar);
						DALLocadora.pausarConsole();
						break;
				}
		}while(true);
	}

	private void apagarProduto() {
		int opcao, idProduto;
		String tipoDelecao;
		do{
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("======== Qual produto deseja deletar ========");
			System.out.println("========== 0-Sair 1- Filme 2-Jogo ===========");
			System.out.println("=============================================");
			
			opcao = teclado.nextInt();
			teclado.nextLine();
			
			switch(opcao) {
			case 1:
				
				DALLocadora.clearConsole();
				System.out.println("=============================================");
				System.out.println("============== Deletar Filmes ===============");
				System.out.println("=============================================");
				System.out.println("====== *Selecione o Id para Deletar* ========");
				System.out.println("=============================================");
				DALLocadora.mostrarFilmes(2);
				idProduto = teclado.nextInt();
				teclado.nextLine();
				tipoDelecao = "filme";
				
				DALLocadora.deletarProduto(idProduto, tipoDelecao);
				
				break;
			case 2:
				
				DALLocadora.clearConsole();
				System.out.println("=============================================");
				System.out.println("============== Deletar Jogos ===============");
				System.out.println("=============================================");
				System.out.println("====== *Selecione o Id para Deletar* ========");
				System.out.println("=============================================");
				DALLocadora.mostrarJogo(2);
				idProduto = teclado.nextInt();
				teclado.nextLine();
				tipoDelecao = "jogo";
				
				DALLocadora.deletarProduto(idProduto, tipoDelecao);
				
				break;
			default:
				System.out.println("");
			}
				
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
						filmes.setNota(nota);
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
		int opcao;
		do {
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("====== Deseja ativar Filmes ou Jogos? =======");
			System.out.println("======== 1- Filmes ======== 2- Jogos ========");
			System.out.println("================== 0- Sair ==================");
			System.out.println("=============================================");

			opcao = teclado.nextInt();
			teclado.nextLine();

			if(opcao == 0) {
				break;
			}

			if(opcao != 1 && opcao != 2) {
				System.out.println("Erro: comando inválido. Tente novamente.");
				continue;
			}
			switch (opcao){
				case 1:
					System.out.println("=============================================");
					System.out.println("=========== Insira o ID do Filme: ===========");
					System.out.println("=============================================");
					int idFilme = teclado.nextInt();

					break;
				case 2:
					System.out.println("=============================================");
					System.out.println("=========== Insira o ID do Jogo: ============");
					System.out.println("=============================================");
					int idJogo = teclado.nextInt();

					break;

			}

		} while (true);
	}

	private void inativarProduto() {
		int opcao;
		do {
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("====== Deseja inativar Filmes ou Jogos? =====");
			System.out.println("======== 1- Filmes ======== 2- Jogos ========");
			System.out.println("================== 0- Sair ==================");
			System.out.println("=============================================");

			opcao = teclado.nextInt();
			teclado.nextLine();

			if(opcao == 0) {
				break;
			}

			if(opcao != 1 && opcao != 2) {
				System.out.println("Erro: comando inválido. Tente novamente.");
				continue;
			}
			switch (opcao){
				case 1:
					System.out.println("=============================================");
					System.out.println("=========== Insira o ID do Filme: ===========");
					System.out.println("=============================================");
					int idFilme = teclado.nextInt();

					break;
				case 2:
					System.out.println("=============================================");
					System.out.println("=========== Insira o ID do Jogo: ============");
					System.out.println("=============================================");
					int idJogo = teclado.nextInt();

					break;

			}

		} while (true);
	}

	private void listarProduto() {
		int opcao, listar;
		do {
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("====== Deseja listar Filmes ou Jogos? =======");
			System.out.println("======== 1- Filmes ======== 2- Jogos ========");
			System.out.println("================== 0- Sair ==================");
			System.out.println("=============================================");

			opcao = teclado.nextInt();
			teclado.nextLine();

			if(opcao == 0) {
				break;
			}

			if(opcao != 1 && opcao != 2) {
				System.out.println("Erro: comando inválido. Tente novamente.");
				continue;
			}

			System.out.println("=============================================");
			System.out.println("=== Deseja listar todos ou apenas ativos? ===");
			System.out.println("=============================================");
			System.out.println("======== 1- Todos ======== 2- Ativos ========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");

			listar = teclado.nextInt();
			teclado.nextLine();

			if(listar == 0) {
				break;
			}

			if(listar != 1 && listar != 2) {
				System.out.println("Erro: comando inválido. Tente novamente.");
				continue;
			}

			switch (opcao) {
				case 1:
					DALLocadora.clearConsole();
					DALLocadora.mostrarFilmes(listar);
					DALLocadora.pausarConsole();
					break;
				case 2:
					DALLocadora.clearConsole();
					DALLocadora.mostrarJogo(listar);
					DALLocadora.pausarConsole();
					break;
			}
		} while (true);
	}


}
