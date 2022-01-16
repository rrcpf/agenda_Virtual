import java.time.LocalDate;

public class Evento implements IEvento {
    private int id;
    private LocalDate data;
    private String description;

    public Evento (int id, LocalDate data, String description){
        this.id = id;
        this.data = data;
        this.description = description;
    }

    @Override
    public void imprimir() {
        System.out.println(this.id + " " + this.data.toString() + " - "  + this.description + " - ");
    }
}
