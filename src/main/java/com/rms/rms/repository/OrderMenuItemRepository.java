package com.rms.rms.repository;

import com.rms.rms.entity.Ingredient;
import com.rms.rms.entity.OrderMenuItem;
import com.rms.rms.entity.embedded.OrderMenuItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderMenuItemRepository extends JpaRepository<OrderMenuItem, OrderMenuItemId> {

    @Query(value = "SELECT orders_menu_items.menu_item_id\n" +
                        "FROM orders_menu_items\n" +
                        "LEFT JOIN orders ON orders.id = orders_menu_items.order_id\n" +
                        "LEFT JOIN menu_item ON menu_item.id = orders_menu_items.menu_item_id\n" +
                        "WHERE menu_item.type = '0'\n" +
                        "GROUP BY orders_menu_items.menu_item_id\n" +
                        "ORDER BY COUNT(orders_menu_items.menu_item_id) DESC\n" +
                        "LIMIT ?", nativeQuery = true)
    List<Long> findTopMenuItems(Integer n);

}
