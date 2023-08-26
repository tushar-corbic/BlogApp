package com.tech.blog.dao;
import java.sql.*;
import com.tech.blog.entity.*;
public class UserDao {
    private Connection conn;
    public UserDao(Connection conn){
        this.conn = conn;
    }

    public boolean saveUser(User user){
        boolean flag = false;
        try{
            String query = "insert into user(name, email, password, gender, about) values(?,?,?,?,?)";
            PreparedStatement smt = this.conn.prepareStatement(query);
            smt.setString(1, user.getName());
            smt.setString(2, user.getEmail());
            smt.setString(3, user.getPassword());
            smt.setString(4, user.getGender());
            smt.setString(5, user.getAbout());

            smt.executeUpdate();
            flag = true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return flag;
    }

    public User getUserByEmailAndPassword(String email, String password){
        User user = null;
        try{
            String query = "select * from user where email=? and password=?";
            PreparedStatement smt = this.conn.prepareStatement(query);
            smt.setString(1, email);
            smt.setString(2, password);

            ResultSet result = smt.executeQuery();
            if(result.next()){
                user = new User();
                user.setName(result.getString("name"));
                user.setDatetime(result.getTimeStamp("rdate"));
                user.setId(result.getInt("id"));
                user.setPassword(result.getString("password"));
                user.setGender(result.getString("gender"));
                user.setAbout(result.getString("about"));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateUser(User user){
        boolean flag = false;
        try{
            String query = "update user set name=?, email=?, password=?, gender=?, about=? where id=?";
            PreparedStatement smt = this.conn.prepareStatement(query);
            smt.setString(1, user.getName());
            smt.setString(2, user.getEmail());
            smt.setString(3, user.getPassword());
            smt.setString(4, user.getGender());
            smt.setString(5, user.getAbout());
            smt.setInt(6, user.getId());

            smt.executeUpdate();
            flag = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    public User getUserByUserId(int userId){
        User user = null;
        try{
            String query = "select * from user where id=?";
            PreparedStatement smt = this.conn.prepareStatement(query);
            smt.setInt(1, userId);
            ResultSet set = smt.executeQuery();
            if(set.next()){
                user = new User();
                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
                user.setDatetime(set.getTimestamp("rdate"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
