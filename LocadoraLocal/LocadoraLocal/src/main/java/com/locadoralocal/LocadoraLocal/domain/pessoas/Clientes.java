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
		DALLocadora banco = new DALLocadora();
		ArrayList<Integer> listaFilmes = new ArrayList<>();
		int idFilme;

			banco.clearConsole();
			System.out.println("=============================================");
			System.out.println("========= Qual filme deseja alugar? =========");
			System.out.println("=============================================");

			System.out.println("=============================================");
			System.out.println("========= Digite um Id de filme ou ==========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");
			
			
		do {
			banco.mostrarFilmes(2);
			
			idFilme = teclado.nextInt();
			teclado.nextLine();

			if (idFilme == 0){
				System.out.println("Saindo...");
				break;
			}

			if(banco.SelecionarFilme(idFilme)){
				listaFilmes.add(idFilme);
				banco.filmeInativo(idFilme);
			}else {
				System.out.println("Seleção invalida Filme inativo ou locado");
				System.out.println("Selecione mais Filmes ou digite zero para Sair");
				banco.pausarConsole();
			}

		} while(true);

		return listaFilmes;
		
	}

	public ArrayList<Integer> alugarJogo() {

		DALLocadora banco = new DALLocadora();
		ArrayList<Integer> listaJogos = new ArrayList<>();
		int idJogo;

			banco.clearConsole();
			System.out.println("=============================================");
			System.out.println("======== Qual jogo deseja alugar? ===========");
			System.out.println("=============================================");

			System.out.println("=============================================");
			System.out.println("========== Digite um Id de jogo ou ==========");
			System.out.println("============== 0- Para sair =================");
			System.out.println("=============================================");

			

		do {
			banco.mostrarJogo(2);
			idJogo = teclado.nextInt();
			teclado.nextLine();

			if(idJogo == 0) {
				System.out.println("Saindo...");
				break;
			}

			if(banco.selecionarJogo(idJogo)) {
				listaJogos.add(idJogo);
				banco.JogoInativo(idJogo);
			}else {
				System.out.println("Seleção invalida Jogo inativado ou locado");
				System.out.println("Selecione mais filmes ou digite zero para sair");
				banco.pausarConsole();
			}

		}while(true);

		return listaJogos;

	}

	public void listarFilmes() {
		DALLocadora banco = new DALLocadora();
		int opcaoCliente;

		do{
			banco.clearConsole();
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
						banco.clearConsole();
						banco.mostrarFilmes(opcaoCliente);
						banco.pausarConsole();
						break;
					case 2:
						banco.clearConsole();
						banco.mostrarFilmes(opcaoCliente);
						banco.pausarConsole();
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
		DALLocadora banco = new DALLocadora();
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
						banco.clearConsole();
						banco.mostrarJogo(opcaoCliente);
						banco.pausarConsole();
						break;
					case 2:
						banco.clearConsole();
						banco.mostrarJogo(opcaoCliente);
						banco.pausarConsole();
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
		DALLocadora banco = new DALLocadora();

		System.out.println("=============================================");
		System.out.println("================= Locação: ==================");
		System.out.println("=============================================");

		banco.mostrarLocacao(idCliente,listaFilmes, listaJogos);
		banco.pausarConsole();

	}

	public void concluirLocacao(int idCliente, ArrayList<Integer> listaFilmes, ArrayList<Integer> listaJogos) {
		DALLocadora banco = new DALLocadora();
		if(listaFilmes.isEmpty() && listaJogos.isEmpty()) {
			
			System.out.println("=====================================");
			System.out.println("= Erro as duas listas estão vazias =");
			System.out.println("======== Locação Cancelada =========");
			System.out.println("=====================================");
			banco.pausarConsole();
			
		}else {
		
			
			
			banco.clearConsole();
			
			banco.clearConsole();
			int numeroLocacao = banco.concluirLocacao(idCliente, listaFilmes, listaJogos);
			System.out.println("==========================================");
			System.out.println("=========== Locação Finalizada ===========");
			System.out.printf("=============== Locação Nº%d =============\n", numeroLocacao);
			System.out.println("==========================================");
			banco.mostrarLocacao(idCliente, listaFilmes, listaJogos);
			banco.pausarConsole();
		}
	}
}
