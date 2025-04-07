package PracticaTrivial.Ficheros;

import PracticaTrivial.Preguntas.Opcion;
import PracticaTrivial.Preguntas.Pregunta;
import PracticaTrivial.Users.Partida;
import PracticaTrivial.Users.User;

import java.io.*;
import java.util.ArrayList;

public class GestionaFicheros {
    private static final File FILE_PREGUNTAS = new File( "src\\Files\\preguntas.txt");
    private static final File FILE_USERS = new File( "src\\Files\\users.dat");
    private static final File FILE_PARTIDAS = new File("src\\Files\\partidas.txt");

    /**
     * Atributo que guarda el contenido de filePartidas.txt antes de guardar otra partida (para solucionar la sobreescritura)
     */
    private static String partidas = "";

    public static void guardaUsers(ArrayList<User> users) throws IOException {
        FileOutputStream fos = new FileOutputStream("users.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users);
        oos.close();
    }
    public static ArrayList<User> cargaUsers() throws IOException, ClassNotFoundException {
        ArrayList<User> users;

        FileInputStream fis = new FileInputStream(FILE_USERS);
        ObjectInputStream ois = new ObjectInputStream(fis);
        users = (ArrayList<User>) ois.readObject();
        ois.close();

        return users;
    }

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

    public static void guardaPartida(Partida partida) throws IOException {
        FileWriter salida = new FileWriter(FILE_PARTIDAS);
        BufferedWriter bw = new BufferedWriter(salida);
        partidas += "Fecha y hora: " + partida.getDate()+ " Usuario: " + partida.getPlayer().getNombre() + " Puntuación: " + partida.getPuntuacion() + "\n";
        bw.write(partidas);


        bw.close();
        salida.close();
    }

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
