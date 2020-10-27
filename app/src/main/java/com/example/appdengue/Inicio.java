package com.example.appdengue;

import android.content.Intent;

import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Inicio extends AppCompatActivity {

    TextView tv_usuario;
    Button btn_denuncia;
    EditText edt_usuario, edt_contrasenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edt_usuario = findViewById(R.id.edt_usuario);
        edt_contrasenha = findViewById(R.id.edt_contrasenha);

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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.ver_historia:
                Intent intent = new Intent(this,Historial.class);
                startActivity(intent);
                break;
            case R.id.politica:
                Intent intent2 = new Intent(this,Politica.class);
                startActivity(intent2);
                break;
            case R.id.contacto:
                Intent intent3 = new Intent(this,Contacto.class);
                startActivity(intent3);
                break;
            case R.id.cerrar:
                Login.cambiar_rbestado(Inicio.this,false);
                Intent intent4 = new Intent(Inicio.this, Login.class);
                Inicio.this.startActivity(intent4);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

}





