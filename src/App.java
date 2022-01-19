import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    
    static UserData ud = new UserData();
    static ScheduleData sd = new ScheduleData();
    static Scanner input = new Scanner(System.in);
    static IUsuario usuarioLogado;
    static int loggedAs = -1;
    Autenticador auth = new Autenticador();

    public static void main(String[] args) throws Exception {
        int loggedin = 0;
        boolean exit = false;
        while(!exit){
            switch(loggedin){
                case 0: loggedin = App.autenticar(); break;
                case 1: loggedin = App.getcommand(); break;
                default: exit = true; break;
            }
        }
    }
    public static int autenticar(){
        System.out.println("==== Bem vindo ====");
        System.out.println("");
        System.out.println("[0]- Sair");
        System.out.println("[1]- Cadastrar");
        System.out.println("[2]- Logar");
        String cadastro = App.input.nextLine();

        switch (cadastro) {
            case "0":
                return -1;
            case "1":
                System.out.print("Usuário: ");
                String userReg = App.input.nextLine();
                System.out.print("Senha: ");
                String senhaReg = App.input.nextLine();
                System.out.print("Email: ");
                String email = App.input.nextLine();
                App.ud.addUser(userReg, senhaReg, email);
                return 0;
            case "2":
                System.out.print("Usuário: ");
                String user = App.input.nextLine();
                App.loggedAs = App.ud.getUser(user).getId();
                System.out.print("Senha: ");
                String senha = App.input.nextLine();
                usuarioLogado = App.ud.getUser(user);
                return Autenticador.autenticar(senha, usuarioLogado.getPassword()) ? 1 : 0;
            default:
                return 0;
        }
    }

    public static int getcommand(){
        ArrayList<IAgenda> agendas = null;
        IAgenda agenda = null;
        String nomeAgenda = "";
        System.out.println("\n==== Bem vindo ====");
        System.out.println("");
        System.out.println("[1]- Exibir Calendário");
        System.out.println("[2]- Adicionar Agenda");
        System.out.println("[3]- Excluir Agenda");
        System.out.println("[4]- Adicionar Evento");
        System.out.println("[5]- Remover Evento");
        System.out.println("[6]- Compartilhar Agenda");
        System.out.println("[7]- Exibir Agenda");
        System.out.println("[0]- Sair");
        String choice = App.input.nextLine();
        switch(choice){
            case "0":
                App.loggedAs = -1;
                return 0;
            case "1":
                agendas = sd.listSchedules(usuarioLogado.getId());

                if (agendas.isEmpty()) {
                    System.out.print("Nenhuma agenda encontrada \n");
                    return 1;
                }

                for (IAgenda agendaUsuario : agendas) {
                    agendaUsuario.imprimir();
                }
                break;
            case "2":
                // Adiciona Agenda
                System.out.print("Digite o nome da agenda: ");
                nomeAgenda = App.input.nextLine();
                sd.addSchedule(nomeAgenda, usuarioLogado);
                break;
            case "3":
                //Exclui Agenda
                System.out.print("Digite o nome da agenda: ");
                nomeAgenda = App.input.nextLine();

                if (sd.removeSchedule(nomeAgenda, usuarioLogado)){
                    System.out.print("Agenda removida com sucesso! \n");
                } else {
                    System.out.print("Falha ao remover! \n");
                }
                break;
            case "4":
                //Adiciona Evento
                System.out.print("Digite o nome da agenda: ");
                nomeAgenda = App.input.nextLine();
                System.out.print("Digite a descrição do evento: ");
                String descricaoEvento = App.input.nextLine();
                System.out.print("Digite a data do evento (dd/MM/yyyy): ");
                String data = App.input.nextLine();

                agenda = sd.getAgenda(nomeAgenda, usuarioLogado);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(data, formatter);

                agenda.criarEvento(localDate, descricaoEvento);
                break;
            case "5":
                //Remove Evento
                System.out.print("Digite o nome da agenda: ");
                nomeAgenda = App.input.nextLine();
                System.out.print("Digite o id do evento: ");
                String idEvento = App.input.nextLine();

                agenda = sd.getAgenda(nomeAgenda, usuarioLogado);
                agenda.excluirEvento(Integer.parseInt(idEvento));
                break;
            case "6":
                //Compartilha Agenda
                System.out.print("Digite o nome da agenda: ");
                nomeAgenda = App.input.nextLine();
                System.out.print("Digite o nome do usuário: ");
                String userName = App.input.nextLine();

                agenda = sd.getAgenda(nomeAgenda, usuarioLogado);
                IUsuario usuario = ud.getUser(userName);
                agenda.compartilhar(usuario);

                break;
            case "7":
                System.out.print("Digite o nome da agenda: ");
                nomeAgenda = App.input.nextLine();

                agenda = sd.getAgenda(nomeAgenda, usuarioLogado);

                if (agenda == null) {
                    System.out.print("Nenhuma agenda encontrada \n");
                    return 1;
                }

                agenda.imprimir();
                break;
        }
        return 1;
    }
}
