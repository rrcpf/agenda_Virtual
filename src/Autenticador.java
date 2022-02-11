public class Autenticador {

    public static boolean autenticar(String passwordTyped, String password) {
        return passwordTyped.equals(password);
    }
}
