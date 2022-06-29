package pt.estig.twdm.pdm.keep_pocket;


import java.util.HashMap;
import java.util.Map;

public class LoginManager {

    private static Map<String, pt.estig.twdm.pdm.keep_pocket.User> users;

    private static Map<String, pt.estig.twdm.pdm.keep_pocket.User> getUsers() {
        if (users == null) {
            users = new HashMap<>();
            users.put("22541@stu.ipbeja.pt", new pt.estig.twdm.pdm.keep_pocket.User(1, "22541@stu.ipbeja.pt", "22541"));
            users.put("22606@stu.ipbeja.pt", new pt.estig.twdm.pdm.keep_pocket.User(2, "22606@stu.ipbeja.pt", "22606"));
            users.put("22690@stu.ipbeja.pt", new pt.estig.twdm.pdm.keep_pocket.User(3, "22690@stu.ipbeja.pt", "22690"));
            users.put("22623@stu.ipbeja.pt", new pt.estig.twdm.pdm.keep_pocket.User(4, "22623@stu.ipbeja.pt", "22623"));
            users.put("22644@stu.ipbeja.pt", new pt.estig.twdm.pdm.keep_pocket.User(5, "22644@stu.ipbeja.pt", "22644"));
            users.put("22652@stu.ipbeja.pt", new pt.estig.twdm.pdm.keep_pocket.User(6, "22652@stu.ipbeja.pt", "22652"));
            users.put("23104@stu.ipbeja.pt", new pt.estig.twdm.pdm.keep_pocket.User(7, "23104@stu.ipbeja.pt", "23104"));
            users.put("22586@stu.ipbeja.pt", new pt.estig.twdm.pdm.keep_pocket.User(8, "22586@stu.ipbeja.pt", "22586"));
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
