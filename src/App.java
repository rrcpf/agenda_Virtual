import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    
    static final int EXIT = -1;
    static final int UNLOGGED = -2;
    static final int LOGINSCREEN = 0;
    static final int COMMANDSCREEN = 1;
    static UserData ud = new UserData();
    static ScheduleData sd = new ScheduleData();
    static Scanner input = new Scanner(System.in);
    static IUsuario usuarioLogado;
    static int loggedAs = UNLOGGED;
    

    public static void main(String[] args) throws Exception {
        int loggedin = 0;
        boolean exit = false;
        while(!exit){
            switch(loggedin){
                case LOGINSCREEN: loggedin = App.autenticate(); break;
                case COMMANDSCREEN: loggedin = App.getCommand(); break;
                default: exit = true; break;
            }
        }
    }
    public static int autenticate(){
        App.printMenu(LOGINSCREEN);
        String cadastro = App.input.nextLine();

        switch (cadastro) {
            case "0":
                return EXIT;
            case "1":
                signup();
                return LOGINSCREEN;
            case "2":
                return login(); 
            default:
                return LOGINSCREEN;
        }
    }

    public static int getCommand(){
        ArrayList<IAgenda> agendas = null;
        IAgenda agenda = null;
        String nomeAgenda = "";
        App.printMenu(COMMANDSCREEN);
        String choice = App.input.nextLine();
        switch(choice){
            case "0":
                App.exitCommandScreen();
                return LOGINSCREEN;
            case "1":
                // Exibe Calendario
                showCalendar(agendas);
                break;
            case "2":
                // Adiciona Agenda
                nomeAgenda = askInput("agenda");          
                addAgenda(nomeAgenda);;
                break;
            case "3":
                //Exclui Agenda
                nomeAgenda = askInput("agenda");
                removeAgenda(nomeAgenda);
                break;
            case "4":
                //Adiciona Evento
                nomeAgenda = askInput("agenda");
                addEvent(nomeAgenda, agenda);
                break;
            case "5":
                //Remove Evento
                nomeAgenda = askInput("agenda");
                removeEvent(nomeAgenda, agenda);
                break;
            case "6":
                //Compartilha Agenda
                nomeAgenda = askInput("agenda");
                
                shareAgenda(nomeAgenda, agenda);
                break;
            case "7":
                //Exibe Agenda
                nomeAgenda = askInput("agenda");
                showAgenda(nomeAgenda, agenda);
    
                break;
        }
        return COMMANDSCREEN;
    }

    public static void printMenu (int menu){
        switch(menu){
            case LOGINSCREEN:
                System.out.println("==== Bem vindo ====");
                System.out.println("");
                System.out.println("[0]- Sair");
                System.out.println("[1]- Cadastrar");
                System.out.println("[2]- Logar");
            break;
            case COMMANDSCREEN:
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
            break;
        }
    }

    public static void signup(){
        String userReg = App.askInput("usuário");
        String senhaReg = App.askInput("senha");
        String email = App.askInput("email");
        App.ud.addUser(userReg, senhaReg, email);
    }

    public static int login(){
        String user = App.askInput("usuário");
        App.loggedAs = App.ud.getUser(user).getId();
        String senha = App.askInput("senha");
        usuarioLogado = App.ud.getUser(user);
        return Autenticador.autenticar(senha, usuarioLogado.getPassword()) ? COMMANDSCREEN : LOGINSCREEN;
    }
 
    public static void showCalendar(ArrayList<IAgenda> agendas){
        agendas = sd.listSchedules(usuarioLogado.getId());

                if (agendas.isEmpty()) {
                    System.out.print("Nenhuma agenda encontrada \n");
                }

                for (IAgenda agendaUsuario : agendas) {
                    agendaUsuario.printAgenda();
                }
    }

    public static void addAgenda(String nomeAgenda){
        sd.addSchedule(nomeAgenda, usuarioLogado);
    }

    public static void removeAgenda(String nomeAgenda){
        if (sd.removeSchedule(nomeAgenda, usuarioLogado)){
            System.out.print("Agenda removida com sucesso! \n");
        } else {
            System.out.print("Falha ao remover! \n");
        }
    }

    public static void addEvent(String nomeAgenda, IAgenda agenda){
 
        System.out.print("Digite a descrição do evento: ");
        String descricaoEvento = App.input.nextLine();
        System.out.print("Digite a data do evento (dd/MM/yyyy): ");
        String data = App.input.nextLine();

        agenda = sd.getAgenda(nomeAgenda, usuarioLogado);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);

        agenda.createEvent(localDate, descricaoEvento);
    }

    public static void removeEvent(String nomeAgenda, IAgenda agenda){
        System.out.print("Digite o id do evento: ");
        String idEvento = App.input.nextLine();

        agenda = sd.getAgenda(nomeAgenda, usuarioLogado);
        agenda.removeEvent(Integer.parseInt(idEvento));
    }

    public static void shareAgenda(String nomeAgenda, IAgenda agenda){
        String userName = App.askInput("usuário");
        agenda = sd.getAgenda(nomeAgenda, usuarioLogado);
        IUsuario usuario = ud.getUser(userName);
        agenda.share(usuario);

    }

    public static void showAgenda(String nomeAgenda, IAgenda agenda){
        agenda = sd.getAgenda(nomeAgenda, usuarioLogado);

        if (agenda == null) {
            System.out.print("Nenhuma agenda encontrada \n");
            return;
        }

        agenda.printAgenda();
    }

    public static void exitCommandScreen(){
        App.loggedAs = UNLOGGED;
    }

    public static String askInput(String input){
        System.out.println("Digite o nome do/a " + input + ":");
        return App.input.nextLine();
    }

}
