package com.example.point_of_sales_app;

public class stockCount {
    private int stockChange;
    private String namaItem, day_itemID, month_itemID, year_itemID;

    public stockCount(){}

    public stockCount(String namaItem, int stockChange, String status, String day_itemID, String month_itemID, String year_itemID) {
        this.namaItem = namaItem;
        this.stockChange = stockChange;
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

    public int getStockChange() {
        return stockChange;
    }

    public void setStockChange(int stockChange) {
        this.stockChange = stockChange;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }
}
