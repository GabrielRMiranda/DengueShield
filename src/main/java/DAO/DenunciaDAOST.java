package DAO;

import Classes.Denuncia;
import Classes.Usuario;
import Global.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DenunciaDAOST {

    private static Connection conectar = Util.getConnection();

    public DenunciaDAOST(Connection conectar){
        this.conectar = Util.getConnection();
    }

    public static void novaDenuncia(Denuncia denuncia){
        String sql = "INSERT INTO Denuncia (denuncianteId, statusId, enderecoId, descricao, anexoId,dataAbertura) VALUES (?,?,?,?,?,?)";
        String dataAbertura = Util.RegistraDataAtual();

        try(PreparedStatement pstmt = conectar.prepareStatement(sql)){
            pstmt.setInt(1, denuncia.getDenuncianteId());
            pstmt.setInt(2, denuncia.getStatusId());
            pstmt.setInt(3, denuncia.getEnderecoId());
            pstmt.setString(4, denuncia.getDescricao());
            pstmt.setInt(5, denuncia.getAnexoId());
            pstmt.setString(6, dataAbertura);
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Código: " + e.getErrorCode() + "\nMensagem: " + e.getMessage());
        }
    }



}
