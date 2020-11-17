package com.example.appdengue.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appdengue.Model.Mostrar;
import com.example.appdengue.R;

import java.util.List;

public class MostrarAdapter extends ArrayAdapter<Mostrar> {
    private List<Mostrar> mostrarList;
    private Context mCtx;


    public MostrarAdapter(List<Mostrar>P,Context c){
        super(c, R.layout.activity_list,P);
        this.mostrarList = P;
        this.mCtx = c;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.activity_list,null,true);

        TextView user_id =(TextView)view.findViewById(R.id.tv_listid);
        TextView den_tipo =(TextView)view.findViewById(R.id.tv_listden);

        Mostrar mostrar = mostrarList.get(position);
        user_id.setText(mostrar.getUser_id());
        den_tipo.setText(mostrar.getDen_tipo());

        return view;
    }
}
