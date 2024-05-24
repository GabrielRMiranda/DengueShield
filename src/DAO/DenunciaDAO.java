package DAO;

import BancoDados.ConexaoBancoDados;
import Classes.Denuncia;
import Globais.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DenunciaDAO {
    public DenunciaDAO() {}

    public void novaDenuncia(Denuncia denuncia) throws SQLException{
        String sql = "INSERT INTO Denuncia(id_denuncia, id_usuario, id_status,cep,logradouro,bairro,complemento,cidade,estado, descricao, dataAbertura,dataFechamento,pais) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
        Connection conectar = ConexaoBancoDados.getConnection();
        PreparedStatement statement = conectar.prepareStatement(sql);

        try {
            statement.setString(1, denuncia.getCep());
            statement.setString(2, denuncia.getLogradouro());
            statement.setString(3, denuncia.getBairro());
            statement.setString(4, denuncia.getComplemento());
            statement.setString(5, denuncia.getCidade());
            statement.setString(6, denuncia.getEstado());
            statement.setString(7, denuncia.getPais());
            statement.setString(8, denuncia.getDescricao());
            statement.setString(9,Util.RegistraDataAtual());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar nova denuncia: " + e.getMessage());
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

    /*public static void alterarDenuncia(Denuncia denuncia) {
        String sql = "UPDATE Denuncia SET ID_status = ?, endereco = ?, descricao = ? WHERE ID = ?";
        PreparedStatement pstmt = null;

        try {
            Connection conn = ConexaoBancoDados.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, denuncia.getID_status());
            pstmt.setString(2, denuncia.getEndereco());
            pstmt.setString(3, denuncia.getDescricao());
            pstmt.setInt(4, denuncia.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar denúncia: " + e.getMessage());
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }*/

    public static void inativarDenuncia(int ID) {
        Util.inativarCadastro(ID, "Denuncia");
    }

    public static void retornarDeununcia() {
        Util.retornarTabela("Denuncia");
    }
}
