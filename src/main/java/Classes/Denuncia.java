package Classes;

import Global.Util;

import java.sql.Date;
import java.time.LocalDate;


public class Denuncia {
    public Usuario usuario;
    private int id;
    private int denuncianteId;
    private int statusId;
    private int enderecoId;
    private String descricao;
    private int anexoId;
    private String dataCadastro;
    private String dataInativacao;

    public Denuncia(int denuncianteId, int enderecoId, String descricao, int anexoId) {
        this.denuncianteId = denuncianteId;
        this.statusId = 1;
        this.enderecoId = enderecoId;
        this.descricao = descricao;
        this.anexoId = anexoId;
        this.dataCadastro = Util.RegistraDataAtual();
    }

    public Denuncia() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDenuncianteId() {
        return denuncianteId;
    }

    public void setDenuncianteId(int denuncianteId) {
        this.denuncianteId = usuario.getId();
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public int getAnexoId() {
        return anexoId;
    }

    public void setAnexoId(int anexoId) {
        this.anexoId = anexoId;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataInativacao() {
        return dataInativacao;
    }

    public void setDataInativacao(String dataInativacao) {
        this.dataInativacao = dataInativacao;
    }

    public void inativarCadastro() {
        this.dataInativacao = Util.RegistraDataAtual();
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}