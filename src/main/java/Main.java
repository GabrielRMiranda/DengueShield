import API.ApiConnection;
import Classes.Login;
import Classes.TipoCadastro;
import Classes.Usuario;
import DAO.UsuarioDAO;
import Global.Util;
import Validadores.UsuarioLogin;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Connection conectar = Util.getConnection();

        UsuarioDAO usuarioDAO = new UsuarioDAO(conectar);
        Login.realizaLogin();

        /*ApiConnection.printCepData("85901080");*/


        /*UsuarioDAO.alterarSenha("T01@teste.com","S001");*/
    }
}