package com.example.calculadoradenmerosprimos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void numerosPrimos(View vista){
        ArrayList <Integer> numPrimos = new ArrayList <Integer> ();
        TextView salida = (TextView)findViewById(R.id.txtSalida);
        EditText entrada = (EditText) findViewById(R.id.editEntrada);
        for(Integer  i = 0; i < 10000; i++){
            if(esPrimo(i)){
                numPrimos.add(i);

            }
        }
        entrada.setText(entrada.getText());
        String valor = entrada.getText().toString();
        int valorFinal = Integer.parseInt(valor);
        salida.setText("El primo número " + valorFinal + " es el: " + numPrimos.get(valorFinal));
    }

    public boolean esPrimo(Integer n){

        boolean esPrimo = true;
        Integer i = 2;
        while (i < n){
            if(n % i == 0){
                esPrimo = false;
            }
            i++;
        }

        return esPrimo;
    }
}
