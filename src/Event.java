import java.time.LocalDate;

public class Event implements IEvent {
    private int id;
    private LocalDate date;
    private String description;

    public Event(int id, LocalDate date, String description){
        this.id = id;
        this.date = date;
        this.description = description;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void printEvent() {
        System.out.println(this.id + " - " + this.date.toString() + " - "  + this.description + "\n");
    }
}
