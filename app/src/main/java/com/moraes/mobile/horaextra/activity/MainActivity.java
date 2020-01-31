package com.moraes.mobile.horaextra.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.moraes.mobile.horaextra.R;
import com.moraes.mobile.horaextra.model.Categoria;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Categoria> listaCat;
    ArrayList<String> listArrayCat;
    private Spinner sp1, sp2;
    ArrayList<String> listAdicional;

    double salario = 0.0;
    double valorAdicional = 0.0;
    double valorHoraExtra = 0.0;
    double horaTrabalho = 0.0;
    double valorTotalExtra = 0.0;
    TextView txtValorHoraExtra;
    TextView horaTrabalhada;
    TextView totalExtra;
    NumberFormat formato1 = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtValorHoraExtra = findViewById(R.id.lblValorExtra);
        horaTrabalhada = findViewById(R.id.txtHoraTrabalho);
        gerarListaAdicional();
        gerarLista();
        gerarArrayCat();

        ArrayAdapter<String> adapterAdicional = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listAdicional);
        adapterAdicional.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2 = findViewById(R.id.spinnerAdicional);
        sp2.setAdapter(adapterAdicional);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecionaAdicional(position);
                calculaHoraExtra();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listArrayCat);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1 = findViewById(R.id.spinnerSalario);
        sp1.setAdapter(adapterCategoria);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecionaCategoria(position);
                calculaHoraExtra();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void gerarLista(){
        listaCat = new ArrayList<Categoria>();
        Categoria c = new Categoria("Aux Cozinha",1341.08);
        listaCat.add(c);
        c = new Categoria("Aux Limpeza",1341.08);
        listaCat.add(c);
        c = new Categoria("Cozinheiro 2",2047.84);
        listaCat.add(c);
        c = new Categoria("Cozinheiro 1",2062.14);
        listaCat.add(c);


    }
    public void gerarListaAdicional (){
        listAdicional = new ArrayList<String>();
        listAdicional.add("60 %");
        listAdicional.add("50 %");
        listAdicional.add("40 %");

    }
    public void gerarArrayCat(){
        int i = 0;
        listArrayCat = new ArrayList<String>();
        while ( i < listaCat.size() ){
            StringBuilder stb = new StringBuilder();
            String n = listaCat.get(i).getNome();
            String v = String.valueOf(listaCat.get(i).getValor());
            String t = " - ";
            stb.append(n+t+v);
            listArrayCat.add(stb.toString());
            i++;
        }
    }
    public void calculaHoraExtra(){
        if ( salario != 0 && valorAdicional != 0 ){
            double aux = salario/220;
            valorHoraExtra = aux + (aux*valorAdicional);
        }
        txtValorHoraExtra.setText(formato1.format(valorHoraExtra));

    }
    public void CalculaHoraExtraTotal(View v){
        horaTrabalhada = findViewById(R.id.txtHoraTrabalho);
        horaTrabalho = Double.valueOf(horaTrabalhada.getText().toString());
        valorTotalExtra = horaTrabalho*valorHoraExtra;
        totalExtra = findViewById(R.id.txtTotalExtra);
        totalExtra.setText(formato1.format(valorTotalExtra));

    }




    public void selecionaAdicional(int p){

        if( p == 0 ){
            valorAdicional = 0.6;
        }else if ( p == 1 ){
            valorAdicional = 0.5;
        }else {
            valorAdicional = 0.4;
        }


    }
    public void selecionaCategoria(int p){
        Categoria c = listaCat.get(p);
        salario = c.getValor();
        String valor = formato1.format(valorHoraExtra);
        txtValorHoraExtra.setText(String.valueOf(valor));
    }
}
