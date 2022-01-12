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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private ArrayList<String> order = new ArrayList<String>();
//    private ArrayList<String> quantity = new ArrayList<String>();
//    private ArrayList<String> subtotal = new ArrayList<String>();
    ListView listView;
    MyAdapter adapter;
    ArrayList<String> mTitle; //= {"Bakso", "Siomay", "Kentang Goreng", "Es teh"};
    ArrayList<Integer> mQuantity; //int mQuantity[] = {2, 3, 2, 5};
    ArrayList<Integer> mItemPrice; //mItemPrice[] = {7000, 7000, 5000, 3000};

    TextView totalCount;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mTitle = new ArrayList<>();
        mQuantity = new ArrayList<>();
        mItemPrice = new ArrayList<>();

        totalCount = findViewById(R.id.total);


        //Add datasets
        mTitle.add("Bakso");
        mTitle.add("Siomay");
        mTitle.add("Kentang Goreng");
        mTitle.add("Es Teh");

        mQuantity.add(2);
        mQuantity.add(3);
        mQuantity.add(2);
        mQuantity.add(5);

        mItemPrice.add(7000);
        mItemPrice.add(7000);
        mItemPrice.add(5000);
        mItemPrice.add(3000);



        listView = findViewById(R.id.listview);

        adapter = new MyAdapter(this, mTitle, mQuantity, mItemPrice);
        listView.setAdapter(adapter);


        countTotal();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Toast.makeText(MainActivity.this, "Makanan ini berkuah", Toast.LENGTH_SHORT).show();
                }
                if (position == 1) {
                    Toast.makeText(MainActivity.this, "Nama Makanan ini berbumbu kacang", Toast.LENGTH_SHORT).show();
                }
                if (position == 2) {
                    Toast.makeText(MainActivity.this, "Nama Makanan ini potato", Toast.LENGTH_SHORT).show();
                }
                if (position == 3) {
                    Toast.makeText(MainActivity.this, "Ini minuman sih..", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        //Create Data
//        ArrayList<makanan> arrayList= new ArrayList<>();
//        arrayList.add(new makanan("Tes12", 1, "-", "+", 7000));
//        arrayList.add(new makanan("Siomay", 1, "-", "+", 7000));
//        arrayList.add(new makanan("Kentang", 1, "-", "+", 5000));
//        arrayList.add(new makanan("Es teh", 1, "-", "+", 3000));
//
//        //Make Custom Adapter
//        makananAdapter makananAdapter = new makananAdapter(this, R.layout.list_transaction,arrayList);
//        listView.setAdapter(makananAdapter);


    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> rTitle;
        ArrayList<Integer> rQuantity;
        ArrayList<Integer> ritemPrice;

        MyAdapter (Context c, ArrayList<String> title, ArrayList<Integer> quantity, ArrayList<Integer>  subtotal) {
            super(c, R.layout.list_transaction, R.id.itemName, title);
            this.context = c;
            this.rTitle = title;
            this.rQuantity = quantity;
            this.ritemPrice= subtotal;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list_transaction, parent, false);


            TextView itemName = row.findViewById(R.id.itemName);
            Button quantity = row.findViewById(R.id.quantity);
            TextView subtotal = row.findViewById(R.id.subtotalTextView);
            TextView plusButton = row.findViewById(R.id.plusButton);
            TextView minusButton = row.findViewById(R.id.minusButton);

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"Item +1 for " + rTitle.get(position), Toast.LENGTH_SHORT).show();
                    int newQuant = rQuantity.get(position) + 1;
                    rQuantity.set(position, newQuant);
                    Log.i("ppp: ", "" + rQuantity.get(position));
                    int subtotals = (int) ritemPrice.get(position) * rQuantity.get(position);
                    subtotal.setText("" + subtotals);
                    quantity.setText("" + rQuantity.get(position));
                    countTotal();
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"Item -1 for " + rTitle.get(position), Toast.LENGTH_SHORT).show();
                    int newQuant = rQuantity.get(position) - 1;
                    rQuantity.set(position, newQuant);
                    Log.i("ppp: ", "" + rQuantity.get(position));
                    int subtotals = (int) ritemPrice.get(position) * rQuantity.get(position);
                    subtotal.setText("" + subtotals);
                    quantity.setText("" + rQuantity.get(position));

                    if (newQuant == 0) {
                        rTitle.remove(position);
                        rQuantity.remove(position);
                        ritemPrice.remove(position);
                        adapter.notifyDataSetChanged();
                        countTotal();
                    }
                }
            });


            itemName.setText(rTitle.get(position));
            quantity.setText("" + rQuantity.get(position));
            int subtotals = (int) ritemPrice.get(position) * rQuantity.get(position);
            subtotal.setText("" + subtotals);
            return row;

        }
    }

    public void countTotal() {
        int total = 0;
        int repeater = 0;
        int subtotal = 0;
        while (repeater < mItemPrice.size()) {
            subtotal = mQuantity.get(repeater) * mItemPrice.get(repeater);
            total = total + subtotal;
            Log.i("total is..," ,  "" +  total);
            repeater++;
        }

        totalCount.setText("" + total);


    }





//        @NonNull
//        @Override
//        public View getView(int position, View convertView,  ViewGroup parent) {
//            ViewHolder mainViewHolder = null;
//            LayoutInflater inflater = context.getLayoutInflater();
//            View rowView = inflater.inflate(R.layout.list_transaction, null, true);
//            TextView itemName = (TextView) rowView.findViewById(R.id.itemName);
//            Button quantity = (Button) rowView.findViewById(R.id.quantity);
//            TextView minusButton = (TextView) rowView.findViewById(R.id.minusButton);
//            TextView plusButton = (TextView) rowView.findViewById(R.id.plusButton);
//            TextView subtotalTextView = (TextView) rowView.findViewById(R.id.subtotalTextView);
//
//            itemName.setText(order.get(position));
//            quantity.setText(""+this.quantity);
//            subtotalTextView.setText("Rp" + this.subtotal);






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

//            return rowView;
//        }
//    }
//
//    public class ViewHolder {
//        TextView itemName;
//        Button quantity;
//        TextView minusButton;
//        TextView plusButton;
//        TextView subtotalTextView;
//    }
}