package com.example.appdengue;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.temporal.Temporal;
import java.util.Map;

public class Denuncia extends AppCompatActivity implements View.OnClickListener{
    Spinner sp_denuncia;
    ImageButton btn_gps;
    ImageButton btn_camara;
    ImageButton btn_mapa;
    ImageView iv_camara;
    ImageButton btn_gp;
    TextView tv_lng;
    Button btn_cancel;
    TextView tv_lat;
    Button btn_guardar;
    ProgressDialog cargando;
    Bitmap bitmap =null;


    private final int cod_foto = 100;
    private final int selec_foto = 200;
    static final String STRING_PREFERENCES="com.example.appdengue";
    static final String PREFERENCES_ESTADO_BUTTON_SESION = "estado.boton.sesion";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        /*
        if (obtener_rbestado()){
            Intent intent = new Intent(Denuncia.this, Inicio.class);
            Denuncia.this.startActivity(intent);
        }
        */
        sp_denuncia = (Spinner) findViewById(R.id.sp_denuncia);
        btn_camara = (ImageButton) findViewById(R.id.btn_camara);
        iv_camara = (ImageView) findViewById(R.id.iv_camara);
        tv_lng = (TextView)findViewById(R.id.tv_lng);
        btn_cancel= (Button) findViewById(R.id.btn_cancelar);
        tv_lat = (TextView) findViewById(R.id.tv_lat);
        btn_guardar= (Button) findViewById(R.id.btn_guardar);
        btn_mapa=(ImageButton)findViewById(R.id.btn_mapa);
        cargando=new ProgressDialog(this);
        btn_guardar.setOnClickListener(this);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Denuncia.this, Inicio.class);
                Denuncia.this.startActivity(intent);
        }
    });

/*
        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(Denuncia.this, Mapa.class);
                Denuncia.this.startActivity(inten);
            }
        });

        btn_gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            LocationManager locationManager = (LocationManager) Denuncia.this.getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    tv_lng.setText("" + location.getLongitude());
                    tv_lat.setText("" + location.getLatitude());


                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };
            int permissionCheck = ContextCompat.checkSelfPermission(Denuncia.this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
        });
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck== PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }
        //*/
        btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options={"Ubicación actual","Buscar","Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(Denuncia.this);
                builder.setTitle("Elige una opción");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selection) {
                        if (options[selection]=="Ubicación actual"){
                                LocationManager locationManager = (LocationManager) Denuncia.this.getSystemService(Context.LOCATION_SERVICE);
                                LocationListener locationListener = new LocationListener() {
                                    public void onLocationChanged(Location location) {
                                        tv_lng.setText("" + location.getLongitude());
                                        tv_lat.setText("" + location.getLatitude());


                                    }

                                    public void onStatusChanged(String provider, int status, Bundle extras) {
                                    }

                                    public void onProviderEnabled(String provider) {
                                    }

                                    public void onProviderDisabled(String provider) {
                                    }
                                };
                                int permissionCheck = ContextCompat.checkSelfPermission(Denuncia.this,
                                        Manifest.permission.ACCESS_FINE_LOCATION);
                                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

                        }else if(options[selection]=="Buscar"){
                            Intent inte = new Intent(Denuncia.this, Mapa.class);
                            Denuncia.this.startActivity(inte);
                        }else if(options[selection]=="Cancelar"){
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });



        btn_camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(Denuncia.this);
                builder.setTitle("Elige una opción");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (options[seleccion] == "Tomar foto") {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, cod_foto);
                        } else if (options[seleccion] == "Elegir de galeria") {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Seleciona una aplicación"), selec_foto);
                        } else if (options[seleccion] == "Cancelar") {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }

        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista, android.R.layout.simple_list_item_1);
        sp_denuncia.setAdapter(adapter);


    }

    public void onClick(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando");
        /// validar
        if (sp_denuncia.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Campo tipo vacío", Toast.LENGTH_LONG).show();
        } else {
            if (tv_lat.getText().toString().isEmpty()) {
                Toast.makeText(this, "Campo ubicación vacío", Toast.LENGTH_LONG).show();
            } else {
                        if (tv_lng.getText().toString().isEmpty()) {
                            Toast.makeText(this, "Campo ubicación vacío", Toast.LENGTH_LONG).show();
                        } else {
                            if (iv_camara.getDrawable() == null) {
                                Toast.makeText(this, "Campo imagen vacío", Toast.LENGTH_LONG).show();
                            } else {

                        ////
                        final String den_tipo = sp_denuncia.getSelectedItem().toString().trim();
                        final String den_imagen = iv_camara.getDrawable().toString();
                        final String den_lat = tv_lat.getText().toString().trim();
                        final String den_lng = tv_lng.getText().toString().trim();
                                progressDialog.show();
                                Intent intent = new Intent(Denuncia.this, Inicio.class);
                                Denuncia.this.startActivity(intent);
                                Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();
                        Response.Listener<String> respoListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                    try {
                                        JSONObject jsonReponse = new JSONObject(response);
                                        boolean success = jsonReponse.getBoolean("success");
                                        //progressDialog.dismiss();
                                        if (success) {
                                                 Intent intent = new Intent(Denuncia.this, Inicio.class);
                                                 Denuncia.this.startActivity(intent);
                                        } else {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Denuncia.this);
                                            builder.setMessage("error registro")
                                                    .setNegativeButton("Retry", null)
                                                    .create().show();

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                        DenunciaRequest denunciaRequest = new DenunciaRequest(den_tipo, den_imagen, den_lat, den_lng, respoListener);
                        RequestQueue queue = Volley.newRequestQueue(Denuncia.this);
                        queue.add(denunciaRequest);
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case cod_foto:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    iv_camara.setImageBitmap(bitmap);
                }
                break;
            case selec_foto:
                if (resultCode == RESULT_OK) {
                    Uri path = data.getData();
                    iv_camara.setImageURI(path);
                }
                break;
        }
    }
    /*
    public boolean obtener_rbestado(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCES_ESTADO_BUTTON_SESION,false);
    }
    */

}