package com.example.point_of_sales_app;

import android.widget.Button;
import android.widget.TextView;

public class makanan {
    String itemName;
    int quantity;
    String minusButton;
    String plusButton;
    int subtotalTextView;

    public makanan(String itemName, int quantity, String minusButton, String plusButton, int subtotalTextView) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.minusButton = minusButton;
        this.plusButton = plusButton;
        this.subtotalTextView = subtotalTextView;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMinusButton() {
        return minusButton;
    }

    public void setMinusButton(String minusButton) {
        this.minusButton = minusButton;
    }

    public String getPlusButton() {
        return plusButton;
    }

    public void setPlusButton(String plusButton) {
        this.plusButton = plusButton;
    }

    public int getSubtotalTextView() {
        return subtotalTextView;
    }

    public void setSubtotalTextView(int subtotalTextView) {
        this.subtotalTextView = subtotalTextView;
    }
}

