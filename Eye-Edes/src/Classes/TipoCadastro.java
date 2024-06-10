package Classes;

public class TipoCadastro {
    public int id;
    public String descricao;

    public TipoCadastro(int id) {
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

    public static boolean defineTipoCadastro(){
        TipoCadastro civil = new TipoCadastro(1);
        TipoCadastro vistoriador = new TipoCadastro(2);

        return defineTipoCadastro();
    }

}
