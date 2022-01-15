import java.awt.Color;
import java.util.ArrayList;
import java.time.LocalDate;

public class Agenda {
    private int id;
    private Color color;
    private String name;
    private Usuario creator;
    private ArrayList <Usuario> shared_Users;
    private ArrayList <Evento> eventos;


    public Agenda (int id, Color color, String name, Usuario creator){
        this.id = id;
        this.color = color;
        this.name = name;
        this.creator = creator;
        this.shared_Users = new ArrayList<Usuario>();
        this.eventos = new ArrayList<Evento>();
    }

    public boolean excluirEvento(Evento e){
        boolean bool = true;
        return bool;
    }

    public boolean compartilharUsuário(Usuario u){
        boolean bool = true;
        return bool;
    }

    public ArrayList <Evento> exibirEventos(){
        return this.eventos;
    }

    public Evento criarEvento(){
        int id = 1;
        LocalDate data = LocalDate.now();  
        String description = "teste";
        Evento e = new Evento(id,data,description); // valores de placeholder
        eventos.add(e);
        return e;
    }

}
