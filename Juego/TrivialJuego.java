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

        //Variable que almacena la respuesta del usuario
        char respuesta;

        //Variable que almacena la respuesta del usuario y que correcponde al índice de la opción que ha elegido
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

            //Como 'scL.nextLine()' devuelve un String y no un char, primero almacenaremos la respuesta en un string
            String respuestaString;

            //Bucle que se repite mientras que el usuario no haya introducido A,B,C o D o sus variantes en minúsculas
            do{

                //Bucle que se repite si el usuario ha introducido más de una letra
                do {
                    System.out.println("¿Cuál es la correcta?");
                    respuestaString = scL.nextLine();
                }while(respuestaString.length() > 1);

                //Una vez que el usuario ha introducido una sola letra, la convertimos a char
                respuesta = respuestaString.charAt(0);

            }while((respuesta != 'A' && respuesta != 'a') && (respuesta != 'B' && respuesta != 'b') && (respuesta != 'C' && respuesta != 'c') && (respuesta != 'D' && respuesta != 'd'));


            //'respuestaInt' contendrá el índice de la opción que haya elegido el usuario
            if(respuesta == 'A' || respuesta == 'a'){
                respuestaInt = 0;
            }else if(respuesta == 'B' || respuesta == 'b'){
                respuestaInt = 1;
            }else if(respuesta == 'C' || respuesta == 'c'){
                respuestaInt = 2;
            }else{
                respuestaInt = 3;
            }

            //Compruebo si la opción que eligió era la correcta
            if(preguntaActual.esCorrecta(respuestaInt)){
                partida.sumarPuntos();
                System.out.println("Respuesta correcta!!!");
            }else{
                System.out.println("Respuesta incorrecta :c");
                System.out.println("La respuesta era: " + preguntaActual.getOpcionCorrecta());
            }


        }

        //Cuando acaben las preguntas, guarda automáticamente la partida
        try{
            GestionaFicheros.guardaPartida(partida);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

}
