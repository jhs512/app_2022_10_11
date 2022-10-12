package com.ll.exam.app__2022_10_11.app.order.repository;

import com.ll.exam.app__2022_10_11.app.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
