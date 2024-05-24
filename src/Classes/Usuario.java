package Classes;

import java.security.NoSuchAlgorithmException;

public class Usuario {
    public int id;
    public String nome;
    public String cpf;
    public String email;
    public String senha;
    private String hash;
    private String salt;
    public TipoCadastro tipoDeCadastro;

    public Usuario(String nome, String cpf, String email,String senha,TipoCadastro tipoDeCadastro) throws NoSuchAlgorithmException { /*Apagar em caso de erro*/
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.tipoDeCadastro = tipoDeCadastro;
        this.salt = CriptografiaSenha.gerarSalt();
        this.hash = CriptografiaSenha.gerarHash(senha, this.salt);
    }

    public String getSalt() {
        return salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setSenha(String senha) throws NoSuchAlgorithmException {
        this.hash = CriptografiaSenha.gerarHash(senha, this.salt);
    }

    public TipoCadastro getTipoDeCadastro() {
        return tipoDeCadastro;
    }

    public void setTipoDeCadastro(TipoCadastro tipoDeCadastro) {
        this.tipoDeCadastro = tipoDeCadastro;
    }
}
