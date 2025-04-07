package PracticaTrivial.Juego;

import PracticaTrivial.Ficheros.GestionaFicheros;
import PracticaTrivial.Preguntas.Pregunta;
import PracticaTrivial.Users.Partida;
import PracticaTrivial.Users.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrivialJuego {
    private ArrayList<Pregunta> preguntas;
    private Player player;
    private Partida partida;

    public TrivialJuego(Player player) {
        try{
            this.player = player;
            partida = new Partida(player);
            preguntas = GestionaFicheros.cargaPreguntas();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void jugar(){
        Scanner scN = new Scanner(System.in);
        System.out.println("Empieza el juego, " + player.getNombre() + "!!");

        for(int i = 0; i < preguntas.size(); i++){
            System.out.println("Pregunta " + (i+1));
            System.out.println();
        }
    }

}
