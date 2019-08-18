package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Campos de clase
    private int jugadores;
    private int[] casillas;
    private Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Guardamos las casillas del tablero en el array[] casillas
        casillas = new int[9];
        casillas[0] = R.id.a1;
        casillas[1] = R.id.a2;
        casillas[2] = R.id.a3;
        casillas[3] = R.id.b1;
        casillas[4] = R.id.b2;
        casillas[5] = R.id.b3;
        casillas[6] = R.id.c1;
        casillas[7] = R.id.c2;
        casillas[8] = R.id.c3;
    }

    // Método inicioJuego
    public void incioJuego(View vista){
        ImageView imagen;

        // Bucle foreach para recorrer el array casillas
        for(int cadaCasilla:casillas){
            imagen = (ImageView)findViewById((cadaCasilla));
            imagen.setImageResource(R.drawable.casilla);
        }

        jugadores = 1;

        if(vista.getId() == R.id.dos_jugadores){
            jugadores = 2;
        }

        RadioGroup seleccionDificultad = (RadioGroup)findViewById(R.id.radioGroupDificultad);
        int id = seleccionDificultad.getCheckedRadioButtonId();
        int dificultad = 0;

        if(id == R.id.normal){
            dificultad = 1;
        }
        else if(id == R.id.imposible){
            dificultad = 2;
        }

        partida = new Partida(dificultad);

        // Hacemos los botones invisibles para que no se vean mientras el juego esta en funcionamiento
        ((Button)findViewById(R.id.un_jugador)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.radioGroupDificultad)).setAlpha(0);
        ((Button)findViewById(R.id.dos_jugadores)).setEnabled(false);
    }

    // Método pulsarCasilla
    public void pulsarCasilla(View vista){

        if(partida == null){
            return;
        }

        int casilla = 0;

        for(int i = 0; i < 9; i++){
            if(casillas[i] == vista.getId()){
                casilla = i;
                break;
            }
        }


        if(partida.compruebaCasilla(casilla) == false){
            return;
        }

        marcarCasilla(casilla);
        int resultado = partida.turno();

        if(resultado > 0){
            termina(resultado);
            return;
        }

        casilla = partida.iA();

        while (partida.compruebaCasilla(casilla) != true){
            casilla = partida.iA();
        }

        marcarCasilla(casilla);
        resultado = partida.turno();

        if(resultado > 0){
            termina(resultado);
        }
    }

    // Método marcarCasilla
    public void marcarCasilla(int casilla){
        ImageView imagen;
        imagen = (ImageView)findViewById(casillas[casilla]);

        if(partida.jugador == 1){
            imagen.setImageResource(R.drawable.circulo);
        }
        else {
            imagen.setImageResource(R.drawable.aspa);
        }
    }

    // Método termina() se encarga de pasar el resultado como argumento para que la partida termine
    private void termina(int resultado){
        String mensaje;

        if(resultado == 1){
            mensaje = "!Han ganado los círculos¡";
        }
        else if(resultado == 2){
            mensaje = "!Han ganado las aspas¡";
        }
        else{
            mensaje = "!La partida ha terminado en empate¡";
        }

        // Mensaje emergente que dice el resultado de la partida
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        // Ponemos la partida ha null para que termine el juego
        partida = null;

        // Una vez terminada la partida volvemos ha hacer visibles los botones
        ((Button)findViewById(R.id.un_jugador)).setEnabled(true);
        ((RadioGroup)findViewById(R.id.radioGroupDificultad)).setAlpha(1);
        ((Button)findViewById(R.id.dos_jugadores)).setEnabled(true);
    }


}




























