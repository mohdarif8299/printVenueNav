package com.example.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Fragment.MyDialogFragment;
import com.example.myapplication.Fragment.PreviewFragment;
import com.google.android.material.snackbar.Snackbar;

public class customizeActivity extends AppCompatActivity implements MyDialogFragment.OninputListener {

    Button bc;
    TextView display,Preview,plus,minus,price,addCart;
    int i=1;;
    int p;
    String in;
    Typeface typeface;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("parker jotter");

        bc=findViewById(R.id.dialogfragment);
        display=(TextView) findViewById(R.id.displayName);
        plus = (TextView) findViewById(R.id.plus);
        minus = (TextView) findViewById(R.id.minus);
        Preview=findViewById(R.id.previewT);
        price=(TextView)findViewById(R.id.price);
        addCart =(TextView)findViewById(R.id.addToCart);




        price.setText("550");
        p=Integer.parseInt(price.getText()+"");



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>=1){
                    minus.setText(" - ");
                    minus.setTextSize(20);
                    plus.setText(i+" + ");
                    plus.setPadding(7,2,7,2);
                    plus.setTextSize(20);
                   price.setText(550*i+"");
                    i++;
                }
            }
        });



        minus.setOnClickListener(v -> {
            if (i > 0) {

                minus.setText(" - ");
                minus.setTextSize(20);
                plus.setText((i)+" + ");
                plus.setPadding(7,2,7,2);
                plus.setTextSize(20);
                /*int pr=Integer.parseInt(price.getText().toString());
                price.setText(pr-p+"");*/
                i--;
            }
            else{
                minus.setText("");
                plus.setText("Add + ");
                plus.setPadding(7,2,7,2);
                plus.setTextSize(20);
                price.setText(550+"");
            }
        });

        Preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction f1=getSupportFragmentManager().beginTransaction();
                PreviewFragment pf=new PreviewFragment(typeface);
                Bundle b=new Bundle();
                b.putString("TextView",in);
               /* b.putString("TypeFace",typeface.toString());*/
                pf.setArguments(b);
                pf.show(f1,"peviewFragment");

            }
        });

        bc.setOnClickListener(v -> {
            MyDialogFragment mb=new MyDialogFragment();
            mb.show(getSupportFragmentManager(),"MyDialogFragment");


        });

        addCart.setOnClickListener((v)->{
            Snackbar snackbar= Snackbar.make(findViewById(R.id.snackBar),"Item Added to Cart", Snackbar.LENGTH_LONG).setAction("View Cart", v1 -> {

            });
            snackbar.show();


        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void sendInput(String input, Typeface tf) {
        display.setTypeface(tf);
        display.setText(input);
        in=input;
        typeface=tf;


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();



    }


}
