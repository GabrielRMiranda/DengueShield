package Classes;

import Global.Util;

public class Anexo {
    private int id;
    private String link;
    private String dataAnexo;

    public Anexo(String link) {
        this.link = link;
        this.dataAnexo = dataAnexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDataAnexo() {
        return dataAnexo;
    }

    public void setDataAnexo(String dataAnexo) {
        this.dataAnexo = dataAnexo;
    }
}
