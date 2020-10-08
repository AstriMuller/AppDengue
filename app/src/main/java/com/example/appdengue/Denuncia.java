package com.example.appdengue;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Denuncia extends AppCompatActivity {
    Spinner sp_denuncia;
    Button btnGPS;
    TextView tvUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        sp_denuncia = (Spinner) findViewById(R.id.sp_denuncia);
        btnGPS = (Button)findViewById(R.id.button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.lista, android.R.layout.simple_list_item_1);
        sp_denuncia.setAdapter(adapter);

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager = (LocationManager) Denuncia.this.getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        tvUbicacion.setText(""+location.getLatitude()+""+location.getLongitude());

                    }
                    public void onStatusChanged(String provider, int status, Bundle extras){}
                    public void onProviderEnabled(String provider){}
                    public void onProviderDisabled(String provider){}
                };

                int permissionCheck = ContextCompat.checkSelfPermission(Denuncia.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        });

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck== PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }

        }
    }
}