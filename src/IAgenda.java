import java.time.LocalDate;
import java.util.ArrayList;

public interface IAgenda {
    boolean excluirEvento(int id);

    boolean compartilhar(IUsuario usuario);

    boolean criarEvento(LocalDate data, String description);

    void imprimir();

    String getName();

    int getID();

    void exibirEventos();

    ArrayList<IUsuario> getSharedUsers();

    IUsuario getCreator();
}