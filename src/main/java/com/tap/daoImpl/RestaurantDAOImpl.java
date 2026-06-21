package com.tap.daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    private Connection connection;

    public RestaurantDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // CREATE

    @Override
    public boolean addRestaurant(Restaurant restaurant) {

        String sql = """
                INSERT INTO restaurant
                (name,cuisineType,deliveryTime,address,
                rating,isActive,imagePath)
                VALUES(?,?,?,?,?,?,?)
                """;

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setDouble(5, restaurant.getRating());
            pstmt.setBoolean(6, restaurant.isActive());
            pstmt.setString(7, restaurant.getImagePath());

            return pstmt.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // READ BY ID

    @Override
    public Restaurant getRestaurantById(int restaurantId) {

        String sql =
                "SELECT * FROM restaurant WHERE restaurantId=?";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setInt(1, restaurantId);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return extractRestaurant(rs);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // READ ALL

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> restaurants =
                new ArrayList<>();

        String sql = "SELECT * FROM restaurant";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                restaurants.add(
                        extractRestaurant(rs));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    // UPDATE

    @Override
    public boolean updateRestaurant(
            Restaurant restaurant) {

        String sql = """
                UPDATE restaurant
                SET name=?,
                cuisineType=?,
                deliveryTime=?,
                address=?,
                rating=?,
                isActive=?,
                imagePath=?
                WHERE restaurantId=?
                """;

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setDouble(5, restaurant.getRating());
            pstmt.setBoolean(6, restaurant.isActive());
            pstmt.setString(7, restaurant.getImagePath());
            pstmt.setInt(8,
                    restaurant.getRestaurantId());

            return pstmt.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE

    @Override
    public boolean deleteRestaurant(
            int restaurantId) {

        String sql =
                "DELETE FROM restaurant WHERE restaurantId=?";

        try(PreparedStatement pstmt =
                connection.prepareStatement(sql)) {

            pstmt.setInt(1, restaurantId);

            return pstmt.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // HELPER METHOD

    private Restaurant extractRestaurant(
            ResultSet rs) throws SQLException {

        Restaurant restaurant =
                new Restaurant();

        restaurant.setRestaurantId(
                rs.getInt("restaurantId"));

        restaurant.setName(
                rs.getString("name"));

        restaurant.setCuisineType(
                rs.getString("cuisineType"));

        restaurant.setDeliveryTime(
                rs.getInt("deliveryTime"));

        restaurant.setAddress(
                rs.getString("address"));

        restaurant.setRating(
                rs.getDouble("rating"));

        restaurant.setActive(
                rs.getBoolean("isActive"));

        restaurant.setImagePath(
                rs.getString("imagePath"));

        return restaurant;
    }
}