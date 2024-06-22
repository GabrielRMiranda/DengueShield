import API.ApiConnection;
import Classes.*;
import DAO.*;
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
        /*Login.realizaLogin();*/

        /*ApiConnection.printCepData("85901080");*/
        /*UsuarioDAO.alterarSenha("T01@teste.com","S001");*/


        /*Simulação de login*/
        String email = "T03@teste.com";
        String senha = "S03";

        Usuario usuarioLogado = Login.realizaLogin(email, senha);
        if (usuarioLogado != null) {
            Login.setUsuarioLogado(usuarioLogado);
            System.out.println("Usuário logado: " + Login.getUsuarioLogado().getNome());

            EnderecoDAO enderecoDAO = new EnderecoDAO(conectar);
            DenunciaDAOST denunciaDAO = new DenunciaDAOST(conectar);
            AnexoDAO anexoDAO = new AnexoDAO(conectar);

            String cep = "85901080";
            String numero = "123";
            String complemento = "Apto 101";
            int enderecoId = enderecoDAO.cadastraEnderecoAPI(cep, numero, complemento);

            if (enderecoId != -1) {
                String descricao = "Descrição da denúncia.";

                int denuncianteId = Login.getUsuarioLogado().getId();

                String linkAnexo = "link/do/anexo";
                Anexo anexo = new Anexo(linkAnexo);
                int anexoId = anexoDAO.salvarAnexo(anexo);

                Denuncia denuncia = new Denuncia(denuncianteId, enderecoId, descricao, anexoId);

                denunciaDAO.novaDenuncia(denuncia);
                System.out.println("Denúncia cadastrada com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o endereço.");
            }
        } else {
            System.out.println("Falha ao autenticar usuário.");
        }

    }
}