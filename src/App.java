import java.util.Scanner;

public class App {
    
    static UserData ud = new UserData();
    static ScheduleData sd = new ScheduleData();
    static Scanner input = new Scanner(System.in);
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
        int cadastro = 3;
        switch(cadastro){
            case 0:
                return -1;
            case 1:
                System.out.print("Usuário: ");
                String userReg = App.input.nextLine();
                System.out.print("Senha: ");
                String senhaReg = App.input.nextLine();
                System.out.print("Email: ");
                String email = App.input.nextLine();
                App.ud.add_user(userReg, senhaReg, email);
            return 1;
            case 2:
                System.out.print("Usuário: ");
                String user = App.input.nextLine();
                App.loggedAs = App.ud.getUser(user).getId();
                System.out.print("Senha: ");
                String senha = App.input.nextLine();
            return Autenticador.autenticar(senha, App.ud.getUser(user).getPassword())? 1 : 0;
            default:
                return 0;
        }
    }

    public static int getcommand(){
        IAgenda schedule = null;
        System.out.println("==== Bem vindo ====");
        System.out.println("");
        System.out.println("[1]- Exibir Agenda");
        System.out.println("[2]- Adicionar Agenda");
        System.out.println("[3]- Excluir Agenda");
        System.out.println("[4]- Adicionar Evento");
        System.out.println("[5]- Remover Evento");
        System.out.println("[6]- Compartilhar Agenda");
        System.out.println("[7]- Excluir Conta");
        System.out.println("[0]- Sair");
        String choice = App.input.nextLine();
        switch(choice){
            case "0":
                App.loggedAs = -1;
                return 0;
            case "1":
                schedule = selectSchedule();
                schedule.exibirEventos();
                break;
            case "2":
                // Adiciona Agenda
                break;
            case "3":
                schedule = selectSchedule();
                //Exclui Agenda
                break;
            case "4":
                schedule = selectSchedule();
                //Adiciona Evento
                break;
            case "5":
                schedule = selectSchedule();
                //Remove Evento
                break;
            case "6":
                schedule = selectSchedule();
                //Compartilha Agenda
                break;
            case "7":
                // Exclui conta
                return 0;
        }
        return 1;
    }
}
