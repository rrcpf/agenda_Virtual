import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class UserData {
    private ArrayList<IUser> usuarios;

    public UserData() {
        usuarios = new  ArrayList<IUser>();
        initialize();
    }

    private void initialize(){
        IUser usuario = new User("teste", "1", "teste@email.com", 0);
        this.usuarios.add(usuario);
    }

    public IUser autenticate(String username, String password) {
        Optional<IUser> usuario = this.usuarios.stream().filter(f -> f.getUsername().equals(username)).findFirst();
        if (usuario == null) {
            return null;
        }

        if (!usuario.get().getPassword().equals(password)) {
            return null;
        }

        return usuario.get();
    }

    public IUser getUser(String username){
        Iterator<IUser> it = this.usuarios.iterator();
        while(it.hasNext()) {
            IUser i = it.next();
            if(i.getUsername().equals(username)) {
                return i;
            }
        }
        return null;
    }

    public IUser getUser(int id){
        Iterator<IUser> it = this.usuarios.iterator();
        while(it.hasNext()) {
            IUser i = it.next();
            if(i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public void addUser(String username, String password, String email){
        IUser usuario = new User(username, password, email, this.usuarios.size());
        this.usuarios.add(usuario);
    }

    public void removeUser(int id){

        this.usuarios.removeIf(u -> u.getId() == id);
    }
}
