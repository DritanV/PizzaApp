package com.homework.pizza_app.service.impl.dtoexternalservice;

import java.util.Date;

/**
 *
 * @author dritan
 */
public class ReceivedOrder {

    public String Crust;
    public String Flavor;
    public int Order_ID;
    public String Size;
    public int Table_No;
    public String Timestamp;

    public ReceivedOrder(String Crust, String Flavor, int Order_ID, String Size, int Table_No, String Timestamp) {
        this.Crust = Crust;
        this.Flavor = Flavor;
        this.Order_ID = Order_ID;
        this.Size = Size;
        this.Table_No = Table_No;
        this.Timestamp = Timestamp;
    }

    @Override
    public String toString() {
        return "ReceivedOrder{" + "Crust=" + Crust + ", Flavor=" + Flavor + ", Order_ID=" + Order_ID + ", Size=" + Size + ", Table_No=" + Table_No + ", Timestamp=" + Timestamp + '}';
    }

}
