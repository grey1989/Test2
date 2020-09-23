package caomei.dao;

import caomei.domain.User;

public interface UserDao {
    public boolean findUserName(String username);
    public void registUser(String username,String password);

    User userLogin(User user);

    User findUserById(int uid);
}
