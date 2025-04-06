package PracticaTrivial.Users;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected String nombre;
    protected String pass;

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }


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
