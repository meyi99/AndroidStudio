package com.example.tresenraya;

import java.util.Random;

public class Partida {

    public final int DIFICULTAD;
    public  int jugador;
    private int[] casillas;
    private final int[][] COMBINACIONES ={
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    // Constructor
    public Partida(int dificultad){

        this.DIFICULTAD = dificultad;
        jugador = 1;
        casillas = new int[9];

        for (int i = 0; i < 9; i++){
            casillas[i] = 0;
        }

    }

    // Método iA para que la maquina juegue contra nosotros
    public int iA(){
        int casilla;
        casilla = dosEnRaya(2);

        if(casilla != -1){
            return casilla;
        }
        if(DIFICULTAD > 0){
            casilla = dosEnRaya(1);

            if(casilla != -1){
                return casilla;
            }
        }

        if(DIFICULTAD == 2){
            if(casillas[0] == 0){
                return 0;
            }
            if(casillas[2] == 0){
                return 2;
            }
            if(casillas[6] == 0){
                return 6;
            }
            if(casillas[8] == 0){
                return 8;
            }

        }

        Random casillaAleatoria = new Random();
        casilla = casillaAleatoria.nextInt(9);

        return casilla;
    }

    // Método turno
    public int turno(){
        boolean empate = true;
        boolean ultimoMovimiento = true;

        for (int i = 0; i < COMBINACIONES.length; i++){
            for (int pos: COMBINACIONES[i]){

                //System.out.println("Valor en posición " + pos + " " + casillas[pos]);

                if(casillas[pos] != jugador){
                    ultimoMovimiento = false;
                }

                if(casillas[pos] == 0){
                    empate = false;
                }
            }

            //System.out.println("---------------------------");

            if(ultimoMovimiento){
                return jugador;
            }

            ultimoMovimiento = true;
        }

        if(empate){
            return 3;
        }

        jugador++;

        if(jugador > 2){
            jugador = 1;
        }

        return 0;
    }

    // Método compruebaCasilla sirve para comprobar si la casilla ya ha sido pulsada
    public boolean compruebaCasilla(int casilla){
        if(casillas[casilla] != 0){
            return false;
        }
        else {
            casillas[casilla] = jugador;
        }

        return true;
    }

    // Método dosEnRaya
    public int dosEnRaya(int jugadorEnTurno){
        int casilla = -1;
        int cuantasLleva = 0;

        for (int i = 0; i < COMBINACIONES.length; i++){
            for (int pos: COMBINACIONES[i]) {

                if(casillas[pos] == jugadorEnTurno){
                    cuantasLleva++;
                }
                if(casillas[pos] == 0){
                    casilla = pos;
                }
            }

            if(cuantasLleva == 2 && casilla != -1){
                return casilla;
            }

            casilla = -1;
            cuantasLleva = 0;
        }

        return -1;
    }
}








































