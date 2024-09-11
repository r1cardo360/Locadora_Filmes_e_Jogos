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

	public ArrayList<Integer> alugarFilme(int pk_locacao) {
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
			banco.mostrarFilmes(1);
			
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
			}

		} while(true);

		return listaFilmes;
		
	}

	public ArrayList<Integer> alugarJogo(int pkLocacao) {

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
			banco.mostrarJogo(1);
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

	public void listarLocacao(int pkLocacao, ArrayList<Integer> listaFilmes, ArrayList<Integer> listaJogos) {
		DALLocadora banco = new DALLocadora();

		System.out.println("=============================================");
		System.out.println("================= Locação: ==================");
		System.out.println("=============================================");

		banco.mostrarLocacao(pkLocacao, listaFilmes, listaJogos);
		banco.pausarConsole();

	}

	public void concluirLocacao() {
		
		DALLocadora banco = new DALLocadora();
		
		banco.clearConsole();
		
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
}
