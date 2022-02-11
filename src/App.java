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
    static IUser loggedUser;
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
        String register = App.input.nextLine();

        switch (register) {
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
        ArrayList<ISchedule> agendas = null;
        ISchedule schedule = null;
        String scheduleName = "";
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
                scheduleName = askInput("agenda");
                addSchedule(scheduleName);;
                break;
            case "3":
                //Exclui Agenda
                scheduleName = askInput("agenda");
                removeSchedule(scheduleName);
                break;
            case "4":
                //Adiciona Evento
                scheduleName = askInput("agenda");
                addEvent(scheduleName, schedule);
                break;
            case "5":
                //Remove Evento
                scheduleName = askInput("agenda");
                removeEvent(scheduleName, schedule);
                break;
            case "6":
                //Compartilha Agenda
                scheduleName = askInput("agenda");
                
                shareAgenda(scheduleName, schedule);
                break;
            case "7":
                //Exibe Agenda
                scheduleName = askInput("agenda");
                showAgenda(scheduleName, schedule);
    
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
        String passwordReg = App.askInput("senha");
        String email = App.askInput("email");
        App.ud.addUser(userReg, passwordReg, email);
    }

    public static int login(){
        String user = App.askInput("usuário");
        loggedUser = App.ud.getUser(user);

        if (loggedUser == null) {
            System.out.print("Usuário não encontrado \n");
            return 0;
        }

        App.loggedAs = loggedUser.getId();
        String password = App.askInput("senha");
        return Auth.singIn(password, loggedUser.getPassword()) ? COMMANDSCREEN : LOGINSCREEN;
    }
 
    public static void showCalendar(ArrayList<ISchedule> Schedules){
        Schedules = sd.listSchedules(loggedUser.getId());

                if (Schedules.isEmpty()) {
                    System.out.print("Nenhuma agenda encontrada \n");
                }

                for (ISchedule scheduleUser : Schedules) {
                    scheduleUser.printSchedule();
                }
    }

    public static void addSchedule(String scheduleName){
        sd.addSchedule(scheduleName, loggedUser);
    }

    public static void removeSchedule(String agendaName){
        if (sd.removeSchedule(agendaName, loggedUser)){
            System.out.print("Agenda removida com sucesso! \n");
        } else {
            System.out.print("Falha ao remover! \n");
        }
    }

    public static void addEvent(String scheduleName, ISchedule schedule){
 
        System.out.print("Digite a descrição do evento: ");
        String eventDescription = App.input.nextLine();
        System.out.print("Digite a data do evento (dd/MM/yyyy): ");
        String date = App.input.nextLine();

        schedule = sd.getSchedule(scheduleName, loggedUser);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        schedule.createEvent(localDate, eventDescription);
    }

    public static void removeEvent(String scheduleName, ISchedule schedule){
        System.out.print("Digite o id do evento: ");
        String idEvento = App.input.nextLine();

        schedule = sd.getSchedule(scheduleName, loggedUser);
        schedule.removeEvent(Integer.parseInt(idEvento));
    }

    public static void shareAgenda(String scheduleName, ISchedule schedule){
        String userName = App.askInput("usuário");
        schedule = sd.getSchedule(scheduleName, loggedUser);
        IUser user = ud.getUser(userName);
        schedule.share(user);

    }

    public static void showAgenda(String scheduleName, ISchedule schedule){
        schedule = sd.getSchedule(scheduleName, loggedUser);

        if (schedule == null) {
            System.out.print("Nenhuma agenda encontrada \n");
            return;
        }

        schedule.printSchedule();
    }

    public static void exitCommandScreen(){
        App.loggedAs = UNLOGGED;
    }

    public static String askInput(String input){
        System.out.println("Digite o nome do/a " + input + ":");
        return App.input.nextLine();
    }

}
