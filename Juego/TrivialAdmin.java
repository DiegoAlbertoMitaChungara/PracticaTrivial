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
                    Lo que hago con estos bucles anidados es: por cada elemento de la lista de usuarios ('userMenor' es inicializado con ese valor) recorro todos los elementos de 'usuarios' buscando un User con una letra menor que la de 'userMenor', si la encuentra reemplaza el valor de 'userMenor' con ese User
                    para que en la siguiente iteración compare con la letra del nuevo 'userMenor' (por si hubiera otra letra menor). Cuando han sido comparados todos los users de 'usuarios' con 'userMenor', añado 'userMenor' a 'usuariosOrdenados'
                    y luego elimino 'userMenor' de 'usuarios'
                 */
                int i = 0;
                while (!usuarios.isEmpty()){
                    userMenor = usuarios.get(i);
                    for(int j = i; j < usuarios.size(); j++){
                        if(userMenor.compareTo(usuarios.get(j)) == 1){
                            userMenor = usuarios.get(j);
                        }
                    }

                    //Añadimos 'userMenor' a nuestra lista de usuarios ordenados
                    usuariosOrdenados.add(userMenor);
                    usuarios.remove(userMenor);
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
