import java.util.ArrayList;
import java.time.LocalDate;

public class Agenda implements IAgenda {
    private int id = 0;
    private String name;
    private IUsuario creator;
    private ArrayList<IUsuario> sharedUsers;
    private ArrayList<IEvento> eventos;

    public Agenda(int id, String name, IUsuario creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.sharedUsers = new ArrayList<IUsuario>();
        this.eventos = new ArrayList<IEvento>();
    }

    @Override
    public boolean removeEvent(int id) {
        return this.eventos.removeIf(e -> e.getID() == id);
    }

    @Override
    public boolean share(IUsuario usuario) {
        return this.sharedUsers.add(usuario);
    }

    @Override
    public boolean createEvent(LocalDate data, String description) {
        IEvento event = new Evento(eventos.size(), data, description);

        return this.eventos.add(event);
    }

    @Override
    public void printAgenda() {
        System.out.println("Agenda: " + this.name);
        this.showEvents();
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
    public void showEvents() {
        for (IEvento evento : this.eventos) {
            evento.printEvent();
        }
    }

    @Override
    public ArrayList<IUsuario>  getSharedUsers() {
        return this.sharedUsers;
    }

    @Override
    public IUsuario getCreator() {
        return this.creator;
    }
}
