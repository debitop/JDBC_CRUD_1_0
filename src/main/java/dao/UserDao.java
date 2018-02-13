package dao;

import model.User;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection = DbUtil.getConnection();


   public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getDate("dob"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

   public void addUser(User user) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO users (firstname, email, lastname, dob) VALUES (?,?,?,?)");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getLastName());
            ps.setDate(4, new Date(user.getDob().getTime()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setInt(1, userId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editUser(User user) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE users SET firstname=?,lastname=?,email=?,dob=? WHERE id=?");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setDate(4, new Date(user.getDob().getTime()));
            ps.setInt(5,user.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

   public List<User> listUser() {
        List<User>userList = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()){
                User user = new User();
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getDate("dob"));
                user.setId(rs.getInt("id"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
