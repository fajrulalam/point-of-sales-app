package com.example.point_of_sales_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> order = new ArrayList<String>();
    private ArrayList<String> quantity = new ArrayList<String>();
    private ArrayList<String> subtotal = new ArrayList<String>();
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        generateContent();
        MyListAdapter adapter = new MyListAdapter(this, order, quantity, subtotal);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        //Check if Array list is filled
        for (int i = 0; i<15; i++) {
            String test = order.get(i);
            Log.i("Test Array", test);
        }

    }

    private void generateContent() {
        for (int i =0; i < 15; i++) {
            order.add("Siomay " + i);
            quantity.add("" +i);
            subtotal.add(i + "000");
        }
    }

    private class MyListAdapter extends ArrayAdapter<String> {

        private Activity context;
        private ArrayList<String> itemName;
        private ArrayList<String> quantity;
        private ArrayList<String> subtotal;


        public MyListAdapter(Activity context, ArrayList<String> itemName, ArrayList<String> quantity, ArrayList<String> subtotal) {
            super(context, R.layout.list_transaction, quantity);
            this.context = context;
            this.itemName = itemName;
            this.quantity = quantity;
            this.subtotal = subtotal;

        }


        @NonNull
        @Override
        public View getView(int position, View convertView,  ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.list_transaction, null, true);
            TextView itemName = (TextView) rowView.findViewById(R.id.itemName);
            Button quantity = (Button) rowView.findViewById(R.id.quantity);
            TextView minusButton = (TextView) rowView.findViewById(R.id.minusButton);
            TextView plusButton = (TextView) rowView.findViewById(R.id.plusButton);
            TextView subtotalTextView = (TextView) rowView.findViewById(R.id.subtotalTextView);

            itemName.setText(order.get(position));
            quantity.setText(""+this.quantity);
            subtotalTextView.setText("Rp" + this.subtotal);






//            if(convertView == null) {
//                LayoutInflater inflater = LayoutInflater.from(getContext());
//                convertView = inflater.inflate(layout, parent, false);
//                ViewHolder viewHolder = new ViewHolder();
//                viewHolder.itemName = (TextView)  convertView.findViewById(R.id.itemName);
////                viewHolder.itemName.setText("Bakso");
//                viewHolder.quantity = (Button) convertView.findViewById(R.id.quantity);
//                viewHolder.minusButton = (TextView) convertView.findViewById(R.id.minusButton);
//                viewHolder.plusButton = (TextView) convertView.findViewById(R.id.plusButton);
//                viewHolder.subtotalTextView= (TextView) convertView.findViewById(R.id.subtotalTextView);
//                convertView.setTag(viewHolder);
//
//                //Reduce Quantity
//                viewHolder.minusButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(),"Item -1 for " + position, Toast.LENGTH_SHORT).show();
//                    }
//                });

                //Increase Quantity
//                viewHolder.plusButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(),"Item +1 for " + position, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//                mainViewHolder = (ViewHolder) convertView.getTag();
//                mainViewHolder.itemName.setText(getItem(position));

            return rowView;
        }
    }

    public class ViewHolder {
        TextView itemName;
        Button quantity;
        TextView minusButton;
        TextView plusButton;
        TextView subtotalTextView;
    }
}