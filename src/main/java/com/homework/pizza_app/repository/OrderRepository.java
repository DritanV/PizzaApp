package com.homework.pizza_app.repository;

import com.homework.pizza_app.io.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dritan
 */
@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {

    @Query(value = "select * from order_entity  where order_entity.user_id = :userId ", nativeQuery = true)
    public Page<OrderEntity> findAllUsersOrders(@Param("userId") long userId, Pageable pageableRequest);

    public OrderEntity findByMyOrderId(String orderId);

    @Transactional
    @Modifying
    public void deleteBymyOrderId(String myOrderId);

}
