package PracticaTrivial.Users;


public class Admin extends User {

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
    @Override
    public int compareTo(User o) {
        char thisChar = this.getNombre().charAt(0);
        char oChar = o.getNombre().charAt(0);

        if(thisChar < oChar){
            return 1;
        }else if(thisChar > oChar){
            return -1;
        }else{
            return 0;
        }
    }
}
