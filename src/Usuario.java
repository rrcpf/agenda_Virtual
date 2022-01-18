import java.awt.Color;
import java.util.ArrayList;

public class Usuario implements IUsuario {
    private int incrementador = 0;
    private int id;
    private String username;
    private String password;
    private String email;
    private ArrayList<IAgenda> agendas;

    public Usuario (String username, String password, String email, int id){
        this.username = username;
        this.password = password;
        this.email = email;
        this.agendas = new ArrayList<IAgenda>();
    }

    @Override
    public void exibirAgendas() {
        for (var i = 0; i < this.agendas.size(); i++){
            System.out.println(i + " - " + this.agendas.get(i).getName());
        }
    }

    @Override
    public void exibirCalendario(int indexAgenda) {
        var agenda = this.agendas.get(indexAgenda);
        if (agenda == null) {
            System.out.println("Agenda informada não existe");
            return;
        }

        agenda.imprimir();
    }

    @Override
    public Agenda criarAgenda(String name, Color color) {
        this.incrementador++;
        Agenda a = new Agenda(incrementador, color, name, this); // valores de placeholder
        return a;
    }

    @Override
    public boolean excluirAgenda(IAgenda agenda) {
        return this.agendas.remove(agenda);
    }

    @Override
    public void editarAgenda(IAgenda agenda) {
        return;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
