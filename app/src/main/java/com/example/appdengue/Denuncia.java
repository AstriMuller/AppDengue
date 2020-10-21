package com.example.appdengue;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.time.temporal.Temporal;

public class Denuncia extends AppCompatActivity {
    Spinner sp_denuncia;
    ImageButton btn_gps;
    ImageButton btn_camara;
    ImageView iv_camara;


    private final int cod_foto = 100;
    private final int selec_foto = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        sp_denuncia = (Spinner) findViewById(R.id.sp_denuncia);
        btn_gps = (ImageButton) findViewById(R.id.btn_ubicacion);
        btn_camara = (ImageButton) findViewById(R.id.btn_camara);
        iv_camara = (ImageView) findViewById(R.id.iv_camara);

        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(Denuncia.this, Mapa.class);
                Denuncia.this.startActivity(inten);
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

}