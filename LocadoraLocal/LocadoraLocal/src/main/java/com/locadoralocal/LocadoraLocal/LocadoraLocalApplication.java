package com.locadoralocal.LocadoraLocal;

import java.util.Scanner;

public class LocadoraLocalApplication {

	private static Scanner teclado = new Scanner(System.in).useDelimiter("\n");

	public static void main(String[] args) {

		var opcao = exibirMenu();
		while (opcao != 3) {
			try {
				switch (opcao){
					case 1:
						validarCliente();
						break;
					case 2:
						validarFuncionario();
						break;

				}
		} catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
			opcao = exibirMenu();
		}
		System.out.println("Finalizando a aplicação");
	}

	private static int exibirMenu(){
		System.out.println("=============================================");
		System.out.println("====== Seja Bem-vindo a LocadoraLocal =======");
		System.out.println("=============================================");
		System.out.println("====== Escolha uma das opções a seguir: =====");
		System.out.println("=============================================");
		System.out.println("====== 1- Menu de Clientes ==================");
		System.out.println("====== 2- Menu de Funcionários ==============");
		System.out.println("=============================================");
		return teclado.nextInt();
	}

	// criar um metodo para validar o acesso do cliente

	private static void validarCliente(){
		System.out.println("=============================================");
		System.out.println("======= Insira seu numero de Cliente: =======");
		System.out.println("=============================================");
		var numCliente = teclado.nextInt();
		int codCliente = 1;

		if (numCliente == codCliente){
			menuCliente();
		}else {
			System.out.println("=============================================");
			System.out.println("======== Número de Cliente invalido =========");
			System.out.println("=============================================");
		}

	}

	// Criar um método para listar as funções para clientes

