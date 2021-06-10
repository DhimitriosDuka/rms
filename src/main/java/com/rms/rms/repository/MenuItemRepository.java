package com.rms.rms.repository;

import com.rms.rms.entity.MenuItem;
import com.rms.rms.filters.MenuItemFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findAllByFilter(MenuItemFilter menuItemFilter);

}
