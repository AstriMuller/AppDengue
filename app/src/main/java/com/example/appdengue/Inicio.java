package com.example.appdengue;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    TextView tv_usuario;
    Button btn_denuncia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_usuario = (TextView) findViewById(R.id.tv_bienvenida);

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");

        tv_usuario.setText(usuario);
        btn_denuncia = (Button) findViewById(R.id.btn_nuevad);

        btn_denuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNuevo = new Intent(Inicio.this,Denuncia.class);
                Inicio.this.startActivity(intentNuevo);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }
    public boolean onOptionItemSelected(MenuItem item){
        int id = item.getItemId();
        FragmentManager fragmentManager=getSupportFragmentManager();
         if (id == R.id.ver_historia) {
            fragmentManager.beginTransaction().replace(R.id.bienv, new FragmentHistorial()).commit();
        } else if (id == R.id.politica) {
            fragmentManager.beginTransaction().replace(R.id.bienv, new FragmentPolitica()).commit();
        }
        return true;
}
}


