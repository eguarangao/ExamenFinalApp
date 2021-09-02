package com.example.examenfinal;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.examenfinal.Modelos.Revista;
import com.example.examenfinal.WebService.Asynchtask;
import com.example.examenfinal.WebService.WebService;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    String URL = "https://revistas.uteq.edu.ec/ws/journals.php";
    PlaceHolderView placeHolderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Entramos main ebert");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos_map = new HashMap<String, String>();
        WebService web = new WebService(URL, datos_map, MainActivity.this, MainActivity.this);
        web.execute("GET");

        // Instanciar el placeHolder con el componente del layout activity_main.xml
        placeHolderView = findViewById(R.id.place_revista);
        System.out.println("salimos main ebert");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        // Obtencción de datos del web service
        JSONArray json_array = new JSONArray(result);
        for (int i = 0; i < json_array.length(); i++){
            JSONObject json_obj_revista = json_array.getJSONObject(i);
            // Añado el context de la aplicacion y el objeto json para enviarselo al modelo Revista
            placeHolderView.addView(new Revista(getApplicationContext(), json_obj_revista));
        }
    }
}