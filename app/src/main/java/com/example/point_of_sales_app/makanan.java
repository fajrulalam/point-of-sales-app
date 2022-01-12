package com.example.point_of_sales_app;

import android.widget.Button;
import android.widget.TextView;

public class makanan {
    TextView itemName;
    Button quantity;
    TextView minusButton;
    TextView plusButton;
    TextView subtotalTextView;

    public makanan(TextView itemName, Button quantity, TextView minusButton, TextView plusButton, TextView subtotalTextView) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.minusButton = minusButton;
        this.plusButton = plusButton;
        this.subtotalTextView = subtotalTextView;
    }

    public TextView getItemName() {
        return itemName;
    }

    public void setItemName(TextView itemName) {
        this.itemName = itemName;
    }

    public Button getQuantity() {
        return quantity;
    }

    public void setQuantity(Button quantity) {
        this.quantity = quantity;
    }

    public TextView getMinusButton() {
        return minusButton;
    }

    public void setMinusButton(TextView minusButton) {
        this.minusButton = minusButton;
    }

    public TextView getPlusButton() {
        return plusButton;
    }

    public void setPlusButton(TextView plusButton) {
        this.plusButton = plusButton;
    }

    public TextView getSubtotalTextView() {
        return subtotalTextView;
    }

    public void setSubtotalTextView(TextView subtotalTextView) {
        this.subtotalTextView = subtotalTextView;
    }
}
