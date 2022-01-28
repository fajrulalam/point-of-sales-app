package com.example.point_of_sales_app;

public class transactionDetail {

    private int transactionDetailID, transactionID, quantity, lineTotal, noCustomer;
    private  String itemID, timeStamp, status, day_itemID, month_itemID, year_itemID;
    public transactionDetail(){}

    public transactionDetail(int transactionDetailID, int transactionID, int quantity, int lineTotal, String itemID, String timeStamp, int noCustomer, String status, String day_itemID, String month_itemID, String year_itemID) {
        this.transactionDetailID = transactionDetailID;
        this.transactionID = transactionID;
        this.quantity = quantity;
        this.lineTotal = lineTotal;
        this.itemID = itemID;
        this.timeStamp = timeStamp;
        this.noCustomer = noCustomer;
        this.status = status;
        this.day_itemID = day_itemID;
        this.month_itemID = month_itemID;
        this.year_itemID = year_itemID;

    }

    public String getDay_itemID() {
        return day_itemID;
    }

    public void setDay_itemID(String day_itemID) {
        this.day_itemID = day_itemID;
    }

    public String getMonth_itemID() {
        return month_itemID;
    }

    public void setMonth_itemID(String month_itemID) {
        this.month_itemID = month_itemID;
    }

    public String getYear_itemID() {
        return year_itemID;
    }

    public void setYear_itemID(String year_itemID) {
        this.year_itemID = year_itemID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNoCustomer() {
        return noCustomer;
    }

    public void setNoCustomer(int noCustomer) {
        this.noCustomer = noCustomer;
    }

    public int getTransactionDetailID() {
        return transactionDetailID;
    }

    public void setTransactionDetailID(int transactionDetailID) {
        this.transactionDetailID = transactionDetailID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(int lineTotal) {
        this.lineTotal = lineTotal;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
