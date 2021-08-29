package com.homework.pizza_app.io.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author dritan
 */
@Entity
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 30, nullable = false)
    private String myOrderId;

    private String Crust;

    @Column(nullable = false)
    private String Flavor;

    @Column(nullable = false)
    private int Order_ID;

    @Column(nullable = false)
    private String Size;

    @Column(nullable = false)
    private int Table_No;
    @Column
    private Date Timestamp;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private UserEntity userDetails;

    public OrderEntity() {
    }

    public OrderEntity(long id, String myOrderId, String Crust, String Flavor, int Order_ID, String Size, int Table_No, Date Timestamp, UserEntity userDetails) {
        this.id = id;
        this.myOrderId = myOrderId;
        this.Crust = Crust;
        this.Flavor = Flavor;
        this.Order_ID = Order_ID;
        this.Size = Size;
        this.Table_No = Table_No;
        this.Timestamp = Timestamp;
        this.userDetails = userDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMyOrderId() {
        return myOrderId;
    }

    public void setMyOrderId(String myOrderId) {
        this.myOrderId = myOrderId;
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

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
    }

    
}
