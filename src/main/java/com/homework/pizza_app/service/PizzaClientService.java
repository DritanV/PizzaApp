package com.homework.pizza_app.service;

import com.homework.pizza_app.service.impl.dtoexternalservice.DeleteOrder;
import com.homework.pizza_app.service.impl.dtoexternalservice.ReceivedOrder;
import com.homework.pizza_app.service.impl.dtoexternalservice.SendOrder;

/**
 *
 * @author dritan
 */
public interface PizzaClientService {
    
    public void authenticate();
    
    public void getOrders();
   
    public ReceivedOrder makeOrder(SendOrder order);
    
    public void deleteOrder(DeleteOrder deleteOrder);
    
}
