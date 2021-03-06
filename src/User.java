public class User implements IUser {
    private int id;
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email, int id){
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
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
