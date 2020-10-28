package com.example.appdengue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class ContactoRequest extends StringRequest {
    private static final String SUGERENCIA_REQUEST_URL="http://192.168.88.194/appdengue/Sugerencia.php";
    private Map<String,String> params;
    public ContactoRequest(String den_sugerencia,
                           Response.Listener<String>listener){
        super(Method.POST, SUGERENCIA_REQUEST_URL,listener, null);
        params=new HashMap<>();
        params.put("den_sugerencia",den_sugerencia);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

