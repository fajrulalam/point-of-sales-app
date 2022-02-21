package com.example.point_of_sales_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Map;

import com.bumptech.glide.Glide;
import com.example.point_of_sales_app.fragments.*;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements dialog.DialogBuyListener, SecondFragment.MinumanFragment{

    ListView listView;
    MyAdapter adapter;
    public ArrayList<String> mTitle; //= {"Bakso", "Siomay", "Kentang Goreng", "Es teh"};
    ArrayList<Integer> mQuantity; //int mQuantity[] = {2, 3, 2, 5};
    ArrayList<Integer> mItemPrice; //mItemPrice[] = {7000, 7000, 5000, 3000};
    ArrayList<Integer> msubTotal;
    ArrayList<Integer> mQuantityUrut;
    int kembalian;
    TextView totalCount;
    TextView nomorPelangganBerikutnya;
    int nextCustomerNumber;

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

    TextView try_bro;
    CheckBox bungkusCheckbox;
    CheckBox pesanCheckbox;
    CardView cover00;



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


    //Firestore
    FirebaseFirestore fs;
    Map<String, Object> pesanan = new HashMap<>();
    Map<String, Object> stockChange = new HashMap<>();
    Map<String, Object> status = new HashMap<>();
    Map<String, Object> dailyTranscation = new HashMap<>();
    Map<String, Object> monthlyTransaction = new HashMap<>();
    Map<String, Object> yearlyTransaction = new HashMap<>();






    public void onClick(View view) {
            switch (view.getTag().toString()) {
                case "Bakso":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(0 , 1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();

                    break;
                case "Siomay":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(12 , 1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Nasi Ayam":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(5 , 1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Nasi Pindang":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(6 , 1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "NasBung A":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(3 , 1);
                    mItemPrice.add(3000);
                    msubTotal.add(3000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "NasBung B":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(4 , 1);
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
                case "Kentang G":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(1 , 1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Popmie":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(9 , 1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Tahu G":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(11 , 1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Mie Ayam":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(2 , 1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Pisang G":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(8 , 1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Sosis Naget":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(13 , 1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Sereal":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(10 , 1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Nasi Telur":
                    mTitle.add(view.getTag().toString());
                    mQuantity.add(1);
                    mQuantityUrut.set(9 , 1);
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


        //Firestore TEst
        fs = FirebaseFirestore.getInstance();

        bungkusCheckbox = (CheckBox) findViewById(R.id.bungkusCheckbox);
        pesanCheckbox = findViewById(R.id.pesanCheckbox);

        Map mapAshiap = new HashMap();
        mapAshiap.put("ashiap", 99);
        fs.collection("OrderHistory").document("2").update("ashiapLur", 88);


//        fs.collection("DailyTransaction").document("2022-02-17").update("customerNumber", 61);
//        fs.collection("DailyTransaction").document("2022-02-19").update("customerNumber", 89);
//        fs.collection("DailyTransaction").document("2022-02-20").update("customerNumber", 83);
//        fs.collection("DailyTransaction").document("2022-02-21").update("customerNumber", 69);
////
//        fs.collection("MonthlyTransaction").document("2022-02").update("customerNumber",302);
//        fs.collection("YearlyTransaction").document("2022").update("customerNumber", 302);





    nomorPelangganBerikutnya = findViewById(R.id.nomorPelangganBerikutnya);
    nomorPelangganBerikutnya.setText("Nomor Berikutnya: ");

//        Glide.with(MainActivity.this).load("http://goo.gl/gEgYUd").into((ImageView) findViewById(R.id.gambarBakso));

        //TransactionID
        sharedPreferencesTransactionID = getSharedPreferences("transactionID", 0);
        SharedPreferences.Editor editor = sharedPreferencesTransactionID.edit();

        sharedPreferencesCustomerID = getSharedPreferences("customerID", 0);
        SharedPreferences.Editor editor1 = sharedPreferencesCustomerID.edit();
//        editor1.putInt("customerID", 0);
//        editor1.commit();



//        editor.putInt("transactionID", 1);
//        editor.commit();

        //Picasso Imageviews

        transactionID_update = sharedPreferencesTransactionID.getInt("transactionID", 0);
        customerNumber_update = sharedPreferencesCustomerID.getInt("CustomerID", 0);
        nomorPelangganBerikutnya.setText("Nomor Berikutnya: "+ customerNumber_update);

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

//        backEndButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openBackEnd();
//            }
//        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor1 = sharedPreferencesCustomerID.edit();
                editor1.putInt("customerID", 0);
                editor1.commit();
                customerNumber_update = sharedPreferencesCustomerID.getInt("customerID", 0);
                Toast.makeText(getApplicationContext(), "Nomor Pelanggan sudah diulang dari 1", Toast.LENGTH_SHORT).show();
                nextCustomerNumber = customerNumber_update +1;
                nomorPelangganBerikutnya.setText("Nomor Berikutnya: " +nextCustomerNumber);
            }
        });



        //Initialize Array List
        mTitle = new ArrayList<>();
        mQuantity = new ArrayList<>();
        mItemPrice = new ArrayList<>();
        msubTotal = new ArrayList<>();
        mQuantityUrut = new ArrayList<>();

        int i = 0;
        while (i < 27) {
            mQuantityUrut.add(0);
            i++;
        }

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

        adapter = new MyAdapter(this, mTitle, mQuantity, mItemPrice, msubTotal, mQuantityUrut);
        listView.setAdapter(adapter);

        pesanCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pesanCheckbox.isChecked()) {
                    bungkusCheckbox.setChecked(false);
                    bungkusCheckbox.setVisibility(View.GONE);
                } else {
                    bungkusCheckbox.setChecked(false);
                    bungkusCheckbox.setVisibility(View.VISIBLE);
                }

            }
        });



        countTotal();

        //Tombol Beli
        buttonBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                if (pesanCheckbox.isChecked()){
                    bundle.putInt("pesan", 1);

                } else {
                    bundle.putInt("pesan", 0);
                }
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
        ArrayList<Integer> rQuantityUrut;


        MyAdapter(Context c, ArrayList<String> title, ArrayList<Integer> quantity, ArrayList<Integer> price, ArrayList<Integer> subTotal, ArrayList<Integer> quantityUrut) {
            super(c, R.layout.list_transaction, R.id.itemName, title);
            this.context = c;
            this.rTitle = title;
            this.rQuantity = quantity;
            this.ritemPrice = price;
            this.rsubTotal = subTotal;
            this.rQuantityUrut = quantityUrut;


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

                    int subtotals = (int) ritemPrice.get(position) * rQuantity.get(position);
                    rsubTotal.set(position, subtotals);
                    subtotal.setText("" + subtotals);
                    quantity.setText("" + rQuantity.get(position));
                    countTotal();
                    updateQuantity(rTitle.get(position), newQuant);
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int newQuant = rQuantity.get(position) - 1;
                    rQuantity.set(position, newQuant);
                    int subtotals = (int) ritemPrice.get(position) * rQuantity.get(position);
                    rsubTotal.set(position, subtotals);
                    Log.i("Subtotal...", "is updated to " + subtotals);
                    subtotal.setText(String.valueOf(subtotals));
                    quantity.setText("" + rQuantity.get(position));
                    countTotal();
                    updateQuantity(rTitle.get(position), newQuant);


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

        public void updateQuantity(String item, int sum){
            switch (item) {

                //Makanan
                case "Bakso":
                    rQuantityUrut.set(0, sum);
                    mQuantityUrut.set(0, sum);
                    break;
                case "Kentang G":
                     rQuantityUrut.set(1, sum);
                     mQuantityUrut.set(1, sum);
                    break;
                case "Mie Ayam":
                     rQuantityUrut.set(2, sum);
                     mQuantityUrut.set(2, sum);
                    break;
                case "NasBung A":
                     rQuantityUrut.set(3, sum);
                     mQuantityUrut.set(3, sum);
                    break;
                case "NasBung B":
                     rQuantityUrut.set(4, sum);
                     mQuantityUrut.set(4, sum);
                    break;
                case "Nasi Ayam":
                     rQuantityUrut.set(5, sum);
                     mQuantityUrut.set(5, sum);
                    break;
                case "Nasi Pindang":
                     rQuantityUrut.set(6, sum);
                     mQuantityUrut.set(6, sum);
                    break;
                case "Nasi Telur":
                     rQuantityUrut.set(7, sum);
                     mQuantityUrut.set(7, sum);
                    break;
                case "Pisang G":
                     rQuantityUrut.set(8, sum);
                     mQuantityUrut.set(8, sum);
                    break;
                case "Popmie":
                     rQuantityUrut.set(9, sum);
                     mQuantityUrut.set(9, sum);
                    break;
                case "Sereal":
                     rQuantityUrut.set(10, sum);
                     mQuantityUrut.set(10, sum);
                    break;
                case "Tahu G":
                     rQuantityUrut.set(11, sum);
                     mQuantityUrut.set(11, sum);
                    break;
                case "Siomay":
                     rQuantityUrut.set(12, sum);
                     mQuantityUrut.set(12, sum);
                    break;
                case "Sosis Naget":
                     rQuantityUrut.set(13, sum);
                     mQuantityUrut.set(13, sum);
                    break;

                    //Minuman
                case "Aqua 600ml":
                     rQuantityUrut.set(14, sum);
                     mQuantityUrut.set(14, sum);
                    break;
                case "Coca Cola":
                     rQuantityUrut.set(15, sum);
                     mQuantityUrut.set(15, sum);
                    break;
                case "Es Kopi Durian":
                     rQuantityUrut.set(16, sum);
                     mQuantityUrut.set(16, sum);
                    break;
                case "Es Teh":
                     rQuantityUrut.set(17, sum);
                     mQuantityUrut.set(17, sum);
                    break;
                case "Fanta":
                     rQuantityUrut.set(18, sum);
                     mQuantityUrut.set(18, sum);
                    break;
                case "Floridina":
                     rQuantityUrut.set(19, sum);
                     mQuantityUrut.set(19, sum);
                case "Frestea":
                     rQuantityUrut.set(20, sum);
                     mQuantityUrut.set(20, sum);
                    break;
                case "Isoplus":
                     rQuantityUrut.set(21, sum);
                     mQuantityUrut.set(21, sum);
                    break;
                case "Kopi Hitam":
                     rQuantityUrut.set(22, sum);
                     mQuantityUrut.set(22, sum);
                    break;
                case "Milo":
                     rQuantityUrut.set(23, sum);
                     mQuantityUrut.set(23, sum);
                    break;
                case "Sprite":
                     rQuantityUrut.set(24, sum);
                     mQuantityUrut.set(24, sum);
                    break;
                case "Teh Pucuk Harum":
                     rQuantityUrut.set(25, sum);
                     mQuantityUrut.set(25, sum);
                    break;
                case "Teh Panas":
                    rQuantityUrut.set(26, sum);
                    mQuantityUrut.set(26, sum);

            }
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
    public void countChange(int result, String waktuPengambilan) {
        Bundle bundle = new Bundle();
        bundle.putInt("kembalian", result);
        bundle.putInt("customerNumber_update", customerNumber_update);
        DialogKonfirmasi dialogKonfirmasi = new DialogKonfirmasi();
        dialogKonfirmasi.setArguments(bundle);
        dialogKonfirmasi.show(getSupportFragmentManager(), "test");
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        String namaSubMakanan_container = "";
        String namaSubMakanan = "";
        String jumlahSub_container = "";
        String jumlahSub = "";
        UpdateTransactionID();
        UpdateTCustomerID();
        int bungkus = 0;
        if(bungkusCheckbox.isChecked()) {
            bungkus = 1;
        } else if (pesanCheckbox.isChecked()) {
            bungkus = 2;
        }
        //Firebase Database
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();


        fs.collection("YearlyTransaction").document(getYear()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (!documentSnapshot.exists()) {
                    yearlyTransaction.put("date", getDate());
                    yearlyTransaction.put("month", getMonth());
                    yearlyTransaction.put("year", getYear());
                    yearlyTransaction.put("timestamp", FieldValue.serverTimestamp());
                    yearlyTransaction.put("customerNumber", 1);
                    yearlyTransaction.put("total", countTotal());
                    yearlyTransaction.put("Bakso", mQuantityUrut.get(0));
                    yearlyTransaction.put("Kentang G", mQuantityUrut.get(1));
                    yearlyTransaction.put("Mie Ayam", mQuantityUrut.get(2));
                    yearlyTransaction.put("NasBung A", mQuantityUrut.get(3));
                    yearlyTransaction.put("NasBung B", mQuantityUrut.get(4));
                    yearlyTransaction.put("Nasi Ayam", mQuantityUrut.get(5));
                    yearlyTransaction.put("Nasi Pindang", mQuantityUrut.get(6));
                    yearlyTransaction.put("Nasi Telur", mQuantityUrut.get(7));
                    yearlyTransaction.put("Pisang G", mQuantityUrut.get(8));
                    yearlyTransaction.put("Popmie", mQuantityUrut.get(9));
                    yearlyTransaction.put("Sereal", mQuantityUrut.get(10));
                    yearlyTransaction.put("Tahu G", mQuantityUrut.get(11));
                    yearlyTransaction.put("Siomay", mQuantityUrut.get(12));
                    yearlyTransaction.put("Sosis Naget", mQuantityUrut.get(13)); //14 Makanan

                    yearlyTransaction.put("Aqua 600ml", mQuantityUrut.get(14));
                    yearlyTransaction.put("Coca-Cola", mQuantityUrut.get(15));
                    yearlyTransaction.put("Es Kopi Durian", mQuantityUrut.get(16));
                    yearlyTransaction.put("Es Teh", mQuantityUrut.get(17));
                    yearlyTransaction.put("Fanta", mQuantityUrut.get(18));
                    yearlyTransaction.put("Floridina", mQuantityUrut.get(19));
                    yearlyTransaction.put("Frestea", mQuantityUrut.get(20));
                    yearlyTransaction.put("Isoplus", mQuantityUrut.get(21));
                    yearlyTransaction.put("Kopi Hitam", mQuantityUrut.get(22));
                    yearlyTransaction.put("Milo", mQuantityUrut.get(23));
                    yearlyTransaction.put("Sprite", mQuantityUrut.get(24));
                    yearlyTransaction.put("Teh Pucuk Harum", mQuantityUrut.get(25));
                    yearlyTransaction.put("Teh Hangat", mQuantityUrut.get(26));
                    fs.collection("YearlyTransaction").document(getYear()).set(yearlyTransaction);

                } else {
                    fs.collection("YearlyTransaction").document(getYear()).update(
                            "total", FieldValue.increment(countTotal()),
                            "timestamp", FieldValue.serverTimestamp(),
                            "customerNumber", FieldValue.increment(1),
                            "Bakso", FieldValue.increment(mQuantityUrut.get(0)),
                            "Kentang G", FieldValue.increment(mQuantityUrut.get(1)),
                            "Mie Ayam", FieldValue.increment(mQuantityUrut.get(2)),
                            "NasBung A", FieldValue.increment(mQuantityUrut.get(3)),
                            "NasBung B", FieldValue.increment(mQuantityUrut.get(4)),
                            "Nasi Ayam", FieldValue.increment(mQuantityUrut.get(5)),
                            "Nasi Pindang", FieldValue.increment(mQuantityUrut.get(6)),
                            "Nasi Telur", FieldValue.increment(mQuantityUrut.get(7)),
                            "Pisang G", FieldValue.increment(mQuantityUrut.get(8)),
                            "Popmie", FieldValue.increment(mQuantityUrut.get(9)),
                            "Sereal", FieldValue.increment(mQuantityUrut.get(10)),
                            "Tahu G", FieldValue.increment(mQuantityUrut.get(11)),
                            "Siomay", FieldValue.increment(mQuantityUrut.get(12)),
                            "Sosis Naget", FieldValue.increment(mQuantityUrut.get(13)), //14 Makanan

                            "Aqua 600ml", FieldValue.increment(mQuantityUrut.get(14)),
                            "Coca-Cola", FieldValue.increment(mQuantityUrut.get(15)),
                            "Es Kopi Durian", FieldValue.increment(mQuantityUrut.get(16)),
                            "Es Teh", FieldValue.increment(mQuantityUrut.get(17)),
                            "Fanta", FieldValue.increment(mQuantityUrut.get(18)),
                            "Floridina", FieldValue.increment(mQuantityUrut.get(19)),
                            "Frestea", FieldValue.increment(mQuantityUrut.get(20)),
                            "Isoplus", FieldValue.increment(mQuantityUrut.get(21)),
                            "Kopi Hitam", FieldValue.increment(mQuantityUrut.get(22)),
                            "Milo", FieldValue.increment(mQuantityUrut.get(23)),
                            "Sprite", FieldValue.increment(mQuantityUrut.get(24)),
                            "Teh Pucuk Harum", FieldValue.increment(mQuantityUrut.get(25)),
                            "Teh Hangat", FieldValue.increment(mQuantityUrut.get(26))
                    );
                }
            }
        });
        fs.collection("MonthlyTransaction").document(getMonth()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (!documentSnapshot.exists()) {
                    monthlyTransaction.put("date", getDate());
                    monthlyTransaction.put("month", getMonth());
                    monthlyTransaction.put("year", getYear());
                    monthlyTransaction.put("timestamp", FieldValue.serverTimestamp());
                    monthlyTransaction.put("customerNumber", 1);
                    monthlyTransaction.put("total", countTotal());
                    monthlyTransaction.put("Bakso", mQuantityUrut.get(0));
                    monthlyTransaction.put("Kentang G", mQuantityUrut.get(1));
                    monthlyTransaction.put("Mie Ayam", mQuantityUrut.get(2));
                    monthlyTransaction.put("NasBung A", mQuantityUrut.get(3));
                    monthlyTransaction.put("NasBung B", mQuantityUrut.get(4));
                    monthlyTransaction.put("Nasi Ayam", mQuantityUrut.get(5));
                    monthlyTransaction.put("Nasi Pindang", mQuantityUrut.get(6));
                    monthlyTransaction.put("Nasi Telur", mQuantityUrut.get(7));
                    monthlyTransaction.put("Pisang G", mQuantityUrut.get(8));
                    monthlyTransaction.put("Popmie", mQuantityUrut.get(9));
                    monthlyTransaction.put("Sereal", mQuantityUrut.get(10));
                    monthlyTransaction.put("Tahu G", mQuantityUrut.get(11));
                    monthlyTransaction.put("Siomay", mQuantityUrut.get(12));
                    monthlyTransaction.put("Sosis Naget", mQuantityUrut.get(13)); //14 Makanan

                    monthlyTransaction.put("Aqua 600ml", mQuantityUrut.get(14));
                    monthlyTransaction.put("Coca-Cola", mQuantityUrut.get(15));
                    monthlyTransaction.put("Es Kopi Durian", mQuantityUrut.get(16));
                    monthlyTransaction.put("Es Teh", mQuantityUrut.get(17));
                    monthlyTransaction.put("Fanta", mQuantityUrut.get(18));
                    monthlyTransaction.put("Floridina", mQuantityUrut.get(19));
                    monthlyTransaction.put("Frestea", mQuantityUrut.get(20));
                    monthlyTransaction.put("Isoplus", mQuantityUrut.get(21));
                    monthlyTransaction.put("Kopi Hitam", mQuantityUrut.get(22));
                    monthlyTransaction.put("Milo", mQuantityUrut.get(23));
                    monthlyTransaction.put("Sprite", mQuantityUrut.get(24));
                    monthlyTransaction.put("Teh Pucuk Harum", mQuantityUrut.get(25));
                    monthlyTransaction.put("Teh Hangat", mQuantityUrut.get(26));
                    fs.collection("MonthlyTransaction").document(getMonth()).set(monthlyTransaction);


                } else {
                    fs.collection("MonthlyTransaction").document(getMonth()).update(
                            "total", FieldValue.increment(countTotal()),
                            "timestamp", FieldValue.serverTimestamp(),
                            "customerNumber", FieldValue.increment(1),
                            "Bakso", FieldValue.increment(mQuantityUrut.get(0)),
                            "Kentang G", FieldValue.increment(mQuantityUrut.get(1)),
                            "Mie Ayam", FieldValue.increment(mQuantityUrut.get(2)),
                            "NasBung A", FieldValue.increment(mQuantityUrut.get(3)),
                            "NasBung B", FieldValue.increment(mQuantityUrut.get(4)),
                            "Nasi Ayam", FieldValue.increment(mQuantityUrut.get(5)),
                            "Nasi Pindang", FieldValue.increment(mQuantityUrut.get(6)),
                            "Nasi Telur", FieldValue.increment(mQuantityUrut.get(7)),
                            "Pisang G", FieldValue.increment(mQuantityUrut.get(8)),
                            "Popmie", FieldValue.increment(mQuantityUrut.get(9)),
                            "Sereal", FieldValue.increment(mQuantityUrut.get(10)),
                            "Tahu G", FieldValue.increment(mQuantityUrut.get(11)),
                            "Siomay", FieldValue.increment(mQuantityUrut.get(12)),
                            "Sosis Naget", FieldValue.increment(mQuantityUrut.get(13)), //14 Makanan

                            "Aqua 600ml", FieldValue.increment(mQuantityUrut.get(14)),
                            "Coca-Cola", FieldValue.increment(mQuantityUrut.get(15)),
                            "Es Kopi Durian", FieldValue.increment(mQuantityUrut.get(16)),
                            "Es Teh", FieldValue.increment(mQuantityUrut.get(17)),
                            "Fanta", FieldValue.increment(mQuantityUrut.get(18)),
                            "Floridina", FieldValue.increment(mQuantityUrut.get(19)),
                            "Frestea", FieldValue.increment(mQuantityUrut.get(20)),
                            "Isoplus", FieldValue.increment(mQuantityUrut.get(21)),
                            "Kopi Hitam", FieldValue.increment(mQuantityUrut.get(22)),
                            "Milo", FieldValue.increment(mQuantityUrut.get(23)),
                            "Sprite", FieldValue.increment(mQuantityUrut.get(24)),
                            "Teh Pucuk Harum", FieldValue.increment(mQuantityUrut.get(25)),
                            "Teh Hangat", FieldValue.increment(mQuantityUrut.get(26))
                    );
                }
            }
        });
        fs.collection("Stock").document("Stocks").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (!documentSnapshot.exists()) {
                    dailyTranscation.put("Bakso", (-1)*mQuantityUrut.get(0));
                    dailyTranscation.put("Kentang G", (-1)*mQuantityUrut.get(1));
                    dailyTranscation.put("Mie Ayam", (-1)*mQuantityUrut.get(2));
                    dailyTranscation.put("NasBung A", (-1)*mQuantityUrut.get(3));
                    dailyTranscation.put("NasBung B", (-1)*mQuantityUrut.get(4));
                    dailyTranscation.put("Nasi Ayam", (-1)*mQuantityUrut.get(5));
                    dailyTranscation.put("Nasi Pindang", (-1)*mQuantityUrut.get(6));
                    dailyTranscation.put("Nasi Telur", (-1)*mQuantityUrut.get(7));
                    dailyTranscation.put("Pisang G", (-1)*mQuantityUrut.get(8));
                    dailyTranscation.put("Popmie", (-1)*mQuantityUrut.get(9));
                    dailyTranscation.put("Sereal", (-1)*mQuantityUrut.get(10));
                    dailyTranscation.put("Tahu G", (-1)*mQuantityUrut.get(11));
                    dailyTranscation.put("Siomay", (-1)*mQuantityUrut.get(12));
                    dailyTranscation.put("Sosis Naget", (-1)*mQuantityUrut.get(13)); //14 Makanan

                    dailyTranscation.put("Aqua 600ml", (-1)*mQuantityUrut.get(14));
                    dailyTranscation.put("Coca-Cola", (-1)*mQuantityUrut.get(15));
                    dailyTranscation.put("Es Kopi Durian", (-1)*mQuantityUrut.get(16));
                    dailyTranscation.put("Es Teh", (-1)*mQuantityUrut.get(17));
                    dailyTranscation.put("Fanta", (-1)*mQuantityUrut.get(18));
                    dailyTranscation.put("Floridina", (-1)*mQuantityUrut.get(19));
                    dailyTranscation.put("Frestea", (-1)*mQuantityUrut.get(20));
                    dailyTranscation.put("Isoplus", (-1)*mQuantityUrut.get(21));
                    dailyTranscation.put("Kopi Hitam", (-1)*mQuantityUrut.get(22));
                    dailyTranscation.put("Milo", (-1)*mQuantityUrut.get(23));
                    dailyTranscation.put("Sprite", (-1)*mQuantityUrut.get(24));
                    dailyTranscation.put("Teh Pucuk Harum", (-1)*mQuantityUrut.get(25));
                    dailyTranscation.put("Teh Hangat", mQuantityUrut.get(26));
                    fs.collection("Stock").document("Stocks").set(dailyTranscation);

                } else {
                    fs.collection("Stock").document("Stocks").update(
                            "Bakso", FieldValue.increment((-1)*mQuantityUrut.get(0)),
                            "Kentang G", FieldValue.increment((-1)*mQuantityUrut.get(1)),
                            "Mie Ayam", FieldValue.increment((-1)*mQuantityUrut.get(2)),
                            "NasBung A", FieldValue.increment((-1)*mQuantityUrut.get(3)),
                            "NasBung B", FieldValue.increment((-1)*mQuantityUrut.get(4)),
                            "Nasi Ayam", FieldValue.increment((-1)*mQuantityUrut.get(5)),
                            "Nasi Pindang", FieldValue.increment((-1)*mQuantityUrut.get(6)),
                            "Nasi Telur", FieldValue.increment((-1)*mQuantityUrut.get(7)),
                            "Pisang G", FieldValue.increment((-1)*mQuantityUrut.get(8)),
                            "Popmie", FieldValue.increment((-1)*mQuantityUrut.get(9)),
                            "Sereal", FieldValue.increment((-1)*mQuantityUrut.get(10)),
                            "Tahu G", FieldValue.increment((-1)*mQuantityUrut.get(11)),
                            "Siomay", FieldValue.increment((-1)*mQuantityUrut.get(12)),
                            "Sosis Naget", FieldValue.increment((-1)*mQuantityUrut.get(13)), //14 Makanan

                            "Aqua 600ml", FieldValue.increment((-1)*mQuantityUrut.get(14)),
                            "Coca-Cola", FieldValue.increment((-1)*mQuantityUrut.get(15)),
                            "Es Kopi Durian", FieldValue.increment((-1)*mQuantityUrut.get(16)),
                            "Es Teh", FieldValue.increment((-1)*mQuantityUrut.get(17)),
                            "Fanta", FieldValue.increment((-1)*mQuantityUrut.get(18)),
                            "Floridina", FieldValue.increment((-1)*mQuantityUrut.get(19)),
                            "Frestea", FieldValue.increment((-1)*mQuantityUrut.get(20)),
                            "Isoplus", FieldValue.increment((-1)*mQuantityUrut.get(21)),
                            "Kopi Hitam", FieldValue.increment((-1)*mQuantityUrut.get(22)),
                            "Milo", FieldValue.increment((-1)*mQuantityUrut.get(23)),
                            "Sprite", FieldValue.increment((-1)*mQuantityUrut.get(24)),
                            "Teh Hangat", FieldValue.increment(mQuantityUrut.get(26)),
                            "Teh Pucuk Harum", FieldValue.increment((-1)*mQuantityUrut.get(25)));
                }
            }
        });

        fs.collection("DailyTransaction").document(getDate()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (!documentSnapshot.exists()) {
                    dailyTranscation.put("date", getDate());
                    dailyTranscation.put("month", getMonth());
                    dailyTranscation.put("year", getYear());
                    dailyTranscation.put("total", countTotal());
                    dailyTranscation.put("timestamp", FieldValue.serverTimestamp());
                    dailyTranscation.put("customerNumber", 1);
                    dailyTranscation.put("Bakso", mQuantityUrut.get(0));
                    dailyTranscation.put("Kentang G", mQuantityUrut.get(1));
                    dailyTranscation.put("Mie Ayam", mQuantityUrut.get(2));
                    dailyTranscation.put("NasBung A", mQuantityUrut.get(3));
                    dailyTranscation.put("NasBung B", mQuantityUrut.get(4));
                    dailyTranscation.put("Nasi Ayam", mQuantityUrut.get(5));
                    dailyTranscation.put("Nasi Pindang", mQuantityUrut.get(6));
                    dailyTranscation.put("Nasi Telur", mQuantityUrut.get(7));
                    dailyTranscation.put("Pisang G", mQuantityUrut.get(8));
                    dailyTranscation.put("Popmie", mQuantityUrut.get(9));
                    dailyTranscation.put("Sereal", mQuantityUrut.get(10));
                    dailyTranscation.put("Tahu G", mQuantityUrut.get(11));
                    dailyTranscation.put("Siomay", mQuantityUrut.get(12));
                    dailyTranscation.put("Sosis Naget", mQuantityUrut.get(13)); //14 Makanan

                    dailyTranscation.put("Aqua 600ml", mQuantityUrut.get(14));
                    dailyTranscation.put("Coca-Cola", mQuantityUrut.get(15));
                    dailyTranscation.put("Es Kopi Durian", mQuantityUrut.get(16));
                    dailyTranscation.put("Es Teh", mQuantityUrut.get(17));
                    dailyTranscation.put("Fanta", mQuantityUrut.get(18));
                    dailyTranscation.put("Floridina", mQuantityUrut.get(19));
                    dailyTranscation.put("Frestea", mQuantityUrut.get(20));
                    dailyTranscation.put("Isoplus", mQuantityUrut.get(21));
                    dailyTranscation.put("Kopi Hitam", mQuantityUrut.get(22));
                    dailyTranscation.put("Milo", mQuantityUrut.get(23));
                    dailyTranscation.put("Sprite", mQuantityUrut.get(24));
                    dailyTranscation.put("Teh Pucuk Harum", mQuantityUrut.get(25));
                    yearlyTransaction.put("Teh Hangat", mQuantityUrut.get(26));
                    fs.collection("DailyTransaction").document(getDate()).set(dailyTranscation).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            ClearOrderList();
                            progressDialog.dismiss();

                        }
                    });

                } else {
                    fs.collection("DailyTransaction").document(getDate()).update(
                            "total", FieldValue.increment(countTotal()),
                            "timestamp", FieldValue.serverTimestamp(),
                            "customerNumber", FieldValue.increment(1),
                            "Bakso", FieldValue.increment(mQuantityUrut.get(0)),
                            "Kentang G", FieldValue.increment(mQuantityUrut.get(1)),
                            "Mie Ayam", FieldValue.increment(mQuantityUrut.get(2)),
                            "NasBung A", FieldValue.increment(mQuantityUrut.get(3)),
                            "NasBung B", FieldValue.increment(mQuantityUrut.get(4)),
                            "Nasi Ayam", FieldValue.increment(mQuantityUrut.get(5)),
                            "Nasi Pindang", FieldValue.increment(mQuantityUrut.get(6)),
                            "Nasi Telur", FieldValue.increment(mQuantityUrut.get(7)),
                            "Pisang G", FieldValue.increment(mQuantityUrut.get(8)),
                            "Popmie", FieldValue.increment(mQuantityUrut.get(9)),
                            "Sereal", FieldValue.increment(mQuantityUrut.get(10)),
                            "Tahu G", FieldValue.increment(mQuantityUrut.get(11)),
                            "Siomay", FieldValue.increment(mQuantityUrut.get(12)),
                            "Sosis Naget", FieldValue.increment(mQuantityUrut.get(13)), //14 Makanan

                            "Aqua 600ml", FieldValue.increment(mQuantityUrut.get(14)),
                            "Coca-Cola", FieldValue.increment(mQuantityUrut.get(15)),
                            "Es Kopi Durian", FieldValue.increment(mQuantityUrut.get(16)),
                            "Es Teh", FieldValue.increment(mQuantityUrut.get(17)),
                            "Fanta", FieldValue.increment(mQuantityUrut.get(18)),
                            "Floridina", FieldValue.increment(mQuantityUrut.get(19)),
                            "Frestea", FieldValue.increment(mQuantityUrut.get(20)),
                            "Isoplus", FieldValue.increment(mQuantityUrut.get(21)),
                            "Kopi Hitam", FieldValue.increment(mQuantityUrut.get(22)),
                            "Milo", FieldValue.increment(mQuantityUrut.get(23)),
                            "Sprite", FieldValue.increment(mQuantityUrut.get(24)),
                            "Teh Hangat", FieldValue.increment(mQuantityUrut.get(26)),
                            "Teh Pucuk Harum", FieldValue.increment(mQuantityUrut.get(25))
                    ).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.i("DALAM Urutan order", "" + mQuantityUrut);
                            ClearOrderList();
                            progressDialog.dismiss();

                        }
                    });
                }
            }
        });




        while (j <mTitle.size()) {
            //Transaction Detail
            namaMakananPesanan = mTitle.get(j);
            jumlahMakananPesanan = mQuantity.get(j);
            subTotalMakananPesanann = msubTotal.get(j);
            if (subTotalMakananPesanann == 0) {
                switch (mTitle.get(j)) {
                    case "Bakso":
                    case "Siomay":
                    case "Mie Ayam":
                    case "Nasi Ayam":
                    case "Nasi Pindang":
                    case "Nasi Telur":
                    case "Sosis Naget":
                    case "Popmie":
                    case "Es Kopi Durian":
                    case "Frestea":
                        subTotalMakananPesanann = jumlahMakananPesanan * 7000;
                        break;
                    case "Kentang G":
                    case "NasBung B":
                    case "Pisang G":
                    case "Sereal":
                    case "Tahu G":
                    case "Milo":
                    case "Coca-Cola":
                    case "Fanta":
                    case "Kopi Hitam":
                    case "Sprite":
                        subTotalMakananPesanann = jumlahMakananPesanan * 5000;
                        break;
                    case "NasBung A":
                    case "Aqua 600ml":
                    case "Es Teh":
                    case "Floridina":
                    case "Teh Pucuk Harum":
                    case "Isoplus":
                        subTotalMakananPesanann = jumlahMakananPesanan * 3000;
                        break;
                }

            }
            pesanan.put("noCustomer", customerNumber_update);
            pesanan.put("itemID", namaMakananPesanan);
            pesanan.put("day_itemID", getDate() +"_"+namaMakananPesanan);
            pesanan.put("month_itemID", getMonth()+"_"+namaMakananPesanan);
            pesanan.put("year_itemID", getYear() +"_"+namaMakananPesanan);
            pesanan.put("quantity", jumlahMakananPesanan);
            pesanan.put("lineTotal", subTotalMakananPesanann);
            pesanan.put("timeStamp", getTimeStamp());
            pesanan.put("Status", "Serving");


//            fs.collection("TransactionDetail").add(pesanan);


            stockChange.put("timeStamp", getTimeStamp());
            stockChange.put("itemID", namaMakananPesanan);
            stockChange.put("day_itemID", getDate() +"_"+namaMakananPesanan);
            stockChange.put("month_itemID", getMonth()+"_"+namaMakananPesanan);
            stockChange.put("year_itemID", getYear() +"_"+namaMakananPesanan);
            stockChange.put("quantity", (-1)*jumlahMakananPesanan);
//            fs.collection("Stock").add(stockChange);

//            stockCount.setStockChange((-1)*jumlahMakananPesanan);
//            stockCount.setNamaItem(namaMakananPesanan);
//            stockCount.setDay_itemID(getDate() + "_" + namaMakananPesanan);
//            stockCount.setMonth_itemID(getMonth() + "_" + namaMakananPesanan);
//            stockCount.setYear_itemID(getYear() + "_" + namaMakananPesanan);
//            reffStock.push().setValue(stockCount);

            j++;
        }
        nextCustomerNumber = customerNumber_update +1;
        nomorPelangganBerikutnya.setText("Nomor Berikutnya: " +nextCustomerNumber);
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
        status.put("customerNumber", customerNumber_update);
        status.put("itemID", namaSubMakanan);
        status.put("quantity", jumlahSub);
        status.put("status", "Serving");
        status.put("bungkus", bungkus);
        status.put("total", countTotal());
        status.put("waktuPengambilan", waktuPengambilan);
        fs.collection("Status").document(""+customerNumber_update).set(status);

        fs.collection("OrderHistory").document(""+transactionID_update).set(status);

