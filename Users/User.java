package PracticaTrivial.Users;

public abstract class User {
    protected String nombre;
    protected String pass;

    public User(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }

    public boolean cambiarPass(String pass) {
        if(pass.length() >= 8){
            this.pass = pass;
            return true;
        }else return false;
    }
    public boolean compuebaPass(String pass) {
        return this.pass.equals(pass);
    }

    public abstract boolean permisosAdmin();
}
