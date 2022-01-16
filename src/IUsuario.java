import java.awt.*;

public interface IUsuario {
    void exibirAgendas();

    void exibirCalendario(int indexAgenda);

    Agenda criarAgenda(String name, Color color);

    boolean excluirAgenda(IAgenda agenda);

    void editarAgenda(IAgenda agenda);

    String getUsername();

    String getPassword();
}
