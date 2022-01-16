import java.time.LocalDate;

public interface IAgenda {
    boolean excluirEvento(IEvento evento);

    boolean compartilhar(IUsuario usuario);

    Evento criarEvento(LocalDate data, String description);

    void imprimir();

    String getName();
}