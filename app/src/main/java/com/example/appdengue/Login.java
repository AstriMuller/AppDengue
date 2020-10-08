package com.example.appdengue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appdengue.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    EditText edt_usuario, edt_contrasenha;
    TextView tv_registrar;
    Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_usuario = findViewById(R.id.edt_usuario);
        edt_contrasenha = findViewById(R.id.edt_contrasenha);
        tv_registrar = (TextView) findViewById(R.id.tv_registrar);
        bt_login = (Button) findViewById(R.id.bt_ingresar);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(Login.this, Registro.class);
                Login.this.startActivity(intentReg);
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String user_usuario=edt_usuario.getText().toString();
                final String user_contrasenha=edt_contrasenha.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new  JSONObject(response);
                            boolean success= jsonResponse.getBoolean("success");
                            if (success){
                                String usuario=jsonResponse.getString("usuario");
                                Intent intent = new Intent(Login.this, Inicio.class);
                                intent.putExtra("usuario", user_usuario);
                                Login.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("error login")
                                        .setNegativeButton("Retry",null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest= new LoginRequest(user_usuario, user_contrasenha, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);
            }
        });
    }


}
