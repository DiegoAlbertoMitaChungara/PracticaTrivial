package PracticaTrivial.Juego;

import PracticaTrivial.Ficheros.GestionaFicheros;
import PracticaTrivial.Users.Partida;

import java.io.IOException;
import java.util.ArrayList;

public class TrivialAdmin {
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
}
