package DAO;

import API.ApiConnection;
import Classes.Endereco;
import Global.Util;
import org.json.JSONObject;

import java.sql.*;

public class EnderecoDAO {
    private Endereco endereco;
    public Connection conectar = Util.getConnection();

    public EnderecoDAO(Connection conectar){
        this.conectar = conectar;
    }

    public Endereco obterEndereco(String cep){
        JSONObject enderecoJson = ApiConnection.apiCEP(cep);
        if(enderecoJson != null){
            return new Endereco(
                    enderecoJson.optString("cep"),
                    enderecoJson.optString("logradouro"),
                    "",
                    enderecoJson.optString("bairro"),
                    enderecoJson.optString("localidade"),
                    enderecoJson.optString("uf"),
                    ""
            );
        }
        return null;
    }

    public int salvaEndereco(Endereco endereco){
        String sql = "INSERT INTO Endereco (cep, logradouro, numero, bairro, localidade, uf, complemento) VALUES (?,?,?,?,?,?,?)";
        int generatedId = -1;
        try(PreparedStatement pstmt = conectar.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, endereco.getCep());
            pstmt.setString(2, endereco.getLogradouro());
            pstmt.setString(3, endereco.getNumero());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getLocalidade());
            pstmt.setString(6, endereco.getUf());
            pstmt.setString(7, endereco.getComplemento());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch(SQLException e){
            System.out.println("Código: " + e.getErrorCode() + "\nMensagem: " + e.getMessage());
        }
        return generatedId;
    }

    public int cadastraEnderecoAPI(String cep, String numero, String complemento){
        Endereco endereco = obterEndereco(cep);
        if (endereco != null) {
            endereco.setNumero(numero);
            endereco.setComplemento(complemento);
            int enderecoId = salvaEndereco(endereco);
            if (enderecoId != -1) {
                System.out.println("Endereço cadastrado com sucesso!");
                return enderecoId;
            }
        }
        System.out.println("Não foi possível obter os dados do CEP.");
        return -1;
    }

}
