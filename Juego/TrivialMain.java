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
        //Variable que almacena la respuesta del usuario en las opciones del menú
        int respuesta = 0;

        //Variable de control para saber si el usuario ha iniciado sesión correctamente o no
        boolean inicioSesion = false;


        //Inicializo la lista de usuarios cargándolos desde users.dat, si no encuentra el archivo o no encuentra la clase User, la inicializa vacía
        try{
            users = GestionaFicheros.cargaUsers();
        }catch (IOException e) {
            System.out.println(e.getMessage());
            users = new ArrayList<>();
        }catch (ClassNotFoundException c) {
            System.out.println(c.getMessage());
            users = new ArrayList<>();
        }

        //Repite el bucle mientras que la opción del usuario sea distinto de 4 (4 es la opcion de salir) y mientras no se haya iniciado sesión correctamente
        do{

            /*
                Si el usuario escirbe letras, vuelve a pedir al usuario el input. Para verificar si el usuario ha introducido un número
                he creado un flag 'caracterValido' que inicializo en false. El bucle de pedir el input se repite mientras 'caracterValido'
                sea false y solo se pondrá a true (la línea que va después del 'scN.nextInt()' ) si en la línea 'respuesta = scN.nextInt();'
                no saltó una excepción.

                Si NO SALTA la EXCEPCIÓN, continua ejecutando la siguiente línea con normalidad (en la que ponemos el flag a true para que salga del bucle).
                Si SI SALTA la EXCEPCIÓN, se salta todas las líneas siguientes para ir al 'catch' directamente (dejando el flag a false y provocando que se vuelva a repetir el bucle
                debido a la condición)
             */

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


            // Puede no encontrar el archivo para leer, por ello hacemos un try-catch
            try{
                // El usuario quiere crear un jugador
                if(respuesta == 1){
                    Player p = (Player)GestionaUsuarios.creaUsuario(false);
                    if(p != null) {
                        users.add(p);
                        GestionaFicheros.guardaUsers(users);
                    }
                }
                // El usuario quiere crear un administrador
                else if(respuesta == 2){
                    Admin a = (Admin) GestionaUsuarios.creaUsuario(true);
                    if(a != null) {
                        users.add(a);
                        GestionaFicheros.guardaUsers(users);
                    }
                }
                // El usuario quiere iniciar sesión
                else if(respuesta == 3){
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
                        }
                        //Si es una instancia de Player. Un jugador
                        else{
                            TrivialJuego juego = new TrivialJuego((Player) u);
                            juego.jugar();
                        }

                        //En cualquiera de los casos, el inicio de sesión fue exitoso y saldrá del bucle
                        inicioSesion = true;
                    }else{ //Si el objeto devuelto fue null, es decir, no se encontró el usuario o la contraseña fue incorrecta
                        System.out.println("Has introducido un nombre de usuario o una contrseña incorrecta");
                    }

                }

            }catch (IOException e) { //Si no se ha encontrado el archivo para leer
                System.out.println(e.getMessage());
            }


        }while(respuesta != 4 && !inicioSesion);


    }

}
