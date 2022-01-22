package com.example.point_of_sales_app;

public class stockCount {
    private int stockChange;
    private String namaItem;

    public stockCount(){}

    public stockCount(String namaItem, int stockChange) {
        this.namaItem = namaItem;
        this.stockChange = stockChange;

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
