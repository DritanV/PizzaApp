package com.homework.pizza_app.ui.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.pizza_app.io.entity.OrderEntity;
import com.homework.pizza_app.io.entity.UserEntity;
import com.homework.pizza_app.model.request.OrderDetailsRequestModel;
import com.homework.pizza_app.model.response.OperationStatusModel;
import com.homework.pizza_app.model.response.OrderRest;
import com.homework.pizza_app.service.OrderService;
import com.homework.pizza_app.service.PizzaClientService;
import com.homework.pizza_app.service.UserService;
import com.homework.pizza_app.service.impl.dtoexternalservice.DeleteOrder;
import com.homework.pizza_app.share.dto.OrderDto;
import com.homework.pizza_app.share.dto.UserDto;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author dritan
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    PizzaClientService pizzaClientService;

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<OrderRest> getOrder(@PathVariable String orderId) throws ResponseStatusException {
        try {

            OrderRest orderRest = new OrderRest();
            OrderDto order = orderService.getOrder(orderId);
            BeanUtils.copyProperties(order, orderRest);
            return new ResponseEntity<OrderRest>(orderRest, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service not available");
        }
    }

    @PostMapping()
    public ResponseEntity<OrderRest> placeOrder(@RequestBody OrderDetailsRequestModel orderdModel, @RequestHeader(name = "Authorization") String token) throws JsonProcessingException {
        try {
            String username = getUsername(token);
            UserEntity findUserByUserName = this.userService.findUserByUserName(username);
            OrderRest retval = new OrderRest();
            OrderDto orderDto = new OrderDto();

            BeanUtils.copyProperties(orderdModel, orderDto);
            orderDto.setUserDetails(findUserByUserName);
            OrderDto placeOrder = orderService.saveOrder(orderDto);
            BeanUtils.copyProperties(placeOrder, retval);
            
            return new ResponseEntity<OrderRest>(retval, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service not available");
        }
    }

    @GetMapping()
    public ResponseEntity<List<OrderEntity>> getAllOrders(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "2") int limit, @RequestHeader(name = "Authorization") String token) throws ResponseStatusException {
            
        if (page < 0 || limit <= 0 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Page should not be negative and limit should be positive ");
        }
        
        try {
            String username = getUsername(token);
            UserDto user = userService.getUser(username);
            List<OrderEntity> retunValue = new ArrayList<>();
            List<OrderDto> orderDto;

            orderDto = orderService.getAllOrders(user.getId(), page, limit);

            for (OrderDto order : orderDto) {
                OrderEntity response = new OrderEntity();
                BeanUtils.copyProperties(order, response);
                retunValue.add(response);
            }

            return new ResponseEntity<List<OrderEntity>>(retunValue, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service not available");
        }
    }

    @DeleteMapping(path = "/{myOrderId}")
    public ResponseEntity<OperationStatusModel> deleteOrder(@PathVariable String myOrderId, @RequestHeader(name = "Authorization") String token) throws JsonProcessingException {
        
        try{
        OperationStatusModel returnValue = new OperationStatusModel();
        String username = getUsername(token);

        UserDto user = userService.getUser(username);
        returnValue.setOperationName("DELETE ORDER");
        OrderDto order = orderService.getOrder(myOrderId);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OrderId does not exist!!");
        }
        if ((order.getUserDetails().getUserName()).equals(user.getUserName())) {
            //Delete Remote
            pizzaClientService.deleteOrder(new DeleteOrder(order.getOrder_ID()));
            orderService.deleteOrder(order.getMyOrderId());
        }

        returnValue.setOperationResult("SUCCES");
        return new ResponseEntity<OperationStatusModel>(returnValue, HttpStatus.OK);
        }catch(Exception e){
             throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service not available");
        }
    }

    public static String getUsername(String token) throws JsonProcessingException {
        token = token.substring("bearer ".length());
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();

        String payload = new String(decoder.decode(chunks[1]));
        ObjectMapper objectMapper = new ObjectMapper();

        PayloadClass payloadClass = objectMapper.readValue(payload, PayloadClass.class);
        String username = payloadClass.getSub();
        return username;
    }
}
