package Classes;

public class Vistoria {
    private int ID;
    private int ID_denuncia;
    private int ID_vistoriador;
    private String endereco;
    private String descricao;
    private java.sql.Date dataVisita;

    public Vistoria(int ID, int ID_denuncia, int ID_vistoriador, String endereco, String descricao, java.sql.Date dataVisita) {
        this.ID = ID;
        this.ID_denuncia = ID_denuncia;
        this.ID_vistoriador = ID_vistoriador;
        this.endereco = endereco;
        this.descricao = descricao;
        this.dataVisita = dataVisita;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_denuncia() {
        return this.ID_denuncia;
    }

    public void setID_denuncia(int ID_denuncia) {
        this.ID_denuncia = ID_denuncia;
    }

    public int getID_vistoriador() {
        return this. ID_vistoriador;
    }

    public void setID_vistoriador(int ID_vistoriador) {
        this.ID_vistoriador = ID_vistoriador;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public java.sql.Date getdataVisita() {
        return this.dataVisita;
    }

    public void setdataVisita(java.sql.Date dataVisita) {
        this.dataVisita = dataVisita;
    }
}
