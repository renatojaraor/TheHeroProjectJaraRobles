package com.example.theheroprojectjararobles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Bundle bundleR=getIntent().getExtras();
        TextView tvSup=findViewById(R.id.tv_sup);
        TextView tvCom=findViewById(R.id.tv_completo);
        Heroe superHeroe=(Heroe)bundleR.getSerializable("Heroes");
        tvSup.setText(superHeroe.nombre);
        tvCom.setText(superHeroe.alterego);
        HashMap habilidades=superHeroe.habilidades;
        int inteligencia=Integer.parseInt(habilidades.get("Inteligencia").toString());
        int fuerza=Integer.parseInt(habilidades.get("Fuerza").toString());
        int durabilidad=Integer.parseInt(habilidades.get("Durabilidad").toString());
        int velocidad=Integer.parseInt(habilidades.get("Velocidad").toString());
        int poder=Integer.parseInt(habilidades.get("Poder").toString());
        int combate=Integer.parseInt(habilidades.get("Combate").toString());
        BarChart barChart=findViewById(R.id.BarChart);
        ArrayList<BarEntry> datos=new ArrayList<>();
        datos.add(new BarEntry(1,inteligencia));
        datos.add(new BarEntry(2,fuerza));
        datos.add(new BarEntry(3,durabilidad));
        datos.add(new BarEntry(4,velocidad));
        datos.add(new BarEntry(5,poder));
        datos.add(new BarEntry(6,combate));
        BarDataSet barDataSet=new BarDataSet(datos,"Estadisticas");
        BarData barData=new BarData(barDataSet);
        barChart.setData(barData);
    }
    public void Volver(View v){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}