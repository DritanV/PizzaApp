package com.homework.pizza_app.service;

import com.homework.pizza_app.share.dto.OrderDto;
import java.util.List;

/**
 *
 * @author dritan
 */
public interface OrderService {

    public void deleteOrder(String myOrderId);

    public OrderDto saveOrder(OrderDto orderDto);

    public List<OrderDto> getAllOrders(long userId, int page, int limit);

    public OrderDto getOrder(String orderId);

}
