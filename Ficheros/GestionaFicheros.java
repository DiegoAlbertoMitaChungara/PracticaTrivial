package PracticaTrivial.Ficheros;

import PracticaTrivial.Preguntas.Opcion;
import PracticaTrivial.Preguntas.Pregunta;
import PracticaTrivial.Users.Partida;
import PracticaTrivial.Users.User;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GestionaFicheros {
    /**
     * Guarda el path donde va a crear o a leer el archivo de preguntas.txt
     */
    private static final File FILE_PREGUNTAS = Paths.get("src","PracticaTrivial","Files","preguntas.txt").toFile();
    /**
     * Guarda el path donde va a crear o a leer el archivo de users.dat
     */
    private static final File FILE_USERS = Paths.get("src","PracticaTrivial","Files","users.dat").toFile();
    /**
     * Guarda el path donde va a crear o a leer el archivo de partidas.txt
     */
    private static final File FILE_PARTIDAS = Paths.get("src","PracticaTrivial","Files","partidas.txt").toFile();

    /**
     * Metodo para guardar los usuarios registrados (tanto Players como Admins)en un archivo binario que se llama 'users.dat'. Guarda su nombre de usuario y su contraseña
     * @param users La lista de usuarios que desee guardar
     * @throws IOException Lanza esta excepción cuando no se ha encontrado el archivo o no lo ha podido crear
     */
    public static void guardaUsers(ArrayList<User> users) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_USERS);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users);
        oos.close();
    }

    /**
     * Metodo para cargar los usuarios registrados, tanto Players como Admins
     * @return ArrayList de User. Una lista con los usuarios leidos
     * @throws IOException Lanza esta excepción cuando no se ha encontrado el archivo, es decir, no han habido usuarios registrados aún
     * @throws ClassNotFoundException Lanza esta excepción cuando no se ha encontrado la clase User
     */
    public static ArrayList<User> cargaUsers() throws IOException, ClassNotFoundException {
        ArrayList<User> users;

        FileInputStream fis = new FileInputStream(FILE_USERS);
        ObjectInputStream ois = new ObjectInputStream(fis);
        users = (ArrayList<User>) ois.readObject();
        ois.close();

        return users;
    }

    /**
     * Metodo para cargar las preguntas guardadas en el archivo 'partidas.txt'
     * @return ArrayList de Pregunta. Una lista con las preguntas leidas
     * @throws IOException Lanza esta excepción cuando no se ha encontrado el archivo
     */
    public static ArrayList<Pregunta> cargaPreguntas() throws IOException{
        FileReader entrada = new FileReader(FILE_PREGUNTAS);
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

    /**
     * Metodo que guarda una partida en un archivo de texto que se llama 'partidas.txt'. Guarda la fecha y hora de la partida, el usuario y su puntaje
     * @param partida La partida que quieres guardar
     * @throws IOException Lanza esta excepción cuando no se ha encontrado el archivo o no lo ha podido crear
     */
    public static void guardaPartida(Partida partida) throws IOException {
        FileWriter salida = new FileWriter(FILE_PARTIDAS, true);
        BufferedWriter bw = new BufferedWriter(salida);
        bw.write("Fecha y hora: " + partida.getDate()+ " Usuario: " + partida.getPlayer().getNombre() + " Puntuación: " + partida.getPuntuacion() + "\n");
        bw.close();
        salida.close();
    }

    /**
     * Metodo para leer las partidas que se han guardado.
     * @return ArrayList de Strings, cada elemento de la lista contiene la información de una partida
     * @throws IOException Lanza esta excepción cuando no se ha encontrado el archivo, es decir, no han habido partidas aún
     */
    public static ArrayList<String> leePartidas() throws IOException {
        FileReader fr = new FileReader(FILE_PARTIDAS);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> partidas = new ArrayList<>();
        String linea = br.readLine();

        while(linea != null){
            String infoPartida = linea;
            partidas.add(infoPartida);
            linea = br.readLine();
        }

        br.close();
        fr.close();

        return partidas;
    }
}
