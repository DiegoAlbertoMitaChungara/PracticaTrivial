package PracticaTrivial.Juego;

import PracticaTrivial.Ficheros.GestionaFicheros;
import PracticaTrivial.Preguntas.Pregunta;
import PracticaTrivial.Users.Partida;
import PracticaTrivial.Users.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrivialJuego {
    /**
     * Lista de preguntas del juego
     */
    private ArrayList<Pregunta> preguntas;
    /**
     * Jugador del juego
     */
    private Player player;
    /**
     * Partida que está jugando el jugador
     */
    private Partida partida;

    /**
     * Constructor de un juego. El constructor recibe solo un jugador, dentro se crea una partida con ese jugador se cargan las preguntas del juego
     * @param player Jugador que va a jugar al juego
     */
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
        char respuesta;
        int respuestaInt;
        System.out.println("Empieza el juego, " + player.getNombre() + "!!");

        for(int i = 0; i < preguntas.size(); i++){

            Pregunta preguntaActual = preguntas.get(i);

            System.out.println("Pregunta " + (i+1));
            System.out.println(preguntaActual.getPregunta());
            preguntaActual.desordenaOpciones();
            System.out.println("A. " + preguntaActual.getOpcion(0));
            System.out.println("B. " + preguntaActual.getOpcion(1));
            System.out.println("C. " + preguntaActual.getOpcion(2));
            System.out.println("D. " + preguntaActual.getOpcion(3));


            Scanner scL = new Scanner(System.in);

            String respuestaString;
            do{
                do {
                    System.out.println("¿Cuál es la correcta?");
                    respuestaString = scL.nextLine();
                }while(respuestaString.length() > 1);

                respuesta = respuestaString.charAt(0);

            }while((respuesta != 'A' && respuesta != 'a') && (respuesta != 'B' && respuesta != 'b') && (respuesta != 'C' && respuesta != 'c') && (respuesta != 'D' && respuesta != 'd'));


            if(respuesta == 'A' || respuesta == 'a'){
                respuestaInt = 0;
            }else if(respuesta == 'B' || respuesta == 'b'){
                respuestaInt = 1;
            }else if(respuesta == 'C' || respuesta == 'c'){
                respuestaInt = 2;
            }else{
                respuestaInt = 3;
            }

            if(preguntaActual.esCorrecta(respuestaInt)){
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
