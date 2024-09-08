package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import src.model.Genero;

public class Locadora {

	private static final String stringConnect = "jdbc:sqlite:/C:\\Users\\pc\\Desktop\\Projeto_Locadora\\Locadora_Filmes_e_Jogos\\Locadora\\Locadora.db";
	
	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		int opcao = 99;
		
		do {
			
			System.out.println("+----------------------------+");
			System.out.println("|        MENU PRINCIPAL      |");
			System.out.println("+----------------------------+");
			System.out.println("| 1. Generos                 |");
			System.out.println("| 2. Filmes                  |");
			System.out.println("| 3. Jogos                   |");
			System.out.println("| 4. Clientes                |");
			System.out.println("| 5. Funcionarios            |");
			System.out.println("| 0. SAIR                    |");
			System.out.println("+----------------------------+");
			
			opcao = scanner.nextInt();
			
			switch(opcao) {
			case 1:
				menuGenero(scanner);
				break;
			}
			
		}while (opcao != 0);
		
		scanner.close();
			
		}
	
	//Sub Menus.
	public static void menuGenero(Scanner scanner) {
		int opcaoGenero;
		
		do {
			System.out.println("+-----------------------------+");
			System.out.println("|       MENU DE GENEROS       +");
			System.out.println("+-----------------------------+");
			System.out.println("| 1. Cadastrar Genero         |");
			System.out.println("| 2. Alterar Genero           |");
			System.out.println("| 3. Listar todos os Generos  |");
			System.out.println("| 4. Deletar Genero           |");
			System.out.println("| 0. SAIR                     |");
			System.out.println("+-----------------------------+");
			
			opcaoGenero = scanner.nextInt();
			scanner.nextLine();
			
			switch(opcaoGenero) {
			case 1:
				System.out.println("Digite o nome do Genero para cadastro ?");
				String nomeGenero = scanner.nextLine();
				Genero genero = new Genero(nomeGenero);
				inserirGenero(genero);
				
				break;
			case 2:
				System.out.println("Qual Genero deseja alterar ?");
				int idGeneroAlterar = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Qual o novo nome para o Genero ?");
				String novoNome = scanner.nextLine();
				atualizarGenero(idGeneroAlterar, novoNome);
				break;
			case 3:
				listarGeneros();
				break;
			case 4:
				System.out.println("Deseja deletar qual registro do banco de dados ?");
				int idGeneroDeletar = scanner.nextInt();
				scanner.nextLine();
				System.out.println("+-------------------------------+");
				System.out.println("|     Deletar Genero Cuidado    |");
				System.out.println("+-------------------------------+");
				System.out.println("|    A deleção de um genero     |");
				System.out.println("|    pode acabar trazendo       |");
				System.out.println("|    erros ao banco de dados    |");
				System.out.println("| por conta que outros registros|");
				System.out.println("| depende deste registro para   |");
				System.out.println("| eles existirem.               |");
				System.out.println("+-------------------------------+");
				
				System.out.println("Deletar mesmo assim 1-(SIM) 2-(Não)");
				int response = scanner.nextInt();
				scanner.nextLine();
				if(response == 1) {
					deletarGenero(idGeneroDeletar);
				}
				break;
			}
			
		}while (opcaoGenero != 0);
	
	}
	
	public static void inserirGenero(Genero genero) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			String sql = "INSERT INTO genero(nome_genero) VALUES(?)";
			preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,genero.getNome());
			preparedStatement.executeUpdate();
			
			ResultSet generateKeys = preparedStatement.getGeneratedKeys();
			if (generateKeys.next()) {
				long id = generateKeys.getLong(1);
				System.out.println("Genero cadastrado com ID = " + id);
			}else {
				System.out.println("Nenhuma chave Gerada");
			}
		}catch(SQLException e) {
			System.out.println("Erro ao conectar-se ao banco de dados " + e.getMessage());
		}finally {
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
			}catch(SQLException ex){
				System.out.println("Erro ao fechar a conexão com o banco");
			}
		}
	}

	public static void atualizarGenero(long id, String novoNome) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			String sql = "UPDATE genero SET nome_genero = ? WHERE pk_genero = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, novoNome);
			preparedStatement.setLong(2, id);
			
			int rowAffected = preparedStatement.executeUpdate();
			if(rowAffected > 0) {
				System.out.println("Genero foi alterado com sucesso!");
				
			}else {
				System.out.println("Nenhum Genero encontrado com o ID:" + id);
			}
			
		}catch(SQLException e){
			System.out.println("Erro ao conectarse ao banco de dados: " + e.getMessage());
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex){
				System.out.println("Erro ao fechar a conexão com o banco de dados: ");
			}
		}
		
	}
	
	public static void deletarGenero(long id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			String sql = "DELETE FROM genero WHERE pk_genero = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, id);
			
			int rowAffected = preparedStatement.executeUpdate();
			
			if (rowAffected > 0) {
				System.out.println("O dado foi deletado com sucesso");
			}
			else {
				System.out.println("O id:" + id + "Não foi encontrado na base de dados");
			}
		}catch(SQLException e) {
			System.out.println("Não foi possivel se conectar ao banco de dados: " + e.getMessage());
		}finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException ex){
				System.out.println("Erro ao fechar a conexão com o banco de dados");
			}
		}
	}

	public static void listarGeneros() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(stringConnect);
			String sql = "SELECT * FROM genero";
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			
            System.out.println("+----------------+-------------------------+");
            System.out.printf("| %-6s | %-23s |\n", "ID Gênero", "Nome Gênero");
            System.out.println("+----------------+-------------------------+");
            
			while(resultSet.next()) {
				int id = resultSet.getInt("pk_genero");
				String nome = resultSet.getString("nome_genero");
				
				System.out.printf("| %-6d | %23s |\n", id, nome);
			}
			
			System.out.println("+----------------+-------------------------+");
			
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
}
