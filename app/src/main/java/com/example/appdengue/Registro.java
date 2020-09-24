package com.example.appdengue;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
EditText edt_usuario, edt_email, edt_contrasenha;
Button bt_registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edt_usuario=(EditText) findViewById(R.id.edit_usuario);
        edt_email=(EditText) findViewById(R.id.edit_email);
        edt_contrasenha=(EditText) findViewById(R.id.edit_contrasenha);
        bt_registro= (Button) findViewById(R.id.btn_registrar);
        bt_registro.setOnClickListener(this);
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
                   final String user_usuario=edt_usuario.getText().toString();
                   final String user_email=edt_email.getText().toString();
                   final String user_contrasenha=edt_contrasenha.getText().toString();
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