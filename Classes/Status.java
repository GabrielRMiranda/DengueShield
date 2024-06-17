package Classes;

import Global.Util;
import java.sql.Date;
import java.time.LocalDate;

public class Status {
    private int id;
    private String descricao;
    public Status(String descricao) {
        this.descricao = descricao;
    }

    public int getID() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
