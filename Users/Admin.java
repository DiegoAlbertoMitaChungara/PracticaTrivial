package PracticaTrivial.Users;


public class Admin extends User {

    /**
     * Contructor de un administrador
     * @param nombre Nombre del administrador
     * @param pass Contraseña del administrador
     */
    public Admin(String nombre, String pass) {
        super(nombre, pass);
    }

    @Override
    public boolean permisosAdmin(){
        return true;
    }

    @Override
    public String toString() {
        return "Nombre Administrador: "+super.nombre+" Password: "+super.pass;
    }

    /**
     * Metodo que compara dos objetos de tipo User (el objeto desde el que llamas a esta función con el objeto que pasas por parámetro)
     * @param o Objeto User con el que quieres comparar
     * @return 1 - si la primera letra de 'o' se encuentra antes en el abecedario que la primera letra del objeto desde el que llamas a esta función. <br>
     *        -1 - si la primera letra del objeto desde el que llamas a esta función se encuentra antes en el abecedario que la primera letra de 'o'. <br>
     *         0 - si la primera letra del objeto desde el que llamas a esta función es igual que la primera letra de 'o'.
     */
    @Override
    public int compareTo(User o) {
        char thisChar = this.getNombre().charAt(0);
        char oChar = o.getNombre().charAt(0);

        //Si la primera letra del nombre de este usuario es mayor que la primera letra del nombre del usuario 'o' (Es decir que la primera letra de 'o' va antes en el abecedario que la primera letra del nombre de este usuario)
        if(thisChar > oChar){
            return 1;
        }
        //Si la primera letra del nombre de este usuario es menor que la primera letra del nombre del usuario 'o' (Es decir que la primera letra de 'o' va después en el abecedario que la primera letra del nombre de este usuario)
        else if(thisChar < oChar){
            return -1;
        }
        //Si la primera letra del nombre de este usuario es igual que la primera letra del nombre del usuario 'o'
        else{
            return 0;
        }
    }
}
