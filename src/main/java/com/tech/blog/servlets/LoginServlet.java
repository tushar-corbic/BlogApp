package com.tech.blog.servlets;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import com.tech.blog.dao.UserDao;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.entity.User;
import javax.servlet.http.HttpSession;
import com.tech.blog.entity.Message;
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/html;Charset=UTF-8");
        try(PrintWriter out = res.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            UserDao userDao = new UserDao(ConnectionProvider.getConnection());

            User u = userDao.getUserByEmailAndPassword(email, password);
            if(u==null){
                Message msg = new Message("Invalid Details ! try with another", "error", "alert-danger");
                HttpSession session = req.getSession();
                session.setAttribute("msg", msg);
                res.sendRedirect("login_page.jsp");
            } else {

                HttpSession s = req.getSession();
                s.setAttribute("currentUser", u);
                res.sendRedirect("profile.jsp");

            }
            out.println("</body>");
            out.println("</html>");

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
