package com.example.appdengue;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DenunciaRequest extends StringRequest {
    private static final String DENUNCIA_REQUEST_URL="http://192.168.88.194/appdengue/Denuncia.php";
    private Map<String,String> params;
    public DenunciaRequest(String den_tipo, String den_imagen, String den_lat, String den_lng,
                           Response.Listener<String>listener){
        super(Method.POST, DENUNCIA_REQUEST_URL,listener, null);
        params=new HashMap<>();
        params.put("den_tipo",den_tipo);
        params.put("den_lat",den_lat);
        params.put("den_lng",den_lng);
        params.put("den_imagen",den_imagen);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
