package com.locadoralocal.LocadoraLocal.domain.locacao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.locadoralocal.LocadoraLocal.domain.pessoas.Pessoas;
import com.locadoralocal.LocadoraLocal.domain.produtos.Filmes;
import com.locadoralocal.LocadoraLocal.domain.produtos.Produto;

public class DALLocadora {
	
	private static Scanner teclado = new Scanner(System.in);
	
	private static final String stringConnect = "jdbc:sqlite:/C:\\Users\\pc\\Desktop\\Projeto_Locadora\\Locadora_Filmes_e_Jogos\\Locadora\\Locadora.db";

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

	public static boolean verificarFuncionario(long id) {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			String sql = "SELECT * FROM funcionario WHERE pk_funcionario = ?";
			
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
		String sql = "";
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			if (opcao == 1) {
				sql = "SELECT pk_filmes, nome_filme, classificacao_filme, ano_lancamento_filmes, nota_filme FROM filmes";
			}else if(opcao == 2) {
				sql = "SELECT pk_filmes, nome_filme, classificacao_filme, ano_lancamento_filmes, nota_filme FROM filmes WHERE ativo_filmes = 'S'";
			}else {
				System.out.println("Erro opção invalida");
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

	public static int concluirLocacao(int idCliente, ArrayList<Integer> listaFilmes, ArrayList<Integer> listaJogos) {
		
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
			
			if(!listaFilmes.isEmpty()) {
				for(int i = 0; i < listaFilmes.size(); i++) {
					sql = "INSERT INTO item_filme_locacao(fk_locacao, fk_filme) VALUES(?, ?)";
					
					preparedStatement = connection.prepareStatement(sql);
					
					preparedStatement.setInt(1, pkLocacao);
					preparedStatement.setInt(2, listaFilmes.get(i));
					
					preparedStatement.executeUpdate();
				}
			}
			
			if(!listaJogos.isEmpty()) {
				for(int i = 0; i < listaJogos.size(); i++) {
					sql = "INSERT INTO item_jogo_locacao(fk_locacao, fk_jogo) VALUES(?, ?)";
					
					preparedStatement = connection.prepareStatement(sql);
					
					preparedStatement.setInt(1, pkLocacao);
					preparedStatement.setInt(2, listaJogos.get(i));
					
					preparedStatement.executeUpdate();
				}
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
		String sql = "";
		
		try {
		
			connection = DriverManager.getConnection(stringConnect);
			if(opcao == 1) {
				sql = "SELECT pk_jogo, nome_jogo, classificacao_jogo, ano_lancamento_jogo FROM jogos";
			}else if(opcao == 2) {
				sql = "SELECT pk_jogo, nome_jogo, classificacao_jogo, ano_lancamento_jogo FROM jogos WHERE ativo_jogos = 'S'";
			}else {
				System.out.println("Opção invalida");
			}
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
		
			System.out.println("+-------+----------------------------------------------------+-----------------+--------------------+");
			System.out.printf("| %-5s | %-50s | %-15s | %-18s |\n", "ID", "Nome", "Classificação", "Ano de Lançamento");
			System.out.println("+-------+----------------------------------------------------+-----------------+--------------------+");
			
			while(resultSet.next()) {
				int id = resultSet.getInt("pk_jogo");
				String nome = resultSet.getString("nome_jogo");
				int classificacao = resultSet.getInt("classificacao_jogo");
				int anoLancamento = resultSet.getInt("ano_lancamento_jogo");
				
				System.out.printf("| %-5d | %-50s | %-15d | %-18d |\n", id, nome, classificacao, anoLancamento);
				
			}
			
			System.out.println("+-------+----------------------------------------------------+-----------------+--------------------+");
			
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
	
    public static void mostrarLocacao(int idCliente, ArrayList<Integer> listaFilmes, ArrayList<Integer> listaJogos) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql;
		
		try {
		
			connection = DriverManager.getConnection(stringConnect);
			sql = "SELECT pk_cliente, nome_cliente FROM cliente WHERE pk_cliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, idCliente);
			
			resultSet = preparedStatement.executeQuery();
			
			int idClientesql = resultSet.getInt("pk_cliente");
			String nomeCliente = resultSet.getString("nome_cliente");
		
			System.out.println("+-------+----------------------------------------------------+");
			System.out.printf("| %-5d | %-50s |\n", idClientesql, nomeCliente);
			System.out.println("+-------+----------------------------------------------------+");
			System.out.println("=========================== FILMES ===========================");
			
			if(listaFilmes.isEmpty()) {
				System.out.println("Nenhum Filme na lista");
			}else {
				String placeholder = listaFilmes.stream().map(id -> "?").collect(Collectors.joining(","));
				sql = String.format("SELECT pk_filmes, nome_filme FROM filmes WHERE pk_filmes in (%s)", placeholder);
				preparedStatement = connection.prepareStatement(sql);
				
				for(int i = 0; i < listaFilmes.size(); i++) {
					preparedStatement.setInt(i + 1, listaFilmes.get(i));
				}
				
				resultSet = preparedStatement.executeQuery();
				
				System.out.println("+-------+----------------------------------------------------+");
				System.out.printf("| %-5s | %-50s |\n", "ID", "Nome");
				System.out.println("+-------+----------------------------------------------------+");
				
				while(resultSet.next()) {
					int idFilme = resultSet.getInt("pk_filmes");
					String nomeFilme = resultSet.getString("nome_filme");
					
					System.out.printf("| %-5d | %-50s |\n", idFilme, nomeFilme);
				}	
			}
			
			System.out.println("+-------+----------------------------------------------------+");
			System.out.println("=========================== JOGOS ============================");
			
			if(listaJogos.isEmpty()) {
				System.out.println("Nenhum Jogo na lista");
			}else {
				String placeholder = listaJogos.stream().map(id -> "?").collect(Collectors.joining(","));
				sql = String.format("SELECT pk_jogo, nome_jogo FROM jogos WHERE pk_jogo in (%s)", placeholder);
				preparedStatement = connection.prepareStatement(sql);
				
				for(int i = 0; i < listaJogos.size(); i++) {
					preparedStatement.setInt(i + 1, listaJogos.get(i));
				}
	
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("+-------+----------------------------------------------------+");
			System.out.printf("| %-5s | %-50s |\n", "ID", "Nome");
			System.out.println("+-------+----------------------------------------------------+");
			
			while(resultSet.next()) {
				int idJogo = resultSet.getInt("pk_jogo");
				String nomeJogo = resultSet.getString("nome_jogo");
				
				System.out.printf("| %-5d | %-50s |\n", idJogo, nomeJogo);
				
				}
			}
			System.out.println("+-------+----------------------------------------------------+");
			
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

    public static void filmeInativo(int idFilme) {
    	
    	Connection connection = null;
    	PreparedStatement preparedstatement = null;
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		String sql = "UPDATE filmes SET ativo_filmes = 'N' WHERE pk_filmes = ?";
    		
    		preparedstatement = connection.prepareStatement(sql);
    		
    		preparedstatement.setInt(1, idFilme);
    		
    		preparedstatement.executeUpdate();
    		
    		
    	}catch(SQLException e) {
    		System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
    	}finally {
    		try {
    			if(connection != null) {
    				connection.close();
    			}
    			if(preparedstatement != null) {
    				preparedstatement.close();
    			}
    		}catch(SQLException ex) {
    			System.out.println("Erro ao Fechar a conexão com o banco de dados");
    		}
    	}
    	
    }
    
    public static void JogoInativo(int idJogo) {
    	
    	Connection connection = null;
    	PreparedStatement preparedstatement = null;
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		String sql = "UPDATE jogos SET ativo_jogos = 'N' WHERE pk_jogo = ?";
    		
    		preparedstatement = connection.prepareStatement(sql);
    		
    		preparedstatement.setInt(1, idJogo);
    		
    		preparedstatement.executeUpdate();
    		
    		
    	}catch(SQLException e) {
    		System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
    	}finally {
    		try {
    			if(connection != null) {
    				connection.close();
    			}
    			if(preparedstatement != null) {
    				preparedstatement.close();
    			}
    		}catch(SQLException ex) {
    			System.out.println("Erro ao Fechar a conexão com o banco de dados");
    		}
    	}
    	
    }
    
    public static void ativarTodosFilmesEJogos(ArrayList<Integer> listaFilmes, ArrayList<Integer> listaJogos) {
 
    	
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String sql;
    	
    	try {
    		connection = DriverManager.getConnection(stringConnect);
    		
    		if(!listaFilmes.isEmpty()) {
	    		for(int i = 0; i < listaFilmes.size(); i++) {
	    			sql = "UPDATE filmes SET ativo_filmes = 'S' WHERE pk_filmes = ?";
	    			
	    			preparedStatement = connection.prepareStatement(sql);
	    			preparedStatement.setInt(1, listaFilmes.get(i));
	    			preparedStatement.executeUpdate();
	    		}
    		}
    		if(!listaJogos.isEmpty()) {
    			for(int i = 0; i < listaJogos.size(); i++) {
    				sql = "UPDATE jogos SET ativo_jogos = 'S' WHERE pk_jogo = ?";
    				
    				preparedStatement = connection.prepareStatement(sql);
    				preparedStatement.setInt(1, listaJogos.get(i));
    				
    				preparedStatement.executeUpdate();
    			}
    		}
    	}catch(SQLException e) {
    		System.out.println("Não foi possivel se conectar ao banco de dados: " + e.getMessage());
    	}finally {
    		try {
    			if(preparedStatement != null) {
    				preparedStatement.close();
    			}
    			if(connection != null) {
    				connection.close();
    			}
    		}catch(SQLException ex) {
    			System.out.println("Não foi possivel fechar a conexão com o banco de dados");
    		}
    	}
    	
    }
  
    public static void deletarProduto(int idProduto, String tipoDelecao) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String opcao, sql;
    	
		System.out.println("=============================================");
		System.out.println("===== Essa ação pode causar alguns erros ====");
		System.out.println("==== no banco de dados, deseja continuar? ===");
		System.out.println("========== S- Sim ========= N- Não ==========");
		System.out.println("=============================================");
		opcao = teclado.nextLine().toUpperCase();
		
		if(opcao.equalsIgnoreCase("S")) {
	    	try {
	    		
	    		connection = DriverManager.getConnection(stringConnect);
	    		
	    		if(tipoDelecao == "F") {
	    			sql = "DELETE FROM filmes WHERE pk_filmes = ?";
	    			
	    			preparedStatement = connection.prepareStatement(sql);
	    			preparedStatement.setInt(1, idProduto);
	    			
	    			preparedStatement.executeUpdate();
	    			
					System.out.println("=============================================");
					System.out.println("================== Filme ====================");
					System.out.println("========== Deletado com Sucesso =============");
					System.out.println("=============================================");
	    			
	    		}else if(tipoDelecao == "J") {
	    			sql = "DELETE FROM jogos WHERE pk_jogo = ?";
	    			
	    			preparedStatement = connection.prepareStatement(sql);
	    			preparedStatement.setInt(1, idProduto);
	    			
	    			preparedStatement.executeUpdate();
	    			
					System.out.println("=============================================");
					System.out.println("================== Jogo =====================");
					System.out.println("========== Deletado com Sucesso =============");
					System.out.println("=============================================");
	    			
	    		}else {
	    			System.out.println("Algo deu errado com a query para deletar");
	    		}
	    		
	    		
	    	}catch(SQLException e) {
	    		System.out.println("Não foi possivel conectar-se ao banco de dados: " + e.getMessage());
	    	}finally {
	    		try {
	    			if(preparedStatement != null) {
	    				preparedStatement.close();
	    			}
	    			if(connection != null) {
	    				connection.close();
	    			}
	    		}catch(SQLException ex) {
	    			System.out.println("Não foi possivel fechar o banco de dados: " + ex.getMessage());
	    		}
	    	}
		}else{
			System.out.println("=============================================");
			System.out.println("========== A opração foi cancelada ==========");
			System.out.println("=============================================");
			return;
	    	}
    }

    public static void deletarPessoa(int idCliente, String tipoDelecao) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String opcao, sql;
    	
    	DALLocadora.clearConsole();
		System.out.println("=============================================");
		System.out.println("===== Essa ação pode causar alguns erros ====");
		System.out.println("==== no banco de dados, deseja continuar? ===");
		System.out.println("========== S- Sim ========= N- Não ==========");
		System.out.println("=============================================");
		opcao = teclado.nextLine().toUpperCase();
		
		if(opcao.equalsIgnoreCase("S")) {
	    	try {
	    		
	    		connection = DriverManager.getConnection(stringConnect);
	    		
	    		if(tipoDelecao.equalsIgnoreCase("C")) {
	    			sql = "DELETE FROM cliente WHERE pk_cliente = ?";
	    			
	    			preparedStatement = connection.prepareStatement(sql);
	    			preparedStatement.setInt(1, idCliente);
	    			
	    			preparedStatement.executeUpdate();
	    			
	    			DALLocadora.clearConsole();
					System.out.println("=============================================");
					System.out.println("================= Cliente ===================");
					System.out.println("========== Deletado com Sucesso =============");
					System.out.println("=============================================");
	    			
	    		}else if(tipoDelecao.equalsIgnoreCase("F")) {
	    			sql = "DELETE FROM funcionario WHERE pk_funcionario = ?";
	    			
	    			preparedStatement = connection.prepareStatement(sql);
	    			preparedStatement.setInt(1, idCliente);
	    			
	    			preparedStatement.executeUpdate();
	    			
	    			DALLocadora.clearConsole();
					System.out.println("=============================================");
					System.out.println("=============== Funcionario =================");
					System.out.println("========== Deletado com Sucesso =============");
					System.out.println("=============================================");
	    			
	    		}else {
	    			System.out.println("Algo deu errado com a query para deletar");
	    		}
	    		
	    		
	    	}catch(SQLException e) {
	    		System.out.println("Não foi possivel conectar-se ao banco de dados: " + e.getMessage());
	    	}finally {
	    		try {
	    			if(preparedStatement != null) {
	    				preparedStatement.close();
	    			}
	    			if(connection != null) {
	    				connection.close();
	    			}
	    		}catch(SQLException ex) {
	    			System.out.println("Não foi possivel fechar o banco de dados: " + ex.getMessage());
	    		}
	    	}
		}else{
			System.out.println("=============================================");
			System.out.println("========== A opração foi cancelada ==========");
			System.out.println("=============================================");
			return;
	    	}
    }
    
    public static void listarClientes(int listar) {
    	
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	String sql = "";
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		if(listar == 1) {
    			sql = "SELECT pk_cliente, nome_cliente, cpf_cliente, ano_nascimento_cliente, sexo_cliente FROM cliente";
    		}else if(listar == 2) {
    			sql = "SELECT pk_cliente, nome_cliente, cpf_cliente, ano_nascimento_cliente, sexo_cliente FROM cliente WHERE ativo_cliente = 'S'";
    		}else {
    			System.out.println("Opção invalida");
    		}
    		
    		preparedStatement = connection.prepareStatement(sql);
    		resultSet = preparedStatement.executeQuery();
    		
			System.out.println("+-------+----------------------------------------------------+--------------+-----------------+------------+");
			System.out.printf("| %-5s | %-50s | %-12s | %-15s | %-10s |\n", "ID", "Nome", "CPF", "Ano de Nasc", "Sexo");
			System.out.println("+-------+----------------------------------------------------+--------------+-----------------+------------+");
    		
			while(resultSet.next()) {
				int id = resultSet.getInt("pk_cliente");
				String nome = resultSet.getString("nome_cliente");
				String cpf = resultSet.getString("cpf_cliente");
				int anoNascimento = resultSet.getInt("ano_nascimento_cliente");
				String sexo = resultSet.getString("sexo_cliente");
				
				System.out.printf("| %-5d | %-50s | %-12s | %-15d | %-10s |\n", id, nome, cpf, anoNascimento, sexo);
				
			}
			
			System.out.println("+-------+----------------------------------------------------+--------------+-----------------+------------+");
    		
    		
    	}catch(SQLException e){
    		System.out.println("Não foi possivel conectar-se ao banco de daods: " + e.getMessage());
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
    			System.out.println("naõ foi possivel fechar a conexão com o banco de dados");
    		}
    	}
    		
    }
    
