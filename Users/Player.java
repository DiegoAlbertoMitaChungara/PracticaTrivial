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
}
