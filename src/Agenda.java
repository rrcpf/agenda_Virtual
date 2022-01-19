import java.util.ArrayList;
import java.time.LocalDate;

public class Agenda implements IAgenda {
    private int id = 0;
    private String name;
    private IUsuario creator;
    private ArrayList<IUsuario> shared_Users;
    private ArrayList<IEvento> eventos;

    public Agenda(int id, String name, IUsuario creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.shared_Users = new ArrayList<IUsuario>();
        this.eventos = new ArrayList<IEvento>();
    }

    @Override
    public boolean excluirEvento(int id) {
        return this.eventos.removeIf(e -> e.getID() == id);
    }

    @Override
    public boolean compartilhar(IUsuario usuario) {
        return this.shared_Users.add(usuario);
    }

    @Override
    public boolean criarEvento(LocalDate data, String description) {
        IEvento event = new Evento(eventos.size() + 1, data, description); // valores de placeholder

        return this.eventos.add(event);
    }

    @Override
    public void imprimir() {
        System.out.println("Agenda: " + this.name);
        this.exibirEventos();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void exibirEventos() {
        for (IEvento evento : this.eventos) {
            evento.imprimir();
        }
    }

    @Override
    public ArrayList<IUsuario>  getSharedUsers() {
        return this.shared_Users;
    }

    @Override
    public IUsuario getCreator() {
        return this.creator;
    }
}
