package PracticaTrivial.Juego;

import PracticaTrivial.Ficheros.GestionaFicheros;
import PracticaTrivial.Users.User;

import java.io.IOException;
import java.util.ArrayList;

public class TrivialAdmin {

    /**
     * Metodo para poder ver todas las partidas que se han jugado
     */
    public void administrar(){
        try{
            ArrayList<String> partidas = GestionaFicheros.leePartidas();

            for(String partida : partidas){
                System.out.println(partida);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que muestra los nombres de todos los usuarios por orden alfabetico
     */
    public void mostrarUsuarios(){
        try{
            //Array de usuarios
            ArrayList<User> usuarios = GestionaFicheros.cargaUsers();

            //Variable para almacenar el User que tenga la inicial con menor valor en UNICODE
            User userMenor;

            //ArrayList de los usuarios ordenados
            ArrayList<User> usuariosOrdenados = new ArrayList<>();

            //Si solo hay un elemnto en Users, saco el nombre por pantalla y ya
            if(usuarios.size() == 1){
                System.out.println(usuarios.get(0).getNombre());
            }
            //Si hay más de un User
            else{

                /*
                    Lo que hago con estos bucles anidados es: por cada elemento en 'usuarios', recorro todos los elementos de 'usuarios' buscando un User con una letra menor que la suya, si la encuentra reemplaza el valor de 'userMenor' con ese User
                    y luego lo elimina del ArrayList (haciendo que recorra 1 iteracion menos cada vez que encontramos el nombre con menor valor. Por eso hago 'i--', porque llegará un momento en el que solo tenga 1 elemento en 'usuarios',
                    lo guarde en 'userMenor' y después lo elimine, quedando el ArrayLits vacío, con .size() = 0, y como 0 no es < que 0, no vuelve a entrar al bucle, no es un bucle infinito pese a no incrementar el valor de 'i' )
                 */
                for(int i = 0; i < usuarios.size(); i++){
                    userMenor = usuarios.get(i);
                    for(int j = i; j < usuarios.size(); j++){
                        if(usuarios.get(i).compareTo(usuarios.get(j)) == 1){
                            userMenor = usuarios.get(j);
                            usuarios.remove(j);
                            i--;
                        }
                    }

                    //Añadimos 'userMenor' a nuestra lista de usuarios ordenados
                    usuariosOrdenados.add(userMenor);
                }

                //Sacamos por pantalla los usuarios ordenados
                for(User u : usuariosOrdenados){
                    System.out.println(u.getNombre());
                }
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