    public static void listarFuncionarios(int listar) {
    	
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	String sql = "";
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		if(listar == 1) {
    			sql = "SELECT pk_funcionario, nome_funcionario, cpf_funcionario, ano_nascimento_funcionario, sexo_funcionario, funcao_funcionario FROM funcionario";
    		}else if(listar == 2) {
    			sql = "SELECT pk_funcionario, nome_funcionario, cpf_funcionario, ano_nascimento_funcionario, sexo_funcionario, funcao_funcionario FROM funcionario WHERE ativo_funcionario = 'S'";
    		}else {
    			System.out.println("Opção invalida");
    		}
    		
    		preparedStatement = connection.prepareStatement(sql);
    		resultSet =  preparedStatement.executeQuery();
    		
			System.out.println("+-------+----------------------------------------------------+--------------+-----------------+------------+----------------------+");
			System.out.printf("| %-5s | %-50s | %-12s | %-15s | %-10s | %-20s |\n", "ID", "Nome", "CPF", "Ano de Nasc", "Sexo", "Função");
			System.out.println("+-------+----------------------------------------------------+--------------+-----------------+------------+----------------------+");
    		
			while(resultSet.next()) {
				int id = resultSet.getInt("pk_funcionario");
				String nome = resultSet.getString("nome_funcionario");
				String cpf = resultSet.getString("cpf_funcionario");
				int anoNascimento = resultSet.getInt("ano_nascimento_funcionario");
				String sexo = resultSet.getString("sexo_funcionario");
				String funcao = resultSet.getString("funcao_funcionario");
				
				System.out.printf("| %-5d | %-50s | %-12s | %-15d | %-10s | %-20s |\n", id, nome, cpf, anoNascimento, sexo, funcao);
				
			}
			
			System.out.println("+-------+----------------------------------------------------+--------------+-----------------+------------+----------------------+");
			
    	}catch(SQLException e){
    		System.out.println("Não foi possivel conectar-se ao banco de daods: " + e.getMessage());
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
    			System.out.println("naõ foi possivel fechar a conexão com o banco de dados");
    		}
    	}
    	
    	
    	
    }
    
    public static void ativarFilmeseJogos(int opcao, int pkProduto) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String sql = "";
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		if(opcao == 1) {
    			sql = "UPDATE filmes SET ativo_filmes = 'S' WHERE pk_filmes = ?";
    			
        		preparedStatement = connection.prepareStatement(sql);
        		preparedStatement.setInt(1, pkProduto);
        		
        		preparedStatement.executeUpdate();
    		}else if(opcao == 2) {
    			sql = "UPDATE jogos SET ativo_jogos = 'S' WHERE pk_jogo = ?";
    			
        		preparedStatement = connection.prepareStatement(sql);
        		preparedStatement.setInt(1, pkProduto);
        		
        		preparedStatement.executeUpdate();
    		}else {
    			System.out.println("Algo deu errado com a consulta SQL");
    		}
    		
    	}catch(SQLException e) {
    		System.out.println("Não foi possivel Conectar-se com o banco de dados");
    	}finally {
    		try {
    			if(preparedStatement != null) {
    				preparedStatement.close();
    			}
    			if(connection != null) {
    				connection.close();
    			}
    		}catch(SQLException ex) {
    			System.out.println("Não conseguimos fechar a conexão com o banco de dados");
    		}
    	}
    	
    }
    
    public static void inativarFileseJogos(int opcao, int pkProduto) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String sql = "";
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		if(opcao == 1) {
    			sql = "UPDATE filmes SET ativo_filmes = 'N' WHERE pk_filmes = ?";
    			
        		preparedStatement = connection.prepareStatement(sql);
        		preparedStatement.setInt(1, pkProduto);
        		
        		preparedStatement.executeUpdate();
    		}else if(opcao == 2) {
    			sql = "UPDATE jogos SET ativo_jogos = 'N' WHERE pk_jogo = ?";
    			
        		preparedStatement = connection.prepareStatement(sql);
        		preparedStatement.setInt(1, pkProduto);
        		
        		preparedStatement.executeUpdate();
    		}else {
    			System.out.println("Algo deu errado com a consulta SQL");
    		}
    		
    	}catch(SQLException e) {
    		System.out.println("Não foi possivel Conectar-se com o banco de dados");
    	}finally {
    		try {
    			if(preparedStatement != null) {
    				preparedStatement.close();
    			}
    			if(connection != null) {
    				connection.close();
    			}
    		}catch(SQLException ex) {
    			System.out.println("Não conseguimos fechar a conexão com o banco de dados");
    		}
    	}
    	
    }
    
    public static void ativarClientesFuncionarios(int opcao, int idPessoa) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String sql = "";
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		if(opcao == 1) {
    			sql = "UPDATE funcionario SET ativo_funcionario = 'S' WHERE pk_funcionario = ?";
    			
        		preparedStatement = connection.prepareStatement(sql);
        		preparedStatement.setInt(1, idPessoa);
        		
        		preparedStatement.executeUpdate();
    		}else if(opcao == 2) {
    			sql = "UPDATE cliente SET ativo_cliente = 'S' WHERE pk_cliente = ?";
    			
        		preparedStatement = connection.prepareStatement(sql);
        		preparedStatement.setInt(1, idPessoa);
        		
        		preparedStatement.executeUpdate();
    		}else {
    			System.out.println("Algo deu errado com a consulta SQL");
    		}
    		
    	}catch(SQLException e) {
    		System.out.println("Não foi possivel Conectar-se com o banco de dados");
    	}finally {
    		try {
    			if(preparedStatement != null) {
    				preparedStatement.close();
    			}
    			if(connection != null) {
    				connection.close();
    			}
    		}catch(SQLException ex) {
    			System.out.println("Não conseguimos fechar a conexão com o banco de dados");
    		}
    	}
    	
    }
    
    public static void inativaClientesFuncionarios(int opcao, int idPessoa) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String sql = "";
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		if(opcao == 1) {
    			sql = "UPDATE funcionario SET ativo_funcionario = 'N' WHERE pk_funcionario = ?";
    			
        		preparedStatement = connection.prepareStatement(sql);
        		preparedStatement.setInt(1, idPessoa);
        		
        		preparedStatement.executeUpdate();
    		}else if(opcao == 2) {
    			sql = "UPDATE cliente SET ativo_cliente = 'N' WHERE pk_cliente = ?";
    			
        		preparedStatement = connection.prepareStatement(sql);
        		preparedStatement.setInt(1, idPessoa);
        		
        		preparedStatement.executeUpdate();
    		}else {
    			System.out.println("Algo deu errado com a consulta SQL");
    		}
    		
    	}catch(SQLException e) {
    		System.out.println("Não foi possivel Conectar-se com o banco de dados");
    	}finally {
    		try {
    			if(preparedStatement != null) {
    				preparedStatement.close();
    			}
    			if(connection != null) {
    				connection.close();
    			}
    		}catch(SQLException ex) {
    			System.out.println("Não conseguimos fechar a conexão com o banco de dados");
    		}
    	}
    	
    }
    
    public static void CadastrarFilmesJogos(Produto produto, String tipo, Filmes filme) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String sql = "";
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		if(tipo.equalsIgnoreCase("F")) {
    			System.out.println("1");
    			sql = "INSERT INTO filmes(nome_filme, classificacao_filme, ano_lancamento_filmes, nota_filme) VALUES(?, ?, ?, ?)";
    			preparedStatement = connection.prepareStatement(sql);
    			preparedStatement.setString(1, produto.getNome());
    			preparedStatement.setInt(2, produto.getClassificacao());
    			preparedStatement.setInt(3, produto.getAnoLancamento());
    			preparedStatement.setDouble(4, filme.getNota());
    			
    			preparedStatement.executeUpdate();
    			
    		}else if(tipo.equalsIgnoreCase("J")) {
    			sql = "INSERT INTO jogos(nome_jogo, classificacao_jogo, ano_lancamento_jogo) VALUES(?, ?, ?)";
    			
    			preparedStatement = connection.prepareStatement(sql);
    			preparedStatement.setString(1, produto.getNome());
    			preparedStatement.setInt(2, produto.getClassificacao());
    			preparedStatement.setInt(3, produto.getAnoLancamento());
    			
    			preparedStatement.executeUpdate();
    			
    		}else {
    			System.out.println("Algo deu errado na inserção dos dados");
    		}
    		
    		
    	}catch(SQLException e) {
    		System.out.println("Não foi possivel conectar-se ao banco de dados");
    	}finally {
    		try {
    			if(preparedStatement != null) {
    				preparedStatement.close();
    			}
    			if(connection != null) {
    				connection.close();
    			}
    		}catch(SQLException ex) {
    			System.out.println("Não foi possivel fechar a conexão com o banco de dados");
    		}
    	}
    	
    }

    public static void CadastrarClienteseFuncionarios(Pessoas pessoa, String tipo, String funcao) {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	String sql = "";
    	
    	try {
    		
    		connection = DriverManager.getConnection(stringConnect);
    		
    		if(tipo.equalsIgnoreCase("c")) {
    			System.out.println("1");
    			sql = "INSERT INTO cliente(nome_cliente, cpf_cliente, ano_nascimento_cliente, sexo_cliente) VALUES(?, ?, ?, ?)";
    			preparedStatement = connection.prepareStatement(sql);
    			preparedStatement.setString(1, pessoa.getNome());
    			preparedStatement.setString(2, pessoa.getCpf());
    			preparedStatement.setInt(3, pessoa.getAnoNascimento());
    			preparedStatement.setString(4, pessoa.getSexo().toUpperCase());
    			
    			preparedStatement.executeUpdate();
    			
    		}else if(tipo.equalsIgnoreCase("f")) {
    			sql = "INSERT INTO funcionario(nome_funcionario, cpf_funcionario, ano_nascimento_funcionario, sexo_funcionario, funcao_funcionario) VALUES(?, ?, ?, ?, ?)";
    			
    			preparedStatement = connection.prepareStatement(sql);
    			preparedStatement.setString(1, pessoa.getNome());
    			preparedStatement.setString(2, pessoa.getCpf());
    			preparedStatement.setInt(3, pessoa.getAnoNascimento());
    			preparedStatement.setString(4, pessoa.getSexo());
    			preparedStatement.setString(5, funcao);
    			
    			preparedStatement.executeUpdate();
    			
    		}else {
    			System.out.println("Algo deu errado na inserção dos dados");
    		}
    		
    		
    	}catch(SQLException e) {
    		System.out.println("Não foi possivel conectar-se ao banco de dados");
    	}finally {
    		try {
    			if(preparedStatement != null) {
    				preparedStatement.close();
    			}
    			if(connection != null) {
    				connection.close();
    			}
    		}catch(SQLException ex) {
    			System.out.println("Não foi possivel fechar a conexão com o banco de dados");
    		}
    	}
    	
    }
    
	public static void mostrarClientes() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "";
		
		try {
		
			connection = DriverManager.getConnection(stringConnect);

			sql = "SELECT pk_cliente, nome_cliente, cpf_cliente, ano_nascimento_cliente, sexo_cliente FROM cliente";

			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
		
			System.out.println("+-------+----------------------------------------------------+-----------------+---------------------------+-------+");
			System.out.printf("| %-5s | %-50s | %-15s | %-25s | %-5s |\n", "ID", "Nome", "CPF", "Ano Nascimento Cliente", "Sexo");
			System.out.println("+-------+----------------------------------------------------+-----------------+---------------------------+-------+");
			
			while(resultSet.next()) {
				int id = resultSet.getInt("pk_cliente");
				String nome = resultSet.getString("nome_cliente");
				String cpf_cliente = resultSet.getString("cpf_cliente");
				int anoNascimentoCliente = resultSet.getInt("ano_nascimento_cliente");
				String sexoCliente = resultSet.getString("sexo_cliente");
				
				System.out.printf("| %-5d | %-50s | %-15s | %-25d | %-5s |\n", id, nome, cpf_cliente, anoNascimentoCliente, sexoCliente);
				
			}
			
			System.out.println("+-------+----------------------------------------------------+-----------------+---------------------------+-------+");
			
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
	
	public static void mostrarFuncionario() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "";
		
		try {
		
			connection = DriverManager.getConnection(stringConnect);

			sql = "SELECT pk_funcionario, nome_funcionario, cpf_funcionario, ano_nascimento_funcionario, sexo_funcionario, funcao_funcionario FROM funcionario";

			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
		
			System.out.println("+-------+----------------------------------------------------+-----------------+---------------------------+-------+-----------------+");
			System.out.printf("| %-5s | %-50s | %-15s | %-25s | %-5s | %-15s |\n", "ID", "Nome", "CPF", "Ano Nascimento Cliente", "Sexo", "Função");
			System.out.println("+-------+----------------------------------------------------+-----------------+---------------------------+-------+-----------------+");
			
			while(resultSet.next()) {
				int id = resultSet.getInt("pk_funcionario");
				String nome = resultSet.getString("nome_funcionario");
				String cpf_cliente = resultSet.getString("cpf_funcionario");
				int anoNascimentoCliente = resultSet.getInt("ano_nascimento_funcionario");
				String sexoCliente = resultSet.getString("sexo_funcionario");
				String funcaoFuncionario = resultSet.getString("funcao_funcionario");
				
				System.out.printf("| %-5d | %-50s | %-15s | %-25d | %-5s | %-15s |\n", id, nome, cpf_cliente, anoNascimentoCliente, sexoCliente, funcaoFuncionario);
				
			}
			
			System.out.println("+-------+----------------------------------------------------+-----------------+---------------------------+-------+-----------------+");
			
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
