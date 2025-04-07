package PracticaTrivial.Users;

public class Player extends User{

    public Player(String nombre, String pass) {
        super(nombre, pass);
    }

    @Override
    public boolean permisosAdmin(){
        return false;
    }

    @Override
    public String toString() {
        return "Nombre jugador: "+super.nombre+" Password: "+super.pass;
    }

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
