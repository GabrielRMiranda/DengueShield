package Globais;

import BancoDados.ConexaoBancoDados;
import Classes.TipoCadastro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Classes.Usuario;
import org.json.JSONObject;

public interface Util {
    public static String formataCPF(String cpf){ /*Usado para formatar o CPF na hora de registrar no db*/
        cpf = cpf.replaceAll("\\D", "");
        return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String RegistraDataAtual(){ /*Usado para registrar a data de operação no padrão ISO8601, seja ela cadastro ou inativação*/
        LocalDateTime dataAtual = LocalDateTime.now();

        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        String dataRegistro = dataAtual.format(formataData);

        return dataRegistro;
    }

    public static void inativarCadastro(int id, String tabela) {
        String sql = "UPDATE " + tabela + " SET dataInativacao = ? WHERE ID = ?";
        PreparedStatement pstmt = null;

        try {
            Connection conn = ConexaoBancoDados.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, Date.valueOf(LocalDate.now()));
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inativar a denuncia: " + e.getMessage());
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static void retornarTabela(String tabela) {
        String sql = "SELECT * FROM " + tabela + " WHERE dataInativacao IS NULL";

        try (Connection conn = ConexaoBancoDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();) {

            int colunas = rs.getMetaData().getColumnCount();

            while(rs.next()) {
                for(int i = 1; i<= colunas; i++){
                    System.out.println(rs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao retornar dados: " + e.getMessage());
        }
    }

    public static void defineTipoCadastro(){
        TipoCadastro civil = new TipoCadastro(1);
        TipoCadastro vistoriador = new TipoCadastro(2);
    }

    public static JSONObject cepAPI(String cep) throws Exception{
        String url = "http://viacep.com.br/ws/" + cep + "/json/";
        URL objeto = new URL(url);
        HttpURLConnection conectarUrl = (HttpURLConnection) objeto.openConnection();

        conectarUrl.setRequestMethod("GET");

        int resposta = conectarUrl.getResponseCode();
        if(resposta == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conectarUrl.getInputStream()));
            String retornoApiLine;
            StringBuffer respostaApi = new StringBuffer();

            while ((retornoApiLine = in.readLine()) != null) {
                respostaApi.append(retornoApiLine);
            }

            in.close();

            JSONObject jsonResposta = new JSONObject(respostaApi.toString());
            if (jsonResposta.has("Erro")) {
                throw new Exception("Cep inválido!");
            }
            return (JSONObject) jsonResposta;
        } else {
            throw new Exception("Erro ao consultar o CEP.");
        }

    }

   /* public static List<String> enderecoCEP(String cep) throws Exception {
        List<String> endereco = new ArrayList<>();
        try {
            String url = "http://viacep.com.br/ws/" + cep + "/json/";
            URL urlGet = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlGet.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            BufferedReader in;
            if(status > 299) {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }

            String inputLine; Aplicar o filtro de resposta da API aqui
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                endereco.add(inputLine);
            }

            in.close();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return endereco;
    }



   public List<Usuario> listarUsuarios() throws SQLException{
       List<Usuario> usuarios = new ArrayList<>();
       String querySQLLista = "SELECT " +
               "Usuarios.id, " +
               "Usuarios.nome, " +
               "Usuarios.cpf AS CPF, " +
               "Usuarios.email, " +
               "Usuarios.id_TipoCadastro, " +
               "CASE " +
               "    WHEN Usuarios.id_TipoCadastro = 1 THEN 'Civil' " +
               "    WHEN Usuarios.id_TipoCadastro = 2 THEN 'Vistoriador' " +
               "    ELSE TipoCadastro.descricao " +
               "END AS descricao " +
               "FROM Usuarios " +
               "JOIN TipoCadastro ON Usuarios.id_TipoCadastro = TipoCadastro.id";
       try(PreparedStatement statement = conectar.prepareStatement(querySQLLista)){
            ResultSet resultset = statement.executeQuery();
            while(resultset.next()){
                Usuario usuario = new Usuario(
                    resultset.getString("nome"),
                    resultset.getString("cpf"),
                    resultset.getString("email"),
                    new TipoCadastro(resultset.getInt("id_TipoCadastro")
                    ));
                usuario.setId(resultset.getInt("id"));
                usuarios.add(usuario);

                System.out.println("Id de Usuário: " + usuario.getId());
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("CPF: " + usuario.getCpf());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("Tipo de Cadastro: " + usuario.getTipoDeCadastro().getId());
                System.out.println("\n");
            }
       } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e); (APAGAR EM CASO DE ERRO)
       }
       return usuarios;
   } */

}
