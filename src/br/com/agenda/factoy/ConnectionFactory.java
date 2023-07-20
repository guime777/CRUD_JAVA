package br.com.agenda.factoy;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//Nome do usuario mysql
	private static final String USERNAME = "root";
	
	//senha do banco
	private static final String PASSWORD = "root";
		
	//Caminho do banco de dados, porta
	private static final String DATABASE_URL = "jdbc:mariadb://127.0.0.1/agenda";

	/*
	 * conexao com o banco de dados 
	 */
public static Connection createConnectionToMysql() throws Exception {
	//faz com que a classe seja carregada pela JVM 
	Class.forName("org.mariadb.jdbc.Driver");
	
	//cria a conexao com obanco de dados 
	Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

return connection;

}


public static void main (String[]args) throws Exception {
	//recuperar uma conexao com o banco de dados 
	Connection con = createConnectionToMysql();
	
	//testar se a conexao e nula 
	if(con!=null) {
		System.out.println("conexão obtida ");
		con.close();
	}
}
}