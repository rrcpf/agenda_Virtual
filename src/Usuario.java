import java.awt.Color;
import java.util.ArrayList;

public class Usuario {
    private String username;
    private String password;
    private String email;
    private ArrayList <Agenda> agendas;

    public Usuario (String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        this.agendas = new ArrayList<Agenda>();
    }

    public void exibirCalendario() {
        
    }

    public Agenda criarAgenda(){
        int id = 1;
        String name = "Minha Agenda";
        Color color = Color.BLACK;
        Agenda a = new Agenda(id, color, name, this); // valores de placeholder
        return a;
    }

    public boolean excluirAgenda(Agenda a) {
        boolean bool = true;
        return bool;
    }

    public void editarAgenda(Agenda a) {

    }
}
