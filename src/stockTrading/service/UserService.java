package stockTrading.service;

import stockTrading.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    List<User> users;
    public UserService() {
        this.users = new ArrayList<User>();
    }

    public void addUser(User user) {
        this.users.add(user);
        System.out.println("Added user: " + user.getUserName());
    }
    public void removeUser(User user) {
        this.users.remove(user);
    }
    public User getUser(String id) {
        return this.users.stream().filter(user -> user.getUserID() != null && user.getUserID().equals(id)).findFirst().orElse(null);
    }
}
