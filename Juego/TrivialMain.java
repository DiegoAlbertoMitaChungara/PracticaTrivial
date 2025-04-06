package PracticaTrivial.Juego;

import PracticaTrivial.Users.Player;
import PracticaTrivial.Users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class TrivialMain {
    private ArrayList<User> users;

    public static void main(String[] args) {
        int respuesta;

        do{
            Scanner scN = new Scanner(System.in);
            Scanner scL = new Scanner(System.in);
            System.out.println("Bienvenido al juego del Trivial. Por favor, escoge una de las siguientes opciones");
            System.out.println("""
                1. Registrar Jugador
                2. Registrar Administrador
                3. Iniciar Sesión
                4. Salir
                """);
            respuesta = scN.nextInt();

            if(respuesta == 1){
                String nombreJugador;
                String passwordJugador;
                String confirmaPassword;

                System.out.println("Vas a regsitrar un jugador.");
                System.out.println("Introduzca el nombre del jugador: ");
                nombreJugador = scN.nextLine();

                System.out.println("Introduzca el password del jugador: ");
                passwordJugador = scN.nextLine();

                System.out.println("Confirma el password del jugador: ");
                confirmaPassword = scN.nextLine();


                Player jugador = new Player()
            }
        }while(respuesta != 4);



    }
}
