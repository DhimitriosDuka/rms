package com.rms.rms.repository;

import com.rms.rms.entity.User;
import com.rms.rms.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByIdAndRole(Long id, Role role);
    Optional<User> findByUserName(String userName);

    @Query(value = "SELECT users.id FROM users\n" +
                        "LEFT JOIN schedule ON schedule.user_id = users.id\n" +
                        "LEFT JOIN orders ON orders.delivery_guy_id = users.id\n" +
                        "WHERE users.role = '2' \n" +
                        "AND (now() NOT BETWEEN orders.created_at AND orders.delivery_time) OR (orders.created_at IS null)\n" +
                        "AND now() + INTERVAL '45 min' BETWEEN schedule.start_work_hour AND schedule.end_work_hour\n" +
                        "AND schedule.is_present = true\n" +
                        "GROUP BY users.id", nativeQuery = true)
    List<Integer> findAllAvailableDeliveryGuys();

    @Query(value = "SELECT * FROM users\n" +
                        "LEFT JOIN schedule ON schedule.user_id = users.id\n" +
                        "LEFT JOIN orders ON orders.delivery_guy_id = users.id\n" +
                        "WHERE users.role = '2'\n" +
                        "AND now() BETWEEN schedule.start_work_hour AND schedule.end_work_hour\n" +
                        "AND orders.delivery_time > now()\n" +
                        "AND schedule.is_present = true\n" +
                        "ORDER BY (orders.delivery_time - now())\n" +
                        "LIMIT 1", nativeQuery = true)
    User findDeliveryGuyWithClosestDeliveryTime();

    @Query(value = "SELECT users.id, COUNT(users.id) as a\n" +
                        "FROM users\n" +
                        "LEFT JOIN orders ON orders.costumer_id = users.id\n" +
                        "WHERE users.role = '1'\n" +
                        "GROUP BY users.id\n" +
                        "ORDER BY COUNT(users.id) DESC\n" +
                        "LIMIT ?\n", nativeQuery = true)
    List<Long> findTopCustomers(Integer n);


}
