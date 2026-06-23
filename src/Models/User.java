package Models;

public abstract class User {
    protected int user_id;
    protected String name;
    protected String email;
    protected long phone_no;
    protected String password;
    protected Role role;

    public User(int userId,
                String name,
                String email,
                long phoneNo,
                String password,
                Role role) {

        this.user_id = userId;
        this.name = name;
        this.email = email;
        this.phone_no = phoneNo;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}