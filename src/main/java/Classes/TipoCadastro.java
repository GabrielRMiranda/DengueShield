package Classes;

public class TipoCadastro {
    public static final TipoCadastro Civil = new TipoCadastro(1,"Civil");
    public static final TipoCadastro Vistoriador = new TipoCadastro(2,"Vistoriador");
    public int id;
    public String descricao;

    public TipoCadastro(int id, String descricao) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



}
