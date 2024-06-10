package Classes;

import DAO.UsuarioDAO;

import java.security.NoSuchAlgorithmException;

public class Login {
    private UsuarioDAO usuarioDAO;

    public Login(UsuarioDAO usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }

    public boolean login(String email, String senha) throws NoSuchAlgorithmException {
        return usuarioDAO.ValidaLogin(email, senha);
    }
}
