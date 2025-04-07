package PracticaTrivial.Juego;

import PracticaTrivial.Users.Admin;
import PracticaTrivial.Users.GestionaUsuarios;
import PracticaTrivial.Users.Player;
import PracticaTrivial.Users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class TrivialMain {
    private static ArrayList<User> users;

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
                Player p = (Player)GestionaUsuarios.creaUsuario(false);
                if(p != null) {
                    users.add(p);
                }
            }else if(respuesta == 2){
                Admin a = (Admin) GestionaUsuarios.creaUsuario(true);
                if(a != null) {
                    users.add(a);
                }
            }else if(respuesta == 3){

            }
        }while(respuesta != 4);


    }

}
