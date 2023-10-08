class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String say() {
        return "User: " + this.name;
    }
}

class LogUser {
    private User user;
    private String id;
    private String group;

    public LogUser(User user, String id, String group) {
        this.user = user;
        this.id = id;
        this.group = group;
    }

    public void say() {
        String userMessage = this.user.say();
        System.out.println( userMessage + ", " + this.id + ", " + this.group);
    }
}

public class Main {
    public static void main(String[] args) {
        User user = new User("Aikyn");
        System.out.println(user.say());

        LogUser decorated = new LogUser(user, "221108", "SE-2213");
        decorated.say();
    }
}