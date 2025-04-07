package PracticaTrivial.Users;

import PracticaTrivial.Ficheros.GestionaFicheros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionaUsuarios {

    public static boolean existeUsuario(String nombre){
        try{

            if(GestionaFicheros.cargaUsers().size() > 0){
                ArrayList<User> usuarios = GestionaFicheros.cargaUsers();

                boolean existe = false;
                int i = 0;
                while(i < usuarios.size() && !existe){
                    if(usuarios.get(i).getNombre().equals(nombre)){
                        existe = true;
                    }else{
                        i++;
                    }
                }
                return existe;
            }else return false;

        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este metodo sirve para crear usuarios (tanto Player como Admin) y devuelve el objeto creado (o Player o Admin)
     * @param tipoUsuario Booleano para diferenciar qué tipo de usuario quieres crear. True->Admin False->Player
     * @return Objeto de tipo Player o Admin
     */
    public static User creaUsuario(boolean tipoUsuario){
        Scanner sc = new Scanner(System.in);
        String nombreJugador;

        System.out.println("Vas a regsitrar un jugador.");
        nombreJugador = pideNombreUsuario();

        if(existeUsuario(nombreJugador)){
            System.out.println("Este usuario ya existe");
            return null;
        }else{
            String pass1 = pideContrasena();
            String pass2 = pideContrasena();

            if(pass1.equals(pass2)){
                System.out.println("Usuario creado");

                if(tipoUsuario){
                    return new Admin(nombreJugador, pass1);
                }else{
                    return new Player(nombreJugador,pass1);
                }
            }else{
                System.out.println("Las contraseñas no coincidieron. Usuario no creado");
                return null;
            }
        }

    }

    public static String pideContrasena(){
        Scanner sc = new Scanner(System.in);
        String password;

        do{
            System.out.println("Introduzca la contraseña: ");
            password = sc.nextLine();
        }while(password.length() < 8);

        return password;
    }

    public static String pideNombreUsuario(){
        Scanner sc = new Scanner(System.in);
        String nombre;

        System.out.println("Introduzca el nombre de usuario: ");
        nombre = sc.nextLine();

        return nombre;
    }
}
