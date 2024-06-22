package Classes;

import DAO.UsuarioDAO;
import Global.Util;
import Validadores.UsuarioLogin;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private UsuarioDAO usuarioDAO;

    private static Usuario usuarioLogado;

    public Login(){}

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogin) {
        usuarioLogado = usuarioLogin;
    }

    public static boolean validaLogin(String email, String senha) throws NoSuchAlgorithmException {
        String sql = "SELECT id, hash, salt FROM Usuario WHERE email = ?";

        try(PreparedStatement pstmt = Util.getConnection().prepareStatement(sql)){
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String hashArmazenado = rs.getString("hash");
                String saltArmazenado = rs.getString("salt");
                String hashCalculado = CriptografiaSenha.gerarHash(senha, saltArmazenado);
                if(hashArmazenado.equals(hashCalculado)){
                    return rs.getBoolean("id");
                }
            }
        } catch (SQLException e){
            System.out.println("Erro código: " + e.getErrorCode() + "\n" + "Mensagem: " + e.getMessage());
        }
        return false;
    }

    public static Usuario realizaLogin(String email, String senha) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        try (Connection conectar = Util.getConnection();
             PreparedStatement pstmt = conectar.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hash = rs.getString("hash");
                    String salt = rs.getString("salt");

                    if (CriptografiaSenha.validarSenha(senha, hash, salt)) {
                        Usuario usuario = new Usuario();
                        usuario.setId(rs.getInt("id"));
                        usuario.setNome(rs.getString("nome"));
                        usuario.setEmail(rs.getString("email"));
                        usuario.setCpf(rs.getString("cpf"));
                        setUsuarioLogado(usuario);
                        return usuario;
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
        }
        return null;
    }


}
