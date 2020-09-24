package com.example.appdengue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {
TextView tv_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        tv_usuario = (TextView) findViewById(R.id.tv_bienvenida);

        Intent intent = getIntent();
        String usuario=intent.getStringExtra("usuario");

        tv_usuario.setText(usuario);

    }
}