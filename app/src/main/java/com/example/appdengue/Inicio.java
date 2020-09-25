package com.example.appdengue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {
TextView tv_usuario;
ImageButton btn_bienv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

       tv_usuario = (TextView) findViewById(R.id.tv_bienvenida);

       Intent intent = getIntent();
      String usuario=intent.getStringExtra("usuario");

        tv_usuario.setText(usuario);
        btn_bienv = (ImageButton) findViewById(R.id.bt_menu);



    }

    }
