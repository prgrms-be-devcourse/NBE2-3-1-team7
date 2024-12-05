package com.example.shop.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderNumber(String orderNumber);

    @Query("select o from Order o" +
            " join fetch o.orderDetails od" +
            " join fetch od.product" +
            " where o.orderNumber = :orderNumber")
    Optional<Order> findOrderAndOrderDetailByOrderNumber(String orderNumber);
  
}
