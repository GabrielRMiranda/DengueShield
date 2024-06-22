package Classes;

import DAO.UsuarioDAO;
import Global.Util;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    private UsuarioDAO usuarioDAO;

    public Login(UsuarioDAO usuarioDAO){
        this.usuarioDAO = usuarioDAO;
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

    public static void realizaLogin(String email, String senha) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if(validaLogin(email,senha)){
            System.out.println("Login bem sucedido!");
        } else {
            System.out.println("Credenciais inválidas.");
        }
    }

}
