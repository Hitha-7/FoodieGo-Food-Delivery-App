package com.tap.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

    private Connection connection;

    public MenuDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addMenu(Menu menu) {

        String sql = """
                INSERT INTO menu
                (restaurantId,itemName,description,
                price,isAvailable,imagePath)
                VALUES(?,?,?,?,?,?)
                """;

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImagePath());

            return pstmt.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Menu getMenuById(int menuId) {

        String sql = "SELECT * FROM menu WHERE menuId=?";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return extractMenu(rs);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Menu> getMenuByRestaurantId(int restaurantId) {

        List<Menu> menus = new ArrayList<>();

        String sql =
                "SELECT * FROM menu WHERE restaurantId=?";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setInt(1, restaurantId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                menus.add(extractMenu(rs));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return menus;
    }

    @Override
    public boolean updateMenu(Menu menu) {

        String sql = """
                UPDATE menu
                SET itemName=?,
                description=?,
                price=?,
                isAvailable=?,
                imagePath=?
                WHERE menuId=?
                """;

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setString(1, menu.getItemName());
            pstmt.setString(2, menu.getDescription());
            pstmt.setDouble(3, menu.getPrice());
            pstmt.setBoolean(4, menu.isAvailable());
            pstmt.setString(5, menu.getImagePath());
            pstmt.setInt(6, menu.getMenuId());

            return pstmt.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteMenu(int menuId) {

        String sql =
                "DELETE FROM menu WHERE menuId=?";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);

            return pstmt.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private Menu extractMenu(ResultSet rs)
            throws SQLException {

        Menu menu = new Menu();

        menu.setMenuId(rs.getInt("menuId"));
        menu.setRestaurantId(rs.getInt("restaurantId"));
        menu.setItemName(rs.getString("itemName"));
        menu.setDescription(rs.getString("description"));
        menu.setPrice(rs.getDouble("price"));
        menu.setAvailable(rs.getBoolean("isAvailable"));
        menu.setImagePath(rs.getString("imagePath"));

        return menu;
    }
}