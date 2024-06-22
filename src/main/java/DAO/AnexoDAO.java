package DAO;

import Classes.Anexo;
import Global.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnexoDAO {
    private static Connection conectar = Util.getConnection();

    public AnexoDAO(Connection conectar) {
        AnexoDAO.conectar = conectar;
    }

    public static int salvarAnexo(Anexo anexo) {
        String sql = "INSERT INTO Anexo (link, dataAnexo) VALUES (?, ?)";
        String dataAnexo = Util.RegistraDataAtual();
        int generatedId = -1;

        try (PreparedStatement pstmt = conectar.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, anexo.getLink());
            pstmt.setString(2, dataAnexo);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Código: " + e.getErrorCode() + "\nMensagem: " + e.getMessage());
        }

        return generatedId;
    }
}
