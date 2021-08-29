package com.homework.pizza_app.model.request;

/**
 *
 * @author dritan
 */
public class OrderDetailsRequestModel {

    private String Crust;
    private String Flavor;
    private String Size;
    private int Table_No;

    public OrderDetailsRequestModel(String Crust, String Flavor, String Size, int Table_No) {
        this.Crust = Crust;
        this.Flavor = Flavor;
        this.Size = Size;
        this.Table_No = Table_No;
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

}
