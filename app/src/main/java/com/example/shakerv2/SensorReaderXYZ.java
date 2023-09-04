package com.example.shakerv2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorReaderXYZ#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorReaderXYZ extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView xView;
    TextView yView;
    TextView zView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SensorReaderXYZ() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sensorReaderXYZ.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorReaderXYZ newInstance(String param1, String param2) {
        SensorReaderXYZ fragment = new SensorReaderXYZ();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void setXYZ(int x, int y, int z){
        xView.setText(String.valueOf(x));
        yView.setText(String.valueOf(y));
        zView.setText(String.valueOf(z));

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
        return inflater.inflate(R.layout.fragment_sensor_reader_x_y_z, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        xView = view.findViewById(R.id.xView);
        yView = view.findViewById(R.id.yView);
        zView = view.findViewById(R.id.zView);
        super.onViewCreated(view, savedInstanceState);
    }
}