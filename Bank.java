import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, User> users;

    public Bank() {
        users = new HashMap<>();
    }

    public boolean createUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password));
        return true;
    }

    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    public User getUser(String username) {
        return users.get(username);
    }
}
