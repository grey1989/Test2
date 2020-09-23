package caomei.servlet;

import caomei.domain.User;
import caomei.service.UserService;
import caomei.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service=new UserServiceImpl();
    public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean isUsername = service.findUsername(user.getUsername());
        String error;
        if(isUsername==false){
            service.registUser(user.getUsername(),user.getPassword());
            error="注册成功!";
        }else {
            error="你好，"+user.getUsername()+"已经存在!";
        }
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(error);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map); 
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User loginUser=service.userLogin(user);
        String error;
        if(loginUser==null){
            error="登陆失败，用户名或密码错误!";
        }else{
            error="你好,"+loginUser.getUsername()+"欢迎登录!";
        }
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(error);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
    public void findUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getQueryString();
        User user=service.findUserById(Integer.parseInt(uid));
        String error;
        ObjectMapper mapper=new ObjectMapper();
        String json;
        if(user!=null){
            json = mapper.writeValueAsString(user);
        }else{
            error="输入的id错误";
            json = mapper.writeValueAsString(error);
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
