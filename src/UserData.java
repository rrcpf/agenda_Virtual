import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class UserData {
    private ArrayList<IUsuario> usuarios;

    public UserData() {
        var usuario = new Usuario("teste", "1", "teste@email.com", 0);
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

    public IUsuario getUser(String username){
        Iterator<IUsuario> it = this.usuarios.iterator();
        while(it.hasNext()) {
            IUsuario i = it.next();
            if(i.getUsername() == username) {
                return i;
            }
        }
        return null;
    }

    public IUsuario getUser(int id){
        Iterator<IUsuario> it = this.usuarios.iterator();
        while(it.hasNext()) {
            IUsuario i = it.next();
            if(i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public void add_user(String username, String password, String email){
        var usuario = new Usuario(username, password, email, this.usuarios.size());
        this.usuarios.add(usuario);
    }

    public void remove_user(int id){
        /*Iterator<IUsuario> it = this.usuarios.iterator();
        while(it.hasNext()) {
        IUsuario i = it.next();
        if(i.username == username) {
            it.remove();
            break;
            }
        }*/
        this.usuarios.removeIf(u -> u.getId() == id);
    }
}
