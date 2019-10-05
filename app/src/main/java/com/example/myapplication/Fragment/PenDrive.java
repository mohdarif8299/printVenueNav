package com.example.myapplication.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Items;
import com.example.myapplication.ItemsAdapter;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PenDrive extends Fragment {
    RecyclerView recyclerView,recyclerView1;
    List<Items> list;
    private static String URL_PENDRIVES="https://colorpress.000webhostapp.com/vistaprint/Authentication/pendrives.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_pen_drive, container, false);
        recyclerView =view.findViewById(R.id.recyclerview1);
        recyclerView1 =view.findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        loadItems();
        return view;
    }
    public void loadItems(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PENDRIVES,
                response -> {
                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject product = array.getJSONObject(i);
                            list.add(new Items(
                                    product.getString("item_name"),
                                    product.getString("item_image"),
                                    product.getString("item_price")
                            ));
                        }
                        ItemsAdapter adapter = new ItemsAdapter(getActivity(), list);
                        recyclerView.setAdapter(adapter);
                        recyclerView1.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),"Error"+e.toString(),Toast.LENGTH_SHORT).show();
                    }
                },error->
            Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show()
        );
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}
