package pt.estig.twdm.pdm.keep_pocket;


import java.util.HashMap;
import java.util.Map;

public class LoginManager {

    private static Map<String, pt.estig.twdm.pdm.keep_pocket.User> users;

    private static Map<String, pt.estig.twdm.pdm.keep_pocket.User> getUsers() {
        if (users == null) {
            users = new HashMap<>();
            users.put("user1@teste.pt", new pt.estig.twdm.pdm.keep_pocket.User(1, "user1@teste.pt", "password1"));
            users.put("user2@teste.pt", new pt.estig.twdm.pdm.keep_pocket.User(2, "user2@teste.pt", "password2"));
            users.put("user3@teste.pt", new pt.estig.twdm.pdm.keep_pocket.User(3, "user3@teste.pt", "password3"));
        }
        return users;
    }

    public static pt.estig.twdm.pdm.keep_pocket.User validateUser(String username, String password) {
        pt.estig.twdm.pdm.keep_pocket.User user = getUsers().get(username);
        if (user == null) return null;
        return user.getPassword().equals(password) ? user : null;
//        if (user.getPassword().equals(password)) {
//            return user;
//        } else {
//            return null;
//        }
    }
}
