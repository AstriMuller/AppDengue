package com.example.appdengue;

import android.content.Intent;

import android.content.SharedPreferences;
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
    Button btn_estado;
    EditText edt_usuario, edt_contrasenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        /*
        if (obtener_rbestado()){
            Intent intent = new Intent(Inicio.this, Denuncia.class);
            Inicio.this.startActivity(intent);
        }
        /*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edt_usuario = findViewById(R.id.edt_usuario);
        edt_contrasenha = findViewById(R.id.edt_contrasenha);

        tv_usuario = (TextView) findViewById(R.id.tv_bienvenida);

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");

        tv_usuario.setText(usuario);
        btn_denuncia = (Button) findViewById(R.id.btn_nuevad);
        btn_estado=(Button)findViewById(R.id.btn_estado);

        btn_estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEstado= new Intent(Inicio.this,Estado.class);
                Inicio.this.startActivity(intentEstado);
            }
        });
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
            case R.id.mostrar_historial:
                Intent intent6 = new Intent(this,MostrarHistorial.class);
                startActivity(intent6);
                break;
            case R.id.politica:
                Intent intent2 = new Intent(this,Politica.class);
                startActivity(intent2);
                break;

            case R.id.termino:
                Intent intent5 = new Intent(this,Termino.class);
                startActivity(intent5);
                break;
            case R.id.contacto:
                Intent intent3 = new Intent(this,Contacto.class);
                startActivity(intent3);
                break;
            case R.id.cerrar:
                //Login.cambiar_rbestado(Inicio.this,false);
                Intent intent4 = new Intent(Inicio.this, Login.class);
                Inicio.this.startActivity(intent4);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
    /*
    public boolean obtener_rbestado(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCES_ESTADO_BUTTON_SESION,false);
    }
    /*/

}





