import API.ApiConnection;
import BancoDados.ConexaoBancoDados;
import Classes.Denuncia;
import Classes.Login;
import Classes.TipoCadastro;
import Classes.Usuario;
import DAO.DenunciaDAO;
import DAO.UsuarioDAO;
import Global.Util;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception{
        /*CADASTRO DE USUÁRIO*/

        try {
            Connection conectar = ConexaoBancoDados.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conectar);

            TipoCadastro Civil = new TipoCadastro(1);
            TipoCadastro Vistoriador = new TipoCadastro(2);

            Usuario usuario = new Usuario("Zero","74191783817","teste10@teste.com","testeZero1",Civil);
            usuarioDAO.novoUsuario(usuario);

            Login login = new Login(usuarioDAO);


            /*LOGIN*/

            String email = "teste0@teste.com";
            String senha = "testeZero";

            usuarioDAO.ValidaLogin(email,senha);

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

        System.out.println("\n");


        /*CADASTRO DE DENUNCIA E CONSULTA DE ANEXO*/
        /*Connection conn = ConexaoBancoDados.getConnection();
        DenunciaDAO denunciaDAO = new DenunciaDAO(conn);

        try{
            Denuncia denuncia = new Denuncia("");
            int denunciaId = denunciaDAO.geraDenuncia(denuncia);
            int enderecoId = denunciaDAO.inserirEnderecoPorApi("85901080","235","Esquina com a Machinne pinturas.");
            if (enderecoId > 0){
                denunciaDAO.atualizarDenunciaComEndereco(denunciaId, enderecoId);
            }

            int anexoId = denunciaDAO.inserirAnexo("https://s2-g1.glbimg.com/YN6SaPDGMoTtCEF_dFCupujkf-M=/0x0:796x448/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2020/f/R/q2ztjtSjONmmyH1AYNcw/49721237182-bbe3e499a4-o.jpg");
            if(anexoId > 0){
                denunciaDAO.atualizarDenunciaComAnexo(denunciaId,anexoId);
            }

            String linkAnexo = denunciaDAO.acessaLinkDoAnexo(5);
            Util.AbrirImagemNoNavegador.abrirImagemNoNavegador(linkAnexo);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }*/
        }
    }
}