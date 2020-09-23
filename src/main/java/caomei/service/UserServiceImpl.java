package caomei.service;

import caomei.dao.UserDao;
import caomei.dao.UserDaoImpl;
import caomei.domain.User;

public class UserServiceImpl implements UserService{
    private UserDao dao=new UserDaoImpl();
    @Override
    public boolean findUsername(String username) {
        boolean isUsername = dao.findUserName(username);
        return isUsername;
    }

    @Override
    public void registUser(String username, String password) {
        dao.registUser(username,password);
    }

    @Override
    public User userLogin(User user) {
        User loginUser=dao.userLogin(user);
        return loginUser;
    }

    @Override
    public User findUserById(int parseInt) {

        return dao.findUserById(parseInt);
    }
}
