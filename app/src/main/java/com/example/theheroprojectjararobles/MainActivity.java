package com.example.theheroprojectjararobles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText nombre;
    ArrayList<Heroe> Heroes= new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
    }

    public void buscarHeroe(View view) {

        nombre=findViewById(R.id.et_heroe);
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());

        if (nombre.getText().toString().length()>0){
            String url="https://www.superheroapi.com/api.php/3915287195168857/search/"+nombre.getText().toString();
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray myJsonArray = response.getJSONArray("results");
                        for (int i=0;i<myJsonArray.length();i++){
                            JSONObject myObject=myJsonArray.getJSONObject(i);
                            System.out.println(myObject.toString());
                            String id=myObject.getString("id");
                            String alterego=myObject.getJSONObject("biography").getString("full-name");
                            String nombre=myObject.getString("name");
                            HashMap habilidades=new HashMap();
                            String inteligencia = myObject.getJSONObject("powerstats").getString("intelligence");
                            String fuerza = myObject.getJSONObject("powerstats").getString("strength");
                            String velocidad = myObject.getJSONObject("powerstats").getString("speed");
                            String durabilidad = myObject.getJSONObject("powerstats").getString("durability");
                            String poder = myObject.getJSONObject("powerstats").getString("power");
                            String combate = myObject.getJSONObject("powerstats").getString("combat");
                            if(!inteligencia.equals("null")){
                                habilidades.put("Inteligencia",inteligencia);
                            }
                            else{
                                habilidades.put("Inteligencia","0");
                            }
                            if(!fuerza.equals("null")){
                                habilidades.put("Fuerza",fuerza);
                            }
                            else{
                                habilidades.put("Fuerza","0");
                            }
                            if(!velocidad.equals("null")){
                                habilidades.put("Velocidad",velocidad);
                            }
                            else{
                                habilidades.put("Velocidad","0");
                            }
                            if(!durabilidad.equals("null")){
                                habilidades.put("Durabilidad",durabilidad);
                            }
                            else{
                                habilidades.put("Durabilidad","0");
                            }
                            if(!poder.equals("null")){
                                habilidades.put("Poder",poder);
                            }
                            else{
                                habilidades.put("Poder","0");
                            }
                            if(!combate.equals("null")){
                                habilidades.put("Combate",combate);
                            }
                            else{
                                habilidades.put("Combate","0");
                            }


                            Heroe h=new Heroe(id,alterego,nombre,habilidades);
                            Heroes.add(h);
                            System.out.println(h);
                        }
                        Intent intent=new Intent(context,Lista.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("Heroes",Heroes);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("No entra ni por Dios ni por la patria "+error);
                }
            });
            queue.add(request);
        }
        else{
            Toast.makeText(this,"El campo de nombre no debe estar vacío",Toast.LENGTH_SHORT).show();
            nombre.setText("");
        }
    }

}