package com.tap.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDAO;
import com.tap.model.User;
import com.tap.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    private Connection connection;

    public UserDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // CREATE

    @Override
    public boolean addUser(User user) {

        String sql = """
                INSERT INTO users
                (username,password,email,address,
                role,createdDate,lastLoginDate)
                VALUES(?,?,?,?,?,?,?)
                """;

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getRole());
            pstmt.setTimestamp(6,
                    Timestamp.valueOf(user.getCreatedDate()));
            pstmt.setTimestamp(7,
                    Timestamp.valueOf(user.getLastLoginDate()));

            return pstmt.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // READ BY ID

    @Override
    public User getUserById(int userId) {

        String sql =
                "SELECT * FROM users WHERE userId=?";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return extractUser(rs);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // READ BY USERNAME

    @Override
    public User getUserByUsername(String username) {

        String sql =
                "SELECT * FROM users WHERE username=?";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return extractUser(rs);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // READ ALL USERS

    @Override
    public List<User> getAllUsers() {

        List<User> users =
                new ArrayList<>();

        String sql = "SELECT * FROM users";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                users.add(extractUser(rs));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    // UPDATE

    @Override
    public boolean updateUser(User user) {

        String sql = """
                UPDATE users
                SET username=?,
                password=?,
                email=?,
                address=?,
                role=?,
                lastLoginDate=?
                WHERE userId=?
                """;

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getRole());
            pstmt.setTimestamp(6,
                    Timestamp.valueOf(user.getLastLoginDate()));
            pstmt.setInt(7, user.getUserId());

            return pstmt.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE

    @Override
    public boolean deleteUser(int userId) {

        String sql =
                "DELETE FROM users WHERE userId=?";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            return pstmt.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // HELPER METHOD

    private User extractUser(ResultSet rs)
            throws SQLException {

        User user = new User();

        user.setUserId(
                rs.getInt("userId"));

        user.setUsername(
                rs.getString("username"));

        user.setPassword(
                rs.getString("password"));

        user.setEmail(
                rs.getString("email"));

        user.setAddress(
                rs.getString("address"));

        user.setRole(
                rs.getString("role"));

        Timestamp created =
                rs.getTimestamp("createdDate");

        if(created != null) {
            user.setCreatedDate(
                    created.toLocalDateTime());
        }

        Timestamp lastLogin =
                rs.getTimestamp("lastLoginDate");

        if(lastLogin != null) {
            user.setLastLoginDate(
                    lastLogin.toLocalDateTime());
        }

        return user;
    }
}