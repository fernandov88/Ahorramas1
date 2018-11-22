package Modelo;

/**
 * Created by mario on 6/29/2018.
 */

public final class Usuario {
    private static String uNombre;
    private static String uCorrreo;

    private Usuario(){

    }

    public static String getuNombre() {
        return uNombre;
    }

    public static void setuNombre(String uNombre) {
        Usuario.uNombre = uNombre;
    }

    public static String getuCorrreo() {
        return uCorrreo;
    }

    public static void setuCorrreo(String uCorrreo) {
        Usuario.uCorrreo = uCorrreo;
    }
}
