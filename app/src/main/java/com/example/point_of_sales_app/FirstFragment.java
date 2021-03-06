package com.example.point_of_sales_app;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment implements View.OnClickListener{
    private CardView makanan00;
    private CardView makanan01;
    private CardView makanan02;
    private CardView makanan10;
    private CardView makanan11;
    private CardView makanan12;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        makanan00 = (CardView) view.findViewById(R.id.makanan00);
        makanan01 = (CardView) view.findViewById(R.id.makanan01);
        makanan02 = (CardView) view.findViewById(R.id.makanan02);
        makanan10 = (CardView) view.findViewById(R.id.makanan10);
        makanan11 = (CardView) view.findViewById(R.id.makanan11);
        makanan12 = (CardView) view.findViewById(R.id.makanan12);

        ImageView gambar1 = view.findViewById(R.id.gambar1);
        ImageView gambar2 = view.findViewById(R.id.gambar2);
        ImageView gambar3 = view.findViewById(R.id.gambar3);
        ImageView gambar4 = view.findViewById(R.id.gambar4);
        ImageView gambar5 = view.findViewById(R.id.gambar5);
        ImageView gambar6 = view.findViewById(R.id.gambar6);
        ImageView gambar7 = view.findViewById(R.id.gambar7);
        ImageView gambar8 = view.findViewById(R.id.gambar8);
        ImageView gambar9 = view.findViewById(R.id.gambar9);
        ImageView gambar10 = view.findViewById(R.id.gambar10);
        ImageView gambar11 = view.findViewById(R.id.gambar11);
        ImageView gambar12 = view.findViewById(R.id.gambar12);
        ImageView gambar13 = view.findViewById(R.id.gambar13);
        ImageView gambar14 = view.findViewById(R.id.gambar14);

        Glide.with(this).load(R.drawable.bakso_compressed).into(gambar1);
        Glide.with(this).load(R.drawable.kentang_compressed).into(gambar2);
        Glide.with(this).load(R.drawable.mie_ayam).into(gambar3);
        Glide.with(this).load(R.drawable.nasbung_a_compressed).into(gambar4);
        Glide.with(this).load(R.drawable.nasbung_b_compressed).into(gambar5);
        Glide.with(this).load(R.drawable.nasi_ayam_compressed).into(gambar6);
        Glide.with(this).load(R.drawable.nasi_pindang).into(gambar7);
        Glide.with(this).load(R.drawable.pisang_goreng).into(gambar8);
        Glide.with(this).load(R.drawable.popmie_compressed).into(gambar9);
        Glide.with(this).load(R.drawable.sereal_real).into(gambar10);
        Glide.with(this).load(R.drawable.siomay_compressed).into(gambar11);
        Glide.with(this).load(R.drawable.sausage).into(gambar12);
        Glide.with(this).load(R.drawable.tofu).into(gambar13);
        Glide.with(this).load(R.drawable.egg_rice).into(gambar14);



        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.makanan00:
                Toast.makeText(getActivity(), "Bakso"  , Toast.LENGTH_SHORT).show();
        }
    }
}