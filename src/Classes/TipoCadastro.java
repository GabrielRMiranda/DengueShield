package Classes;

public class TipoCadastro {
    public int id;
    public String descricao;

    public TipoCadastro(int id) {
        this.id = id;
    }

    public String getDescricao(){
        return descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}