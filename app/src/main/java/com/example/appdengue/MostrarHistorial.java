package com.example.appdengue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.appdengue.Adapter.MostrarAdapter;
import com.example.appdengue.Model.Mostrar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MostrarHistorial extends AppCompatActivity {
    ListView listView;
    List<Mostrar> mostrarList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_historial);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.list_mostrar);
        mostrarList=new ArrayList<>();
        showList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_buscar,menu);
        return true;
    }
    private  void showList(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.88.194/appdengue/mostrar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj =new JSONObject(response);
                            JSONArray array = obj.getJSONArray("den");
                            for (int i =0; i <array.length();i++){

                                JSONObject mostObj = array.getJSONObject(i);
                                Mostrar p = new Mostrar(mostObj.getString("user_id"),mostObj.getString("den_tipo"));
                                mostrarList.add(p);
                            }
                            MostrarAdapter adapter = new MostrarAdapter(mostrarList,getApplicationContext());
                            listView.setAdapter(adapter);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }
}