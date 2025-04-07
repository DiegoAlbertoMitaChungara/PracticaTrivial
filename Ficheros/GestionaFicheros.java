package PracticaTrivial.Ficheros;

import PracticaTrivial.Preguntas.Opcion;
import PracticaTrivial.Preguntas.Pregunta;
import PracticaTrivial.Users.Partida;
import PracticaTrivial.Users.User;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GestionaFicheros {
    private static File filePreguntas;
    private static File fileUser;
    private static File filePartidas;

    /**
     * Atributo que guarda el contenido de filePartidas.txt antes de guardar otra partida (para solucionar la sobreescritura)
     */
    private String partidas = "";

    public void guardaUsers(ArrayList<User> users) throws IOException {
        FileOutputStream fos = new FileOutputStream("users.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users);
        oos.close();
    }
    public ArrayList<User> cargaUsers() throws IOException, ClassNotFoundException {
        ArrayList<User> users;

        FileInputStream fis = new FileInputStream("users.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        users = (ArrayList<User>) ois.readObject();
        ois.close();

        return users;
    }

    public ArrayList<Pregunta> cargaPreguntas() throws IOException{
        FileReader entrada = new FileReader("src\\Files\\Preguntas.txt");
        BufferedReader br = new BufferedReader(entrada);

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        String linea = br.readLine();

        while(linea != null){
            //Leemos cada pregunta y sus posibles respuestas y las al,acenamos en varibales String para, más adelante, poder crear objetos de tipo Opcion y Pregunta
            String textoPregunta = linea;
            String textoOpcion1 = br.readLine();
            String textoOpcion2 = br.readLine();
            String textoOpcion3 = br.readLine();
            String textoOpcion4 = br.readLine();

            //Creamos objetos Opcion. La primera de las opciones es la correcta y lo sabemos por cómo vamos a implementar la lógica de guardado de preguntas
            Opcion op1 = new Opcion(textoOpcion1,true);
            Opcion op2 = new Opcion(textoOpcion2,false);
            Opcion op3 = new Opcion(textoOpcion3,false);
            Opcion op4 = new Opcion(textoOpcion4,false);

            //Creamos el array de opciones para poder crear, más adelante, un objeto Pregunta
            Opcion[] opciones = new Opcion[4];
            opciones[0] = op1;
            opciones[1] = op2;
            opciones[2] = op3;
            opciones[3] = op4;

            //Creamos la pregunta
            Pregunta p = new Pregunta(textoPregunta,opciones);

            //Añadimos la pregunta al ArrayList que vamos a devolver
            preguntas.add(p);

            //Almacenamos el contenido de la siguiente línea para verificarlo en el while
            linea = br.readLine();
        }

        entrada.close();
        return preguntas;
    }

    public void guardaPartida(Partida partida){

    }

    public ArrayList<String> leePartidas(){

    }
}
