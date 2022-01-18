import java.time.LocalDate;
import java.util.ArrayList;

public interface IAgenda {
    boolean excluirEvento(IEvento evento);

    boolean compartilhar(IUsuario usuario);

    Evento criarEvento(LocalDate data, String description);

    void imprimir();

    String getName();

    int getID();

    void exibirEventos();
    ArrayList<IUsuario> getSharedUsers();
    Usuario getCreator();
}