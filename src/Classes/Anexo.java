package Classes;

public class Anexo {
    public int id;
    public String descricao;
    public float tamanhoArquivo;
    public String caminhoArquivo;
    private TipoArquivo tipo;
    private byte[] dados;

    public Anexo(int id) {
        this.id = id;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(){
        this.descricao = descricao;
    }

    public float getTamanhoArquivo(){
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(){
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getCaminhoArquivo(){
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(){
        this.caminhoArquivo = caminhoArquivo;
    }

    public void setTipo(TipoArquivo tipo) {
        this.tipo = tipo;
    }

    public TipoArquivo getTipo() {
        return tipo;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

}

