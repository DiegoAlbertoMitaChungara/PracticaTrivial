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

    private static ArrayList<User> users;

    public static void main(String[] args) {
        int respuesta = 0;
        boolean inicioSesion = false;

        try{
            users = GestionaFicheros.cargaUsers();
        }catch (IOException e) {
            System.out.println(e.getMessage());
            users = new ArrayList<>();
        }catch (ClassNotFoundException c) {
            System.out.println(c.getMessage());
            users = new ArrayList<>();
        }

        do{

            // Vuelve a pedir el input si el usuario escribe letras
            boolean caracterValido = false;
            do{
                try{
                    Scanner scN = new Scanner(System.in);
                    System.out.println("Bienvenido al juego del Trivial. Por favor, escoge una de las siguientes opciones");
                    System.out.println("""
                        1. Registrar Jugador
                        2. Registrar Administrador
                        3. Iniciar Sesión
                        4. Salir
                    """);
                    respuesta = scN.nextInt();

                    caracterValido = true;
                } catch (Exception e) {
                    System.out.println("Inserte un caracter valido");
                }
            }while(!caracterValido);


            try{
                if(respuesta == 1){ //El usuario quiere crear un jugador
                    Player p = (Player)GestionaUsuarios.creaUsuario(false);
                    if(p != null) {
                        users.add(p);
                        GestionaFicheros.guardaUsers(users);
                    }
                }else if(respuesta == 2){ //El usuario quiere crear un administrador
                    Admin a = (Admin) GestionaUsuarios.creaUsuario(true);
                    if(a != null) {
                        users.add(a);
                        GestionaFicheros.guardaUsers(users);
                    }
                }else if(respuesta == 3){ //El usuario quiere iniciar sesión
                    String nombreUser = GestionaUsuarios.pideNombreUsuario();
                    String passUser = GestionaUsuarios.pideContrasena();

                    User u = GestionaUsuarios.validaCredenciales(nombreUser, passUser);

                    //Si el objeto devuleto no es null, es decir, se ha encontrado y devuelto el usuario con esas credenciales
                    if(u != null){
                        //Verificamos si el usuario devuelto es una instancia de Admin, es decir, ha iniciado sesión un administrador
                        if(u instanceof Admin){
                            TrivialAdmin admin = new TrivialAdmin();
                            admin.administrar();
                            admin.mostrarUsuarios();
                        }else{ //Si es una instancia de Player. Un jugador
                            TrivialJuego juego = new TrivialJuego((Player) u);
                            juego.jugar();
                        }

                        //En cualquiera de los casos, el inicio de sesión fue exitoso y saldrá del bucle
                        inicioSesion = true;
                    }else{ //Si el objeto devuelto fue null, es decir, no se encontró el usuario o la contraseña fue incorrecta
                        System.out.println("Has introducido un nombre de usuario o una contrseña incorrecta");
                    }

                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


        }while(respuesta != 4 && !inicioSesion);


    }

}
