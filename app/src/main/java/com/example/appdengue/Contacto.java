package com.example.appdengue;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Contacto extends AppCompatActivity implements View.OnClickListener {
    EditText edt_sugerencia;
    Button bt_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        edt_sugerencia = (EditText) findViewById(R.id.edit_sugerencia);
        bt_enviar = (Button) findViewById(R.id.btn_enviar);
        bt_enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /// validar
        if (edt_sugerencia.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo sugerencias vacío", Toast.LENGTH_LONG).show();
        } else {
            final String den_sugerencia = edt_sugerencia.getText().toString().trim();
            Response.Listener<String> respoListener = new Response.Listener<String>() {
                public void onResponse(String response) {
                    try {
                        JSONObject jsonReponse = new JSONObject(response);
                        boolean success = jsonReponse.getBoolean("success");
                        if (success) {

                            Intent intent = new Intent(Contacto.this, Inicio.class);
                            Contacto.this.startActivity(intent);
                            finish();

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Contacto.this);
                            builder.setMessage("error registro")
                                    .setNegativeButton("Retry", null)
                                    .create().show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            ContactoRequest contactoRequest = new ContactoRequest(den_sugerencia, respoListener);
            RequestQueue queue = Volley.newRequestQueue(Contacto.this);
            queue.add(contactoRequest);
        }
    }
}