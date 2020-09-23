package caomei.service;

import caomei.domain.User;

public interface UserService {
    public boolean findUsername(String username);

    void registUser(String username, String password);

    User userLogin(User user);

    User findUserById(int parseInt);
}
