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

        /*
            Recorre el array de opciones buscando cuál es la correcta (cual de ellas tiene su atributo 'correcta' a true). Como sabemos que una de las 4 opciones es correcta,
            sabemos que va a devolver si o si un enunciado
         */
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

        //Array donde van a ser almacenados los números aleatorios que ya hayan sido generados
        int[] numRepes = new int [opciones.length];

        //Relleno con -1 porque, por defecto se rellena con 0 y 0 es un índice válido, por lo que puede salir en el número aleatorio
        Arrays.fill(numRepes, -1);


        //Bucle que realizará 4 intercambios entre posiciones
        int i = 0;
        while (i < opciones.length){
            nRandom = (int) (Math.random() * opciones.length);

            //Si estoy en la primera vuelta
            if(i == 0){
                //Y el numero random ha salido 0, por lo que haría un cambio de la posición 0 con la posición 0 (quedaría en la misma posición después del cambio)
                if(nRandom == 0){

                    //Genera números aleatorios hasta que salga uno distinto de 0 (para que haga efecto el cambio de posiciones)
                    do{
                        nRandom = (int) (Math.random() * opciones.length);
                    }while(nRandom == 0);
                }
            }

            /*
                Una vez generado el número aleatorio hay que comprobar que ese número no fue generado antes. Esa comprobación la hacemos recorriendo el array 'numRepes'
                buscando si hay algún numero que coincida con 'nRandom'. Para ver el resultado de la comprobación he utilizado un flag 'repeEncontrado', inicializado en false
                , y que solo se pondrá a true si se ha encontrado un número que coincida.
             */
            boolean repeEncontrado = false;
            int j = 0;
            while(j < numRepes.length && !repeEncontrado){
                if(numRepes[j] == nRandom){
                    repeEncontrado = true;
                }else{
                    j++;
                }
            }


            //Si 'repeEncontrado' está en false, quiere decir que no había salido antes 'nRandom', por lo que se puede llevar a cabo el cambio y pasamos a la siguiente posición (con 'i++') para intercambiarla por otra
            if(!repeEncontrado){
                Opcion aux = opciones[i];
                opciones[i] = opciones[nRandom];
                opciones[nRandom] = aux;

                numRepes[i] = nRandom;
                i++;
            }
            /*
                Si 'repeEncontrado' está en true, quiere decir que 'nRandom' ya salió antes. Por eso no incremento 'i', tiene que volver a repetir el proceso sin pasar a la siguiente posición
                hasta que 'nRandom' genere un número que no haya sido repetido
             */


        }
    }
}
