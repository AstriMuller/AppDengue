package com.example.appdengue;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    RadioButton rb_sesion;
    boolean is_action_rb;
    static final String STRING_PREFERENCES="com.example.appdengue";
    static final String PREFERENCES_ESTADO_BUTTON_SESION = "estado.boton.sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (obtener_rbestado()){
            Intent intent = new Intent(Login.this, Inicio.class);
            Login.this.startActivity(intent);
            finish();
        }
        rb_sesion = (RadioButton)findViewById(R.id.rb_nsesion);
        edt_usuario = findViewById(R.id.edt_usuario);
        edt_contrasenha = findViewById(R.id.edt_contrasenha);
        tv_registrar = (TextView) findViewById(R.id.tv_registrar);
        bt_login = (Button) findViewById(R.id.bt_ingresar);
        is_action_rb=rb_sesion.isChecked();

        rb_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_action_rb){
                    rb_sesion.setChecked(false);
                }
                is_action_rb=rb_sesion.isChecked();
            }
        });
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
                            guardar_rbestado();
                            if (success){

                                String usuario=jsonResponse.getString("usuario");
                                Intent intent = new Intent(Login.this, Inicio.class);
                                intent.putExtra("usuario", user_usuario);
                                Login.this.startActivity(intent);
                                finish();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("El nombre de usuario o contraseña que has ingresado no coinciden con nuestros registros.")
                                        .setNegativeButton("Vuelve a intentarlo",null)
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
    public static void cambiar_rbestado(Context c, boolean b){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCES_ESTADO_BUTTON_SESION,b).apply();
    }
    public  void guardar_rbestado(){
    SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
    preferences.edit().putBoolean(PREFERENCES_ESTADO_BUTTON_SESION,rb_sesion.isChecked()).apply();
    }
    public boolean obtener_rbestado(){
    SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
     return preferences.getBoolean(PREFERENCES_ESTADO_BUTTON_SESION,false);
    }

}

