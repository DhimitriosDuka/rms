package com.rms.rms.repository;

import com.rms.rms.entity.Order;
import com.rms.rms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.deliveryGuy = ?1 AND o.createdAt > current_timestamp")
    List<Order> getDeliveriesOfDeliveryGuyAfter(User delivery);

    @Query("SELECT o FROM Order o WHERE o.costumer = ?1 AND o.createdAt > current_timestamp")
    List<Order> getOrdersOfOperator(User operator);

}
