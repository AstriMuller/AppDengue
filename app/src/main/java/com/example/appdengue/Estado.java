package com.example.appdengue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Estado extends AppCompatActivity {
    TextView tv_tipo, tv_estado;
    RequestQueue requestQueue;
    EditText et_buscar;
    ImageButton btn_buscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv_tipo=(TextView)findViewById(R.id.tv_tipo);
        tv_estado=(TextView)findViewById(R.id.tv_estado);
        et_buscar=(EditText)findViewById(R.id.et_buscar);
        btn_buscar=(ImageButton)findViewById(R.id.btn_buscar);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarestado("http://192.168.88.194/appdengue/buscar.php"+et_buscar.getText()+"");
            }
        });
    }


    private void buscarestado(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        for (int i =0; i < response.length(); i++) {
                            try {
                                jsonObject = response.getJSONObject(i);
                                tv_tipo.setText(jsonObject.getString("Tipo"));
                                tv_estado.setText(jsonObject.getString("Estado"));

                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_SHORT).show();

        }
    });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


}
}