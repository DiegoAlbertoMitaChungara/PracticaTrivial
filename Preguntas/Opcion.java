package PracticaTrivial.Preguntas;

public class Opcion {
    /**
     * Enunciado de la opción
     */
    private String enunciado;
    /**
     * Si la opción es correcta o no
     */
    private boolean correcta;

    public String getEnunciado() {
        return enunciado;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    /**
     * Constructor de una opción. Se le pasa el enunciado de la opción y si esa es la opción correcta
     * @param enunciado Enunciado de la opción
     * @param correcta Si la opción es la correcta
     */
    public Opcion(String enunciado, boolean correcta) {
        this.enunciado = enunciado;
        this.correcta = correcta;
    }
}
