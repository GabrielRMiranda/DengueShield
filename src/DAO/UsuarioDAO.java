package DAO;

import Classes.CriptografiaSenha;
import Classes.TipoCadastro;
import Classes.Usuario;
import Globais.Util;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioDAO {
   private Connection conectar;
   private Map<String, Usuario> usuarios = new HashMap<>();
    public UsuarioDAO(Connection conectar){
        this.conectar = conectar;
    }

    public void novoUsuario(Usuario usuario) throws SQLException{
        String querySQL = "INSERT INTO usuarios (nome,email,cpf,DataCadastro,id_TipoCadastro,hash,salt) VALUES (?,?,?,?,?,?,?)";

        try(PreparedStatement statement = conectar.prepareStatement(querySQL)){
            String cpfFormatado = Util.formataCPF(usuario.getCpf());

            String dataCadastro = Util.RegistraDataAtual();

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, cpfFormatado);
            statement.setString(4, dataCadastro);
            statement.setInt(5, usuario.getTipoDeCadastro().getId());
            statement.setString(6,usuario.getHash());
            statement.setString(7, usuario.getSalt());
            statement.executeUpdate();
        }
    }
    public boolean ValidaLogin(String email, String senha) throws NoSuchAlgorithmException{
        String sqlValidaLogin = "SELECT hash, salt FROM Usuarios WHERE email = ?";

        try (PreparedStatement statement = conectar.prepareStatement(sqlValidaLogin)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("hash");
                String storedSalt = rs.getString("salt");
                String hashCalculated = CriptografiaSenha.gerarHash(senha, storedSalt);
                return storedHash.equals(hashCalculated);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void editarUsuario(Usuario usuario) throws SQLException{
        String querySQLEdita = "UPDATE usuarios SET nome = ?, email = ?, cpf = ? WHERE id = ?";
        try(PreparedStatement statement = conectar.prepareStatement(querySQLEdita)){
            String cpfFormatado = Util.formataCPF(usuario.getCpf());
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, cpfFormatado);
            statement.setInt(4, usuario.getId());
            statement.executeUpdate();
        }
    }

    public void inativarUsuario(int id) throws SQLException{
       String querySQLInativos = "UPDATE Usuarios SET id_Status = ?, dataInativado = ? WHERE id = ?";

       try(PreparedStatement statement = conectar.prepareStatement(querySQLInativos)){
        int statusInativo = 2;

        String dataInativado = Util.RegistraDataAtual();

        statement.setInt(1, statusInativo);
        statement.setString(2, dataInativado);
        statement.setInt(3, id);
        statement.executeUpdate();
       }

   }

}