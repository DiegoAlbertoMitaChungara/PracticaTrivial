package PracticaTrivial.Users;

import java.io.Serializable;

public abstract class User implements Serializable, Comparable<User> {
    /**
     * Nombre del usuario
     */
    protected String nombre;
    /**
     * Contraseña del usuario
     */
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

    /**
     * Metodo para comprobar si la contraseña que se le pasa por parámetro es igual a la contraseña del usuario desde el que llamas a este metodo
     * @param pass Contraseña para verificar
     * @return True - si las contraseñas son iguales <br>
     *         False - si las contraseñas son distintas
     */
    public boolean compruebaPass(String pass) {
        return this.pass.equals(pass);
    }

    /**
     * Metodo para comprobar si un usuario tiene permisos de Administrador o no
     * @return True - si tiene permisos de Administrador (es Admin)<br>
     *         False - si no tiene permisos de Administrador (es Player)
     */
    public abstract boolean permisosAdmin();
}