	private static void menuCliente(){
		System.out.println("=============================================");
		System.out.println("============== Área do Cliente ==============");
		System.out.println("=============================================");
		System.out.println("============ Opções Disponiveis: ============");
		System.out.println("=============================================");
		System.out.println("========== 1- Alugar um Filme ===============");
		System.out.println("========== 2- Alugar um Jogo ================");
		System.out.println("========== 3- Listar todos os Filmes ========");
		System.out.println("========== 4- Listar todos os Jogos =========");
		System.out.println("========== 5- Listar locação ================");
		System.out.println("========== 6- Concluir locação ==============");
		System.out.println("========== 7- Sair ==========================");
		System.out.println("=============================================");
		int opcaoCliente = teclado.nextInt();

		while (opcaoCliente != 7){
			try{
				switch (opcaoCliente){
					case 1:
						alugarFilme();
						break;
					case 2:
						alugarJogo();
						break;
					case 3:
						listarFilmes();
						break;
					case 4:
						listarJogos();
						break;
					case 5:
						listarLocacao();
						break;
					case 6:
						concluirLocacao();
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}
	}

	// criar um metodo para cada função do cliente

	private static void alugarFilme() {
		System.out.println("=============================================");
		System.out.println("========= Qual filme deseja alugar? =========");
		System.out.println("=============================================");
		String nomeFilme = teclado.nextLine();
		teclado.nextLine();
		System.out.println("=============================================");
		System.out.println("======== Por quantos dias quer alugar? ======");
		System.out.println("=============================================");
		int diasLocacao = teclado.nextInt();
	}

	private static void alugarJogo() {
		System.out.println("=============================================");
		System.out.println("======== Qual jogo deseja alugar? ===========");
		System.out.println("=============================================");
		String nomeJogo = teclado.nextLine();
		teclado.nextLine();
		System.out.println("=============================================");
		System.out.println("======== Por quantos dias quer alugar? ======");
		System.out.println("=============================================");
		int diasLocacao = teclado.nextInt();
	}

	private static void listarFilmes() {
		System.out.println("=============================================");
		System.out.println("=== Deseja listar todos ou apenas ativos? ===");
		System.out.println("=============================================");
		System.out.println("======== 1- Todos ======== 2- Ativos ========");
		System.out.println("=============================================");
		int opcaoCliente = teclado.nextInt();
		while (opcaoCliente !=3){
			try{
				switch (opcaoCliente){
					case 1:
						System.out.println("Query para listar todos da tabela Filmes");
						break;
					case 2:
						System.out.println("Query para listar apenas ativo = true da tabela Filmes");
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}

	}

	private static void listarJogos() {
		System.out.println("=============================================");
		System.out.println("=== Deseja listar todos ou apenas ativos? ===");
		System.out.println("=============================================");
		System.out.println("======== 1- Todos ======== 2- Ativos ========");
		System.out.println("=============================================");
		int opcaoCliente = teclado.nextInt();
		while (opcaoCliente !=3){
			try{
				switch (opcaoCliente){
					case 1:
						System.out.println("Query para listar todos da tabela Jogos");
						break;
					case 2:
						System.out.println("Query para listar apenas ativo = true da tabela Jogos");
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}

	}

	private static void listarLocacao() {
		System.out.println("=============================================");
		System.out.println("=========== Itens da sua locação: ===========");
		System.out.println("=============================================");
		System.out.println("Query para listar os itens presentes na locação atual");
	}

	private static void concluirLocacao() {
		System.out.println("=============================================");
		System.out.println("======= Deseja concluir sua locação? ========");
		System.out.println("=============================================");
		System.out.println("========= S- Sim ========== N- Não== ========");
		System.out.println("=============================================");
		String opcaoCliente = teclado.nextLine();
		if (opcaoCliente == "S"){
			System.out.println("Concluir a locação e marcar os produtos escolhidos como inativos");
		}if (opcaoCliente == "N"){
			System.out.println("Fechar a aplicação ou voltar para o menu do cliente");
		}else {
			System.out.println("=============================================");
			System.out.println("============ Operação inválida ==============");
			System.out.println("=============================================");
		}
	}


	// Criar um método para validar o acesso do funcionario

	private static void validarFuncionario(){
		System.out.println("=============================================");
		System.out.println("===== Insira seu número de Funcionário: =====");
		System.out.println("=============================================");
		var numFuncionario = teclado.nextInt();
		int codFuncionario = 1;
		if (numFuncionario == codFuncionario){
			menuFuncionario();
		}else {
			System.out.println("=============================================");
			System.out.println("====== Número de Funcionário inválido! ======");
			System.out.println("=============================================");
		}
	}

	// criar um metodo para listar as funções do funcionario

	private static void menuFuncionario(){
		System.out.println("=============================================");
		System.out.println("============ Área do Funcionário ============");
		System.out.println("=============================================");
		System.out.println("============ Opções Disponiveis: ============");
		System.out.println("=============================================");
		System.out.println("========== 1- Listar todos os Produtos ======");
		System.out.println("========== 2- Ativar/Inativar um Produto ====");
		System.out.println("========== 3- Listar todas as Pessoas =======");
		System.out.println("========== 4- Ativa/Inativar uma Pessoa =====");
		System.out.println("========== 5- Adicionar Pessoa/Produto ======");
		System.out.println("========== 6- Apagar Pessoa/Produto =========");
		System.out.println("=============================================");
		int opcaoFuncionario = teclado.nextInt();

		while (opcaoFuncionario != 7){
			try {
				switch (opcaoFuncionario){
					case 1:
						listarProdutos();
						break;
					case 2:
						ativarInativarProduto();
						break;
					case 3:
						listarPessoas();
						break;
					case 4:
						ativarInativarPessoa();
						break;
					case 5:
						adicionarPessoaProduto();
						break;
					case 6:
						apagarPessoaProduto();
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}
	}

	// criar um metodo para cada função do funcionario

	private static void listarProdutos() {
		System.out.println("=============================================");
		System.out.println("=== Deseja listar todos ou apenas ativos? ===");
		System.out.println("=============================================");
		System.out.println("======== 1- Todos ======== 2- Ativos ========");
		System.out.println("=============================================");
		int opcaoCliente = teclado.nextInt();
		while (opcaoCliente !=3){
			try{
				switch (opcaoCliente){
					case 1:
						System.out.println("Query para listar todos os produtos");
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
		}
	}

	private static void ativarInativarProduto() {
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
			System.out.println("Query para colocar o produto ativo = true");
		}if (opcao == 2){
			System.out.println("Query para colocar o produto atiov = false");
		}else {
			System.out.println("=============================================");
			System.out.println("============ Operação inválida ==============");
			System.out.println("=============================================");
		}
	}

	private static void listarPessoas() {
		System.out.println("=============================================");
		System.out.println("======== Lista de Pessoas cadastradas =======");
		System.out.println("=============================================");
		System.out.println("Query para listar a tabela Pessoas");
	}

	private static void ativarInativarPessoa() {
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
			System.out.println("Query para colocar a Pessoa ativo = true");
		}if (opcao == 2){
			System.out.println("Query para colocar a Pessoa ativo = false");
		}else {
			System.out.println("=============================================");
			System.out.println("============ Operação inválida ==============");
			System.out.println("=============================================");
		}
	}

	private static void adicionarPessoaProduto() {
		System.out.println("=============================================");
		System.out.println("== Selecione o tipo da pessoa para Adicionar=");
		System.out.println("====== 1- Cliente ===== 2- Funcionário ======");
		System.out.println("=============================================");
		System.out.println("Querys para adicionar pessoas");
	}

	private static void apagarPessoaProduto() {
		System.out.println("=============================================");
		System.out.println("== Selecione o tipo da pessoa para Apagar ===");
		System.out.println("====== 1- Cliente ===== 2- Funcionário ======");
		System.out.println("=============================================");
		System.out.println("Querys para apagar pessoas");
	}


}
