import java.awt.*;
import java.util.ArrayList;

public class Autenticador {
    private ArrayList<IUsuario> usuarios;

    public Autenticador() {
        var usuario = new Usuario("teste", "1", "teste@email.com");
        this.usuarios.add(usuario);
    }

    public IUsuario autenticar(String username, String password) {
        var usuario = this.usuarios.stream().filter(f -> f.getUsername().equals(username)).findFirst();
        if (usuario == null) {
            return null;
        }

        if (!usuario.get().getPassword().equals(password)) {
            return null;
        }

        return usuario.get();
    }
}
