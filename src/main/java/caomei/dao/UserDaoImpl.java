package caomei.dao;

import caomei.domain.User;
import caomei.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao{
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public boolean findUserName(String username) {
        User user=null;
        try {
            String sql="select * from user where username=? ";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if(user==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void registUser(String username, String password) {
        String sql="insert into user(username,password) values(?,?)";
        template.update(sql,username,password);
    }

    @Override
    public User userLogin(User user) {
        User loginUser=null;
        try {
            String sql="select * from user where username=? and password=?";
            loginUser = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return loginUser;
    }

    @Override
    public User findUserById(int uid) {
        User user=null;
        try {
            String sql="select * from user where uid=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }


}
