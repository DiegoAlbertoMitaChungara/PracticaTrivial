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

        //Vuelve a pedir la contraseña mientras que la contraseña que inserte el usuario sea menor a 8 caracteres
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

        //Como dentro de 'cogeUsuario(String)' verifico si el usuario existe o no, no hace falta que lo verifique aquí
        User u = cogeUsuario(nombre);

        //Si el usuario no es null, es decir, existía, compruebo si su password es igual al password que se le pasa por parámetro
        if(u != null){

            if(u.compruebaPass(password)){
                return u;
            }else{
                System.out.println("La contraseña es incorrecta");
                return null;
            }

        }
        //Si el usuario es null, es decir, no existía
        else{
            System.out.println("Este usuario no existe");
            return null;
        }
    }

    /**
     * Este metodo sirve para crear usuarios (tanto Player como Admin) y devuelve el objeto creado (o Player o Admin)
     * @param tipoUsuario Booleano para diferenciar qué tipo de usuario quieres crear. <br> True para Admin | False para Player
     * @return Si se ha creado correctamente, el usuario creado. De lo contrario, null
     */
    public static User creaUsuario(boolean tipoUsuario){
        String nombreJugador;

        System.out.println("Vas a regsitrar un usuario.");
        nombreJugador = pideNombreUsuario();

        if(existeUsuario(nombreJugador)){
            System.out.println("Este usuario ya existe");
            return null;
        }else{
            String pass1 = pideContrasena();

            System.out.println("Confirmación: ");
            String pass2 = pideContrasena();

            //Si ambas contraseñas han coincidido
            if(pass1.equals(pass2)){
                System.out.println("Usuario creado");

                //Crea y retorna un Admin o un Player en función del booleano que se le pasa por parámetro
                if(tipoUsuario){
                    Admin a = new Admin(nombreJugador, pass1);
                    return a;
                }else{
                    Player p = new Player(nombreJugador,pass1);
                    return p;
                }
            }
            //Si las contraseñas no han coincidido
            else{
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
        //Puede ser que no encuentre el fichero o la clase, por eso hay un try-catch
        try{
            //Verifica primero que la lista no esté vacía
            if(!GestionaFicheros.cargaUsers().isEmpty()){
                ArrayList<User> usuarios = GestionaFicheros.cargaUsers();

                /*
                    Recorre la lista de usuarios posición por posición, accediendo a cada usuario con el índice 'i'. En cuanto encuentre uno con el nombre que se le pasa por parámetro, sale del bucle y devuelve True, si no lo encuentra, devuelve False
                 */
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

        }catch(IOException e){ //Si no ha encontrado el archivo para leer
            System.out.println(e.getMessage());
            return false;
        } catch (ClassNotFoundException c) { //Si no ha encontrado la clase User
            System.out.println(c.getMessage());
            return false;
        }
    }

    /**
     * Metodo para coger un usuario por su nombre de usuario. Solo lo devuelve, no lo elimina
     * @param nombre Nombre del usuario
     * @return Si existe, el objeto. De lo contrario, null
     */
    public static User cogeUsuario(String nombre){
        //Puede ser que no encuentre el fichero o la clase, por eso hay un try-catch
        try{

            //Primero comprobamos que existe el usuario que quiere coger
            if(existeUsuario(nombre)){
                ArrayList<User> usuarios = GestionaFicheros.cargaUsers();


                /*
                    Recorre la lista de usuarios, accediendo a cada usuario con el índice 'i', comparando cada nombre de usuario con el nombre que se le pasa por parámetro.
                    Si o si va a devolver un usuario, pues hemos verificado antes si este existe
                 */
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
        } catch (IOException e) { // Si no ha encontrado el archivo para leer
            System.out.println(e.getMessage());
            return null;
        }catch(ClassNotFoundException c){ // Si no ha encontrado la clase User
            System.out.println(c.getMessage());
            return null;
        }

    }


}
