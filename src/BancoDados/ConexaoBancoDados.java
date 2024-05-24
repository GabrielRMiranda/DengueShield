package BancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
        public static Connection getConnection(){
            String url = "jdbc:sqlite:C:/Users/lgvip/DataGripProjects/CRUD/identifier.sqlite";
            Connection connection = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(url);
                System.out.println("Conexão com o banco de dados SQLite estabelecida.");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver SQLite JDBC não encontrado.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados SQLite.");
                e.printStackTrace();
            }
            return connection;
        }
}
