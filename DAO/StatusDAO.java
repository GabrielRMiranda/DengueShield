package DAO;

import BancoDados.ConexaoBancoDados;
import Classes.Status;
import Global.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusDAO {
    public StatusDAO() {}

    public static void novoStatus(Status status) {
        String sql = "INSERT INTO Status(descricao, dataCadastro) VALUES (?, ?)";
        PreparedStatement statement = null;

        String dataCadastro = Util.RegistraDataAtual();

        try {
            Connection conn = ConexaoBancoDados.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, status.getDescricao());
            statement.setString(2, dataCadastro);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar novo status: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static void alterarStatus(Status status) {
        String sql = "UPDATE Status SET descricao = ? WHERE ID = ?";
        PreparedStatement statement = null;

        try {
            Connection conn = ConexaoBancoDados.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, status.getDescricao());
            statement.setInt(2, status.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status: " + e.getMessage());
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static void inativarStatus(int ID) {
        Util.inativarCadastro(ID, "Status");
    }

    public static void retornarDeununcia() {
        Util.consultaTabela("Status");
    }
}
