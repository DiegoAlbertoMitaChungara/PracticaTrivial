package Trivial.PracticaTrivial.Users;

import java.util.Date;

public class Partida {
    private Date date;
    private int puntuacion;
    private Player player;

    public Partida(Player player) {
        this.player = player;
        this.date = new Date();
        this.puntuacion = 0;
    }

    public int sumarPuntos(){
        puntuacion++;
        return puntuacion;
    }
}
