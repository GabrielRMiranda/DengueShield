package DAO;

import API.ApiConnection;
import BancoDados.ConexaoBancoDados;
import Classes.Denuncia;
import Global.Util;
import org.json.JSONObject;

import java.sql.*;

public class DenunciaDAO {
    private Connection conectar;
    public DenunciaDAO(Connection conectar){
        this.conectar = ConexaoBancoDados.getConnection();
    }

    public int geraDenuncia(Denuncia denuncia) throws SQLException {
        String sql = "INSERT INTO Denuncia (denuncianteId, statusId, descricao, dataAbertura) VALUES (?,?,?,?)";
        String dataAbertura = Util.RegistraDataAtual();
        String dataInativacao = Util.RegistraDataAtual();

        try(PreparedStatement registraDB = conectar.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
            registraDB.setInt(1, denuncia.getDenuncianteId());
            registraDB.setInt(2, denuncia.getStatusId());
            registraDB.setString(3, denuncia.getDescricao());
            registraDB.setString(4, dataAbertura);

            int registrado = registraDB.executeUpdate();

            if (registrado > 0){
                try(ResultSet idGerado = registraDB.getGeneratedKeys()){
                    if(idGerado.next()){
                        denuncia.setId(idGerado.getInt(1));
                        return denuncia.getId();
                    }
                }
            }
        }
        return 0;
    }

    public int inserirEndereco(String cep, String logradouro, String numero, String bairro, String localidade, String uf, String complemento) throws SQLException{
        String sql = "INSERT INTO Endereco (cep,logradouro,numero,bairro,localidade,uf,complemento) VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement registraDB = conectar.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            registraDB.setString(1, cep);
            registraDB.setString(2, logradouro);
            registraDB.setString(3, numero);
            registraDB.setString(4, bairro);
            registraDB.setString(5, localidade);
            registraDB.setString(6, uf);
            registraDB.setString(7,complemento);

            int registro = registraDB.executeUpdate();

            if(registro > 0){
                try(ResultSet idGerado = registraDB.getGeneratedKeys()){
                    if(idGerado.next()){
                        return idGerado.getInt(1);
                    }
                }
            }
        }
        return 0;
    }

    public int inserirEnderecoPorApi(String cep, String numero, String complemento) throws SQLException {
        ApiConnection apiConnection = new ApiConnection();
        JSONObject jsonEnderco = apiConnection.apiCEP(cep);

        if (jsonEnderco == null){
            System.out.println("CEP inválido ou não encontrado.");
            return 0;
        }

        String sql = "INSERT INTO Endereco (cep, logradouro, numero, bairro, localidade, uf, complemento) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement registraDB = conectar.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            registraDB.setString(1, jsonEnderco.getString("cep"));
            registraDB.setString(2, jsonEnderco.getString("logradouro"));
            registraDB.setString(3, numero);
            registraDB.setString(4, jsonEnderco.getString("bairro"));
            registraDB.setString(5, jsonEnderco.getString("localidade"));
            registraDB.setString(6, jsonEnderco.getString("uf"));
            registraDB.setString(7, complemento);

            int registro = registraDB.executeUpdate();

            if (registro > 0) {
                try (ResultSet idGerado = registraDB.getGeneratedKeys()) {
                    if (idGerado.next()) {
                        return idGerado.getInt(1);
                    }
                }
            }
        }
        return 0;
    }

    public int inserirAnexo(String link) throws SQLException{
        String sql = "INSERT INTO Anexo (link,dataAnexo) VALUES (?,?)";
        String dataDeAnexo = Util.RegistraDataAtual();

        try(PreparedStatement registraDB = conectar.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            registraDB.setString(1, link);
            registraDB.setString(2, dataDeAnexo);

            int registro = registraDB.executeUpdate();

            if(registro > 0){
                try(ResultSet idGerado = registraDB.getGeneratedKeys()){
                    if(idGerado.next()){
                        return idGerado.getInt(1);
                    }
                }
            }
        }
        return 0;
    }

    public void atualizarDenunciaComEndereco(int denunciaId, int enderecoId) throws SQLException {
        String sql = "UPDATE Denuncia SET enderecoId = ? WHERE id = ?";
        try (PreparedStatement atualizaDB = conectar.prepareStatement(sql)) {
            atualizaDB.setInt(1, enderecoId);
            atualizaDB.setInt(2, denunciaId);
            atualizaDB.executeUpdate();
        }
    }

    public void atualizarDenunciaComAnexo(int denunciaId, int anexoId) throws SQLException {
        String sql = "UPDATE Denuncia SET anexoId = ? WHERE id = ?";
        try (PreparedStatement atualizaDB = conectar.prepareStatement(sql)) {
            atualizaDB.setInt(1, anexoId);
            atualizaDB.setInt(2, denunciaId);
            atualizaDB.executeUpdate();
        }
    }

    public String acessaLinkDoAnexo(int denunciaId) throws SQLException{
        String sql = "SELECT Anexo.link FROM Anexo INNER JOIN Denuncia ON Anexo.id = Denuncia.AnexoId WHERE Denuncia.id = ?";

        try(PreparedStatement consultaDB = conectar.prepareStatement(sql)){
            consultaDB.setInt(1, denunciaId);

            try(ResultSet rs = consultaDB.executeQuery()){
                if(rs.next()){
                    return rs.getString("link");
                }
            }
        }
        return null;
    }

}
