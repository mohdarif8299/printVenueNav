package com.example.myapplication.Fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
public class PensFragment extends Fragment {
    LinearLayout pen;


    public PensFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pens, container, false);
        pen=view.findViewById(R.id.pens01);

        pen.setOnClickListener(v->{
            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
            productCustomize productCustomize=new productCustomize();
            fragmentTransaction.replace(R.id.container,productCustomize);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();

        });
        return view;
    }

}
