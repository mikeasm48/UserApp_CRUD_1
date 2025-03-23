package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User getUser(int id);
    public void createUser(User user);
    public void updateUser(int id, User user);
    public void deleteUser(int id);
}
