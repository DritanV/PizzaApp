package com.homework.pizza_app.model.response;

import java.util.Date;

/**
 *
 * @author dritan
 */
public class OrderRest {

    private String Crust;
    private String Flavor;
    private int Order_ID;
    private String Size;
    private int Table_No;
    private Date Timestamp;

    public OrderRest() {
    }

    public OrderRest(String Crust, String Flavor, int Order_ID, String Size, int Table_No, Date Timestamp) {
        this.Crust = Crust;
        this.Flavor = Flavor;
        this.Order_ID = Order_ID;
        this.Size = Size;
        this.Table_No = Table_No;
        this.Timestamp = Timestamp;
    }

    public String getCrust() {
        return Crust;
    }

    public void setCrust(String Crust) {
        this.Crust = Crust;
    }

    public String getFlavor() {
        return Flavor;
    }

    public void setFlavor(String Flavor) {
        this.Flavor = Flavor;
    }

    public int getOrder_ID() {
        return Order_ID;
    }

    public void setOrder_ID(int Order_ID) {
        this.Order_ID = Order_ID;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public int getTable_No() {
        return Table_No;
    }

    public void setTable_No(int Table_No) {
        this.Table_No = Table_No;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date Timestamp) {
        this.Timestamp = Timestamp;
    }

}
