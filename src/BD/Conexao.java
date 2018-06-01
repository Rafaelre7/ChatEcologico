package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection getConexao() {
		try {
			
			String url = "jdbc:h2:C://BancoAps/projetoChatEcologico;user=java;password=rafaeljoeber;AUTO_SERVER=TRUE";
			
			Connection minhaConexao = null;
			
			minhaConexao = DriverManager.getConnection(url);

			return minhaConexao;

		} catch (SQLException erro) {
			System.out.println("ACONTECEU UM ERRO");
			erro.printStackTrace();

			return null;
		}
	}
}
