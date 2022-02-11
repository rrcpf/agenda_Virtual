import java.time.LocalDate;
import java.util.ArrayList;

public interface ISchedule {
    boolean removeEvent(int id);

    boolean share(IUser user);

    boolean createEvent(LocalDate date, String description);

    void printSchedule();

    String getName();

    int getID();

    void showEvents();

    ArrayList<IUser> getSharedUsers();

    IUser getCreator();
}