package PracticaTrivial.Preguntas;

import java.util.Arrays;

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

    public String getOpcion(int index){
        return opciones[index].getEnunciado();
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

    /**
     * Metodo que desordena las opciones de la pregunta generando un número aleatorio e intermanbiando cada posicion con la posicion en el número aleatorio, comprobando antes que el número aleatorio no haya salido antes
     */
    public void desordenaOpciones(){
        int nRandom;
        int[] numRepes = new int [opciones.length];

        //Relleno con -1 porque, por defecto se rellena con 0 y 0 es un índice válido, por lo que puede salir en el número aleatorio
        Arrays.fill(numRepes, -1);

        int i = 0;
        while (i < opciones.length){
            nRandom = (int) (Math.random() * opciones.length);

            if(i == 0){
                if(nRandom == 0){
                    do{
                        nRandom = (int) (Math.random() * opciones.length);
                    }while(nRandom == 0);
                }
            }

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
                i++;
            }


        }
    }
}
