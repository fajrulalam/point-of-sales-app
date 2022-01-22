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

        ImageView bakso = view.findViewById(R.id.gambarBakso);;
        ImageView kentangGoreng = view.findViewById(R.id.gambarKentangGorengnew);
        ImageView nasiLauk = view.findViewById(R.id.gambarAyam);
        ImageView nasbungA = view.findViewById(R.id.gambarNasBungA);
        ImageView nasbungB = view.findViewById(R.id.gambarNasBungB);
        ImageView popmie = view.findViewById(R.id.gambarPopmie);
        ImageView siomay = view.findViewById(R.id.gambarSiomay);

        Glide.with(this).load(R.drawable.bakso_compressed).into(bakso);
        Glide.with(this).load(R.drawable.kentang_compressed).into(kentangGoreng);
        Glide.with(this).load(R.drawable.nasi_ayam_compressed).into(nasiLauk);
        Glide.with(this).load(R.drawable.nasbung_a_compressed).into(nasbungA);
        Glide.with(this).load(R.drawable.nasbung_b_compressed).into(nasbungB);
        Glide.with(this).load(R.drawable.popmie_compressed).into(popmie);
        Glide.with(this).load(R.drawable.siomay_compressed).into(siomay);




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