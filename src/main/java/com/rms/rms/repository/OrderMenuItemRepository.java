package com.rms.rms.repository;

import com.rms.rms.entity.OrderMenuItem;
import com.rms.rms.entity.embedded.OrderMenuItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderMenuItemRepository extends JpaRepository<OrderMenuItem, OrderMenuItemId> {
}
