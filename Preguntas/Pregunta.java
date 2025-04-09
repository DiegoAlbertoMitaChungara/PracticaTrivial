package PracticaTrivial.Preguntas;

import java.util.Arrays;

public class Pregunta {
    /**
     * Enunciado de la pregunta
     */
    private String pregunta;
    /**
     * Opciones de la pregunta
     */
    private Opcion[] opciones;

    public String getPregunta() {
        return pregunta;
    }

    /**
     * Constructor de una pregunta. Recibe la pregunta y un array de opciones
     * @param pregunta El enunciado de la pregunta
     * @param opciones Las opciones que tiene la pregunta
     */
    public Pregunta(String pregunta, Opcion[] opciones) {
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    public String getOpcion(int index){
        return opciones[index].getEnunciado();
    }

    /**
     * Metodo para saber el enunciado de la opción correcta
     * @return El enunciado de la opción correcta
     */
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

    /**
     * Metodo para comprobar si una opcion es correcta
     * @param indice El índice de la opción para comprobar
     * @return True - si la opción es correcta. <br>
     *         False - si la opción es incorrecta.
     */
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
