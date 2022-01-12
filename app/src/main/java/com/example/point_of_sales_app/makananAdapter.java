package com.example.point_of_sales_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class makananAdapter extends ArrayAdapter<makanan> {
    private Context mContext;
    private int mResource;

    public makananAdapter(@NonNull Context context, int resource, @NonNull ArrayList<makanan> objects) {
        super(context, resource, objects);
        this.mContext =  context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        Button quantity = (Button) convertView.findViewById(R.id.quantity);
        TextView minusButton = (TextView) convertView.findViewById(R.id.minusButton);
        TextView plusButton = (TextView) convertView.findViewById(R.id.plusButton);
        TextView subtotalTextView = (TextView) convertView.findViewById(R.id.subtotalTextView);

        itemName.setText(getItem(position).getItemName());
//        quantity.setText(getItem(position).getQuantity());
//        subtotalTextView.setText(getItem(position).getSubtotalTextView());


        return convertView;
    }
}
