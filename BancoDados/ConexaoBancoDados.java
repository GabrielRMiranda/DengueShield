package BancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
    public static Connection getConnection(){
        String url = "jdbc:sqlite:C:/Users/lgvip/DataGripProjects/CRUD/identifier.sqlite";
        Connection conexao = null;

        try{
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection(url);
            System.out.println("Conexão com o banco de dados realizada.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver SQLITE JDBC não encontrado." + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados." + e.getErrorCode() +":"+e.getMessage());
        }
        return conexao;
    }

}
