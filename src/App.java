import java.util.Scanner;

public class App {
    
    static UserData ud = new UserData();
    static Scanner input = new Scanner(System.in);
    Autenticador auth = new Autenticador();
    public static void main(String[] args) throws Exception {
        int loggedin = 0;
        boolean exit = false;
        while(!exit){
            switch(loggedin){
                case 0: loggedin = App.autenticar() ? 1 : 0; break;
                case 1: App.getcommand(); break;
                default: exit = true; break;
            }
        }
    }
    public static boolean autenticar(){
        System.out.println("Deseja se Cadastrar? [s/N]");
        String choice = App.input.nextLine();
        switch(choice){
            case "s":
                System.out.print("Usuário: ");
                String userReg = App.input.nextLine();
                System.out.print("Senha: ");
                String senhaReg = App.input.nextLine();
                System.out.print("Email: ");
                String email = App.input.nextLine();
                App.ud.add_user(userReg, senhaReg, email);
            return true;
            default:
                System.out.print("Usuário: ");
                String user = App.input.nextLine();
                System.out.print("Senha: ");
                String senha = App.input.nextLine();
            return Autenticador.autenticar(senha, App.ud.getUser(user).getPassword());
        }
        
        
    }
    public static void getcommand(){
        System.out.println("==== Bem vindo ====");
        System.out.println("");
        System.out.println("[1]- Exibir Agenda");
        System.out.println("[2]- Adicionar Agenda");
        System.out.println("[3]- Excluir Agenda");
        System.out.println("[4]- Adicionar Evento");
        System.out.println("[5]- Remover Evento");
        System.out.println("[6]- Compartilhar Agenda");
        System.out.println("[7]- Excluir Conta");
        System.out.println("[1]- Exibir Agenda");
    }
}
