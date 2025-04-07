package PracticaTrivial.Users;

import PracticaTrivial.Ficheros.GestionaFicheros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionaUsuarios {


    /**
     * Metodo para pedir una contraseña para un usuario.
     * @return La contraseña introducida
     */
    public static String pideContrasena(){
        Scanner sc = new Scanner(System.in);
        String password;

        do{
            System.out.println("Introduzca la contraseña: ");
            password = sc.nextLine();
        }while(password.length() < 8);

        return password;
    }

    /**
     * Metodo para pedir un nombre de usuario.
     * @return El nombre que ha introducido
     */
    public static String pideNombreUsuario(){
        Scanner sc = new Scanner(System.in);
        String nombre;

        System.out.println("Introduzca el nombre de usuario: ");
        nombre = sc.nextLine();

        return nombre;
    }

    /**
     * Metodo para validar las credenciales de un usuario.
     * @param nombre Nombre del usuario
     * @param password Contraseña del usuraio
     * @return El objeto cuyas credenciales han coincidido. Si no existia o las credenciales no coincidieron, null
     */
    public static User validaCredenciales(String nombre, String password){
        if(existeUsuario(nombre)){

            User u = cogeUsuario(nombre);

            if(u.getPass().equals(password)){
                return u;
            }else{
                return null;
            }

        }else{
            System.out.println("Este usuario no existe");
            return null;
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
                    Admin a = new Admin(nombreJugador, pass1);
                    return a;
                }else{
                    Player p = new Player(nombreJugador,pass1);
                    return p;
                }
            }else{
                System.out.println("Las contraseñas no coincidieron. Usuario no creado");
                return null;
            }
        }

    }

    /**
     * Metodo para comprobar si existe un usuario.
     * @param nombre Nombre del usuario
     * @return True, si hay un user con ese nombre. False en cualquier otro caso.
     */
    public static boolean existeUsuario(String nombre){
        try{

            if(!GestionaFicheros.cargaUsers().isEmpty()){
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
     * Metodo para coger un usuario por su nombre de usuario.
     * @param nombre Nombre del usuario
     * @return Si existe, el objeto. De lo contrario, null
     */
    public static User cogeUsuario(String nombre){
        try{
            if(existeUsuario(nombre)){
                ArrayList<User> usuarios = GestionaFicheros.cargaUsers();

                boolean encontrado = false;
                int i = 0;
                while(i < usuarios.size() && !encontrado){
                    if(usuarios.get(i).getNombre().equals(nombre)){
                        encontrado = true;
                    }else{
                        i++;
                    }
                }

                return usuarios.get(i);

            }else return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    }


}
