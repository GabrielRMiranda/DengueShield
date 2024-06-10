package DAO;

import Classes.CriptografiaSenha;
import Classes.Login;
import Classes.Usuario;
import Global.Util;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
public class UsuarioDAO {
    private Connection conectar;
    private Map<String, Usuario> usuarios = new HashMap<>();
    public UsuarioDAO(Connection conectar){
        this.conectar = conectar;
    }

    public void novoUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario (nome,email,cpf,DataCadastro,tipoCadastroId,hash,salt) VALUES (?,?,?,?,?,?,?)";

        String dataCadastro = Util.RegistraDataAtual();
        String cpfFormatado = Util.formataCPF(usuario.getCpf());

        try(PreparedStatement registraDB = conectar.prepareStatement(sql)){

            registraDB.setString(1, usuario.getNome());
            registraDB.setString(2, usuario.getEmail());
            registraDB.setString(3, cpfFormatado);
            registraDB.setString(4, dataCadastro);
            registraDB.setInt(5, usuario.getTipoDeCadastro().getId());
            registraDB.setString(6, usuario.getHash());
            registraDB.setString(7, usuario.getSalt());
            registraDB.executeUpdate();

        } catch (SQLException e){
            System.out.println("Erro ao registrar as informações no banco de dados:" + e.getMessage());
        }
    }

    public boolean ValidaLogin(String email, String senha) throws NoSuchAlgorithmException {
        String sqlValidaLogin = "SELECT id, hash, salt FROM Usuario WHERE email = ?";
        try (PreparedStatement validaDB = conectar.prepareStatement(sqlValidaLogin)) {
            validaDB.setString(1, email);
            ResultSet rs = validaDB.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("hash");
                String storedSalt = rs.getString("salt");
                String hashCalculated = CriptografiaSenha.gerarHash(senha, storedSalt);
                if (storedHash.equals(hashCalculated)){
                    return rs.getBoolean("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar login: " + e.getMessage());
        }
        return false;
    }

    public void realizaLogin(Login login, String email, String senha) throws NoSuchAlgorithmException {
        if (login.login(email, senha)) {
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Credenciais inválidas.");
        }
    }

}
