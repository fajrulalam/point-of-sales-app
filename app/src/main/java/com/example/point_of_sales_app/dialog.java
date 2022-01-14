package com.example.point_of_sales_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.fragment.app.DialogFragment;

public class dialog extends AppCompatDialogFragment {

    private EditText editText;
    private TextView totalTextView;
    private DialogBuyListener dialogBuyListener;
    private Button okButton;
    private Button cancelButton;
    Intent intent;
    Integer kembalian;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.buy_dialog, null);

        String total = getArguments().getString("totalValue");
        Log.i("Your total is... ", total);


        builder.setView(view);
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String uangYangDikembalikan = editText.getText().toString();
//                        try {
//                            int kembalian = (int) (Integer.parseInt(uangYangDikembalikan) - Integer.parseInt(total));
//                            Log.i("Kembalianmu...","Rp" + kembalian);
//                            dialogBuyListener.countChange(kembalian);
//                        } catch (ClassCastException e) {
//                            Log.i("Test","Test");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
        editText = view.findViewById(R.id.uangYangDiterima);
        totalTextView = view.findViewById(R.id.totalTextView);
        totalTextView.setText("(Total: Rp" + total +")");
        okButton = view.findViewById(R.id.okButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uangYangDikembalikan = editText.getText().toString();
                try {
                    int kembalian = (int) (Integer.parseInt(uangYangDikembalikan) - Integer.parseInt(total));
//                    Log.i("Kembalianmu...","Rp" + kembalian);
                    if (kembalian > 0 ) {
                        dialogBuyListener.countChange(kembalian);
                        dialog.this.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Kembaliannya kurang", Toast.LENGTH_SHORT).show();
                    }
                } catch (ClassCastException e) {
                    Toast.makeText(getContext(), "Salah input. Harus angka", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Salah input. Harus angka", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.this.dismiss();
            }
        });
        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogBuyListener = (DialogBuyListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface DialogBuyListener{
        void countChange(int result);

    }
}
