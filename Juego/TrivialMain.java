package PracticaTrivial.Juego;

import PracticaTrivial.Ficheros.GestionaFicheros;
import PracticaTrivial.Users.Admin;
import PracticaTrivial.Users.GestionaUsuarios;
import PracticaTrivial.Users.Player;
import PracticaTrivial.Users.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrivialMain {
    private static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        int respuesta;
        boolean inicioSesion = false;

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

            try{
                if(respuesta == 1){
                    Player p = (Player)GestionaUsuarios.creaUsuario(false);
                    if(p != null) {
                        users.add(p);
                        GestionaFicheros.guardaUsers(users);
                    }
                }else if(respuesta == 2){
                    Admin a = (Admin) GestionaUsuarios.creaUsuario(true);
                    if(a != null) {
                        users.add(a);
                        GestionaFicheros.guardaUsers(users);
                    }
                }else if(respuesta == 3){
                    String nombreUser = GestionaUsuarios.pideNombreUsuario();
                    String passUser = GestionaUsuarios.pideContrasena();

                    User u = GestionaUsuarios.validaCredenciales(nombreUser, passUser);

                    if(u != null){
                        if(u instanceof Admin){
                            TrivialAdmin admin = new TrivialAdmin();
                            admin.administrar();
                        }else{
                            TrivialJuego juego = new TrivialJuego((Player) u);
                            juego.jugar();
                        }
                        inicioSesion = true;
                    }

                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


        }while(respuesta != 4 && !inicioSesion);


    }

}
