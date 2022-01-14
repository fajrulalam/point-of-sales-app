package com.example.point_of_sales_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


public class DialogKonfirmasi extends AppCompatDialogFragment {


    private TextView kembalianTextView;
    private Button okButton;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_konfirmasi, null);

        int kembalian = getArguments().getInt("kembalian");
        Log.i("Kembalianmu... ", "" + kembalian);


        builder.setView(view);


        kembalianTextView = view.findViewById(R.id.kembalianTextView);
        kembalianTextView.setText("(Kembalian: Rp" + kembalian +")");
        okButton = view.findViewById(R.id.okButton);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogKonfirmasi.this.dismiss();
            }
        });


        return builder.create();
    }


//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        try {
//            dialogBuyListener = (DialogBuyListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
//        }
//    }
//
//    public interface DialogBuyListener{
//        void countChange(int result);
//
//    }
}
