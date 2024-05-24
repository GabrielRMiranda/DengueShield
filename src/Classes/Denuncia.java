package Classes;

import org.json.JSONObject;

public class Denuncia {
    private int id;
    private int ID_denunciante;
    private int ID_status;
    public String cep;
    public String logradouro;
    public String bairro;
    public String complemento;
    public String cidade;
    public String estado;
    public String pais;
    private String descricao;
    private String dataAbertura;

    private Denuncia() {

    }

    public String dataAbertura(){
        return dataAbertura;
    }

    public int getId() {
        return id;
    }

    public int getID_denunciante() {
        return ID_denunciante;
    }

    public int getID_status() {
        return ID_status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static class Builder{
        private Denuncia denuncia;

        public Builder(){
            this.denuncia = new Denuncia();

        }

        public Builder comCep(String cep) throws Exception{
            JSONObject endereco = Globais.Util.cepAPI(cep);

            this.denuncia.cep = cep;
            this.denuncia.logradouro = endereco.getString("logradouro");
            this.denuncia.bairro = endereco.getString("bairro");
            this.denuncia.cidade = endereco.getString("localidade");
            this.denuncia.estado = endereco.getString("uf");
            this.denuncia.pais = "Brasil";
            return this;
        }

        public Builder comComplemento(String complemento){
            this.denuncia.complemento = complemento;
            return this;
        }

        public Builder comDescricao(String descricao){
            this.denuncia.descricao = descricao;
            return this;
        }

        public Builder comDataAbertura(){
            this.denuncia.dataAbertura = Globais.Util.RegistraDataAtual();
            return this;
        }

        public Denuncia build(){
            if(this.denuncia.cep == null || this.denuncia.descricao == null){
                throw new IllegalStateException("Cep e Descrição são obrigatórios.");
            }
            return this.denuncia;
        }
    }
}
