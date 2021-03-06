package com.example.appdengue;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout tf_registro, tf_regemail, tf_regpass;
    TextInputEditText edt_usuario, edt_email, edt_contrasenha;
    Button bt_registro;
    TextView tv_temino;
    CheckBox cb_acepta;
    TextView tv_politica;
    ProgressDialog cargando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cb_acepta=(CheckBox)findViewById(R.id.cb_ac);
        tf_registro=(TextInputLayout) findViewById(R.id.tf_reg);
        tf_regemail=(TextInputLayout) findViewById(R.id.tf_rege);
        tf_regpass=(TextInputLayout) findViewById(R.id.tf_regpass);
        tv_temino=(TextView) findViewById(R.id.tv_termino);
        tv_politica=(TextView)findViewById(R.id.tv_politica);
        edt_usuario= findViewById(R.id.edit_usuario);
        edt_email=findViewById(R.id.edit_email);
        edt_contrasenha= findViewById(R.id.edit_contrasenha);
        bt_registro= (Button) findViewById(R.id.btn_registrar);
        cargando=new ProgressDialog(this);
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
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando");
        /// validar
        if (edt_usuario.getText().toString().isEmpty()){
            Toast.makeText(this,"Requiere un nombre de usuario",Toast.LENGTH_LONG).show();
        }else if (edt_email.getText().toString().isEmpty()){
                Toast.makeText(this,"Requiere un email",Toast.LENGTH_LONG).show();
            }else if (edt_contrasenha.getText().toString().length()<8){
                    Toast.makeText(this,"La contraseña requiere al menos 8 caracteres",Toast.LENGTH_LONG).show();
                }
                    else{
                    ////
                    final String user_usuario=edt_usuario.getText().toString().trim();
                    final String user_email=edt_email.getText().toString().trim();
                    final String user_contrasenha=edt_contrasenha.getText().toString().trim();
                    progressDialog.show();
                    Toast.makeText(this, "Registrado", Toast.LENGTH_LONG).show();
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
