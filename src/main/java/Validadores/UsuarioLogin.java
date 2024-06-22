package Validadores;

import Classes.Usuario;

import java.sql.SQLOutput;

public class UsuarioLogin {

    private static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado() {
        System.out.println("getado");
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogin) {
        UsuarioLogin.usuarioLogado = usuarioLogin;
        System.out.println("Bem vindo");
    }

    public static void logout(){
        usuarioLogado = null;
    }
}
