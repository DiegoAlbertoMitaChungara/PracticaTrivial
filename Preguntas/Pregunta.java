package PracticaTrivial.Preguntas;

public class Pregunta {
    private String pregunta;
    private Opcion[] opciones;

    public String getPregunta() {
        return pregunta;
    }

    public Pregunta(String pregunta, Opcion[] opciones) {
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    public String getOpcionCorrecta(){
        boolean correcta = false;
        int i = 0;
        while(i < opciones.length && !correcta){
            if(opciones[i].isCorrecta()){
                correcta = true;
            }else{
                i++;
            }
        }

        return opciones[i].getEnunciado();
    }

    public boolean esCorrecta(int indice){
        return opciones[indice].isCorrecta();
    }

    
    public void desordenaOpciones(){
        int nRandom;
        int[] numRepes = new int [opciones.length];

        for(int i = 0; i < opciones.length; i++){
            nRandom = (int) (Math.random() * opciones.length);

            boolean repeEncontrado = false;
            int j = 0;
            while(j < numRepes.length && !repeEncontrado){
                if(numRepes[j] == nRandom){
                    repeEncontrado = true;
                }else{
                    j++;
                }
            }

            if(!repeEncontrado){
                Opcion aux = opciones[i];
                opciones[i] = opciones[nRandom];
                opciones[nRandom] = aux;

                numRepes[i] = nRandom;
            }else{
                i--;
            }
        }
    }
}
