package com.example.appdengue;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.compass.CompassOverlay;

import java.util.ArrayList;

public class Mapa extends AppCompatActivity {
        TextView tv_latitud,tv_longitud;
        MapView map;
        ImageButton btn_Map;
        private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mapa);


            Context ctx = this.getApplicationContext();
            Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
            btn_Map = (ImageButton)findViewById(R.id.ic_map);
            map = findViewById(R.id.map_view);
            tv_latitud=(TextView)findViewById(R.id.tv_latitud);
            tv_longitud=(TextView)findViewById(R.id.tv_longitud);

            map.setTileSource(TileSourceFactory.MAPNIK);

            map.getController().setZoom(7.0);
            GeoPoint point = new GeoPoint(-23.442503, -58.443832);
            map.getController().setCenter(point);
            requestPermissionsIfNecessary(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.INTERNET
            });

            map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
            map.setMultiTouchControls(true);


            CompassOverlay compassOverlay = new CompassOverlay(this, map);
            compassOverlay.enableCompass();
            map.getOverlays().add(compassOverlay);


            ///

            btn_Map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LocationManager locationManager = (LocationManager) Mapa.this.getSystemService(Context.LOCATION_SERVICE);
                    LocationListener locationListener = new LocationListener() {
                        public void onLocationChanged(Location location) {
                            tv_longitud.setText("" + location.getLongitude());
                            tv_latitud.setText("" + location.getLatitude());
                            //

                            map.getController().setZoom(20.0);
                            GeoPoint point2 = new GeoPoint(-26.9961761797, -55.8821453);

                            Marker startMarker = new Marker(map);
                            startMarker.setPosition(point2);
                            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
                            map.getOverlays().add(startMarker);

                            //
                            startMarker.setIcon(getResources().getDrawable(R.drawable.ic_marker));
                            startMarker.setTitle("Tu ubicación actual");
                            map.getController().setCenter(point2);


                        }

                        public void onStatusChanged(String provider, int status, Bundle extras) {
                        }

                        public void onProviderEnabled(String provider) {
                        }

                        public void onProviderDisabled(String provider) {
                        }
                    };
                    int permissionCheck = ContextCompat.checkSelfPermission(Mapa.this,
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




        }


        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            ArrayList<String> permissionsToRequest = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++) {
                permissionsToRequest.add(permissions[i]);
            }
            if (permissionsToRequest.size() > 0) {
                ActivityCompat.requestPermissions(
                        this,
                        permissionsToRequest.toArray(new String[0]),
                        REQUEST_PERMISSIONS_REQUEST_CODE);
            }
        }

        private void requestPermissionsIfNecessary(String[] permissions) {
            ArrayList<String> permissionsToRequest = new ArrayList<>();
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(permission);
                }
            }
            if (permissionsToRequest.size() > 0) {
                ActivityCompat.requestPermissions(
                        this,
                        permissionsToRequest.toArray(new String[0]),
                        REQUEST_PERMISSIONS_REQUEST_CODE);
            }
        }
    }