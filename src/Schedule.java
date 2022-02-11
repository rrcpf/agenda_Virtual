import java.util.ArrayList;
import java.time.LocalDate;

public class Schedule implements ISchedule {
    private int id = 0;
    private String name;
    private IUser creator;
    private ArrayList<IUser> sharedUsers;
    private ArrayList<IEvent> events;

    public Schedule(int id, String name, IUser creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.sharedUsers = new ArrayList<IUser>();
        this.events = new ArrayList<IEvent>();
    }

    @Override
    public boolean removeEvent(int id) {
        return this.events.removeIf(e -> e.getID() == id);
    }

    @Override
    public boolean share(IUser user) {
        return this.sharedUsers.add(user);
    }

    @Override
    public boolean createEvent(LocalDate date, String description) {
        IEvent event = new Event(events.size(), date, description);

        return this.events.add(event);
    }

    @Override
    public void printSchedule() {
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
        for (IEvent event : this.events) {
            event.printEvent();
        }
    }

    @Override
    public ArrayList<IUser>  getSharedUsers() {
        return this.sharedUsers;
    }

    @Override
    public IUser getCreator() {
        return this.creator;
    }
}
