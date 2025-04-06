package Trivial.PracticaTrivial.Users;

public class Admin extends User {

    public Admin(String nombre, String pass) {
        super(nombre, pass);
    }

    @Override
    public boolean permisosAdmin(){
        return true;
    }
}
