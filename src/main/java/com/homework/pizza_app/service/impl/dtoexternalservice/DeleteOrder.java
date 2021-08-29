package com.homework.pizza_app.service.impl.dtoexternalservice;

/**
 *
 * @author dritan
 */
public class DeleteOrder {

    public int Order_ID;

    public DeleteOrder(int Order_ID) {
        this.Order_ID = Order_ID;
    }

    @Override
    public String toString() {
        return "DeleteOrder{" + "Order_ID=" + Order_ID + '}';
    }

}
