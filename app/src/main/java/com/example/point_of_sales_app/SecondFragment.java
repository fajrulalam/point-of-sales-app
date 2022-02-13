package com.example.point_of_sales_app;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment implements View.OnClickListener {
    private ListView minumanListView;
    private ArrayList<String> minumanList;
    private MinumanFragment minumanFragment;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        minumanListView = (ListView) rootView.findViewById(R.id.minumanListView);
        minumanList = new ArrayList<>();
        minumanList = new ArrayList<>();
        minumanList.add("Aqua 600ml");
        minumanList.add("Coca-Cola");
        minumanList.add("Es Kopi Durian");
        minumanList.add("Es Teh");
        minumanList.add("Fanta");
        minumanList.add("Floridina");
        minumanList.add("Frestea");
        minumanList.add("Isoplus");
        minumanList.add("Kopi Hitam");
        minumanList.add("Milo");
        minumanList.add("Sprite");
        minumanList.add("Teh Pucuk Harum");
        populateList();

        minumanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemClicked = minumanList.get(i);

                minumanFragment.kirimDataMinuman(itemClicked);

            }
        });


        return rootView;
    }

    @Override
    public void onClick(View view) {

    }

    private void populateList() {
        ArrayAdapter minumanArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, minumanList);
        minumanListView.setAdapter(minumanArrayAdapter);


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        minumanFragment = (MinumanFragment) context;
    }

    public interface MinumanFragment {
        void kirimDataMinuman(String minuman);
    }
}