package com.locadoralocal.LocadoraLocal;

import java.util.ArrayList;
import java.util.Scanner;
import com.locadoralocal.LocadoraLocal.domain.locacao.DALLocadora;
import com.locadoralocal.LocadoraLocal.domain.pessoas.Clientes;
import com.locadoralocal.LocadoraLocal.domain.pessoas.Funcionarios;

public class LocadoraLocalApplication {

	private static Scanner teclado = new Scanner(System.in);
	static Funcionarios funcionarios = new Funcionarios();
	static Clientes clientes = new Clientes();

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
		
		DALLocadora.clearConsole();
		System.out.println("=============================================");
		System.out.println("====== Seja Bem-vindo a LocadoraLocal =======");
		System.out.println("=============================================");
		System.out.println("====== Escolha uma das opções a seguir: =====");
		System.out.println("=============================================");
		System.out.println("====== 1- Menu de Clientes ==================");
		System.out.println("====== 2- Menu de Funcionários ==============");
		System.out.println("=============================================");
		int opcao = teclado.nextInt();
		teclado.nextLine();
		
		return opcao;
	}

	// criar um metodo para validar o acesso do cliente

	private static void validarCliente(){
		DALLocadora.clearConsole();
		System.out.println("=============================================");
		System.out.println("======= Insira seu numero de Cliente: =======");
		System.out.println("=============================================");
		var numCliente = teclado.nextInt();

		if (DALLocadora.verificarCliente(numCliente)){
			menuCliente(numCliente);
		}else {
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("======== Número de Cliente invalido =========");
			System.out.println("=============================================");
		}

	}

	// Criar um método para listar as funções para clientes

	private static void menuCliente(int idCliente){
		
		int opcaoCliente = -1;
		ArrayList<Integer> listaFilmes = new ArrayList<>();
		ArrayList<Integer> listaJogos = new ArrayList<>();

		while (opcaoCliente != 7 && opcaoCliente != 6){
			try{
				DALLocadora.clearConsole();
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
				opcaoCliente = teclado.nextInt();
				teclado.nextLine();
				
				switch (opcaoCliente){
					case 1:
						listaFilmes.addAll(clientes.alugarFilme());
						break;
					case 2:
						listaJogos.addAll(clientes.alugarJogo());
						break;
					case 3:
						clientes.listarFilmes();
						break;
					case 4:
						clientes.listarJogos();
						break;
					case 5:
						clientes.listarLocacao(idCliente, listaFilmes, listaJogos);
						break;
					case 6:
						clientes.concluirLocacao(idCliente, listaFilmes, listaJogos);
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}
		
		if(opcaoCliente == 7) {
			DALLocadora.ativarFilmesEJogos(listaFilmes, listaJogos);
			
			System.out.println("==============================================");
			System.out.println("========= Locação não foi concluida ==========");
			System.out.println("======= Os seus itens foram deletados ========");
			System.out.println("=============== Do Carrinho ==================");
			System.out.println("==============================================");
			
			listaFilmes.clear();
			listaJogos.clear();
			DALLocadora.pausarConsole();
			
		}
		
	}

	// Criar um método para validar o acesso do funcionario

	private static void validarFuncionario(){
		System.out.println("=============================================");
		System.out.println("===== Insira seu número de Funcionário: =====");
		System.out.println("=============================================");
		var numFuncionario = teclado.nextInt();
		teclado.nextLine();
		if (DALLocadora.verificarFuncionario(numFuncionario)){
			menuFuncionario();
		}else {
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("====== Número de Funcionário inválido! ======");
			System.out.println("=============================================");
		}
	}

	// criar um metodo para listar as funções do funcionario

	private static void menuFuncionario() {

		int opcaoFuncionario;
		
		do{
			
			System.out.println("=============================================");
			System.out.println("============ Área do Funcionário ============");
			System.out.println("=============================================");
			System.out.println("============ Opções Disponiveis: ============");
			System.out.println("=============================================");
			System.out.println("========== 1- Listar =========================");
			System.out.println("========== 2- Ativar =========================");
			System.out.println("========== 3- Inativar =======================");
			System.out.println("========== 4- Adicionar Pessoa/Produto ======");
			System.out.println("========== 5- Apagar Pessoa/Produto =========");
			System.out.println("=============================================");
			opcaoFuncionario = teclado.nextInt();
			
			try {
				switch (opcaoFuncionario) {
					case 1:
						funcionarios.listar();
						break;
					case 2:
						funcionarios.ativar();
						break;
					case 3:
						funcionarios.inativar();
						break;
					case 4:
						funcionarios.adicionar();
						break;
					case 5:
						funcionarios.apagar();
						break;
				}
			} catch (RegraDeNegocioException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}while(opcaoFuncionario != 7);
	}
}