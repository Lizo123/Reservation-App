package com.example.thespa.view.appointments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thespa.R;

public class AppointmentFragment extends Fragment {

    // Store instance variables
    private String buttonTitle;


    // newInstance constructor for creating fragment with arguments
    public static AppointmentFragment newInstance(String buttonTitle) {
        AppointmentFragment fragmentFirst = new AppointmentFragment();
        Bundle args = new Bundle();

        args.putString("buttonTitle", buttonTitle);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonTitle = getArguments().getString("buttonTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);
        Button tvLabel =  view.findViewById(R.id.button);
        tvLabel.setText(buttonTitle);
        return view;
    }
}
