import BancoDados.ConexaoBancoDados;
import Classes.*;
import DAO.DenunciaDAO;
import DAO.StatusDAO;
import DAO.UsuarioDAO;
import Globais.Util;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
       /* try {
            Connection conectar = ConexaoBancoDados.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conectar);

            TipoCadastro Civil = new TipoCadastro(1);
            TipoCadastro Vistoriador = new TipoCadastro(2);

            Login login = new Login(usuarioDAO);

            String email = "teste7@gmail.com";
            String senha = "senhaSegura1";

            if (login.login(email, senha)) {
                System.out.println("Login realizado com sucesso!");
            } else {
                System.out.println("Credenciais inválidas.");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Erro: Algoritmo de hash não encontrado.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Erro: Salt não pode ser null.");
        }*/

        /*System.out.println(Util.enderecoCEP("85901080"));*/

        try {
            DenunciaDAO denunciaDAO = new DenunciaDAO();

            Denuncia denuncia = new Denuncia.Builder().comCep("85901080").comComplemento("APTO 101").comDescricao("TA COM DENGUE AQUI HEIN").build();

            denunciaDAO.novaDenuncia(denuncia);
            System.out.println("Denuncia registrada.");
        } catch(Exception e){
            System.out.println("Erro ao registrar denuncia: " + e.getMessage());
        }
    }
}