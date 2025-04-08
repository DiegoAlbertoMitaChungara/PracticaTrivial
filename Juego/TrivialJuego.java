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

    /**
     * Metodo que empieza el flujo de juego
     */
    public void jugar(){
        Scanner scN = new Scanner(System.in);
        int respuesta;
        System.out.println("Empieza el juego, " + player.getNombre() + "!!");

        for(int i = 0; i < preguntas.size(); i++){

            Pregunta preguntaActual = preguntas.get(i);

            System.out.println("Pregunta " + (i+1));
            System.out.println(preguntaActual.getPregunta());
            preguntaActual.desordenaOpciones();
            System.out.println("1. " + preguntaActual.getOpcion(0));
            System.out.println("2. " + preguntaActual.getOpcion(1));
            System.out.println("3. " + preguntaActual.getOpcion(2));
            System.out.println("4. " + preguntaActual.getOpcion(3));

            System.out.print("¿Cuál es la correcta?");
            respuesta = scN.nextInt();

            if(preguntaActual.getOpcion(respuesta-1).equalsIgnoreCase(preguntaActual.getOpcionCorrecta())){
                partida.sumarPuntos();
                System.out.println("Respuesta correcta!!!");
            }else{
                System.out.println("Respuesta incorrecta :c");
                System.out.println("La respuesta era: " + preguntaActual.getOpcionCorrecta());
            }


        }

        try{
            GestionaFicheros.guardaPartida(partida);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

}
