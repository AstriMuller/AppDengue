package com.example.appdengue.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appdengue.R;

public class ViewPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;

    public ViewPageAdapter(Context context) {
        this.context = context;
    }
    private int imagen []={
            R.drawable.dn,
            R.drawable.iconodan,
            R.drawable.ic

    };
    private String title[]={
            "Inicio",
            "Manual de usuario",
            "Mas datos"
    };
    private String texto[]={
            "aqui tiene que decir lo que va",
            "aqui tiene que decir lo que va",
            "aqui tiene que decir lo que va"
    };
    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== (LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_manual,container,false);

        ImageView imageView = v.findViewById(R.id.im_ic);
        TextView textTitle = v.findViewById(R.id.tv_title);
        TextView textDesc = v.findViewById(R.id.tv_desc);

        imageView.setImageResource(imagen[position]);
        textTitle.setText(title[position]);
        textDesc.setText(texto[position]);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
