package com.locadoralocal.LocadoraLocal.domain.locacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DALLocadora {
	
	private static Scanner teclado = new Scanner(System.in);
	
	private static final String stringConnect = "jdbc:sqlite:/C:\\Users\\Suporte\\Desktop\\Backup\\HS\\Nova pasta\\Locadora_Filmes_e_Jogos\\Locadora\\Locadora.db";

	public static void pausarConsole() {
		System.out.println("Pressione Enter para Continuar...");
		teclado.nextLine();
	}
	
    public static void clearConsole() {
        // Tenta executar comandos específicos para sistemas operacionais diferentes
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Comando para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Comando para Unix/Linux/Mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else {
                // Caso não seja um sistema operacional suportado
                System.out.println("Sistema operacional não suportado para limpar o console.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static boolean verificarCliente(long id) {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			String sql = "SELECT * FROM cliente WHERE pk_cliente = ?";
			
			preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setLong(1, id);
			
			resultSet = preparedStatment.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
			
			
		}catch(SQLException e) {
			System.out.println("Não foi possivel se conectar ao banco de dados: " + e.getMessage());
			return false;
		}finally{
			try {
				if(preparedStatment != null) {
					preparedStatment.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex){
				System.out.println("Algo deu errado ao fechar a conexão com o banco de dados");
			}
		}
		
	}

	public static void mostrarFilmes(int opcao) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			if (opcao == 1) {
				sql = "SELECT pk_filmes, nome_filme, classificacao_filme, ano_lancamento_filmes, nota_filme FROM filmes WHERE ativo_filmes = 'S'";
			}else {
				sql = "SELECT pk_filmes, nome_filme, classificacao_filme, ano_lancamento_filmes, nota_filme FROM filmes";
			}
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			
            System.out.println("+-------+----------------------------------------------------+-----------------+------------+-------+");
            System.out.printf("| %-5s | %-50s | %-15s | %-10s | %-5s |\n", "ID", "Nome", "Classificação", "Lançamento", "Nota");
            System.out.println("+-------+----------------------------------------------------+-----------------+------------+-------+");
            
			while(resultSet.next()) {
				int id = resultSet.getInt("pk_filmes");
				String nome = resultSet.getString("nome_filme");
				int classificacao = resultSet.getInt("classificacao_filme");
				int anoLancamento = resultSet.getInt("ano_lancamento_filmes");
				float nota = resultSet.getFloat("nota_filme");
				
				
				System.out.printf("| %-5d | %-50s | %-15d | %-10d | %-5.2f | \n", id, nome, classificacao, anoLancamento, nota);
			}
			
			System.out.println("+-------+----------------------------------------------------+-----------------+------------+-------+");
			
		}catch(SQLException e) {
			System.out.println("Erro ao execultar o Select" + e.getMessage());
		}finally{
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex) {
				System.out.println("Erro ao fechar a conexão com o banco de dados");
			}
		}
	}

	public static boolean SelecionarFilme(int filme) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			String sql = "SELECT pk_filmes FROM filmes WHERE pk_filmes = ? AND ativo_filmes = 'S'";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, filme);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e){
			System.out.println("Erro ao conectarse ao banco de dados: " + e.getMessage());
			return false;
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex) {
				System.out.println("Erro ao fechar conexão com o banco de dados");
			}
		}
		
	}

	public static int criarLocacao(int idCliente) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate hoje = LocalDate.now();
		LocalDate devolucao = hoje.plusDays(5);
		
		String hojeDataTexto = hoje.format(formatter);
		String devolucaoDataTexto = devolucao.format(formatter);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int pkLocacao = -1;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			
			String sql = "INSERT INTO locacao(fk_cliente, data_locacao, data_devolucao_prevista) VALUES(?, ?, ?)";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, idCliente);
			preparedStatement.setString(2, hojeDataTexto);
			preparedStatement.setString(3, devolucaoDataTexto);
			
			preparedStatement.executeUpdate();
			
			String sqlGetId = "SELECT last_insert_rowid()";
	        preparedStatement = connection.prepareStatement(sqlGetId);
	        resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				pkLocacao = resultSet.getInt(1);
			}
		return pkLocacao;
			
		}catch(SQLException e) {
			System.out.println("Erro ao conectarse com o banco de dados: " + e.getMessage());
			return -1;
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex) {
				System.out.println("Algo deu errado para fechar a consexão com o banco de dados");
			}
		}
		
	}

	public static void cancelarLocacao(int idCliente) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			
			String sql = "DELETE FROM locacao WHERE pk_locacao = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, idCliente);
			
			preparedStatement.executeUpdate();
			
			
		}catch(SQLException e) {
			System.out.println("Não foi possivel se conectar com o banco: " + e.getMessage());
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex) {
				System.out.println("Ouve um erro ao fechar a conexão com o banco");
			}
		}
		
	}

	public static void adicionarFilme(int pk_locacao, int idLocacao) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			
			String sql = "INSERT INTO item_filme_locacao(fk_locacao, fk_filme) VALUES(?, ?)";
			
			preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setInt(1, pk_locacao);
		    preparedStatement.setInt(2, idLocacao);
		    
		    preparedStatement.executeUpdate();
		    
		}catch(SQLException e) {
			System.out.println("Não conseguiu se conectar ao banco de dados: " + e.getMessage());
		}finally {
			try {
				if(preparedStatement!= null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex) {
				System.out.println("Não conseguir fechar a conexão com o banco de dados");
			}
		}
		
	}

	public static void mostrarJogo(int opcao) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql;
		
		try {
		
			connection = DriverManager.getConnection(stringConnect);
			if(opcao == 1) {
				sql = "SELECT pk_jogo, nome_jogo, classificacao_jogo, ano_lancamento_jogo FROM jogos WHERE ativo_jogos = 'S'";
			}else {
				sql = "SELECT pk_jogo, nome_jogo, classificacao_jogo, ano_lancamento_jogo FROM jogos";
			}
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
		
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			System.out.printf("| %-5s | %-50s | %-15s | %-15s |\n", "ID", "Nome", "Classificação", "Ano de Lançamento");
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			
			while(resultSet.next()) {
				int id = resultSet.getInt("pk_jogo");
				String nome = resultSet.getString("nome_jogo");
				int classificacao = resultSet.getInt("classificacao_jogo");
				int anoLancamento = resultSet.getInt("ano_lancamento_jogo");
				
				System.out.printf("| %-5d | %-50s | %-15d | %-15d |\n", id, nome, classificacao, anoLancamento);
				
			}
			
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			
		}catch(SQLException e) {
			System.out.println("Erro ao conectarse ao banco de dados: " + e.getMessage());
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex) {
				System.out.println("Erro ao fechar a conexão com o banco de dados");
			}
		}
		
	}

	public static boolean selecionarJogo(int idJogo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			String sql = "SELECT pk_jogo FROM jogos WHERE pk_jogo = ? AND ativo_jogos = 'S'";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idJogo);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e){
			System.out.println("Erro ao conectarse ao banco de dados: " + e.getMessage());
			return false;
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex) {
				System.out.println("Erro ao fechar conexão com o banco de dados");
			}
		}
	}
	
	public static void adicionarJogo(int pkJogo, int idJogo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			
			String sql = "INSERT INTO item_jogo_locacao(fk_locacao, fk_jogo) VALUES(?, ?)";
			
			preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setInt(1, pkJogo);
		    preparedStatement.setInt(2, idJogo);
		    
		    preparedStatement.executeUpdate();
		    
		}catch(SQLException e) {
			System.out.println("Não conseguiu se conectar ao banco de dados: " + e.getMessage());
		}finally {
			try {
				if(preparedStatement!= null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex) {
				System.out.println("Não conseguir fechar a conexão com o banco de dados");
			}
		}
	}
	
    public static void mostrarLocacao(int pkLocacao) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql;
		
		try {
		
			connection = DriverManager.getConnection(stringConnect);
			sql = "SELECT pk_cliente, nome_cliente FROM cliente WHERE pk_cliente = (SELECT fk_cliente FROM locacao WHERE pk_locacao = ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, pkLocacao);
			
			resultSet = preparedStatement.executeQuery();
			
			int idCliente = resultSet.getInt("pk_cliente");
			String nomeCliente = resultSet.getString("nome_cliente");
		
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			System.out.printf("| %-5d | %-50s |\n", idCliente, nomeCliente);
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			System.out.println("==================================== FILMES ======================================================");
			
			sql = "SELECT fk_filme, nome_filme FROM item_filme_locacao \r\n"
					+ "INNER JOIN filmes ON item_filme_locacao.fk_filme = filmes.pk_filmes\r\n"
					+ "WHERE fk_locacao = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, pkLocacao);
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			System.out.printf("| %-5s | %-50s |\n", "ID", "Nome");
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			
			while(resultSet.next()) {
				int idFilme = resultSet.getInt("fk_filme");
				String nomeFilme = resultSet.getString("nome_filme");
				
				System.out.printf("| %-5d | %-50s |\n", idFilme, nomeFilme);
				
			}
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			System.out.println("==================================== JOGOS =======================================================");
			
			sql = "SELECT fk_jogo, nome_jogo FROM item_jogo_locacao \r\n"
					+ "INNER JOIN jogos ON item_jogo_locacao.fk_jogo = jogos.pk_jogo\r\n"
					+ "WHERE fk_locacao = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, pkLocacao);
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			System.out.printf("| %-5s | %-50s |\n", "ID", "Nome");
			System.out.println("+-------+----------------------------------------------------+-----------------+-----------------+");
			
			while(resultSet.next()) {
				int idJogo = resultSet.getInt("fk_jogo");
				String nomeJogo = resultSet.getString("nome_jogo");
				
				System.out.printf("| %-5d | %-50s |\n", idJogo, nomeJogo);
				
			}
			
		}catch(SQLException e) {
			System.out.println("Erro ao conectarse ao banco de dados: " + e.getMessage());
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex) {
				System.out.println("Erro ao fechar a conexão com o banco de dados");
			}
		}
		
	}
	
}
