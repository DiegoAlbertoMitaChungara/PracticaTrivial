package PracticaTrivial.Preguntas;

public class Opcion {
    private String enunciado;
    private boolean correcta;

    public String getEnunciado() {
        return enunciado;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public Opcion(String enunciado, boolean correcta) {
        this.enunciado = enunciado;
        this.correcta = correcta;
    }
}
