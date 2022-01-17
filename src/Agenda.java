import java.awt.Color;
import java.util.ArrayList;
import java.time.LocalDate;

public class Agenda implements IAgenda {
    private int incrementador = 0;
    private int id = 0;
    private Color color;
    private String name;
    private Usuario creator;
    private ArrayList<IUsuario> shared_Users;
    private ArrayList<IEvento> eventos;

    public Agenda(int id, Color color, String name, Usuario creator) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.creator = creator;
        this.shared_Users = new ArrayList<IUsuario>();
        this.eventos = new ArrayList<IEvento>();
    }

    @Override
    public boolean excluirEvento(IEvento evento) {
        return this.eventos.remove(evento);
    }

    @Override
    public boolean compartilhar(IUsuario usuario) {
        return this.shared_Users.add(usuario);
    }

    @Override
    public Evento criarEvento( LocalDate data, String description) {
        this.incrementador++;
        var event = new Evento(incrementador, data, description); // valores de placeholder
        this.eventos.add(event);

        return event;
    }

    @Override
    public void imprimir() {
        System.out.println("Agenda: " + this.name + " ---- " + this.color.toString());
        this.exibirEventos();
        System.out.println("\n");
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void exibirEventos() {
        for (var evento : this.eventos) {
            evento.imprimir();
        }
    }
}
