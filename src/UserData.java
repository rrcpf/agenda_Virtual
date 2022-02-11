import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class UserData {
    private ArrayList<IUsuario> usuarios;

    public UserData() {
        usuarios = new  ArrayList<IUsuario>();
        initialize();
    }

    private void initialize(){
        IUsuario usuario = new Usuario("teste", "1", "teste@email.com", 0);
        this.usuarios.add(usuario);
    }

    public IUsuario autenticate(String username, String password) {
        Optional<IUsuario> usuario = this.usuarios.stream().filter(f -> f.getUsername().equals(username)).findFirst();
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
            if(i.getUsername().equals(username)) {
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

    public void addUser(String username, String password, String email){
        IUsuario usuario = new Usuario(username, password, email, this.usuarios.size());
        this.usuarios.add(usuario);
    }

    public void removeUser(int id){

        this.usuarios.removeIf(u -> u.getId() == id);
    }
}
