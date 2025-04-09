package PracticaTrivial.Users;

import java.util.Date;

public class Partida {
    /**
     * Fecha y Hora en la que se inició la partida
     */
    private Date date;
    /**
     * Puntuación de la partida
     */
    private int puntuacion;
    /**
     * Jugador de la partida
     */
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

    /**
     * Constructor de una partida. La fecha y hora se crea al llamar al constructor, la puntuacioón empieza en 0 y el jugador de la partida se pasa por parámetro
     * @param player Jugador de la partida que se va a crear
     */
    public Partida(Player player) {
        this.player = player;
        this.date = new Date();
        this.puntuacion = 0;
    }

    /**
     * Metodo que suma 1 punto a la puntuación de la partida
     * @return Los puntos totales después de la suma
     */
    public int sumarPuntos(){
        puntuacion++;
        return puntuacion;
    }
}
