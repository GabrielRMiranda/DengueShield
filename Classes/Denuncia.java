package Classes;

import Global.Util;

import java.sql.Date;
import java.time.LocalDate;


public class Denuncia {
    private int id;
    private int denuncianteId;
    private int statusId;
    private int enderecoId;
    private String descricao;
    private String dataCadastro;
    private String dataInativacao;

    public Denuncia(String descricao) {
        this.denuncianteId = denuncianteId;
        this.statusId = 1;
        this.enderecoId = enderecoId;
        this.descricao = descricao;
        this.dataCadastro = Util.RegistraDataAtual();
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
        this.denuncianteId = denuncianteId;
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
