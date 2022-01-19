package com.example.point_of_sales_app;

import java.util.HashMap;
import java.util.Set;

public class transactionDetailStatus {

    private int customerNumber;
    private String itemID, quantity;
    public transactionDetailStatus(){}

    public transactionDetailStatus(int customerNumber, String quantity, String itemID) {
        this.customerNumber = customerNumber;
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
