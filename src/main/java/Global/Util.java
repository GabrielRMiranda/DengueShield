package Global;

import Classes.TipoCadastro;
import Classes.Usuario;
import DAO.UsuarioDAO;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Util {

    public static String formataCPF(String cpf){ /*Usado para formatar o CPF na hora de registrar no db*/
        cpf = cpf.replaceAll("\\D", "");
        return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String RegistraDataAtual(){ /*Usado para registrar a data de operação no padrão ISO8601, seja ela cadastro ou inativação*/
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String dataRegistro = dataAtual.format(formataData);
        return dataRegistro;
    }

    public static void inativarCadastro(int id, String tabela) { /*Arthur*/
        String sql = "UPDATE " + tabela + " SET dataInativacao = ? WHERE ID = ?";
        PreparedStatement pstmt = null;
        String dataInativacao = Util.RegistraDataAtual();

        try {
            Connection conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dataInativacao);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Inativado.");
        } catch (SQLException e) {
            System.err.println("Erro ao inativar: " + e.getMessage());
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static void consultaTabela(String tabela) { /*Arthur*/
        String sql = "SELECT * FROM " + tabela + " WHERE dataInativacao IS NULL";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();) {

            int colunas = rs.getMetaData().getColumnCount();

            while(rs.next()) {
                for(int i = 1; i<= colunas; i++){
                    System.out.println(rs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao retornar dados: " + e.getMessage());
        }
    }

    public class AbrirImagemNoNavegador {
        public static void abrirImagemNoNavegador(String url) {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI(url);
                    desktop.browse(uri);
                    System.out.println("Abrindo imagem no navegador...");
                } catch (IOException | URISyntaxException e) {
                    System.out.println("Erro ao abrir a imagem no navegador: " + e.getMessage());
                }
            } else {
                System.out.println("A abertura automática do navegador não é suportada no seu sistema.");
            }
        }
    }

    public static Connection getConnection(){
        String url = "jdbc:sqlite:C:/Users/lgvip/DataGripProjects/CRUD/identifier.sqlite";
        Connection conexao = null;

        try{
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver SQLITE JDBC não encontrado." + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados." + e.getErrorCode() +":"+e.getMessage());
        }
        return conexao;
    }

}
