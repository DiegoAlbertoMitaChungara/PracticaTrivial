package PracticaTrivial.Users;

import java.util.Date;

public class Partida {
    private Date date;
    private int puntuacion;
    private Player player;

    public Date getDate() {
        return date;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public Player getPlayer() {
        return player;
    }

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
