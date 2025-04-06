package Trivial.PracticaTrivial.Users;

public class Player extends User{

    public Player(String nombre, String pass) {
        super(nombre, pass);
    }

    @Override
    public boolean permisosAdmin(){
        return false;
    }
}
