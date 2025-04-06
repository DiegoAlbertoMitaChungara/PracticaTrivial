package PracticaTrivial.Ficheros;

import PracticaTrivial.Preguntas.Pregunta;
import PracticaTrivial.Users.Partida;
import PracticaTrivial.Users.User;

import java.io.*;
import java.util.ArrayList;

public class GestionaFicheros {
    private static File filePreguntas;
    private static File fileUser;
    private static File filePartidas;

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
        FileReader entrada = new FileReader("..\\Files\\Preguntas.txt");

        entrada.close();
    }

    public void guardaPartida(Partida partida){

    }

    public ArrayList<String> leePartidas(){

    }
}
