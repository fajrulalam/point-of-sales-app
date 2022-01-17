package com.example.point_of_sales_app;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOTransaction {
    private DatabaseReference databaseReference;
    public DAOTransaction() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://point-of-sales-app-25e2b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = db.getReference(transactionDetail.class.getSimpleName());
    }

    public Task<Void> add(transactionDetail transactionDetail) {
        return  databaseReference.push().setValue(transactionDetail);
    }
}
