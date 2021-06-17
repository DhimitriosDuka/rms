package com.rms.rms.repository;

import com.rms.rms.dto.order.FoodGroupReportDto;
import com.rms.rms.dto.order.OrderReportDto;
import com.rms.rms.entity.Order;
import com.rms.rms.entity.User;
import com.rms.rms.enums.FoodGroup;
import com.rms.rms.filters.OrderFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCostumer(User costumer);

    @Query("SELECT o FROM Order o WHERE o.deliveryGuy = ?1 AND o.createdAt > current_timestamp")
    List<Order> getDeliveriesOfDeliveryGuyAfter(User delivery);

    @Query("SELECT o FROM Order o WHERE o.costumer = ?1 AND o.createdAt > current_timestamp")
    List<Order> getOrdersOfOperator(User operator);

    List<Order> findAllByFilter(OrderFilter orderFilter);

    @Query(value = "SELECT DATE(created_at), COUNT(*) AS freq FROM orders GROUP BY DATE(created_at) ORDER BY DATE(created_at)", nativeQuery = true)
    List<OrderReportDto> getReportOfOrders();

    @Query(value = "SELECT menu_item.category, COUNT(menu_item.category) AS freq FROM orders_menu_items JOIN orders ON orders_menu_items.order_id = orders.id LEFT JOIN menu_item ON menu_item.id = orders_menu_items.menu_item_id GROUP BY menu_item.category", nativeQuery = true)
    List<FoodGroupReportDto> getReportOfFoodCategory();

}
