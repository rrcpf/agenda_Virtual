import java.time.LocalDate;
import java.util.ArrayList;

public interface IAgenda {
    boolean removeEvent(int id);

    boolean share(IUsuario usuario);

    boolean createEvent(LocalDate data, String description);

    void printAgenda();

    String getName();

    int getID();

    void showEvents();

    ArrayList<IUsuario> getSharedUsers();

    IUsuario getCreator();
}