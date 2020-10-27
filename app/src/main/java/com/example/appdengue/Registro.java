package com.example.appdengue;

import android.app.AlertDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText edt_usuario, edt_email, edt_contrasenha;
    Button bt_registro;
    TextView tv_temino;
    TextView tv_politica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        tv_temino=(TextView) findViewById(R.id.tv_termino);
        tv_politica=(TextView)findViewById(R.id.tv_politica);
        edt_usuario=(EditText) findViewById(R.id.edit_usuario);
        edt_email=(EditText) findViewById(R.id.edit_email);
        edt_contrasenha=(EditText) findViewById(R.id.edit_contrasenha);
        bt_registro= (Button) findViewById(R.id.btn_registrar);
        bt_registro.setOnClickListener(this);
        tv_temino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTer = new Intent(Registro.this, Termino.class);
                Registro.this.startActivity(intentTer);
            }
        });
        tv_politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTer = new Intent(Registro.this, Politica.class);
                Registro.this.startActivity(intentTer);
            }
        });
    }

    @Override
    public void onClick(View view) {
        /// validar
        if (edt_usuario.getText().toString().isEmpty()){
            Toast.makeText(this,"Campo usuario vacío",Toast.LENGTH_LONG).show();
        }else {
            if (edt_email.getText().toString().isEmpty()){
                Toast.makeText(this,"Campo email vacío",Toast.LENGTH_LONG).show();
            }else {
                if (edt_contrasenha.getText().toString().isEmpty()){
                    Toast.makeText(this,"Campo contraseña vacío",Toast.LENGTH_LONG).show();
                }else {
                    ////
                    final String user_usuario=edt_usuario.getText().toString().trim();
                    final String user_email=edt_email.getText().toString().trim();
                    final String user_contrasenha=edt_contrasenha.getText().toString().trim();
                    Response.Listener<String> respoListener =new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonReponse = new JSONObject(response);
                                boolean success= jsonReponse.getBoolean("success");
                                if (success){

                                    Intent intent = new Intent(Registro.this,Login.class);
                                    Registro.this.startActivity(intent);

                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                                    builder.setMessage("error registro")
                                            .setNegativeButton("Retry",null)
                                            .create().show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RegistroRequest registroRequest= new RegistroRequest(user_usuario, user_email, user_contrasenha, respoListener);
                    RequestQueue queue = Volley.newRequestQueue(Registro.this);
                    queue.add(registroRequest);
                }
            }
        }

    }
}