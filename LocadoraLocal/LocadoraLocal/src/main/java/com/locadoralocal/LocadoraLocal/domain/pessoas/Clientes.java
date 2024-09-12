package com.locadoralocal.LocadoraLocal.domain.pessoas;

import com.locadoralocal.LocadoraLocal.RegraDeNegocioException;
import com.locadoralocal.LocadoraLocal.domain.locacao.DALLocadora;

import java.util.ArrayList;
import java.util.Scanner;

public class Clientes extends Pessoas {

	Scanner teclado = new Scanner(System.in);
	
	public Clientes(String nome, String cpf, int anoNascimento, char sexo, boolean ativo) {
		//super(nome, cpf, anoNascimento, sexo, ativo);
	}

	public Clientes() {

	}

	public ArrayList<Integer> alugarFilme() {
		ArrayList<Integer> listaFilmes = new ArrayList<>();
		int idFilme;

			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("========= Qual filme deseja alugar? =========");
			System.out.println("=============================================");

			System.out.println("=============================================");
			System.out.println("========= Digite um Id de filme ou ==========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			
			
		do {
			DALLocadora.mostrarFilmes(2);
			
			idFilme = teclado.nextInt();
			teclado.nextLine();

			if (idFilme == 0){
				System.out.println("Saindo...");
				break;
			}

			if(DALLocadora.SelecionarFilme(idFilme)){
				listaFilmes.add(idFilme);
				DALLocadora.filmeInativo(idFilme);
			}else {
				System.out.println("Seleção invalida Filme inativo ou locado");
				System.out.println("Selecione mais Filmes ou digite zero para Sair");
				DALLocadora.pausarConsole();
			}

		} while(true);

		return listaFilmes;
		
	}

	public ArrayList<Integer> alugarJogo() {

		ArrayList<Integer> listaJogos = new ArrayList<>();
		int idJogo;

			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("======== Qual jogo deseja alugar? ===========");
			System.out.println("=============================================");

			System.out.println("=============================================");
			System.out.println("========== Digite um Id de jogo ou ==========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");

			

		do {
			DALLocadora.mostrarJogo(2);
			idJogo = teclado.nextInt();
			teclado.nextLine();

			if(idJogo == 0) {
				System.out.println("Saindo...");
				break;
			}

			if(DALLocadora.selecionarJogo(idJogo)) {
				listaJogos.add(idJogo);
				DALLocadora.JogoInativo(idJogo);
			}else {
				System.out.println("Seleção invalida Jogo inativado ou locado");
				System.out.println("Selecione mais filmes ou digite zero para sair");
				DALLocadora.pausarConsole();
			}

		}while(true);

		return listaJogos;

	}

	public void listarFilmes() {
		int opcaoCliente;

		do{
			DALLocadora.clearConsole();
			System.out.println("=============================================");
			System.out.println("=== Deseja listar todos ou apenas ativos? ===");
			System.out.println("=============================================");
			System.out.println("======== 1- Todos ======== 2- Ativos ========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			opcaoCliente = teclado.nextInt();

			try{
				switch (opcaoCliente){
					case 1:
						DALLocadora.clearConsole();
						DALLocadora.mostrarFilmes(opcaoCliente);
						DALLocadora.pausarConsole();
						break;
					case 2:
						DALLocadora.clearConsole();
						DALLocadora.mostrarFilmes(opcaoCliente);
						DALLocadora.pausarConsole();
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}while(opcaoCliente == 0);

	}

	public void listarJogos() {
		int opcaoCliente;

		do {
			System.out.println("=============================================");
			System.out.println("=== Deseja listar todos ou apenas ativos? ===");
			System.out.println("=============================================");
			System.out.println("======== 1- Todos ======== 2- Ativos ========");
			System.out.println("=============================================");
			opcaoCliente = teclado.nextInt();

			try{
				switch (opcaoCliente){
					case 1:
						DALLocadora.clearConsole();
						DALLocadora.mostrarJogo(opcaoCliente);
						DALLocadora.pausarConsole();
						break;
					case 2:
						DALLocadora.clearConsole();
						DALLocadora.mostrarJogo(opcaoCliente);
						DALLocadora.pausarConsole();
						break;
				}
			}catch (RegraDeNegocioException e){
				System.out.println("Erro: " +e.getMessage());
				System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
				teclado.next();
			}
		}while(opcaoCliente == 0);

	}

	public void listarLocacao(int idCliente, ArrayList<Integer> listaFilmes, ArrayList<Integer> listaJogos) {

		System.out.println("=============================================");
		System.out.println("================= Locação: ==================");
		System.out.println("=============================================");

		DALLocadora.mostrarLocacao(idCliente,listaFilmes, listaJogos);
		DALLocadora.pausarConsole();

	}

	public void concluirLocacao(int idCliente, ArrayList<Integer> listaFilmes, ArrayList<Integer> listaJogos) {
		if(listaFilmes.isEmpty() && listaJogos.isEmpty()) {
			
			System.out.println("=====================================");
			System.out.println("= Erro as duas listas estão vazias =");
			System.out.println("======== Locação Cancelada =========");
			System.out.println("=====================================");
			DALLocadora.pausarConsole();
			
		}else {
		
			
			
			DALLocadora.clearConsole();
			
			DALLocadora.clearConsole();
			int numeroLocacao = DALLocadora.concluirLocacao(idCliente, listaFilmes, listaJogos);
			System.out.println("==========================================");
			System.out.println("=========== Locação Finalizada ===========");
			System.out.printf("=============== Locação Nº%d =============\n", numeroLocacao);
			System.out.println("==========================================");
			DALLocadora.mostrarLocacao(idCliente, listaFilmes, listaJogos);
			DALLocadora.pausarConsole();
		}
	}
}
