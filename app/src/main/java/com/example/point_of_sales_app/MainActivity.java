package com.example.point_of_sales_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Map;

import com.example.point_of_sales_app.fragments.*;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements dialog.DialogBuyListener, SecondFragment.MinumanFragment{

    ListView listView;
    MyAdapter adapter;
    public ArrayList<String> mTitle; //= {"Bakso", "Siomay", "Kentang Goreng", "Es teh"};
    ArrayList<Integer> mQuantity; //int mQuantity[] = {2, 3, 2, 5};
    ArrayList<Integer> mItemPrice; //mItemPrice[] = {7000, 7000, 5000, 3000};
    ArrayList<Integer> msubTotal;
    int kembalian;
    TextView totalCount;

    //Buttons
    Button buttonBeli;
    ImageButton restartButton;
    ImageButton mainMenuButton;
    private ImageButton backEndButton;

    //For Swipe
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter fragmentAdapter;
    ListView minumanListView;
    ArrayAdapter adapterMinuman;
    ArrayList<String> minumanList;

    //Cards on the screen
    LinearLayout linearLayout;

    //Variabel yang masuk ke DB
    String namaMakananPesanan;
    int jumlahMakananPesanan;
    int hargaMakananPesanan;
    int subTotalMakananPesanann;


    //DataBase
    SQLiteDatabase cafeOrderDatabase;
    SharedPreferences sharedPreferencesTransactionID;
    int transactionID_update;
    SharedPreferences sharedPreferencesCustomerID;
    int customerNumber_update;
    DAOTransaction dao;
    transactionDetail transactionDetail;
    transactionDetailStatus transactionDetailStatus;
    HashMap itemHashMap;
    HashMap quantityHashMap;
    DatabaseReference reff;
    DatabaseReference reffStatus;
    DatabaseReference reffItem;
    DatabaseReference reffQuantity;
    DatabaseReference reffStock;
    DatabaseReference reffStockUpdate;
    stockCount stockCount;
    Query queryGetStock;
    Map<String, Object> map;
    int stockChange_int;

    public void onClick(View view) {
            switch (view.getTag().toString()) {
                case "Bakso":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Siomay":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Nasi Lauk":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Nasi Bungkus A":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mItemPrice.add(3000);
                    msubTotal.add(3000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Nasi Bungkus B":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
//                case "Tahu Goreng":
//                    mTitle.add(view.getTag().toString());
//                    mQuantity.add(1);
//                    mItemPrice.add(5000);
//                    msubTotal.add(5000);
//                    adapter.notifyDataSetChanged();
//                    countTotal();
//                    break;
                case "Kentang Goreng":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Popmie":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                default:
                    Toast.makeText(this, "Ndak masuk", Toast.LENGTH_SHORT).show();

            }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //TransactionID
        sharedPreferencesTransactionID = getSharedPreferences("transactionID", 0);
        SharedPreferences.Editor editor = sharedPreferencesTransactionID.edit();

        sharedPreferencesCustomerID = getSharedPreferences("customerID", 0);
        SharedPreferences.Editor editor1 = sharedPreferencesCustomerID.edit();
//        editor1.putInt("customerID", 0);
//        editor1.commit();


//        editor.putInt("transactionID", 1);
//        editor.commit();

        transactionID_update = sharedPreferencesTransactionID.getInt("transactionID", 0);
        customerNumber_update = sharedPreferencesCustomerID.getInt("CustomerID", 0);

        Log.i("CustomerID", ""+  customerNumber_update);





        //Databse Connection and Setter and Getter for Input
        transactionDetail = new transactionDetail();
        transactionDetailStatus = new transactionDetailStatus();
        stockCount = new stockCount();
        reff = FirebaseDatabase.getInstance("https://point-of-sales-app-25e2b-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("TransacationDetail");
        reffStatus = FirebaseDatabase.getInstance("https://point-of-sales-app-25e2b-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TransacationStatus");
        reffStock = FirebaseDatabase.getInstance("https://point-of-sales-app-25e2b-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("StockCount");
        reffStockUpdate = FirebaseDatabase.getInstance().getReference("StockCount");
//        reffStock.addListenerForSingleValueEvent(valueEventListenerStock);
        reffItem = FirebaseDatabase.getInstance("https://point-of-sales-app-25e2b-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("TransacationStatus").child("Makanan");
        reffQuantity = FirebaseDatabase.getInstance("https://point-of-sales-app-25e2b-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("TransacationStatus").child("Quantity");



        //ButtonLayout Sidebar
        restartButton = (ImageButton) findViewById(R.id.restartButton);
        backEndButton = findViewById(R.id.backEndButton);
        mainMenuButton = findViewById(R.id.mainMenuButton);

        backEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackEnd();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor1 = sharedPreferencesCustomerID.edit();
                editor1.putInt("customerID", 0);
                editor1.commit();
                customerNumber_update = sharedPreferencesCustomerID.getInt("customerID", 0);
                Toast.makeText(getApplicationContext(), "Nomor Pelanggan sudah diulang dari 1", Toast.LENGTH_SHORT).show();
            }
        });



        //Initialize Array List
        mTitle = new ArrayList<>();
        mQuantity = new ArrayList<>();
        mItemPrice = new ArrayList<>();
        msubTotal = new ArrayList<>();

        //Initialize Total Calculator
        totalCount = findViewById(R.id.total);
        buttonBeli = findViewById(R.id.buttonBeli);

        //Initialize Swipe Screen (Fragment Adapter)
        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(fragmentAdapter);
        tabLayout.addTab(tabLayout.newTab().setText("Makanan"));
        tabLayout.addTab(tabLayout.newTab().setText("Minuman"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        //Add Database
        cafeOrderDatabase = this.openOrCreateDatabase("cafeOrderDatabase", MODE_PRIVATE, null);
//        cafeOrderDatabase.execSQL("DROP TABLE IF EXISTS transaction_detail");
        cafeOrderDatabase.execSQL("CREATE TABLE IF NOT EXISTS transaction_detail ( " +
                "transactionDetailID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "transactionID INTEGER," +
                "itemID VARCHAR(255)," +
                "quantity INTEGER," +
                "lineTotal INTEGER," +
                "timeStamp VARCHAR(24))");


        //Add the Cards
//        makananCardFragment = new MakananCardFragment();

        listView = findViewById(R.id.listview);

        adapter = new MyAdapter(this, mTitle, mQuantity, mItemPrice, msubTotal);
        listView.setAdapter(adapter);


        countTotal();

        //Tombol Beli
        buttonBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("totalValue", totalCount.getText().toString());
                dialog buyDialog = new dialog();
                buyDialog.setArguments(bundle);
                buyDialog.show(getSupportFragmentManager(), "test");
            }
        });

    }


    //Update Transaction ID
    public int UpdateTransactionID() {
        SharedPreferences.Editor editor = sharedPreferencesTransactionID.edit();
        editor.putInt("transactionID", 1+transactionID_update);
        editor.commit();
        transactionID_update = sharedPreferencesTransactionID.getInt("transactionID", 0);
        return transactionID_update;
    }

    //Update Customer ID
    public int UpdateTCustomerID() {
        SharedPreferences.Editor editor1 = sharedPreferencesCustomerID.edit();
        editor1.putInt("CustomerID", 1+customerNumber_update);
        editor1.commit();
        customerNumber_update= sharedPreferencesCustomerID.getInt("CustomerID", 0);
        return customerNumber_update;
    }

    //Maknanan yang sudah terpilih Pembelian
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> rTitle;
        ArrayList<Integer> rQuantity;
        ArrayList<Integer> ritemPrice;
        ArrayList<Integer> rsubTotal;

        MyAdapter(Context c, ArrayList<String> title, ArrayList<Integer> quantity, ArrayList<Integer> price, ArrayList<Integer> subTotal) {
            super(c, R.layout.list_transaction, R.id.itemName, title);
            this.context = c;
            this.rTitle = title;
            this.rQuantity = quantity;
            this.ritemPrice = price;
            this.rsubTotal = subTotal;

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
                    int newQuant = rQuantity.get(position) + 1;
                    rQuantity.set(position, newQuant);
                    Log.i("ppp: ", "" + rQuantity.get(position));
                    int subtotals = (int) ritemPrice.get(position) * rQuantity.get(position);
                    rsubTotal.set(position, subtotals);
                    Log.i("Subtotal...", "is updated to " + subtotals);
                    subtotal.setText("" + subtotals);
                    quantity.setText("" + rQuantity.get(position));
                    countTotal();
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int newQuant = rQuantity.get(position) - 1;
                    rQuantity.set(position, newQuant);
                    Log.i("ppp: ", "" + rQuantity.get(position));
                    int subtotals = (int) ritemPrice.get(position) * rQuantity.get(position);
                    rsubTotal.set(position, subtotals);
                    Log.i("Subtotal...", "is updated to " + subtotals);
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

    public int countTotal() {
        int total = 0;
        int repeater = 0;
        int subtotal = 0;
        while (repeater < mItemPrice.size()) {
            subtotal = mQuantity.get(repeater) * mItemPrice.get(repeater);
            total = total + subtotal;
            Log.i("total is..,", "" + total);
            repeater++;
        }

        totalCount.setText("" + total);
        return total;


    }


    @Override
    public void countChange(int result) {
        Bundle bundle = new Bundle();
        bundle.putInt("kembalian", result);
        DialogKonfirmasi dialogKonfirmasi = new DialogKonfirmasi();
        dialogKonfirmasi.setArguments(bundle);
        dialogKonfirmasi.show(getSupportFragmentManager(), "test");
        int i = 0;
        int j = 0;
        int k = 0;
        String namaSubMakanan_container = "";
        String namaSubMakanan = "";
        String jumlahSub_container = "";
        String jumlahSub = "";
        UpdateTransactionID();
        UpdateTCustomerID();
        //Firebase Database
        while (j <mTitle.size()) {
            //Transaction Detail
            namaMakananPesanan = mTitle.get(j);
            jumlahMakananPesanan = mQuantity.get(j);
            subTotalMakananPesanann = msubTotal.get(j);
            transactionDetail.setNoCustomer(customerNumber_update);
            transactionDetail.setTransactionID(transactionID_update);
            transactionDetail.setTransactionDetailID(transactionID_update);
            transactionDetail.setItemID(namaMakananPesanan);
            transactionDetail.setQuantity((1)*jumlahMakananPesanan);
            transactionDetail.setLineTotal(subTotalMakananPesanann);
            transactionDetail.setTimeStamp(getTimeStamp());
            transactionDetail.setStatus("Serving");
            reff.push().setValue(transactionDetail);
            queryGetStock = reffStockUpdate.child(namaMakananPesanan);
            stockCount.setStockChange((-1)*jumlahMakananPesanan);
            stockCount.setNamaItem(namaMakananPesanan);
            reffStock.push().setValue(stockCount);



            j++;
        }
        while (k<mTitle.size()){
            namaSubMakanan_container = mTitle.get(k);
            jumlahSub_container = String.valueOf(mQuantity.get(k));
            if(k==mTitle.size()-1){
                namaSubMakanan += namaSubMakanan_container;
                jumlahSub += jumlahSub_container;
            } else {
                namaSubMakanan += namaSubMakanan_container+", ";
                jumlahSub += jumlahSub_container+", ";
            }

//            itemHashMap.put(""+ k, mTitle.get(k));
//            quantityHashMap.put(""+ k, mQuantity.get(k));
            k++;
        }
        Log.i("Ulang dari 1", ""+customerNumber_update);
        reffStatus.child(""+customerNumber_update).child("status").setValue("Serving");
        reffStatus.child(""+customerNumber_update).child("customerNumber").setValue(customerNumber_update);
        reffStatus.child(""+customerNumber_update).child("quantity").setValue(jumlahSub);
        reffStatus.child(""+customerNumber_update).child("itemID").setValue(namaSubMakanan);
//        transactionDetailStatus.setCustomerNumber(customerNumber_update);
//        transactionDetailStatus.setItemID(namaSubMakanan);
//        transactionDetailStatus.setQuantity(jumlahSub);
//        transactionDetailStatus.setStatus("Serving");
//        reffStatus.push().setValue(transactionDetailStatus);


//        cafeOrderDatabase.execSQL("INSERT INTO transaction_detail (itemID, quantity, Linetotal, TimeStamp) VALUES ('Nasi Padang', 3, 'feaf', '"+getTimeStamp()+"')");
        while (i < mTitle.size()) {

//            transactionDetail = new transactionDetail(1, transactionID_update,  msubTotal.get(i), mQuantity.get(i), mTitle.get(i), getTimeStamp());
//            dao.add(transactionDetail);
            cafeOrderDatabase.execSQL("INSERT INTO transaction_detail (transactionID, itemID, quantity, Linetotal, TimeStamp) VALUES" +
                    " ('"+transactionID_update + "','" + mTitle.get(i) +"','"+ mQuantity.get(i) +"', '"+ msubTotal.get(i)+"','"+getTimeStamp()+"' )");
            i++;
        }
        Log.i("transactionID", ""+transactionID_update);
        Log.i("Customer ID update", "" + customerNumber_update);

        ClearOrderList();

    }

    public String getTimeStamp() {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        return "" + timestamp;
    }

    public void ClearOrderList(){
        int i = 0;
        while (i < mTitle.size()) {
            mTitle.remove(i);
            mQuantity.remove(i);
            msubTotal.remove(i);
            mItemPrice.remove(i);
            totalCount.setText("0");
            adapter.notifyDataSetChanged();
//            i++;

        }
    }

    @Override
    public void kirimDataMinuman(String minuman) {
        if (minuman.startsWith("Jus")) {
            mTitle.add(minuman);
            mQuantity.add(1);
            mItemPrice.add(5000);
            msubTotal.add(5000);
            adapter.notifyDataSetChanged();
            countTotal();
        } else {
            switch (minuman) {
                case "Teh Pucuk Harum":
                case "Es Teh":
                case "Aqua 600ml":
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(3000);
                    msubTotal.add(3000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Sprite":
                case "Fanta":
                case "Coca-Cola":
                case "Kopi Hitam":
                case "Milo":
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Frestea":
                case "Es Kopi Durian":
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
            }
        }
    }

    public void openBackEnd() {
        Intent intent = new Intent(this, BackEnd.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    ValueEventListener valueEventListenerStock = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                map = (Map<String, Object>) snapshot.getValue();
                Object customerNumber_object = map.get("StockChange");
                String stockChange_string = String.valueOf(customerNumber_object);
                stockChange_int = Integer.parseInt(stockChange_string);
                Log.i("StockChangeLoop:", ""+stockChange_int);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}