//        reffStatus.child(""+customerNumber_update).child("status").setValue("Serving");
//        reffStatus.child(""+customerNumber_update).child("customerNumber").setValue(customerNumber_update);
//        reffStatus.child(""+customerNumber_update).child("quantity").setValue(jumlahSub);
//        reffStatus.child(""+customerNumber_update).child("itemID").setValue(namaSubMakanan);
//        reffStatus.child(""+customerNumber_update).child("bungkus").setValue(bungkus);

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
        Log.i("LUAR Urutan order", "" + mQuantityUrut);

        bungkusCheckbox.setChecked(false);
        pesanCheckbox.setChecked(false);
        bungkusCheckbox.setVisibility(View.VISIBLE);

    }

    public String getTimeStamp() {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        return "" + timestamp;
    }

    public void ClearOrderList(){
        mQuantityUrut.clear();
        int i = 0;
        while (i < mTitle.size()) {
            mTitle.remove(i);
            mQuantity.remove(i);
            msubTotal.remove(i);
            mItemPrice.remove(i);
            totalCount.setText("0");

            }
        adapter.notifyDataSetChanged();
        i = 0;
        while (i < 26) {
            mQuantityUrut.add(0);
            i++;
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
                    mQuantityUrut.set(25 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(3000);
                    msubTotal.add(3000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Es Teh":
                    mQuantityUrut.set(17 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(3000);
                    msubTotal.add(3000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Aqua 600ml":
                    mQuantityUrut.set(14 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(3000);
                    msubTotal.add(3000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Sprite":
                    mQuantityUrut.set(24 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Fanta":
                    mQuantityUrut.set(18 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Coca-Cola":
                    mQuantityUrut.set(15 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Kopi Hitam":
                    mQuantityUrut.set(22 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Floridina":
                    mQuantityUrut.set(19 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(3000);
                    msubTotal.add(3000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Isoplus":
                    mQuantityUrut.set(21 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(3000);
                    msubTotal.add(3000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Milo":
                    mQuantityUrut.set(23 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Frestea":
                    mQuantityUrut.set(11 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(3000);
                    msubTotal.add(3000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Es Kopi Durian":
                    mQuantityUrut.set(16 , 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(7000);
                    msubTotal.add(7000);
                    adapter.notifyDataSetChanged();
                    countTotal();
                    break;
                case "Teh Hangat":
                    mQuantityUrut.set(26, 1);
                    mTitle.add(minuman);
                    mQuantity.add(1);
                    mItemPrice.add(5000);
                    msubTotal.add(5000);
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

    public String getDate() {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        String date_full = (String) String.valueOf(timestamp);
        String date = date_full.substring(0, 10);
        return date;
    }

    public String getMonth() {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        String date_full = (String) String.valueOf(timestamp);
        String month = date_full.substring(0, 7);
        return month;
    }

    public String getYear() {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        String date_full = (String) String.valueOf(timestamp);
        String year = date_full.substring(0, 4);
        return year;
    }



}