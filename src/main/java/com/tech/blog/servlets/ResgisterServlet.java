package com.tech.blog.servlets;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import com.tech.blog.entity.*;
import com.tech.blog.dao.UserDao;
import com.tech.blog.helper.ConnectionProvider;
import javax.servlet.annotation.MultipartConfig;
@MultiConfig
public class ResgisterServlet  extends HttpServlet{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            String check = request.getParameter("check");
            if(check==null) {
                out.println("box not checked");
            }else{
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String gender = request.getParameter("gender");
                String about = request.getParameter("about");
                User user = new User(name, email, password, gender, about);

                UserDao dao = new UserDao(ConnectionProvider.getConnection());
                if(dao.saveUser(user)){
                    out.println("successfully saved");
                }else{
                    out.println("error");
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException{
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
