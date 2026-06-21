package com.tap.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, CartItem> items =
            new HashMap<>();

    public void addItem(CartItem item) {

        int menuId = item.getMenuId();

        if(items.containsKey(menuId)) {

            CartItem existing =
                    items.get(menuId);

            existing.setQuantity(
                    existing.getQuantity() + 1);

        }
        else {
            items.put(menuId, item);
        }
    }

    public void removeItem(int menuId) {
        items.remove(menuId);
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public double getTotalAmount() {

        double total = 0;

        for(CartItem item : items.values()) {

            total += item.getTotalPrice();
        }

        return total;
    }
}