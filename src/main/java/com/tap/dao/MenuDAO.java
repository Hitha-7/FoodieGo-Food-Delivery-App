package com.tap.dao;

import java.util.List;
import com.tap.model.Menu;

public interface MenuDAO {

    boolean addMenu(Menu menu);

    Menu getMenuById(int id);

    List<Menu> getMenuByRestaurantId(int restaurantId);

    boolean updateMenu(Menu menu);

    boolean deleteMenu(int id);
}