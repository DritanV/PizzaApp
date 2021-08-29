package com.homework.pizza_app.service.impl;

import com.homework.pizza_app.io.entity.OrderEntity;
import com.homework.pizza_app.repository.OrderRepository;
import com.homework.pizza_app.service.OrderService;
import com.homework.pizza_app.service.PizzaClientService;
import com.homework.pizza_app.service.UserService;
import com.homework.pizza_app.service.impl.dtoexternalservice.ReceivedOrder;
import com.homework.pizza_app.service.impl.dtoexternalservice.SendOrder;
import com.homework.pizza_app.share.dto.OrderDto;
import com.homework.pizza_app.share.dto.Utils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author dritan
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    PizzaClientService pizzaClientService;

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {

        OrderDto fromPizzaApp = new OrderDto();
        OrderDto retVal = new OrderDto();

        if (!Utils.CheckOrder(orderDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Order is not valid");
        } else {

            SendOrder order = new SendOrder();
            BeanUtils.copyProperties(orderDto, order);

            ReceivedOrder recivedOrder = pizzaClientService.makeOrder(order);
            BeanUtils.copyProperties(recivedOrder, fromPizzaApp);
            fromPizzaApp.setUserDetails(orderDto.getUserDetails());
            //insert order from the apllication

        }

        fromPizzaApp.setMyOrderId(Utils.generateOrderId(30));

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(fromPizzaApp, orderEntity);

        OrderEntity savedOrder = orderRepository.save(orderEntity);
        if (savedOrder == null) {
            throw new RuntimeException("Order not registered!!");
        }
        BeanUtils.copyProperties(savedOrder, retVal);
        return retVal;
    }

    @Override
    public void deleteOrder(String myOrderId) {

        orderRepository.deleteBymyOrderId(myOrderId);
    }

    @Override
    public List<OrderDto> getAllOrders(long userId, int page, int limit) {

        List<OrderDto> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<OrderEntity> ordersPage = orderRepository.findAllUsersOrders(userId, pageableRequest);

        List<OrderEntity> allOrders = ordersPage.getContent();

        for (OrderEntity orderItrator : allOrders) {
            OrderDto order = new OrderDto();
            BeanUtils.copyProperties(orderItrator, order);
            returnValue.add(order);
        }
        return returnValue;

    }

    @Override
    public OrderDto getOrder(String orderId) {
        OrderDto orderDto = new OrderDto();
        OrderEntity order = orderRepository.findByMyOrderId(orderId);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This OrderId is not valid");
        }
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }

}
