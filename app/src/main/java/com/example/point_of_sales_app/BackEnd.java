package com.example.point_of_sales_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;

public class BackEnd extends AppCompatActivity {

    private ImageButton mainMenuButton;
    private DatabaseReference reff;
    private Query reffToday;
    private TextView totalHariIniTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_end);
        getSupportActionBar().hide();


        reff = FirebaseDatabase.getInstance("https://point-of-sales-app-25e2b-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("TransacationDetail");
        reffToday = reff.orderByChild("timeStamp").startAt(getDate()).endAt(getDate()+"\uf8ff");

        Log.i("Tanggal", getDate());
        totalHariIniTextView = findViewById(R.id.TotalHariIni);
        mainMenuButton = findViewById(R.id.mainMenuButton);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainMenu();
            }
        });

        reffToday.addListenerForSingleValueEvent(valueEventListener);
//        reffToday.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                int sum = 0;
//                Log.i("test", "..");
//                if (snapshot.exists()) {
//                    for (DataSnapshot ds : snapshot.getChildren()) {
//                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
//                        Object subtotal = map.get("lineTotal");
//                        int pValue = Integer.parseInt(String.valueOf(subtotal));
//                        sum += pValue;
//                        totalHariIniTextView.setText(String.valueOf(sum));
//                    }
//                } else {
//                    Log.i("Query", "tidak tepat");
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }

    public String getDate() {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        String date_full = (String) String.valueOf(timestamp);
        String date = date_full.substring(0, 10);
        return date;
    }

    public void openMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            int sum = 0;
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    transactionDetail artist = snapshot.getValue(transactionDetail.class);
                    Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                    Object subtotal = map.get("lineTotal");
                    int pValue = Integer.parseInt(String.valueOf(subtotal));
                    sum += pValue;
                    totalHariIniTextView.setText("Rp." + sum);
                }
            } else {

            }


        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }


    };
}