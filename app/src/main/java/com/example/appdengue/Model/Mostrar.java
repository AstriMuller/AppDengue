package com.example.appdengue.Model;

public class Mostrar {
    String user_id, den_tipo;

    public Mostrar(String user_id, String den_tipo) {
        this.user_id = user_id;
        this.den_tipo = den_tipo;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getDen_tipo() {
        return den_tipo;
    }
}