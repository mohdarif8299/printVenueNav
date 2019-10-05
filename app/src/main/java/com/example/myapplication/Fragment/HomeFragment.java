package com.example.myapplication.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    SliderLayout sliderLayout;
    LinearLayout li,cardslayout,pendrive;
    RecyclerView recyclerView,recyclerView1;
    List<Items> list;
    private static String URL_IMAGE_SLIDER="https://colorpress.000webhostapp.com/vistaprint/Authentication/image_slider.php";
    private static String URL_TRENDING="https://colorpress.000webhostapp.com/vistaprint/Authentication/trending_products.php";
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView =view.findViewById(R.id.recyclerview1);
        recyclerView1 =view.findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        loadItems();
        list.clear();
        trending_products();
//        li=v.findViewById(R.id.pens);
//        cardslayout=v.findViewById(R.id.cards);
//        pendrive=v.findViewById(R.id.pendrive);
//
//        pendrive.setOnClickListener(v1->{
//            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.container,new PenDrive());
//            fragmentTransaction.commit();
//        });
//
//
//        cardslayout.setOnClickListener(v1 -> {
//            FragmentTransaction f=getFragmentManager().beginTransaction();
//            CardFragment cd=new CardFragment();
//            f.replace(R.id.container,cd);
//            f.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            f.commit();
//
//        });
//        li.setOnClickListener(v1 -> {
//            FragmentTransaction f=getFragmentManager().beginTransaction();
//            PensFragment pf=new PensFragment();
//            f.replace(R.id.container,pf);
//            f.addToBackStack(null);
//            f.commit();
//        });
        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :
        sliderLayout.setContentDescription("");
      //  setSliderViews();
        return view;
    }

    public void loadItems(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_IMAGE_SLIDER,
                response -> {
                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject product = array.getJSONObject(i);
                            list.add(new Items("", product.getString("image_url"), ""));
                            DefaultSliderView sliderView = new DefaultSliderView(getContext());
                            sliderView.setImageUrl(list.get(i).getUrl());
                            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                            sliderLayout.addSliderView(sliderView);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),"Error"+e.toString(),Toast.LENGTH_SHORT).show();
                    }
                },error->
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show()
        );
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
    public void trending_products(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TRENDING,
                response -> {
                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject product = array.getJSONObject(i);
                            list.add(new Items(product.getString("item_name"), product.getString("item_image"), product.getString("item_price")));
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
